package com.example.timepay.timepay;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.Utils.ApplyInputFilters;
import com.example.Utils.GroupedInputFormatWatcher;

import java.io.File;


public class GeneralPublicRegistration extends ActionBarActivity implements View.OnClickListener{

    private static final int CAPTURE_IMAGE_FROM_CAMERA = 0;
    private static final int LOAD_IMAGE_FROM_GALLERY = 1;
    EditText  fullName,cardName,panNumber,address,cardNumber;
    Button searchIFSCCode ;
    TextView expiryMonth ,expiryYear;
    Button uploadPAN;
    ImageView imageOfPANCard;
    Intent builderIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_general_public_registration);

        initialize();
        uploadPAN.setOnClickListener(this);
        expiryMonth.setOnClickListener(this);
        expiryYear.setOnClickListener(this);
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
        uploadPAN=(Button) findViewById(R.id.btnUploadPAN);
        expiryYear = (TextView) findViewById(R.id.tvExpiryYear);
        expiryMonth = (TextView) findViewById(R.id.tvExpiryMonth);
        fullName = (EditText)findViewById(R.id.etFullName);
        cardName = (EditText)findViewById(R.id.etCardFullName);
        panNumber = (EditText)findViewById(R.id.etPANNumber);
        address = (EditText)findViewById(R.id.etAddress);
        cardNumber = (EditText)findViewById(R.id.etCardNumber);
        imageOfPANCard = (ImageView) findViewById(R.id.ivPANImage);
        cardNumber.addTextChangedListener(new GroupedInputFormatWatcher(cardNumber));
        ApplyInputFilters applyFilters = new ApplyInputFilters(getString(R.string.AddressCharacterFilter));
        address.setFilters(new InputFilter[]{applyFilters});
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

        if (view == uploadPAN) {
            Log.i("VendorRegistration", "onclick");
            final CharSequence[] uploadPanOptions = {"Take a Picture", "Choose From Gallery"};
            AlertDialog.Builder builder = new AlertDialog.Builder(GeneralPublicRegistration.this);

            builder.setTitle("Choose Options");
            builder.setItems(uploadPanOptions, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (uploadPanOptions[i].equals("Take a Picture")) {
                        builderIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(builderIntent, CAPTURE_IMAGE_FROM_CAMERA);

                    } else if (uploadPanOptions[i].equals("Choose From Gallery")) {
                        builderIntent = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(builderIntent, LOAD_IMAGE_FROM_GALLERY);
                    }
                }
            });
            builder.setInverseBackgroundForced(true);
            builder.create();
            builder.show();
        } else if (view == expiryMonth) {
            final CharSequence[] mnth = {"01(Jan)", "02(Feb)", "03(Mar)", "04(Apr)", "05(May)", "06(Jun)", "07(Jul)", "08(Aug)", "09(Sep)", "10(Oct)", "11(Nov)", "12(Dec)"};
            AlertDialog.Builder builder = new AlertDialog.Builder(GeneralPublicRegistration.this);
            builder.setTitle("Select Month");
            builder.setItems(mnth, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "You have selected  " + mnth[which], Toast.LENGTH_LONG).show();
                    expiryMonth.setText(mnth[which]);
                }
            });
            builder.setInverseBackgroundForced(true);
            builder.create();
            builder.show();
        }
        if (view == expiryYear) {
            final CharSequence[] yr = {"2015", "2016", "2017", "2018", "2019", "2020", "2021", "2022", "2023", "2024", "2025", "2026"};
            AlertDialog.Builder builder = new AlertDialog.Builder(GeneralPublicRegistration.this);
            builder.setTitle("Select Year");
            builder.setItems(yr, new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "You have selected  " + yr[which], Toast.LENGTH_LONG).show();
                    expiryYear.setText(yr[which]);
                }
            });
            builder.setInverseBackgroundForced(true);
            builder.create();
            builder.show();
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap;
        Bitmap resizedBitmap;
        if (requestCode == LOAD_IMAGE_FROM_GALLERY && resultCode == RESULT_OK && data != null) {
            Uri selectedImageFromUri = data.getData();
            String pathOFImage = getRealPathFromURI(selectedImageFromUri);
            File imgFile = new File(pathOFImage);
            bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            resizedBitmap = Bitmap.createScaledBitmap(bitmap, 80, 45, false);
            imageOfPANCard.setImageBitmap(resizedBitmap);
        } else if (requestCode == CAPTURE_IMAGE_FROM_CAMERA && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            resizedBitmap = Bitmap.createScaledBitmap(bitmap, 80, 45, false);
            imageOfPANCard.setImageBitmap(resizedBitmap);
        }
    }
    private String getRealPathFromURI(Uri selectedImageFromUri) {
        Cursor cursor = getContentResolver().query(selectedImageFromUri, null, null, null, null);
        if (cursor == null) {
            return selectedImageFromUri.getPath();
        } else {
            cursor.moveToFirst();
            int idx = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
            return cursor.getString(idx);
        }
    }
}
