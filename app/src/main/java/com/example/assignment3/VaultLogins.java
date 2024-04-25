package com.example.assignment3;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.content.Context;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Objects;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class VaultLogins extends AppCompatActivity {

    ImageButton btnBack;
    FloatingActionButton fabAdd;
    ListView listView;
    ArrayAdapter<DataItem> adapter;
    ItemsDB itemsDB;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault_logins);
        init();

        itemsDB.open();

        ArrayList<DataItem> items = itemsDB.readAllItems();
        adapter = new ArrayAdapter<>(this, R.layout.data_item, items);
        listView.setAdapter(adapter);

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
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection when the activity is destroyed
        itemsDB.close();
    }
    private void init()
    {
        btnBack = findViewById(R.id.btnBack);
        fabAdd = findViewById(R.id.fabAdd);
        itemsDB = new ItemsDB(this);
        listView = findViewById(R.id.listView);
    }
}