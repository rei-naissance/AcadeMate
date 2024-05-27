package com.example.academate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class Profile extends Fragment {

    TextView borrowhistory, owned;
    Button logout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.academate_profile, container, false);
        borrowhistory = view.findViewById(R.id.borrowHistoryText);
        logout = view.findViewById(R.id.btnLogout);
        owned = view.findViewById(R.id.textOwnedItems);

        borrowhistory.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), BorrowHistory.class);
            startActivity(intent);
        });

        owned.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), UserItems.class);
            startActivity(intent);
        });

        logout.setOnClickListener(v -> {
            FirebaseAuth.getInstance().signOut();
            Intent intent = new Intent(getContext(), Login.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
            requireActivity().finish();
        });
        return view;
    }
}