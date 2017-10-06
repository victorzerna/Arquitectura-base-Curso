package com.cosmo.arquitecturamvpbase.model;

import java.util.ArrayList;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public class Customer {

    private String nameCustomer;

    private String surnameCustomer;

    private ArrayList<Phone> phoneList;

    public String getNameCustomer() {
        return nameCustomer;
    }

    public void setNameCustomer(String nameCustomer) {
        this.nameCustomer = nameCustomer;
    }

    public String getSurnameCustomer() {
        return surnameCustomer;
    }

    public void setSurnameCustomer(String surnameCustomer) {
        this.surnameCustomer = surnameCustomer;
    }

    public ArrayList<Phone> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(ArrayList<Phone> phoneList) {
        this.phoneList = phoneList;
    }
}
