package com.example.charityapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {

    EditText edtEmail, edtPassword;
    TextView textView, btnLogin, btnSigunp;
    ProgressDialog progressDialog;
//    String userType;
    private FirebaseAuth mAuth;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
         sharedPreferences = getSharedPreferences(getPackageName(), MODE_PRIVATE);
        editor = sharedPreferences.edit();
        mAuth = FirebaseAuth.getInstance();
        btnLogin = findViewById(R.id.btnlogin);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtpassword);
        btnSigunp = findViewById(R.id.btnsignUp);
        textView = findViewById(R.id.txt);
        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Please wait...");
        progressDialog.setCancelable(true);

        btnSigunp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));

            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edtEmail.getText().toString().isEmpty() || edtPassword.getText().toString().isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Email or Password is Empty", Toast.LENGTH_SHORT).show();
                } else {


                    progressDialog.show();

                        LoginUser(edtEmail.getText().toString().trim(), edtPassword.getText().toString().trim());

                }

            }
        });

    }

    private void LoginUser(String email, String password) {
        progressDialog.show();
        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
//                            if(user.isEmailVerified()) {
                            fetchUserData(user.getUid());
                            //  startActivity(new Intent(LoginActivity.this, Dashboard.class));

//                            }else{
//                                progressDialog.dismiss();
//                                Toast.makeText(LoginActivity.this, "Please Verified your email first", Toast.LENGTH_SHORT).show();
//                            }

                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(LoginActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            progressDialog.dismiss();
                        }

                        // ...
                    }
                });
    }

    private void fetchUserData(String uuid) {

//        progressBar.setVisibility(View.VISIBLE);
        DatabaseReference dbRef = FirebaseDatabase.getInstance().getReference();
        dbRef.child("Users").child(uuid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                for (DataSnapshot snapshot1:snapshot.getChildren()){

                }
//                AppData.Key = snapshot.getKey();
//                AppData.name = snapshot.child("name").getValue(String.class);
//                AppData.email = snapshot.child("email").getValue(String.class);
//                AppData.password = snapshot.child("password").getValue(String.class);

                editor.putString("userId", snapshot.getKey());
                editor.putString("email", snapshot.child("email").getValue(String.class));
                editor.putString("password", snapshot.child("password").getValue(String.class));
                editor.putString("name", snapshot.child("name").getValue(String.class));
                editor.putString("isLogin","yes");
                editor.apply();

               startActivity(new Intent(LoginActivity.this,MainActivity.class));
               finish();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(LoginActivity.this, error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        progressDialog.dismiss();

    }

}