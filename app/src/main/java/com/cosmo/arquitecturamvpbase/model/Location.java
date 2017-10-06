package com.cosmo.arquitecturamvpbase.model;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public class Location {

    private String type;
    private double[] coordinates = new double[2];

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double[] getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(double[] coordinates) {
        this.coordinates = coordinates;
    }
}
