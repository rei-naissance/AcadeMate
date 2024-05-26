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
import android.widget.SearchView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class Home extends Fragment {
    private RecyclerView recyclerView;
    private SearchView search;
    private ArrayList<Item> itemList = new ArrayList<>();
    private ArrayList<Item> filteredList = new ArrayList<>();
    private FirebaseFirestore db;
    private ItemsAdapter itemsAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.academate_home, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState){
        search = view.findViewById(R.id.itemSearch);
        recyclerView = view.findViewById(R.id.itemsRecycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));

        itemsAdapter = new ItemsAdapter(requireContext(), filteredList);
        recyclerView.setAdapter(itemsAdapter);

        db = FirebaseFirestore.getInstance();

        db.collection("items").get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                for(QueryDocumentSnapshot document : task.getResult()) {
                    Item item = document.toObject(Item.class);
                    itemList.add(item);
                    filteredList.add(item);
                }
                itemsAdapter.setListener(item -> {
                    Intent detailIntent = new Intent(requireContext(), ItemDetails.class);
                    detailIntent.putExtra("username", item.getUsername());
                    detailIntent.putExtra("itemname", item.getItemName());
                    detailIntent.putExtra("itemdescription", item.getItemDescription());
                    startActivity(detailIntent);
                });
                itemsAdapter.notifyDataSetChanged();
            } else {
                Log.d(TAG, "Error getting documents: ", task.getException());
            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                filteredList.clear();
                if(newText.isEmpty()) {
                    filteredList.addAll(itemList);
                } else {
                    for(Item item : itemList) {
                        if(item.getItemName().toLowerCase().contains(newText.toLowerCase())) {
                            filteredList.add(item);
                        }
                    }
                }
                itemsAdapter.notifyDataSetChanged();
                return true;
            }
        });
    }
}

