package com.cosmo.arquitecturamvpbase.views.activities.customer;

import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.views.IBaseView;

import java.util.ArrayList;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public interface ICustomerView extends IBaseView{

    void showCustomerList(ArrayList<Customer> customerArrayList);

    void showAlertDialogInternet(int title, int message);

    void showAlertError(int title, String message);
}
