package com.aditya.babylonplus.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.aditya.babylonplus.R;
import com.aditya.babylonplus.model.object.Address;
import com.aditya.babylonplus.model.object.Company;
import com.aditya.babylonplus.model.object.User;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

public class UserDetailsActivity extends AppCompatActivity implements UserDetailsView {

    @BindView(R.id.avatar)
    CircleImageView avatar;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.email)
    TextView email;
    @BindView(R.id.phone)
    TextView phone;
    @BindView(R.id.website)
    TextView website;
    @BindView(R.id.streetSuite)
    TextView streetSuite;
    @BindView(R.id.city)
    TextView city;
    @BindView(R.id.zipcode)
    TextView zipcode;
    @BindView(R.id.companyName)
    TextView companyName;
    @BindView(R.id.catchPhrase)
    TextView catchPhrase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_details);
        ButterKnife.bind(this);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if (intent.hasExtra("User")) {
            User user = intent.getParcelableExtra("User");
            setUserDetails(user);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setUserDetails(User user) {
        name.setText(user.getName());
        email.setText(user.getEmail());
        phone.setText(user.getPhone());
        website.setText(user.getWebsite());
        Address address = user.getAddress();
        if (address != null) {
            streetSuite.setText(String.format(getString(R.string.street_suite_placeholder), address.getStreet(), address.getSuite()));
            city.setText(address.getCity());
            zipcode.setText(address.getZipCode());
        }
        Company company = user.getCompany();
        if (company != null) {
            companyName.setText(company.getName());
            catchPhrase.setText(company.getCatchPhrase());
        }
    }
}
