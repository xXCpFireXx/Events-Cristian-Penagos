package com.lovelace.eventsUserStories.domain;

public class Venue {
    private Long id;
    private String nameVenue;
    private String address;
    private int capacity;

    public Venue() {
    }

    public Venue(Long id, String nameVenue, String address, int capacity) {
        this.id = id;
        this.nameVenue = nameVenue;
        this.address = address;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameVenue() {
        return nameVenue;
    }

    public void setNameVenue(String nameVenue) {
        this.nameVenue = nameVenue;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
