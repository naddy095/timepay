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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import java.io.File;


public class VendorRegistration extends ActionBarActivity implements View.OnClickListener {


    private static final int CAPTURE_IMAGE_FROM_CAMERA = 0;
    private static final int LOAD_IMAGE_FROM_GALLERY=1;
    EditText expiryDate;
    EditText panNo;
    Button uploadPAN ;
    ImageView imageOfPANCard;
    Intent builderIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vendor_registration);


        initialize();
        Log.i("VendorRegistration", "beforeListener");
        uploadPAN.setOnClickListener(this);
        Log.i("VendorRegistration", "afterListener");

    }

    private void initialize() {
        expiryDate = (EditText)findViewById(R.id.etExpiryDate);
        uploadPAN=(Button)findViewById(R.id.btnUploadPAN);
        panNo=(EditText)findViewById(R.id.etPANNumber);
        imageOfPANCard=(ImageView)findViewById(R.id.ivPANImage);
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

        if(view ==uploadPAN){
            Log.i("VendorRegistration","onclick");
            final CharSequence[] uploadPanOptions={"Take a Picture","Choose From Gallery"};
            AlertDialog.Builder builder=new AlertDialog.Builder(VendorRegistration.this);

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
        Bitmap bitmap ;
        Bitmap resizedBitmap;
            if (requestCode==LOAD_IMAGE_FROM_GALLERY && resultCode==RESULT_OK && data !=null){
                Uri selectedImageFromUri=data.getData();
                String pathOFImage=getRealPathFromURI(selectedImageFromUri);
                File imgFile=new File(pathOFImage);
                bitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
                resizedBitmap = Bitmap.createScaledBitmap(bitmap, 80, 45, false);
                imageOfPANCard.setImageBitmap(resizedBitmap);
            }else if (requestCode==CAPTURE_IMAGE_FROM_CAMERA && resultCode ==RESULT_OK){
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
