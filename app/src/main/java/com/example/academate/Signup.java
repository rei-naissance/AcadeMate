package com.example.academate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Signup extends AppCompatActivity {
    EditText email, password, username;
    Button signupButton;

    private FirebaseAuth mAuth;
    private FirebaseFirestore db;

    TextView login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_signup);

        mAuth = FirebaseAuth.getInstance();

        login = findViewById(R.id.textLogin);
        email = findViewById(R.id.signupUser);
        password = findViewById(R.id.signupPass);
        username = findViewById(R.id.signupName);
        signupButton = findViewById(R.id.btnSignup);

        db = FirebaseFirestore.getInstance();

        signupButton.setOnClickListener(v -> {
            String usernameString = username.getText().toString();
            String emailString = email.getText().toString();
            String passString = password.getText().toString();
            if(usernameString.isEmpty()|| emailString.isEmpty() || passString.isEmpty()) {
                Toast.makeText(Signup.this, "Please enter username, email, and password.", Toast.LENGTH_SHORT).show();
            } else {
                FirebaseAuth.getInstance().createUserWithEmailAndPassword(emailString, passString).addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        Toast.makeText(Signup.this, "Authentication successful.", Toast.LENGTH_SHORT).show();
                        // Create a new user
                        Map<String, Object> user = new HashMap<>();
                        user.put("username", usernameString);
                        user.put("email", emailString);
                        user.put("contact_number", "");

                        // Add a new document with a generated ID
                        db.collection("users")
                                .document(Objects.requireNonNull(mAuth.getCurrentUser()).getUid())
                                .set(user)
                                .addOnSuccessListener(aVoid -> {
                                    Toast.makeText(Signup.this, "User profile stored successfully.", Toast.LENGTH_SHORT).show();
                                    Intent intent = new Intent(Signup.this, MainScreen.class);
                                    startActivity(intent);
                                })
                                .addOnFailureListener(e -> Toast.makeText(Signup.this, "Failed to store the user profile.", Toast.LENGTH_SHORT).show());


                    } else {
                        Toast.makeText(Signup.this, "Authentication failed: " + Objects.requireNonNull(task.getException()).getMessage(), Toast.LENGTH_LONG).show();
                    }
                }).addOnFailureListener(e -> Toast.makeText(Signup.this, "Sign up failed, please try again: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        });

        login.setOnClickListener(v -> {
            Intent intent = new Intent(Signup.this, Login.class);
            startActivity(intent);
        });
    }
}
