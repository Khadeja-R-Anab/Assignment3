package com.example.assignment3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class VaultBin extends AppCompatActivity {

    ImageButton btnBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault_bin);
        init();

        SharedPreferences sPref = getSharedPreferences("Login", MODE_PRIVATE);
        sPref.getBoolean("isLogin", true);

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(VaultBin.this, Home.class);
            startActivity(intent);
            finish();
        });
    }
    private void init()
    {
        btnBack = findViewById(R.id.btnBack);
    }
}