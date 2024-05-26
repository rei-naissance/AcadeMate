package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Forms2 extends AppCompatActivity {
    TextView iDeviceBorrower, iCampusAndAddress, iDateDeviceBorrowal, iBorrowingNumber, iBorrower, iDateBorrowed;
    ImageButton btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_forms2);

        //Initialize TextViews
        iDeviceBorrower = findViewById(R.id.outputDeviceBorrower);
        iCampusAndAddress = findViewById(R.id.outputCampusAndAddress);
        iDateDeviceBorrowal = findViewById(R.id.outputDateOfDeviceBorrowal);
        iBorrowingNumber = findViewById(R.id.outputBorrowingNumber);
        iBorrower = findViewById(R.id.outputBorrowerName);
        iDateBorrowed = findViewById(R.id.outputDateBorrowerRequested);

        //Get data from intent
        Intent intent = getIntent();
        String deviceBorrower = intent.getStringExtra("deviceBorrower_key");
        String campusAndAddress = intent.getStringExtra("campusAndAddress_key");
        String dateDeviceBorrowal = intent.getStringExtra("dateDeviceBorrowal_key");
        String borrowingNumber = intent.getStringExtra("borrowingNumber_key");
        String borrower = intent.getStringExtra("borrower_key");
        String dateBorrowed = intent.getStringExtra("dateBorrowed_key");

        // Set text to TextViews
        iDeviceBorrower.setText(deviceBorrower);
        iCampusAndAddress.setText(campusAndAddress);
        iDateDeviceBorrowal.setText(dateDeviceBorrowal);
        iBorrowingNumber.setText(borrowingNumber);
        iBorrower.setText(borrower);
        iDateBorrowed.setText(dateBorrowed);

        btnReturn = (ImageButton) findViewById(R.id.btnReturn2);
        btnReturn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent int1 = new Intent(Forms2.this, Profile.class);
               startActivity(int1);
           }
        });
    }
}