package com.example.assignment3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Objects;

public class Home extends AppCompatActivity {

    LinearLayout llLogin, llBin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();

        SharedPreferences sPref = getSharedPreferences("Login", MODE_PRIVATE);
        sPref.getBoolean("isLogin", true);

        llLogin.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, VaultLogins.class);
            startActivity(intent);
            finish();
        });

        llBin.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, VaultBin.class);
            startActivity(intent);
            finish();
        });
    }
    private void init()
    {
        llLogin = findViewById(R.id.llLogin);
        llBin = findViewById(R.id.llBin);
    }
}