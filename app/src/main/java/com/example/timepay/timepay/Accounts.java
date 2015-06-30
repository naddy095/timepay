package com.example.timepay.timepay;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.Utils.SharedPreferenceHandler;
import com.example.componentservices.ComponentBaseServices;


public class Accounts extends ActionBarActivity {
    EditText emailAddressET, phoneNumberET;
    Button continueB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accounts);

        initializeView();
        openDialogBox();
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

    private void openDialogBox() {

        // Create custom dialog object
        final Dialog dialog = new Dialog(Accounts.this);
        // Include dialog.xml file
        dialog.setContentView(R.layout.dialogboxforemail);
        // Set dialog title
        dialog.setTitle("Add your Account with ");
        TextView eId=(TextView)dialog.findViewById(R.id.tv_emailid);
        eId.setText(ComponentBaseServices.getEmail(getApplicationContext()));

        // set values for custom dialog components - text, image and button

        //text.setText("Custom dialog Android example.");
        //image.setImageResource(R.drawable.ic_launcher);

        dialog.show();

        final Button cancel = (Button) dialog.findViewById(R.id.btnCancel);
        final Button ok = (Button) dialog.findViewById(R.id.btnOK);
        // if decline button is clicked, close the custom dialog
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailAddressET.setText(ComponentBaseServices.getEmail(getApplicationContext()));
                dialog.dismiss();
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
