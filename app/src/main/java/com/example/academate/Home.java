package com.example.academate;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Home extends Fragment {
    private RecyclerView recyclerView;
    private ArrayList<Item> itemList = new ArrayList<>();
    private FirebaseFirestore db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.academate_home, container, false);
        recyclerView = view.findViewById(R.id.itemsRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        db = FirebaseFirestore.getInstance();

        db.collection("items").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for(QueryDocumentSnapshot document : task.getResult()) {
                    Item item = document.toObject(Item.class);
                    itemList.add(item);
                }
                ItemsAdapter itemsAdapter = new ItemsAdapter(getContext(), itemList);
                itemsAdapter.setListener(item -> {
                    Intent detailIntent = new Intent(getContext(), ItemDetails.class);
                    detailIntent.putExtra("username", item.getUsername());
                    detailIntent.putExtra("itemname", item.getItemName());
                    detailIntent.putExtra("itemdescription", item.getItemDescription());
                    startActivity(detailIntent);
                });
                recyclerView.setAdapter(itemsAdapter);
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });

        return view;
    }
}
