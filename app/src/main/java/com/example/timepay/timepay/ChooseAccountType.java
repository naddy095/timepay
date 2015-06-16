package com.example.timepay.timepay;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.Toast;


public class ChooseAccountType extends ActionBarActivity implements AdapterView.OnItemSelectedListener {

    Spinner spAccountType;
    private String[] Account_Type = {"General Public Registration","Vendor Registration","Privilege Vendor Registration"};
    Button BAccountType;
    CheckBox cbIAgree;
    Integer position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_type);

        spAccountType = (Spinner)findViewById(R.id.spAccountType);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_dropdown_item,Account_Type);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spAccountType.setAdapter(adapter_state);
        spAccountType.setOnItemSelectedListener(this);

        BAccountType = (Button)findViewById(R.id.bContinue);
        cbIAgree = (CheckBox)findViewById(R.id.cbIAgree);

        BAccountType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(cbIAgree.isChecked())
                {
                    Toast.makeText(ChooseAccountType.this,"check box is checked",Toast.LENGTH_LONG).show();

                switch(position)
                {
                    case 0: Toast.makeText(ChooseAccountType.this,"General Public Registration",Toast.LENGTH_SHORT).show();
                        break;

                    case 1:Toast.makeText(ChooseAccountType.this,"Vendor Registration",Toast.LENGTH_SHORT).show();
                        break;

                    case 2:Toast.makeText(ChooseAccountType.this,"Privilege Vendor Registration",Toast.LENGTH_SHORT).show();
                        break;

                }


                }
                else
                {
                    Toast.makeText(ChooseAccountType.this,"Please click on check box to proceed further",Toast.LENGTH_LONG).show();
                }




            }
        });

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

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        spAccountType.setSelection(i);
        position=i;

    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}
