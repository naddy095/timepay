package com.example.timepay.timepay;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.File;

/**
 * Created by Sahil on 20-06-2015.
 */
public class PrivilageVendorRegistration extends ActionBarActivity implements View.OnClickListener {


    private static final int CAPTURE_IMAGE_FROM_CAMERA = 0;
    private static final int LOAD_IMAGE_FROM_GALLERY=1;

    Button uploadPAN,IFSC,companyID;
    EditText companyName ,shopName ,accountNumber, ifscCode,panNo;
    ImageView imageOfPANCard;
    Intent builderIntent;

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_privilage_vendor_registration);
        initialize();

        IFSC.setOnClickListener(this);
        companyID.setOnClickListener(this);
        uploadPAN.setOnClickListener(this);
    }
    private void initialize()
    {
       IFSC = (Button)findViewById(R.id.btnPVRIFSCCode);
       companyID = (Button)findViewById(R.id.btnPVRCompanyID);
        uploadPAN=(Button)findViewById(R.id.btnUploadPAN);
        companyName =(EditText)findViewById(R.id.etRegisteredCompanyName);
        shopName =(EditText)findViewById(R.id.etShopPublicBrandName);
        accountNumber =(EditText)findViewById(R.id.etAccountNumber);
        ifscCode =(EditText)findViewById(R.id.etIFSCCode);
        panNo =(EditText)findViewById(R.id.etPANNumber);
        imageOfPANCard=(ImageView)findViewById(R.id.ivPANImage);
    }

    @Override
    public void onClick(View view) {
        if(view==IFSC)
        {
            Intent i = new Intent(PrivilageVendorRegistration.this,Webview.class);
            i.putExtra("wvTerms", getString(R.string.IFSC));
            startActivity(i);
        }
        if(view==companyID)
        {
            Intent i = new Intent(PrivilageVendorRegistration.this,Webview.class);
            i.putExtra("wvTerms", getString(R.string.CompanyID));
            startActivity(i);
        }if(view ==uploadPAN){
            Log.i("VendorRegistration", "onclick");
            final CharSequence[] uploadPanOptions={"Take a Picture","Choose From Gallery"};
            AlertDialog.Builder builder=new AlertDialog.Builder(PrivilageVendorRegistration.this);

            builder.setTitle("Choose Options");
            builder.setItems(uploadPanOptions, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    if (uploadPanOptions[i].equals("Take a Picture")){
                        builderIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                        startActivityForResult(builderIntent, CAPTURE_IMAGE_FROM_CAMERA);

                    }else if (uploadPanOptions[i].equals("Choose From Gallery")){
                        builderIntent=new Intent (Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                        startActivityForResult(builderIntent, LOAD_IMAGE_FROM_GALLERY);
                    }
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
