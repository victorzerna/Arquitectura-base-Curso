package com.cosmo.arquitecturamvpbase.presenter;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.repository.CustomerRepository;
import com.cosmo.arquitecturamvpbase.repository.MapperError;
import com.cosmo.arquitecturamvpbase.repository.RepositoryError;
import com.cosmo.arquitecturamvpbase.views.activities.customer.ICustomerView;

import java.util.ArrayList;

import retrofit.RetrofitError;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public class CustomerPresenter extends BasePresenter<ICustomerView> {

    private CustomerRepository customerRepository;

    public CustomerPresenter() {
        customerRepository = new CustomerRepository();
    }

    public void getListCustomer() {
        if (getValidateInternet().isConnected()) {
            createThreadCustomer();
        } else {
            getView().showAlertDialogInternet(R.string.error, R.string.validate_internet);
        }
    }

    private void createThreadCustomer() {
        // getView().showProgress(R.string.loading_message);
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                getCustomerList();
            }
        });
        thread.start();
    }

    private void getCustomerList() {

        try {
            ArrayList<Customer> customerArrayList = customerRepository.getCustomerList();
            getView().showCustomerList(customerArrayList);

        } catch (RetrofitError retrofitError) {

            RepositoryError repositoryError = MapperError.convertRetrofitErrorToRepositoryError(retrofitError);
            getView().showAlertError(R.string.error, repositoryError.getMessage());

        }/*finally {
            getView().hideProgress();
        }*/
    }

}
