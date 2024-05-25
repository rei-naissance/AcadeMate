package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {
    Button btnLogin;
   TextView signup;

   EditText user, password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_login);

        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        user = findViewById(R.id.loginUser);
        password = findViewById(R.id.loginPass);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            String userString = user.getText().toString();
            String passString = password.getText().toString();

            if (userString.isEmpty() || passString.isEmpty()) {
                Toast.makeText(Login.this, "Please enter both username and password.", Toast.LENGTH_SHORT).show();
            } else {
                mAuth.signInWithEmailAndPassword(userString, passString).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Intent intent = new Intent(Login.this, MainScreen.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Login.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });

        signup = findViewById(R.id.textSignup);
        signup.setOnClickListener(v -> {
            Intent intent = new Intent(Login.this, Signup.class);
            startActivity(intent);
        });

    }
}