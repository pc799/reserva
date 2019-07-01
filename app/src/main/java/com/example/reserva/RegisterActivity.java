package com.example.reserva;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    DatabaseManager myDb;
    Button register;
    EditText mobile, email, username, password, upi;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        myDb = DatabaseManager.getInstance(this);
        register = (Button) findViewById(R.id.register);
        mobile = (EditText) findViewById(R.id.mobile);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        email = (EditText) findViewById(R.id.email);
        upi = (EditText) findViewById(R.id.upi);

        AddData();
    }

    public  void AddData() {
        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDb.insertData(
                                mobile.getText().toString(),
                                username.getText().toString(),
                                password.getText().toString(),
                                email.getText().toString(),
                                upi.getText().toString()
                        );
                        if(isInserted) {
                            Toast.makeText(RegisterActivity.this, "Registration successful!", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                            startActivity(i);
                        }
                        else
                            Toast.makeText(RegisterActivity.this,"Registration failed. Try Again!",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
