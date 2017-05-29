package com.example.vladislavezerski.geonotes;

import android.graphics.Color;

public class Note {

    private String id;
    private String body;
    private int color;
    private Double lat;
    private Double lng;
    private String imgData;

    public Note(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public int getColor() {
        return color;
    }

    public Note(String id, String body, String color, Double lat, Double lng, String imgData) {
        this.id = id;
        this.body = body;
        this.color = Color.parseColor(color.replace("0x","#"));
        this.lat = lat;
        this.lng = lng;
        this.imgData = imgData;
    }
}
