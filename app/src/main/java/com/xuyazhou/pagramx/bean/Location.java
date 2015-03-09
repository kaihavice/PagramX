package com.xuyazhou.pagramx.bean;

/**
 * Author: xuyazhou(xuyazhou18@gmail.com)
 * <p/>
 * Date: 2015-03-05
 */

public class Location {
	public String id;
	public double latitude;
	public double longitude;
	public String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
