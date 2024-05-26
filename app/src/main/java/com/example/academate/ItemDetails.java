package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ItemDetails extends AppCompatActivity {
    Button borrowItem;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_itemdetails);

        borrowItem = findViewById(R.id.btnBorrowItem);

        String username = getIntent().getStringExtra("username");
        String itemName = getIntent().getStringExtra("itemname");
        String itemDescription = getIntent().getStringExtra("itemdescription");

        TextView usernameTextView = findViewById(R.id.detailOwner);
        TextView itemNameTextView = findViewById(R.id.detailTitle);
        TextView itemDescriptionTextView = findViewById(R.id.detailDescription);

        usernameTextView.setText(username);
        itemNameTextView.setText(itemName);
        itemDescriptionTextView.setText(itemDescription);

        borrowItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ItemDetails.this, Forms.class);
                startActivity(intent);
            }
        });
    }
}