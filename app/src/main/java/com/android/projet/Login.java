package com.android.projet;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.projet.MainActivity;
import com.android.projet.R;
import com.android.projet.Register;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    EditText loginEmail, loginPassword;
    Button loginButton;
    TextView signupRedirectText,forgotPasswordText;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.et_email);
        loginPassword = findViewById(R.id.et_password);
        loginButton = findViewById(R.id.btn_login);
        signupRedirectText = findViewById(R.id.tv_signup);
        forgotPasswordText = findViewById(R.id.tv_forgotpassword);

        auth = FirebaseAuth.getInstance();


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginUser();
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

        //  récupération du mot de passe
        forgotPasswordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = loginEmail.getText().toString().trim();
                if (email.isEmpty()) {
                    loginEmail.setError("Please enter your email to reset password");
                    loginEmail.requestFocus();
                } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    loginEmail.setError("Please provide a valid email");
                    loginEmail.requestFocus();
                } else {
                    auth.sendPasswordResetEmail(email).addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            Toast.makeText(Login.this, "Password reset email sent", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(Login.this, "Error: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            }
        });
    }


    private void loginUser() {
        String email = loginEmail.getText().toString().trim();
        String password = loginPassword.getText().toString().trim();

        if (email.isEmpty()) {
            loginEmail.setError("Email cannot be empty");
            loginEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            loginEmail.setError("Please provide a valid email");
            loginEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            loginPassword.requestFocus();
            return;
        }

        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    Toast.makeText(Login.this, "Login successful", Toast.LENGTH_SHORT).show();
                    String userName = user.getDisplayName() ;
                    Intent intent = new Intent(Login.this, Home.class);
                    intent.putExtra("userName",userName);
                    startActivity(intent);
                    finish();
                }
            } else {
                handleLoginError(task.getException());
            }
        });
    }

    private void handleLoginError(Exception exception) {

            Toast.makeText(Login.this, "Incorrect email or password "  , Toast.LENGTH_SHORT).show();
        }
    }
