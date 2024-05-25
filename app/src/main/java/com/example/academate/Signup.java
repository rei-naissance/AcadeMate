package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;

public class Signup extends AppCompatActivity {
    EditText email, password;
    Button signupButton;

    private FirebaseAuth mAuth;

    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_signup);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.signupUser);
        password = findViewById(R.id.signupPass);
        signupButton = findViewById(R.id.btnSignup);

        signupButton.setOnClickListener(v -> {
            String emailString = email.getText().toString();
            String passString = password.getText().toString();
            if (emailString.isEmpty() || passString.isEmpty()) {
                Toast.makeText(Signup.this, "Please enter both username and password.", Toast.LENGTH_SHORT).show();
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailString, passString).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Signup.this, "Authentication successful.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Signup.this, MainScreen.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Signup.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        login = findViewById(R.id.textLogin);
        login.setOnClickListener(v -> {
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
        });
    }
}