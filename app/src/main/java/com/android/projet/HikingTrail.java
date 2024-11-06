package com.android.projet;

public class HikingTrail {
    private String name;
    private String location;
    private int imageResourceId;

    public HikingTrail(String name, String location, int imageResourceId) {
        this.name = name;
        this.location = location;
        this.imageResourceId = imageResourceId;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getImageResourceId() {
        return imageResourceId;
    }
}

