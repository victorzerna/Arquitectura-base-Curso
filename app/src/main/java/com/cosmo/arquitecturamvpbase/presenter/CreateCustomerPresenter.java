package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.repository.ICustomerRepository;
import com.cosmo.arquitecturamvpbase.views.activities.customer.ICreateCustomerView;

import retrofit.RetrofitError;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public class CreateCustomerPresenter extends BasePresenter<ICreateCustomerView>{

    private ICustomerRepository customerRepository;

    public CreateCustomerPresenter(ICustomerRepository customerRepository){
        this.customerRepository = customerRepository;
    }

    public void createNewCustomer(Customer customer) {
        if (getValidateInternet().isConnected()){
            createThreadCreateCustomer(customer);
        }else{
            getView().showAlertInternet(R.string.error, R.string.validate_internet);
        }
    }

    /*public void createNewProduct(String name, String description, String price, String quantity) {
        Product product = new Product();
        product.setName(name);
        product.setDescription(description);
        product.setPrice(price);
        product.setQuantity(quantity);
        if (getValidateInternet().isConnected()){
            createThreadCreateProduct(product);
        }else{
            getView().showAlertInternet(R.string.error, R.string.validate_internet);
        }
    }
*/
    public void createThreadCreateCustomer(final Customer customer) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                createNewCustomerService(customer);
            }
        });
        thread.start();
    }

    private void createNewCustomerService(Customer customer){
        try{
            customerRepository.createCustomer(customer);
            getView().showResultCreateNewCustomer(true);
        }catch (RetrofitError retrofitError){
            getView().showResultCreateNewCustomer(false);
        }
    }
}
