package com.example.timepay.timepay;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ChooseAccountType extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    Spinner spAccountType;
    private String[] Account_Type = {"General Public Registration", "Vendor Registration", "Privilege Vendor Registration"};
    Button bAccountType;
    CheckBox cbIAgree;
    Integer position;
    WebView wvIAccpet;
    TextView tvIAgree;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);
        initializeView();

        //String checkBoxText = "you must agree with the <a href='http://www.google.com' > Terms and Conditions.</a>";
        //String checkBoxText = "you must agree with the Terms and Conditions.";
       // tvIAgree.setText(Html.fromHtml(checkBoxText));
        tvIAgree.setClickable(true);
        tvIAgree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ChooseAccountType.this, Webview.class);
                intent.putExtra("wvTerms", getString(R.string.wvIAree));
                startActivity(intent);
            }
        });

        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, Account_Type);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAccountType.setAdapter(adapter_state);
        spAccountType.setOnItemSelectedListener(this);

        bAccountType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (cbIAgree.isChecked()) {
                    //Toast.makeText(ChooseAccountType.this, "check box is checked", Toast.LENGTH_LONG).show();
                    Intent i= null;
                    switch (position) {

                        case 0:
                            //Toast.makeText(ChooseAccountType.this, "General Public Registration", Toast.LENGTH_SHORT).show();
                            i = new Intent(ChooseAccountType.this,GeneralPublicRegistration.class);
                            //i.putExtra("AccountType","GPR");
                            startActivity(i);
                            break;

                        case 1:
                            // Toast.makeText(ChooseAccountType.this, "Vendor Registration", Toast.LENGTH_SHORT).show();
                            i = new Intent(ChooseAccountType.this,VendorRegistration.class);
                            //i.putExtra("AccountType","VR");
                            startActivity(i);
                            break;

                        case 2:
                            i = new Intent(ChooseAccountType.this,PrivilageVendorRegistration.class);
                            startActivity(i);
                            // Toast.makeText(ChooseAccountType.this, "Privilege Vendor Registration", Toast.LENGTH_SHORT).show();
                            break;

                    }
                } else {
                    Toast.makeText(ChooseAccountType.this, "Please accept terms and conditions", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initializeView() {
        bAccountType = (Button) findViewById(R.id.bContinue);
        cbIAgree = (CheckBox) findViewById(R.id.cbIAgree);
        spAccountType = (Spinner) findViewById(R.id.spAccountType);
        wvIAccpet = (WebView)findViewById(R.id.webview);

        tvIAgree= (TextView)findViewById(R.id.tvIAgree);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spAccountType.setSelection(i);
        if(cbIAgree.isChecked())
        {
            cbIAgree.toggle();
        }
        position = i;
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_choose_account_type, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
