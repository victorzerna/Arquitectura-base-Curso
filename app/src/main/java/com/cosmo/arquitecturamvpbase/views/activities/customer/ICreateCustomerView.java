package com.cosmo.arquitecturamvpbase.views.activities.customer;

import com.cosmo.arquitecturamvpbase.views.IBaseView;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public interface ICreateCustomerView extends IBaseView{

    void showResultCreateNewCustomer(boolean isCreated);

    void showAlertInternet(int title, int message);
}
