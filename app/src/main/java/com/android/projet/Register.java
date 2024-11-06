package com.android.projet;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.UserProfileChangeRequest;

public class Register extends AppCompatActivity {
    EditText signupName, signupEmail, signupPassword,signupPasswordConfirmation;
    TextView loginRedirectText;
    TextView signupButton;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        signupName = findViewById(R.id.et_fullname);
        signupEmail = findViewById(R.id.et_email2);
        signupPassword = findViewById(R.id.et_password2);
        signupPasswordConfirmation = findViewById(R.id.et_password3);
        loginRedirectText = findViewById(R.id.tv_signin);
        signupButton = findViewById(R.id.btn_register);

        auth = FirebaseAuth.getInstance();

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }

    private void registerUser() {
        String name = signupName.getText().toString().trim();
        String email = signupEmail.getText().toString().trim();
        String password = signupPassword.getText().toString().trim();
        String confirmPassword = signupPasswordConfirmation.getText().toString().trim();


        if (name.isEmpty()) {
            signupName.setError("Name cannot be empty");
            signupName.requestFocus();
            return;
        }


        if (email.isEmpty()) {
            signupEmail.setError("Email cannot be empty");
            signupEmail.requestFocus();
            return;
        }

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            signupEmail.setError("Please provide a valid email");
            signupEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            signupPassword.setError("Password cannot be empty");
            signupPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            signupPassword.setError("Password should be at least 6 characters");
            signupPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            signupPasswordConfirmation.setError("Passwords do not match");
            signupPasswordConfirmation.requestFocus();
            return;
        }

        // Créer un utilisateur avec Firebase Authentication
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                FirebaseUser user = auth.getCurrentUser();
                if (user != null) {
                    UserProfileChangeRequest profileUpdates = new UserProfileChangeRequest.Builder()
                            .setDisplayName(name) // Ajoute le nom saisi
                            .build();

                    user.updateProfile(profileUpdates).addOnCompleteListener(profileTask -> {
                        if (profileTask.isSuccessful()) {
                            Toast.makeText(Register.this, "User registered successfully", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(Register.this, Login.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(Register.this, "Failed to save user name", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            } else {
                Toast.makeText(Register.this, "Registration failed: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
}




