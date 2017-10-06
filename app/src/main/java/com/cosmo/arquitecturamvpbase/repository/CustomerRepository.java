package com.cosmo.arquitecturamvpbase.repository;

import com.cosmo.arquitecturamvpbase.helper.ServicesFactory;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.services.ICustomerServices;
import com.cosmo.arquitecturamvpbase.services.IServices;

import java.util.ArrayList;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public class CustomerRepository implements ICustomerRepository {

    private ICustomerServices customerServices;

    public CustomerRepository() {
        ServicesFactory servicesFactory = new ServicesFactory();
        customerServices = (ICustomerServices) servicesFactory.getInstance(ICustomerServices.class);
    }


    @Override
    public ArrayList<Customer> getCustomerList() {
        ArrayList<Customer> customers = customerServices.getCustomerList();
        return customers;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        Customer customerCreate = customerServices.createCustomer(customer);
        return customerCreate;
    }
}
