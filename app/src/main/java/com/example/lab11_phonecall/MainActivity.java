package com.example.lab11_phonecall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText txt_phoneNumber;
    Button btn_call;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_phoneNumber = (EditText)findViewById(R.id.txt_number);
        btn_call =(Button)findViewById(R.id.btn_call);
        btn_call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentCall = new Intent(Intent.ACTION_CALL);
                String Number = txt_phoneNumber.getText().toString();
                if(ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED)
                {
                    Toast.makeText(MainActivity.this,"Please Grant Permission",Toast.LENGTH_SHORT).show();
                    requestPermission();
                }

                if(Number.trim().isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Please Enter Your phone number",Toast.LENGTH_LONG).show();

                }
                else if (Number.length()<3)
                {
                    Toast.makeText(MainActivity.this,"Please Enter Valid phone number",Toast.LENGTH_LONG).show();
                }
                else
                {
                    intentCall.setData(Uri.parse("tel:"+Number));
                    Toast.makeText(MainActivity.this,"Calling",Toast.LENGTH_LONG).show();
                    startActivity(intentCall);
                }



            }
        });

    }
    private void requestPermission()
    {
        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.CALL_PHONE},1);


    }
}