package com.example.charityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AdminLoginActivity extends AppCompatActivity {
    EditText edtEmail, edtPassword;
    TextView textView, btnLogin;

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);


        btnLogin = findViewById(R.id.btnlogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtpassword);
         textView = findViewById(R.id.txt);


        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (edtEmail.getText().toString().isEmpty()) {
                    Toast.makeText(AdminLoginActivity.this, "Email is Empty", Toast.LENGTH_SHORT).show();
                } else if (edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(AdminLoginActivity.this, "Password is Empty", Toast.LENGTH_SHORT).show();

                } else {
                    if (edtEmail.getText().toString().equals("admin@gmail.com") && edtPassword.getText().toString().equals("admin")) {
                        startActivity(new Intent(AdminLoginActivity.this, AdminSideActivity.class));

                    } else {

                        Toast.makeText(AdminLoginActivity.this, "Admin email or password is incorrect", Toast.LENGTH_SHORT).show();
                    }


                }

            }
        });

    }
}