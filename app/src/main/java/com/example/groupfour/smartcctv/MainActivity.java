package com.example.groupfour.smartcctv;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper helper;
    int attemptCtr;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //attemptCtr=0;


    }

    public void submitFunc(View view){

        helper = new DatabaseHelper(this);
        EditText id = (EditText)findViewById(R.id.editText);
        EditText password = (EditText)findViewById(R.id.editText2);

        String retPass = helper.searchPassword(id.getText().toString());
        if(retPass.equals(password.getText().toString())) //&& attemptCtr<3 )
        {
           // attemptCtr=1;
            Intent intent = new Intent("com.example.groupfour.smartcctv.Main2Activity");
            Toast.makeText(getApplicationContext(), "Welcome " + id.getText().toString() + ".", Toast.LENGTH_SHORT).show();
            id.setText("");
            password.setText("");
            //sendData("1");
            startActivity(intent);
        }
        /*else if(attemptCtr<3)
        {
            Toast.makeText(getApplicationContext(), "Incorrect Login ID or password. "+(3-(attemptCtr))+" attempts remaining", Toast.LENGTH_SHORT).show();
        }*/
        else
        {
            Toast.makeText(getApplicationContext(), "Incorrect Login ID or password.", Toast.LENGTH_SHORT).show();
            //sendData("0");
        }

    }



    public void registerFunc(View view)
    {
        Intent intent = new Intent("com.example.groupfour.smartcctv.Registration_Activity");
        startActivity(intent);
    }


}
