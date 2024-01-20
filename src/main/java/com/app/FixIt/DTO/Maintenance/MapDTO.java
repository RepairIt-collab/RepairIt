package com.app.FixIt.DTO.Maintenance;

public class MapDTO {
    double latitude;
    double longitude;
    Long id;
    double distance;
    
    public double getDistance() {
        return distance;
    }
    public void setDistance(double distance) {
        this.distance = distance;
    }
    public double getLatitude() {
        return latitude;
    }
    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }
    public double getLongitude() {
        return longitude;
    }
    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    } 
}
