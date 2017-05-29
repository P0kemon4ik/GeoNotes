package com.example.vladislavezerski.geonotes;

import android.graphics.Color;
import com.google.android.gms.maps.model.LatLng;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Note implements Serializable {

    private String id;
    private String body;
    private int color;
    private Double lat;
    private Double lng;
    private String imgData;

    public Note() {
    }

    public Note(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public int getColor() {
        return color;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setImgData(String imgData) {
        this.imgData = imgData;
    }

    public void setLatLng(LatLng latLng) {
        this.lat = latLng.latitude;
        this.lng = latLng.longitude;
    }

    public Note(String id, String body, String color, Double lat, Double lng, String imgData) {
        this.id = id;
        this.body = body;
        this.color = Color.parseColor(color.replace("0x", "#"));
        this.lat = lat;
        this.lng = lng;
        this.imgData = imgData;
    }


    public Map<String, Object> toMap() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("body", body);
        map.put("backColor", String.format("#%06X", (0xFFFFFF & color)));
        map.put("lat", lat);
        map.put("lng", lng);
//        map.put("imageData", imgData);
        return null;
    }
}
