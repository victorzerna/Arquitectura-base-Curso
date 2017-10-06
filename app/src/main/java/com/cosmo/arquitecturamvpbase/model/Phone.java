package com.cosmo.arquitecturamvpbase.model;

import java.util.ArrayList;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public class Phone {

    private String description;
    private String number;
    Location location;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
