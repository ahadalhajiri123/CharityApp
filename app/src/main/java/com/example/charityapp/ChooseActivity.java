package com.example.charityapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class ChooseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose);
    }

    public void adminBtn(View view) {
        startActivity(new Intent(ChooseActivity.this,AdminLoginActivity.class));
        finish();
    }
    public void userBtn(View view) {
        startActivity(new Intent(ChooseActivity.this,LoginActivity.class)
                );
        finish();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
    }
}