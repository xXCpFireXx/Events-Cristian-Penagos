# --- ETAPA 1: BUILD (Construcción) ---
# Usamos una imagen de Maven con Java 21 para compilar el proyecto
FROM maven:3.9.6-eclipse-temurin-21 AS build

# Establecemos el directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiamos solo el pom.xml primero para aprovechar el caché de Docker
# (Si no cambias las dependencias, Docker no volverá a descargar todo internet)
COPY pom.xml .
RUN mvn dependency:go-offline

# Ahora copiamos todo el código fuente
COPY src ./src

# Compilamos y empaquetamos (saltando los tests para ir más rápido, ya que ya los probamos)
RUN mvn clean package -DskipTests

# --- ETAPA 2: RUN (Ejecución) ---
# Usamos una imagen JRE (Java Runtime Environment) muy liviana, solo lo necesario para correr
FROM eclipse-temurin:21-jre-alpine

# Directorio de trabajo
WORKDIR /app

# Copiamos SOLAMENTE el archivo .jar generado en la etapa anterior (build)
# Nota: El nombre del jar puede variar, usamos el comodín *.jar para asegurar que lo encuentre
COPY --from=build /app/target/*.jar app.jar

# Exponemos el puerto 8080 (informativo)
EXPOSE 8080

# Comando para arrancar la aplicación
ENTRYPOINT ["java", "-jar", "app.jar"]