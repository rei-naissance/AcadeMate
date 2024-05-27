package com.example.academate;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Objects;

public class UserItems extends AppCompatActivity {
    Button btnBack;
    RecyclerView recyclerView;
    OwnedAdapter adapter;
    ArrayList<Item> itemList = new ArrayList<>();
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    FirebaseAuth mAuth = FirebaseAuth.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_user_items);

        btnBack = findViewById(R.id.btnBack);
        recyclerView = findViewById(R.id.ownedRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        String currentUserEmail = Objects.requireNonNull(mAuth.getCurrentUser()).getEmail();

        db.collection("items")
                .whereEqualTo("email", currentUserEmail)
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : Objects.requireNonNull(task.getResult())) {
                            Item item = document.toObject(Item.class);
                            itemList.add(item);
                        }
                        adapter = new OwnedAdapter(UserItems.this, itemList);
                        recyclerView.setAdapter(adapter);
                    } else {
                        Log.d(TAG, "Error getting documents: ", task.getException());
                    }
                });

       btnBack.setOnClickListener(v -> finish());
    }
}
