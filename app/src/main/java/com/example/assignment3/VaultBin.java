package com.example.assignment3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class VaultBin extends AppCompatActivity {

    ImageButton btnBack;
    TextView tvNoItems;
    ListView lvDeletedList;
    DeletedItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vault_bin);
        init();

        btnBack.setOnClickListener(view -> {
            Intent intent = new Intent(VaultBin.this, Home.class);
            startActivity(intent);
            finish();
        });

        loadDataFromBin();
    }
    // Method to load data from the bin
    private void loadDataFromBin() {
        ItemsDB itemsDB = new ItemsDB(this);
        itemsDB.open();
        ArrayList<DataItem> deletedItems = itemsDB.getDeletedItems();
        itemsDB.close();

        if (deletedItems != null && !deletedItems.isEmpty()) {
            tvNoItems.setVisibility(View.GONE);

            adapter = new DeletedItemAdapter(this, deletedItems);
            lvDeletedList.setAdapter(adapter);
        } else {
            // No items in the bin, show the TextView
            tvNoItems.setVisibility(View.VISIBLE);
        }
    }

    private void init()
    {
        btnBack = findViewById(R.id.btnBack);
        tvNoItems = findViewById(R.id.tvNoItems);
        lvDeletedList = findViewById(R.id.lvDeletedList);
    }
}