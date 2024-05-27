package com.example.academate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Add extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.academate_add, container, false);

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();

        Button addItemButton = view.findViewById(R.id.btnAddItem);

        addItemButton.setOnClickListener(v -> {
            if (user != null) {
                db.collection("users").document(user.getUid()).get().addOnSuccessListener(documentSnapshot -> {
                    if (documentSnapshot.exists()) {
                        String username = (String) documentSnapshot.get("username");

                        EditText itemName = view.findViewById(R.id.itemNameField);
                        EditText itemDescription = view.findViewById(R.id.itemDetailsField);

                        String itemNameString = itemName.getText().toString();
                        String itemDescString = itemDescription.getText().toString();

                        Map<String, Object> itemData = new HashMap<>();
                        itemData.put("itemName", itemNameString);
                        itemData.put("itemDescription", itemDescString);
                        itemData.put("username", username);
                        itemData.put("email", Objects.requireNonNull(mAuth.getCurrentUser()).getEmail());

                        db.collection("items").add(itemData).addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                Toast.makeText(getActivity(), "Item added successfully.", Toast.LENGTH_SHORT).show();

                                FragmentManager fragmentManager = getFragmentManager();
                                if (fragmentManager != null) {
                                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
                                    fragmentTransaction.replace(R.id.addLayout, new Home()); //Assuming 'container' is your FrameLayout id.
                                    fragmentTransaction.commit();

                                    ((MainScreen) requireActivity()).selectHomeNavigationItem();
                                }
                            } else {
                                Toast.makeText(getActivity(), "Failed to add item.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        Toast.makeText(getActivity(), "Failed to fetch user.", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(e -> {
                    Toast.makeText(getActivity(), "Failed to fetch user.", Toast.LENGTH_SHORT).show();
                });
            } else {
                Toast.makeText(getActivity(), "No user signed in.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

}