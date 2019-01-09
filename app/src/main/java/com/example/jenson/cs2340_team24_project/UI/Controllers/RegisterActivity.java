package com.example.jenson.cs2340_team24_project.UI.Controllers;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.app.ProgressDialog;

import com.example.jenson.cs2340_team24_project.R;
import com.example.jenson.cs2340_team24_project.UI.Models.Responsibility;
import com.example.jenson.cs2340_team24_project.UI.Models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SuppressWarnings("ALL")
/**
 * The activity where you register a new user.
 */
public class RegisterActivity extends AppCompatActivity {

    private EditText mPasswordField;
    private EditText mEmailField;
    private EditText mUsernameField;
    private Spinner responsibilitySpinner;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Button btnRegister;
        Button btnCancel;

        mEmailField = findViewById(R.id.emailReg);
        mUsernameField = findViewById(R.id.usernameReg);
        mPasswordField = findViewById(R.id.passWordReg);
        btnCancel = findViewById(R.id.btnCancelReg);
        btnRegister = findViewById(R.id.btnRegister);
        responsibilitySpinner = findViewById(R.id.spinResponsibility);

        progressDialog = new ProgressDialog(this);

        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference();

        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                saveUserInfo();
                createAccount();

                finish();
                Intent i = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(i);
            }
        });
        btnCancel.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });

        //setting up spinner
        //noinspection unchecked
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, User.legalResponsibilities);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        responsibilitySpinner.setAdapter(adapter);
    }

    private void saveUserInfo() {
        String email = mEmailField.getText().toString().trim();
        String username = mUsernameField.getText().toString().trim();
        Responsibility res = (Responsibility) responsibilitySpinner.getSelectedItem();
        boolean state = true;
        User newuser = new User(email, username, res, state);
        databaseReference.child("users").child(username).setValue(newuser);
        Toast.makeText(this, "User information saved.", Toast.LENGTH_LONG).show();

    }

    private void createAccount() {
        String email = mEmailField.getText().toString().trim();
        String username = mUsernameField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this,
                    "Please provide your email address.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            Toast.makeText(this,
                    "Please provide your username.",
                    Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this,
                    "Please enter your password.",
                    Toast.LENGTH_SHORT).show();
            return;
        }

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Registered successfully.", Toast.LENGTH_LONG).show();

                        } else {
                            Toast.makeText(RegisterActivity.this, "Registration failed.", Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });
        progressDialog.dismiss();
    }
}
