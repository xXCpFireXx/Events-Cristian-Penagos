package com.lovelace.eventsUserStories.domain;

public class Event {
    private Long id;
    private String nameEvent;
    private Long idVenueEvent;

    public Event() {
    }

    public Event(Long id, String nameEvent, Long idVenueEvent) {
        this.id = id;
        this.nameEvent = nameEvent;
        this.idVenueEvent = idVenueEvent;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEvent() {
        return nameEvent;
    }

    public void setNameEvent(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Long getIdVenueEvent() {
        return idVenueEvent;
    }

    public void setIdVenueEvent(Long idVenueEvent) {
        this.idVenueEvent = idVenueEvent;
    }
}
