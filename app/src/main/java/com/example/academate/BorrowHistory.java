package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class BorrowHistory extends AppCompatActivity {
    Button btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_borrow_history);

        btnBack = findViewById(R.id.btnBackProfile);
        btnBack.setOnClickListener(v -> {
            Intent intent = new Intent(BorrowHistory.this, Profile.class);
            startActivity(intent);
        });
    }
}