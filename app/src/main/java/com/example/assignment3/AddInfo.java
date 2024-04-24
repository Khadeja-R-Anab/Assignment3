package com.example.assignment3;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddInfo extends AppCompatActivity {

    TextView tvAdd, tvCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_info);
        init ();

        tvCancel.setOnClickListener(view -> {
            finish();
        });

        tvAdd.setOnClickListener(view-> {
            finish();
        });
    }

    private void init(){
        tvAdd = findViewById(R.id.tvAdd);
        tvCancel = findViewById(R.id.tvCancel);
    }
}