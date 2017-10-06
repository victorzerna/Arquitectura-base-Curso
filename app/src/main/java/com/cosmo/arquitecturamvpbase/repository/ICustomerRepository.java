package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.model.Customer;

import java.util.ArrayList;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public interface ICustomerRepository {

    ArrayList<Customer> getCustomerList();

    Customer createCustomer(Customer customer);

}
