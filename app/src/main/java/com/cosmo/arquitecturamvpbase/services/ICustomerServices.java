package com.cosmo.arquitecturamvpbase.services;

import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.Product;

import java.util.ArrayList;

import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public interface ICustomerServices {

    @GET("/customers")
    ArrayList<Customer> getCustomerList();

    @POST("/customers")
    Customer createCustomer(@Body Customer customer);
}
