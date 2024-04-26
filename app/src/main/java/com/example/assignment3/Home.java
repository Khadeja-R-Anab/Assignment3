package com.example.assignment3;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.activity.OnBackPressedCallback;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    LinearLayout llLogin, llBin;
    FloatingActionButton fabAdd;
    TextView tvItemCount, tvBinCount, tvNoFolderCount, tvUserName;
    ArrayList<DataItem> dataItems, deletedItems;
    int userId; // Add user ID variable
    private boolean isAppClosed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        init();
        loadData();

        SharedPreferences sPref = getSharedPreferences("Login", MODE_PRIVATE);
        userId = sPref.getInt("userId", -1); // Assuming user ID is stored as an integer

        String username = sPref.getString("username", null);
        if (username != null) {
            String firstLetter = username.substring(0, 1);
            tvUserName.setText(firstLetter.toUpperCase());
        }

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

        fabAdd.setOnClickListener(view -> {
            Intent intent = new Intent(Home.this, AddInfo.class);
            startActivity(intent);
        });

        tvUserName.setOnClickListener(view ->{
            SharedPreferences.Editor editor = sPref.edit();
            editor.putBoolean("isLoggedIn", false);
            editor.apply();

            Intent intent = new Intent(Home.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        getOnBackPressedDispatcher().addCallback(this, new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                showExitConfirmationDialog();
            }
        });
    }

    private void showExitConfirmationDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit App");
        builder.setMessage("Are you sure you want to exit?");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            isAppClosed = true; // Mark the app as closed
            finish();
        });
        builder.setNegativeButton("No", (dialog, which) -> {
            // Do nothing, dismiss the dialog
        });
        builder.create().show();
    }

    @Override
    protected void onStop() {
        super.onStop();

        // Check if the app is going into the background
        if (!isChangingConfigurations() && !isFinishing()) {
            isAppClosed = true; // Mark the app as closed
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // Check if the app is closed
        if (isAppClosed) {
            // Delete shared preferences when the app is closed
            SharedPreferences.Editor editor = getSharedPreferences("Login", MODE_PRIVATE).edit();
            editor.clear(); // Clear all the shared preferences data
            editor.apply();
        }
    }

    protected void onResume() {
        super.onResume();
        loadData(); // Call your method to reload data here
    }
    private void init()
    {
        dataItems = new ArrayList<>();
        deletedItems = new ArrayList<>();
        llLogin = findViewById(R.id.llLogin);
        llBin = findViewById(R.id.llBin);
        fabAdd = findViewById(R.id.fabAdd);
        tvItemCount = findViewById(R.id.tvItemCount);
        tvBinCount = findViewById(R.id.tvBinCount);
        tvNoFolderCount = findViewById(R.id.tvNoFolderCount);
        tvUserName = findViewById(R.id.tvUserName);
    }
    @SuppressLint("SetTextI18n")
    private void loadData() {
        ItemsDB itemsDB = new ItemsDB(this);
        itemsDB.open();
        dataItems.clear();
        deletedItems.clear();
        dataItems.addAll(itemsDB.readAllItems(userId));
        deletedItems.addAll(itemsDB.getDeletedItems(userId));
        itemsDB.close();

        tvItemCount.setText(dataItems.size()+"");
        tvNoFolderCount.setText(dataItems.size()+"");
        tvBinCount.setText(deletedItems.size()+"");
    }
}