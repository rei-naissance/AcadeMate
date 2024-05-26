package com.example.academate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.academate.databinding.AcademateHomeBinding;
import com.example.academate.databinding.AcademateMainBinding;

public class Home extends Fragment {

    ImageButton btnLaptop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.academate_home, container, false);
        btnLaptop = (ImageButton) view.findViewById(R.id.btnLaptop);

        btnLaptop.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ItemDetails.class);
            startActivity(intent);
        });
        return view;


    }

}
