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

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VaultLogins extends AppCompatActivity {

    ImageButton btnBack;
    FloatingActionButton fabAdd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault_logins);
        init();

        SharedPreferences sPref = getSharedPreferences("Login", MODE_PRIVATE);
        sPref.getBoolean("isLogin", true);

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(VaultLogins.this, Home.class);
            startActivity(intent);
            finish();
        });


        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(VaultLogins.this, AddInfo.class);
            startActivity(intent);
        });
    }
    private void init()
    {
        btnBack = findViewById(R.id.btnBack);
        fabAdd = findViewById(R.id.fabAdd);
    }
}