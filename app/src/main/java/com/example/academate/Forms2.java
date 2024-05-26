package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import android.widget.EditText;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Forms2 extends AppCompatActivity {
    TextView iDeviceBorrower, iNameAndAddress, iDateDeviceBorrowal, iBorrowingNumber, iBorrower, iDateBorrowed;
    ImageButton btnReturn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_forms2);

        //Initialize TextViews
        iDeviceBorrower = findViewById(R.id.outputDeviceBorrower);
        iNameAndAddress = findViewById(R.id.outputNameAndAddress);
        iDateDeviceBorrowal = findViewById(R.id.outputDateOfDeviceBorrowal);
        iBorrowingNumber = findViewById(R.id.outputBorrowingNumber);
        iBorrower = findViewById(R.id.outputBorrowerName);
        iDateBorrowed = findViewById(R.id.outputDateBorrowerRequested);

        //Get data from intent
        Intent intent = getIntent();
        String deviceBorrower = intent.getStringExtra("deviceBorrower_key");
        String nameAndAddress = intent.getStringExtra("nameAndAddress_key");
        String dateDeviceBorrowal = intent.getStringExtra("dateDeviceBorrowal_key");
        String borrowingNumber = intent.getStringExtra("borrowingNumber_key");
        String borrower = intent.getStringExtra("borrower_key");
        String dateBorrowed = intent.getStringExtra("dateBorrowed_key");

        // Set text to TextViews
        iDeviceBorrower.setText(deviceBorrower);
        iNameAndAddress.setText(nameAndAddress);
        iDateDeviceBorrowal.setText(dateDeviceBorrowal);
        iBorrowingNumber.setText(borrowingNumber);
        iBorrower.setText(borrower);
        iDateBorrowed.setText(dateBorrowed);

        btnReturn = (ImageButton) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent int1 = new Intent(Forms2.this, Home.class);
               startActivity(int1);
           }
        });
    }
}