package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Forms extends AppCompatActivity {

    EditText eDeviceBorrower, eCampusAndAddress, eDateDeviceBorrowal, eBorrowingNumber, eBorrower, eDateBorrowed;
    ImageButton btnReturn;
    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_forms);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        //Initialize UI elements
        eDeviceBorrower = findViewById(R.id.inputDeviceBorrower);
        eCampusAndAddress = findViewById(R.id.inputCampusAndAddress);
        eDateDeviceBorrowal = findViewById(R.id.inputDateOfDeviceBorrowal);
        eBorrowingNumber = findViewById(R.id.inputBorrowingNumber);
        eBorrower = findViewById(R.id.inputBorrowerName);
        eDateBorrowed = findViewById(R.id.inputDateBorrowerRequested);
    }

    public void onSubmitClicked(View v) {
        if (eDeviceBorrower.getText().toString().isEmpty() ||
                eCampusAndAddress.getText().toString().isEmpty() ||
                eDateDeviceBorrowal.getText().toString().isEmpty() ||
                eBorrowingNumber.getText().toString().isEmpty() ||
                eBorrower.getText().toString().isEmpty() ||
                eDateBorrowed.getText().toString().isEmpty()) {

            // Notify the user to fill in all fields
            Toast.makeText(Forms.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        String deviceBorrower = eDeviceBorrower.getText().toString();
        String campusAndAddress = eCampusAndAddress.getText().toString();
        String dateDeviceBorrowal = eDateDeviceBorrowal.getText().toString();
        String borrowingNumber = eBorrowingNumber.getText().toString();
        String borrower = eBorrower.getText().toString();
        String dateBorrowed = eDateBorrowed.getText().toString();

        // Save data to Firestore
        Map<String, Object> formData = new HashMap<>();
        formData.put("deviceBorrower", deviceBorrower);
        formData.put("campusAndAddress", campusAndAddress);
        formData.put("dateDeviceBorrowal", dateDeviceBorrowal);
        formData.put("borrowingNumber", borrowingNumber);

        db.collection("forms")
                .add(formData)
                .addOnSuccessListener(documentReference -> {
                    Toast.makeText(Forms.this, "Form submitted successfully", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Forms.this, Forms2.class);
                    intent.putExtra("deviceBorrower_key", deviceBorrower);
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

        btnReturn = (ImageButton) findViewById(R.id.btnReturn);
        btnReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent int1 = new Intent(Forms.this, Profile.class);
                startActivity(int1);
            }
        });

        //For inputBorrowingNumber: limit characters to 10 only!
        eBorrowingNumber = findViewById(R.id.inputBorrowingNumber);
        eBorrowingNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 10) {
                    // inputBorrowingNumber 10 characters only!
                    eBorrowingNumber.setText(s.subSequence(0, 10));
                    // Set the cursor position to the end
                    eBorrowingNumber.setSelection(10);
                }
            }
        });

//        startActivity(intent);
    }
}
