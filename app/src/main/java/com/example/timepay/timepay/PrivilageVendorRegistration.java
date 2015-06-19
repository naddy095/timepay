package com.example.timepay.timepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Sahil on 20-06-2015.
 */
public class PrivilageVendorRegistration extends ActionBarActivity implements View.OnClickListener {

    Button btIFSC,btCompanyID;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privilage_vendor_registration);
        initialize();

        btIFSC.setOnClickListener(this);
        btCompanyID.setOnClickListener(this);

    }

    private void initialize()
    {
       btIFSC = (Button)findViewById(R.id.btnPVRIFSCCode);
        btCompanyID = (Button)findViewById(R.id.btnPVRCompanyID);
    }

    @Override
    public void onClick(View view) {
        if(view==btIFSC)
        {
            Intent i = new Intent(PrivilageVendorRegistration.this,Webview.class);
            i.putExtra("wvTerms", getString(R.string.IFSC));
            startActivity(i);
        }
        if(view==btCompanyID)
        {
            Intent i = new Intent(PrivilageVendorRegistration.this,Webview.class);
            i.putExtra("wvTerms", getString(R.string.CompanyID));
            startActivity(i);
        }

    }
}
