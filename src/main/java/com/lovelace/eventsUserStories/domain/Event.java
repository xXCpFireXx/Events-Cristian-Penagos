package com.lovelace.eventsUserStories.domain;

public class Event {
    private Long id;
    private String nameEvent;

    public Event() {
    }

    public Event(String nameEvent) {
        this.nameEvent = nameEvent;
    }

    public Event(Long id, String nameEvent) {
        this.id = id;
        this.nameEvent = nameEvent;
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
}
