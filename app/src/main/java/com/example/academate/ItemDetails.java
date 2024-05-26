package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class  ItemDetails extends AppCompatActivity {
    Button borrowItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_itemdetails);

        borrowItem = findViewById(R.id.btnBorrowItem);

        borrowItem.setOnClickListener(v -> {
            Intent intent = new Intent(ItemDetails.this, Forms.class);
            startActivity(intent);
        });
    }
}