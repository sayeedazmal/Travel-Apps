package com.example.mdazmal.travel.pojoclass;

public class EventInfo {
    private String name;
    private String location;
    private String destination;
    private String date;

    public EventInfo(String name, String location, String destination, String date) {
        this.name = name;
        this.location = location;
        this.destination = destination;
        this.date = date;
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
