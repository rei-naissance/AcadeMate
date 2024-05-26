package com.example.academate;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class BorrowHistory extends AppCompatActivity {
    Button btnBack;
    RecyclerView recyclerView;
    HistoryAdapter adapter;
    ArrayList<Item> itemList = new ArrayList<>();

    // Firestore and Auth references
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_borrow_history);

        recyclerView = findViewById(R.id.borrowRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new HistoryAdapter(this, itemList);
        recyclerView.setAdapter(adapter);

        String currentUserEmail = mAuth.getCurrentUser().getEmail();

        db.collection("forms")
                .whereEqualTo("email", currentUserEmail)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            Item item = document.toObject(Item.class);
                            itemList.add(item);
                        }
                        adapter.notifyDataSetChanged();
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });

        btnBack = findViewById(R.id.btnBackProfile);
        btnBack.setOnClickListener(v -> finish());
    }
}
