package com.cosmo.arquitecturamvpbase.views.activities.customer;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cosmo.arquitecturamvpbase.R;
import com.cosmo.arquitecturamvpbase.model.Customer;
import com.cosmo.arquitecturamvpbase.model.Location;
import com.cosmo.arquitecturamvpbase.model.Phone;
import com.cosmo.arquitecturamvpbase.presenter.CreateCustomerPresenter;
import com.cosmo.arquitecturamvpbase.repository.CustomerRepository;
import com.cosmo.arquitecturamvpbase.views.BaseActivity;

import java.util.ArrayList;

/**
 * Created by victorhugosernasuarez on 3/10/17.
 */

public class CreateCustomerActivity extends BaseActivity<CreateCustomerPresenter> implements ICreateCustomerView, TextWatcher {

    private EditText customer_etName, customer_etSurname, customer_etPhoneNumber, customer_etPhoneDescription, customer_etLocationType, customer_etLocationLatitud, customer_etLocationLongitud;
    private Button customer_btnCreate;
    private ContentLoadingProgressBar progress;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_customer);
        setPresenter(new CreateCustomerPresenter(new CustomerRepository()));
        getPresenter().inject(this, getValidateInternet());
        progress = (ContentLoadingProgressBar) findViewById(R.id.progress);
        progress.hide();
        loadViews();
        loadEvents();
    }

    private void loadViews() {
        customer_etName = (EditText) findViewById(R.id.customer_etName);
        customer_etName.addTextChangedListener(this);
        customer_etSurname = (EditText) findViewById(R.id.customer_etSurname);
        customer_etSurname.addTextChangedListener(this);
        customer_etPhoneNumber = (EditText) findViewById(R.id.customer_etPhoneNumber);
        customer_etPhoneNumber.addTextChangedListener(this);
        customer_etPhoneDescription = (EditText) findViewById(R.id.customer_etPhoneDescription);
        customer_etPhoneDescription.addTextChangedListener(this);

        customer_etLocationType = (EditText) findViewById(R.id.customer_etLocationType);
        customer_etLocationType.addTextChangedListener(this);
        customer_etLocationLatitud = (EditText) findViewById(R.id.customer_etLocationLatitud);
        customer_etLocationLatitud.addTextChangedListener(this);
        customer_etLocationLongitud = (EditText) findViewById(R.id.customer_etLocationLongitud);
        customer_etLocationLongitud.addTextChangedListener(this);

        customer_btnCreate = (Button) findViewById(R.id.customer_btnCreate);
    }

    private void loadEvents(){
        progress.show();
        customer_btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Customer customer = new Customer();
                Phone phone = new Phone();
                Location location = new Location();

                customer.setNameCustomer(customer_etName.getText().toString());
                customer.setSurnameCustomer(customer_etSurname.getText().toString());
                phone.setNumber(customer_etPhoneNumber.getText().toString());
                phone.setDescription(customer_etPhoneDescription.getText().toString());
                location.setType(customer_etLocationType.toString());
                double coordinates[] = {Double.parseDouble(customer_etLocationLatitud.getText().toString()),Double.parseDouble(customer_etLocationLongitud.getText().toString())};
                location.setCoordinates(coordinates);
                phone.setLocation(location);

                ArrayList<Phone> phoneList = new ArrayList<Phone>();
                phoneList.add(phone);
                customer.setPhoneList(phoneList);


//                getPresenter().createNewCustomer(customer_etName.getText().toString(), customer_etDescription.getText().toString(),
//                        customer_etPrice.getText().toString(),customer_etQuantity.getText().toString());
                getPresenter().createNewCustomer(customer);

            }
        });
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {

    }

    @Override
    public void afterTextChanged(Editable s) {
/*        if (!customer_etName.getText().toString().trim().isEmpty() && !customer_etDescription.getText().toString().trim().isEmpty() &&
                !customer_etPrice.getText().toString().trim().isEmpty() && !customer_etQuantity.getText().toString().trim().isEmpty()) {
            customer_btnCreate.setBackgroundResource(R.color.colorPrimary);
            customer_btnCreate.setEnabled(true);
        }else{
            customer_btnCreate.setBackgroundResource(R.color.colorGray);
            customer_btnCreate.setEnabled(false);
        }
*/
    }

    @Override
    public void showResultCreateNewCustomer(final boolean isCreated) {

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.hide();
                if(isCreated){
                    Toast.makeText(CreateCustomerActivity.this, getResources().getString(R.string.okResponseCreateCustomer), Toast.LENGTH_LONG).show();
                    finish();
                }else{
                    Toast.makeText(CreateCustomerActivity.this, getResources().getString(R.string.errResponseCreateCustomer), Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    @Override
    public void showAlertInternet(final int title, final int message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(CreateCustomerActivity.this, R.string.validate_internet, Toast.LENGTH_SHORT).show();
            }
        });
    }

}
