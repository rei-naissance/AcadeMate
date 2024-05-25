package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.HashMap;
import java.util.Map;

public class Forms extends AppCompatActivity {

    private EditText inputDeviceBorrower;
    private EditText inputNameAndAddress;
    private EditText inputDateOfDeviceBorrowal;
    private EditText inputBorrowingNumber;
    private EditText inputBorrowerName;
    private EditText inputDateBorrowerRequested;
    private ImageButton btnReturn;
    private ImageButton btnSend;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_forms);

        // Initialize Firebase Firestore
        db = FirebaseFirestore.getInstance();

        // Initialize Views
        inputDeviceBorrower = findViewById(R.id.inputDeviceBorrower);
        inputNameAndAddress = findViewById(R.id.inputNameAndAddress);
        inputDateOfDeviceBorrowal = findViewById(R.id.inputDateOfDeviceBorrowal);
        inputBorrowingNumber = findViewById(R.id.inputBorrowingNumber);
        inputBorrowerName = findViewById(R.id.inputBorrowerName);
        inputDateBorrowerRequested = findViewById(R.id.inputDateBorrowerRequested);
        btnReturn = findViewById(R.id.btnReturn);
        btnSend = findViewById(R.id.btnSend);

        // Set OnClickListener for btnReturn
        btnReturn.setOnClickListener(v -> {
            finish(); // Navigate back to the previous activity
        });

        // Set OnClickListener for btnSend
        btnSend.setOnClickListener(v -> saveFormData());
    }

    private void saveFormData() {
        // Collect data from input fields
        String deviceBorrower = inputDeviceBorrower.getText().toString().trim();
        String nameAndAddress = inputNameAndAddress.getText().toString().trim();
        String dateOfDeviceBorrowal = inputDateOfDeviceBorrowal.getText().toString().trim();
        String borrowingNumber = inputBorrowingNumber.getText().toString().trim();
        String borrowerName = inputBorrowerName.getText().toString().trim();
        String dateBorrowerRequested = inputDateBorrowerRequested.getText().toString().trim();

        if (deviceBorrower.isEmpty() || nameAndAddress.isEmpty() || dateOfDeviceBorrowal.isEmpty() ||
                borrowingNumber.isEmpty() || borrowerName.isEmpty() || dateBorrowerRequested.isEmpty()) {
            Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create a map to store the data
        Map<String, Object> formData = new HashMap<>();
        formData.put("deviceBorrower", deviceBorrower);
        formData.put("nameAndAddress", nameAndAddress);
        formData.put("dateOfDeviceBorrowal", dateOfDeviceBorrowal);
        formData.put("borrowingNumber", borrowingNumber);
        formData.put("borrowerName", borrowerName);
        formData.put("dateBorrowerRequested", dateBorrowerRequested);

        // Save data to Firestore
        db.collection("forms")
                .add(formData)
                .addOnSuccessListener(documentReference -> Toast.makeText(Forms.this, "Form submitted successfully", Toast.LENGTH_SHORT).show())
                .addOnFailureListener(e -> Toast.makeText(Forms.this, "Error submitting form: " + e.getMessage(), Toast.LENGTH_SHORT).show());
    }
}

//import androidx.appcompat.app.AppCompatActivity;
//
//import android.os.Bundle;
//
//public class Forms extends AppCompatActivity {
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.academate_forms);
//    }
//}
