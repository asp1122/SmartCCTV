package com.example.groupfour.smartcctv;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Registration_Activity extends AppCompatActivity {

    DatabaseHelper helper = new DatabaseHelper(this);
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
    }

    public void newUser(View view)
    {
        EditText name = (EditText)findViewById(R.id.editText3);
        EditText login = (EditText)findViewById(R.id.editText4);
        EditText password = (EditText)findViewById(R.id.editText5);
        EditText passwordCheck = (EditText)findViewById(R.id.editText6);
        EditText phone = (EditText)findViewById(R.id.editText7);


        if(!password.getText().toString().equals(passwordCheck.getText().toString()))
            Toast.makeText(getApplicationContext(),"Passwords don't match", Toast.LENGTH_SHORT).show();

        else
        {
            Contact contact = new Contact();
            contact.setLoginid(login.getText().toString());
            contact.setName(name.getText().toString());
            contact.setPassword(password.getText().toString());
            contact.setPhone(phone.getText().toString());


            if(helper.insertData(contact))
            {
                Toast.makeText(getApplicationContext(), "Successfully Registered", Toast.LENGTH_SHORT).show();
                finish();
            }
            else
                Toast.makeText(getApplicationContext(), "Registration Unsuccessful", Toast.LENGTH_SHORT).show();

        }
    }
}
