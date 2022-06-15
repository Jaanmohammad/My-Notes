package com.jksurajpuriya.notes.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.jksurajpuriya.notes.databinding.ActivityAddDataBinding;
import com.jksurajpuriya.notes.databinding.ActivityAllDataShowBinding;

public class AllDataShowActivity extends AppCompatActivity {
    ActivityAllDataShowBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAllDataShowBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.showTitle.setText(getIntent().getStringExtra("title"));
        binding.showDescription.setText(getIntent().getStringExtra("description"));


    }

}