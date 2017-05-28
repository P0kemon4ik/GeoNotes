package com.example.vladislavezerski.geonotes;

public class Note {

    private String id;
    private String body;
    private String color;
    private Double lat;
    private Double lng;
    private String imgData;

    public Note(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public Note(String id, String body, String color, Double lat, Double lng, String imgData) {
        this.id = id;
        this.body = body;
        this.color = color;
        this.lat = lat;
        this.lng = lng;
        this.imgData = imgData;
    }
}
