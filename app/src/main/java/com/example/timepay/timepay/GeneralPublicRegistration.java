package com.example.timepay.timepay;

import android.app.DatePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;


public class GeneralPublicRegistration extends ActionBarActivity {

    EditText expiryDate, fullName,cardName,panNumber,address;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_public_registration);

        initialize();
        /*DatePickerDialog datePickerDialog=new DatePickerDialog(this, listener, year, month, day);
        DatePicker datepicker=datePickerDialog.getDatePicker();
        //datep.removeViewAt(0);
        LinearLayout v1=(LinearLayout)datepicker.getChildAt(0);
        LinearLayout v2=(LinearLayout)v1.getChildAt(0);
        View v3=v2.getChildAt(1);
        v3.setVisibility(View.GONE);
        Toast.makeText(this, "" + v3.getClass().getName() + "\ncount " + v2.getChildCount(), 1).show();
        datePickerDialog.show();*/

        //To populate the Card Name from Full Name
        fullName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus){
                    //Toast.makeText(getApplicationContext(), "got unfocus", Toast.LENGTH_LONG).show();
                    cardName.setText("");
                    cardName.setText(fullName.getText());
                }else{
                    //Toast.makeText(getApplicationContext(), "got the focus", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initialize() {
        expiryDate = (EditText)findViewById(R.id.etExpiryDate);
        fullName = (EditText)findViewById(R.id.etFullName);
        cardName = (EditText)findViewById(R.id.etCardFullName);
        panNumber = (EditText)findViewById(R.id.etPANNumber);
        address = (EditText)findViewById(R.id.etAddress);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_general_public_registration, menu);
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
