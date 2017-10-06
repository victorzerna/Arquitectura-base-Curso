package com.cosmo.arquitecturamvpbase.views.activities.customer;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import android.os.Parcelable;
import android.provider.SyncStateContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.helper.Constants;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.presenter.CustomerPresenter;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;
import com.cosmo.arquitecturamvpbase.views.activities.product.DetailActivity;
import com.cosmo.arquitecturamvpbase.views.adapter.CustomerAdapter;


import java.util.ArrayList;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public class CustomerActivity extends BaseActivity<CustomerPresenter> implements ICustomerView {

    private ListView customerList;
    private CustomerAdapter customerAdapter;
    private ContentLoadingProgressBar progress;
    private FloatingActionButton buttonLaunchCreate;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);
        setPresenter(new CustomerPresenter());
        getPresenter().inject(this, getValidateInternet());
        //createProgressDialog();
        customerList = (ListView) findViewById(R.id.customer_listView);
        progress = (ContentLoadingProgressBar) findViewById(R.id.progress);
        progress.show();
        getPresenter().getListCustomer();
        loadEvents();
    }




    @Override
    protected void onRestart() {
        super.onRestart();
        getPresenter().getListCustomer();
    }

    private void loadEvents() {
        buttonLaunchCreate = (FloatingActionButton) findViewById(R.id.fab_launch_createcustomer);
        buttonLaunchCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CustomerActivity.this, CreateCustomerActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        progress.show();
        getPresenter().getListCustomer();
    }

    @Override
    public void showCustomerList(final ArrayList<Customer> customerArrayList) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.hide();
                callAdapter(customerArrayList);
            }
        });
    }

    @Override
    public void showAlertDialogInternet(final int title, final int message) {
        showAlertDialog(title, getResources().getString(message));

    }

    private void showAlertDialog(final int title, final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                getShowAlertDialog().showAlertDialog(title, message, false, R.string.accept, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getPresenter().getListCustomer();
                    }
                }, R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }
                });
            }
        });
    }

    @Override
    public void showAlertError(int title, String message) {
        showAlertDialog(title, message);
    }

    private void callAdapter(final ArrayList<Customer> customerArrayList) {
        customerAdapter =  new CustomerAdapter(this, R.id.customer_listView, customerArrayList);
        customerList.setAdapter(customerAdapter);
        customerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(CustomerActivity.this, DetailActivity.class);
//                intent.putExtra(Constants.ITEM_CUSTOMER,customerArrayList.get(position));
                intent.putExtra(Constants.ITEM_CUSTOMER, position);
                startActivity(intent);
            }
        });
    }
}
