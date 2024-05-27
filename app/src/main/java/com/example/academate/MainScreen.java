package com.example.academate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;

import com.example.academate.databinding.AcademateHomeBinding;
import com.example.academate.databinding.AcademateMainScreenBinding;

public class MainScreen extends AppCompatActivity {
    AcademateMainScreenBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = AcademateMainScreenBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);
//        replaceFragment(new Home());

        if (getIntent().getBooleanExtra("GotoHomeFragment", false)) {
            replaceFragment(new Home());
            selectHomeNavigationItem();
        } else {
            replaceFragment(new Home());
        }

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {

            int itemId = item.getItemId();
            if (itemId == R.id.home) {
                replaceFragment(new Home());
            } else if (itemId == R.id.add) {
                replaceFragment(new Add());
            } else if (itemId == R.id.profile) {
                replaceFragment(new Profile());
            }
            return true;
        });
    }

    public void selectHomeNavigationItem() {
        binding.bottomNavigationView.setSelectedItemId(R.id.home);
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frameLayout,fragment);
        fragmentTransaction.commit();
    }
}