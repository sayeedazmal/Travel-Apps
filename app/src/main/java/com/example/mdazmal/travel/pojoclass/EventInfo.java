package com.example.mdazmal.travel.pojoclass;

public class EventInfo {
    private String eventId;
    private String name;
    private String location;
    private String destination;
    private String date;
    private double budget;

    public EventInfo(String eventId,String name, String location, String destination, String date, double budget) {
        this.eventId = eventId;
        this.name = name;
        this.location = location;
        this.destination = destination;
        this.date = date;
        this.budget = budget;

    }

    public String getEventId() {
        return eventId;
    }

    public void setEventId(String eventId) {
        this.eventId = eventId;
    }

    public double getBudget() {
        return budget;
    }

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
