CREATE TABLE venue (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name_venue VARCHAR(255) NOT NULL,
    address VARCHAR(255) NOT NULL,
    capacity INT NOT NULL,
    CONSTRAINT uk_name_venue UNIQUE (name_venue)
);

CREATE TABLE event (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name_event VARCHAR(255) NOT NULL,
    id_venue BIGINT NOT NULL,
    CONSTRAINT uk_name_event UNIQUE (name_event)
);