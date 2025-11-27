ALTER TABLE event
ADD CONSTRAINT fk_event_venue
FOREIGN KEY (id_venue) REFERENCES venue(id);