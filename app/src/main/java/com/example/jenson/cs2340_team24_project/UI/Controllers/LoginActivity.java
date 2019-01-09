package com.example.jenson.cs2340_team24_project.UI.Controllers;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.jenson.cs2340_team24_project.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A login screen that offers login via email/password.
 */
@SuppressWarnings("ALL")
public class LoginActivity extends AppCompatActivity {
    private EditText mPasswordField;
    private EditText mEmailField;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;
    private TextView forgotPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Button btnLogin;
        Button btnCancel;

        mEmailField = findViewById(R.id.email);
        mPasswordField = findViewById(R.id.password);
        firebaseAuth = FirebaseAuth.getInstance();
        progressDialog = new ProgressDialog(this);
        btnLogin = findViewById(R.id.email_sign_in_button);
        btnCancel = findViewById(R.id.button2);
        forgotPassword = findViewById((R.id.tvForgotPassword));

        if (firebaseAuth.getCurrentUser() != null) {
            finish();
            Intent i = new Intent(LoginActivity.this, ApplicationActivity.class);
            startActivity(i);
        }

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(LoginActivity.this, HomeActivity.class);
                startActivity(i);
            }
        });
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                attemptLogin();
            }
        });

        forgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, ForgotPasswordActivity.class));
            }
        });
    }

    private void attemptLogin() {
        String email = mEmailField.getText().toString().trim();
        String password = mPasswordField.getText().toString().trim();
        if (TextUtils.isEmpty(email)) {
            Toast.makeText(this,
                    "Please enter email to log in.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this,
                    "Please enter password to log in.",
                    Toast.LENGTH_LONG).show();
            return;
        }
        progressDialog.setMessage("Logging in...");
        progressDialog.show();

        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(LoginActivity.this,
                                    "You have logged in.",
                                    Toast.LENGTH_LONG).show();
                            finish();
                            Intent i = new Intent(LoginActivity.this, ApplicationActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(LoginActivity.this,
                                    "Login failed.",
                                    Toast.LENGTH_LONG).show();
                        }
                    }
                });
        progressDialog.hide();
    }
}

