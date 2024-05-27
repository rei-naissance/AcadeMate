package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Forms extends AppCompatActivity {

    EditText eItemBorrower, eCampusAndAddress, eDateDeviceBorrowal, eBorrowingNumber, eBorrower, eDateBorrowed;
    ImageButton btnReturn;
    FirebaseFirestore db;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_forms);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();
        mAuth = FirebaseAuth.getInstance();

        //Initialize UI elements
        eItemBorrower = findViewById(R.id.inputItemBorrower);
        eCampusAndAddress = findViewById(R.id.inputCampusAndAddress);
        eDateDeviceBorrowal = findViewById(R.id.inputDateOfItemBorrowal);
        eBorrowingNumber = findViewById(R.id.inputBorrowingNumber);
        eBorrower = findViewById(R.id.inputBorrowerName);
        eDateBorrowed = findViewById(R.id.inputDateBorrowerRequested);

        btnReturn = findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(view -> finish());
    }

    public void onSubmitClicked(View v) {
        new AlertDialog.Builder(this)
                .setTitle("Submit Form")
                .setMessage("Are you sure you want to submit the form?")
                .setPositiveButton("Yes", (dialog, which) -> submitForm())
                .setNegativeButton("No", null)
                .show();
    }

    private void submitForm() {
        if (eItemBorrower.getText().toString().isEmpty() ||
                eCampusAndAddress.getText().toString().isEmpty() ||
                eDateDeviceBorrowal.getText().toString().isEmpty() ||
                eBorrowingNumber.getText().toString().isEmpty() ||
                eBorrower.getText().toString().isEmpty() ||
                eDateBorrowed.getText().toString().isEmpty()) {

            // Notify the user to fill in all fields
            Toast.makeText(Forms.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String itemBorrower = eItemBorrower.getText().toString();
        String campusAndAddress = eCampusAndAddress.getText().toString();
        String dateDeviceBorrowal = eDateDeviceBorrowal.getText().toString();
        String borrowingNumber = eBorrowingNumber.getText().toString();
        String borrower = eBorrower.getText().toString();
        String dateBorrowed = eDateBorrowed.getText().toString();
        String username = getIntent().getStringExtra("username");
        String itemName = getIntent().getStringExtra("itemname");
        String itemDescription = getIntent().getStringExtra("itemdescription");

        Map<String, Object> formData = new HashMap<>();
        formData.put("itemBorrower", itemBorrower);
        formData.put("campusAndAddress", campusAndAddress);
        formData.put("dateItemBorrowal", dateDeviceBorrowal);
        formData.put("borrowingNumber", borrowingNumber);
        formData.put("username", username);
        formData.put("itemName", itemName);
        formData.put("itemDescription", itemDescription);
        formData.put("email", Objects.requireNonNull(mAuth.getCurrentUser()).getEmail());

        db.collection("forms")
                .add(formData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(Forms.this, "Form submitted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Forms.this, Forms2.class);
                    intent.putExtra("itemBorrower_key", itemBorrower);
                    intent.putExtra("campusAndAddress_key", campusAndAddress);
                    intent.putExtra("dateDeviceBorrowal_key", dateDeviceBorrowal);
                    intent.putExtra("borrowingNumber_key", borrowingNumber);
                    intent.putExtra("borrower_key", borrower);
                    intent.putExtra("dateBorrowed_key", dateBorrowed);
                    startActivity(intent);
                })
                .addOnFailureListener(e -> {
                    Toast.makeText(Forms.this, "Error submitting form", Toast.LENGTH_SHORT).show();
                });
    }

//        startActivity(intent);
}


