package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Forms2 extends AppCompatActivity {
    TextView iItemBorrower, iCampusAndAddress, iDateItemBorrowal, iBorrowingNumber, iBorrower, iDateBorrowed;
    ImageButton btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_forms2);

        //Initialize TextViews
        iItemBorrower = findViewById(R.id.outputItemBorrower);
        iCampusAndAddress = findViewById(R.id.outputCampusAndAddress);
        iDateItemBorrowal = findViewById(R.id.outputDateOfItemBorrowal);
        iBorrowingNumber = findViewById(R.id.outputBorrowingNumber);
        iBorrower = findViewById(R.id.outputBorrowerName);
        iDateBorrowed = findViewById(R.id.outputDateBorrowerRequested);

        //Get data from intent
        Intent intent = getIntent();
        String itemBorrower = intent.getStringExtra("itemBorrower_key");
        String campusAndAddress = intent.getStringExtra("campusAndAddress_key");
        String dateItemBorrowal = intent.getStringExtra("dateItemBorrowal_key");
        String borrowingNumber = intent.getStringExtra("borrowingNumber_key");
        String borrower = intent.getStringExtra("borrower_key");
        String dateBorrowed = intent.getStringExtra("dateBorrowed_key");

        // Set text to TextViews
        iItemBorrower.setText(itemBorrower);
        iCampusAndAddress.setText(campusAndAddress);
        iDateItemBorrowal.setText(dateItemBorrowal);
        iBorrowingNumber.setText(borrowingNumber);
        iBorrower.setText(borrower);
        iDateBorrowed.setText(dateBorrowed);

        btnReturn = (ImageButton) findViewById(R.id.btnReturn2);
        btnReturn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               finish();
           }
        });
    }
}