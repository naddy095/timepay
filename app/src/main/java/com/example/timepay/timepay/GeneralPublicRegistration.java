package com.example.timepay.timepay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Utils.GroupedInputFormatWatcher;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class GeneralPublicRegistration extends ActionBarActivity implements View.OnClickListener{

    private static final int CAPTURE_IMAGE_FROM_CAMERA = 0;
    private static final int LOAD_IMAGE_FROM_GALLERY = 1;
    EditText  fullName,cardName,panNumber,address,cardNumber;
    Button continueBtn ;
    TextView expiryMonth ,expiryYear;
    Intent builderIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_public_registration);

        initialize();
        expiryMonth.setOnClickListener(this);
        expiryYear.setOnClickListener(this);
        continueBtn.setOnClickListener(this);

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
                    cardName.setEnabled(true);
                    cardName.setText("");
                    cardName.setText(fullName.getText());
                    cardName.setEnabled(false);
                }else{
                    //Toast.makeText(getApplicationContext(), "got the focus", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void initialize() {
        expiryYear = (TextView) findViewById(R.id.tvExpiryYear);
        expiryMonth = (TextView) findViewById(R.id.tvExpiryMonth);
        fullName = (EditText)findViewById(R.id.etFullName);
        cardName = (EditText)findViewById(R.id.etCardFullName);
        panNumber = (EditText)findViewById(R.id.etPANNumber);
        address = (EditText)findViewById(R.id.etAddress);
        cardNumber = (EditText)findViewById(R.id.etCardNumber);
        cardName=(EditText)findViewById(R.id.etCardFullName);
        continueBtn=(Button)findViewById(R.id.bContinue);
        cardNumber.addTextChangedListener(new GroupedInputFormatWatcher(cardNumber));
        //ApplyInputFilters applyFilters = new ApplyInputFilters(getString(R.string.AddressCharacterFilter));
        //address.setFilters(new InputFilter[]{applyFilters});
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

    @Override
    public void onClick(View view) {

        if (view == expiryMonth) {

            final CharSequence[] mnth = {"01(Jan)", "02(Feb)", "03(Mar)", "04(Apr)", "05(May)", "06(Jun)", "07(Jul)", "08(Aug)", "09(Sep)", "10(Oct)", "11(Nov)", "12(Dec)"};
            AlertDialog.Builder builder = new AlertDialog.Builder(GeneralPublicRegistration.this);
            builder.setTitle("Select Month");
            builder.setItems(mnth, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(getApplicationContext(), "You have selected  " + mnth[which], Toast.LENGTH_LONG).show();
                    expiryMonth.setText(mnth[which]);
                }
            });
            builder.setInverseBackgroundForced(true);
            builder.create();
            builder.show();
        }
        if (view == expiryYear) {

            Integer currentYear=Integer.parseInt(new SimpleDateFormat("yyyy").format(Calendar.getInstance().getTime()));
            String[] yr= new String[20];
            for (int x=0;x<20;x++){
                    yr[x]=currentYear+x+"";
            }
            final CharSequence[] yearToDisplay = yr;
            AlertDialog.Builder builder = new AlertDialog.Builder(GeneralPublicRegistration.this);
            builder.setTitle("Select Year");
            builder.setItems(yearToDisplay, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    //Toast.makeText(getApplicationContext(), "You have selected  " + yr[which], Toast.LENGTH_LONG).show();
                    expiryYear.setText(yearToDisplay[which]);
                }
            });
            builder.setInverseBackgroundForced(true);
            builder.create();
            builder.show();
        }
        if (view == continueBtn) {
            Log.i("GPR", expiryYear.getText() + "" + expiryMonth.getText());
            Validator validator=new Validator();
            try {
                validator.validateGPR(fullName.getText() + "",
                        address.getText() + "", panNumber.getText() + "",
                        cardNumber.getText() + "", cardName.getText() + "",
                        expiryMonth.getText() + "", expiryYear.getText() + "");
                validator.validateExpiryDate(expiryMonth.getText() + "", expiryYear.getText() + "");
                Toast.makeText(getApplicationContext(), "Completed Successfully", Toast.LENGTH_SHORT).show();
            }catch(Exception e){
                Toast.makeText(getApplicationContext(), e.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        }
    }
}
