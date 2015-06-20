package com.example.timepay.timepay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.Utils.SharedPreferenceHandler;


public class Accounts extends ActionBarActivity {
    EditText emailAddressET, phoneNumberET;
    Button continueB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        initializeView();
        emailAddressET.setText(SharedPreferenceHandler.readValue(this,"Mac Address"));

        continueB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Validator validator=new Validator();
                Log.i("email",emailAddressET.getText()+""+phoneNumberET.getText()+"");

                String message= validator.validateAccountDetatis(emailAddressET.getText()+"",phoneNumberET.getText()+"");
                Log.i("email",message);
                if (message.equals("Completed")) {
                    Log.i("email","inside account");
                    Intent i = new Intent(Accounts.this, ChooseAccountType.class);
                    startActivity(i);
                }else {
                    Log.i("email","error");
                    Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void initializeView() {
        emailAddressET = (EditText)findViewById(R.id.etEmailAddress);
        phoneNumberET = (EditText)findViewById(R.id.etPhoneNumber);
        continueB = (Button)findViewById(R.id.bContinue);

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_accounts, menu);
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
