package com.lovelace.eventsUserStories.domain.model;

import java.time.LocalDateTime;

public class Event {
    private Long id;
    private String nameEvent;
    private Long idVenueEvent;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event() {
    }

    public Event(Long id, String nameEvent, Long idVenueEvent, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.nameEvent = nameEvent;
        this.idVenueEvent = idVenueEvent;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }
}
