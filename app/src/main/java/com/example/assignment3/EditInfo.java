package com.example.assignment3;

import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditInfo extends AppCompatActivity {

    EditText etNewUsername, etNewPassword, etNewUrl;
    TextView btnDelete, btnSave;
    int itemId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_info);

        init();

        itemId = getIntent().getIntExtra("itemID", -1);
        Toast.makeText(this, itemId+"", Toast.LENGTH_SHORT).show();
        if (itemId == -1) {
            // Handle error
            finish();
        } else {
            loadItemData();
        }

        btnDelete.setOnClickListener(view -> {
            deleteItem();
        });

        btnSave.setOnClickListener(view -> {
            updateItem();
        });
    }


    private void loadItemData() {
        ItemsDB itemsDB = new ItemsDB(this);
        itemsDB.open();
        DataItem item = itemsDB.getItemById(itemId);
        itemsDB.close();

        if (item != null) {
            etNewUsername.setText(item.getUsername());
            etNewPassword.setText(item.getPassword());
            etNewUrl.setText(item.getUrl());
        } else {
            // Handle error
            finish();
        }
    }

    private void deleteItem() {
        AlertDialog.Builder deleteDialog = new AlertDialog.Builder(this);
        deleteDialog.setTitle("Confirmation");
        deleteDialog.setMessage("Do you really want to delete this item?");
        deleteDialog.setPositiveButton("Confirm", (dialog, which) -> {
            ItemsDB itemsDB = new ItemsDB(this);
            itemsDB.open();
            itemsDB.deleteItem(itemId);
            itemsDB.close();
            Toast.makeText(this, "Item deleted", Toast.LENGTH_SHORT).show();
            finish();
        });
        deleteDialog.setNegativeButton("Cancel", (dialog, which) -> {
            // Do nothing
        });

        deleteDialog.show();
    }

    private void updateItem() {
        String newUsername = etNewUsername.getText().toString().trim();
        String newPassword = etNewPassword.getText().toString().trim();
        String newUrl = etNewUrl.getText().toString().trim();

        if (newUsername.isEmpty() || newPassword.isEmpty() || newUrl.isEmpty()) {
            Toast.makeText(this, "All fields are required", Toast.LENGTH_SHORT).show();
            return;
        }

        ItemsDB itemsDB = new ItemsDB(this);
        itemsDB.open();
        itemsDB.updateItem(itemId, newUsername, newPassword, newUrl);
        itemsDB.close();
        Toast.makeText(this, "Item updated", Toast.LENGTH_SHORT).show();
        finish();
    }


    private void init(){
        etNewUsername = findViewById(R.id.etNewUsername);
        etNewPassword = findViewById(R.id.etNewPassword);
        etNewUrl = findViewById(R.id.etNewUrl);
        btnDelete = findViewById(R.id.btnDelete);
        btnSave = findViewById(R.id.btnSave);
    }
}
