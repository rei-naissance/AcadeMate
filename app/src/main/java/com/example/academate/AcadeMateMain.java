package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.academate.databinding.AcademateMainBinding;

public class AcadeMateMain extends AppCompatActivity {
    AcademateMainBinding binding;
    ImageButton btnSplash;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.academate_main);


        btnSplash = findViewById(R.id.btnLogo);
        btnSplash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AcadeMateMain.this, Signup.class);
                startActivity(intent);
            }
        });
    }
}