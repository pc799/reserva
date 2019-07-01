package com.example.reserva;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    DatabaseManager myDb;
    Button login;
    TextView register;
    EditText username, password;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myDb = DatabaseManager.getInstance(this);
        login = (Button) findViewById(R.id.login);
        register = (TextView) findViewById(R.id.register);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);

        register.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(LoginActivity.this, RegisterActivity.class);
                        startActivity(i);
                    }
                }
        );

        viewAll();
    }

    public void viewAll() {
        login.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDb.getAllData();

                        String[] data = new String[3];
                        while (res.moveToNext()) {
                            data[0] = res.getString(0);
                            data[1] = res.getString(1);
                            data[2] = res.getString(2);
                        }

                        // Show all data
                        checkData(data);
                    }
                }
        );
    }

    void checkData(String[] data) {
        if ( !(username.getText().toString().equals(data[1])) )
            username.setError("Invalid email id!");
        else if ( !(password.getText().toString().equals(data[2])) )
            password.setError("Invalid password!");
        else {
            Toast.makeText(this, "Login successful!", Toast.LENGTH_LONG).show();
            Intent i = new Intent(LoginActivity.this, MainActivity.class);
            i.putExtra("MOBILE", data[0]);
            startActivity(i);
        }
    }
}
