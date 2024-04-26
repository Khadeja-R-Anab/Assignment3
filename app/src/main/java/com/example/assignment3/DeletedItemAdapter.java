package com.example.assignment3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DeletedItemAdapter extends ArrayAdapter<DataItem> {

    Context context;
    public DeletedItemAdapter (@NonNull Context context, @NonNull ArrayList<DataItem> objects) {
        super(context, 0, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;
        if(v == null)
        {
            v = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.data_item, parent, false);
        }

        TextView tvUsername, tvPassword, tvUrl;

        tvUsername = v.findViewById(R.id.tvUsername);
        tvPassword = v.findViewById(R.id.tvPassword);
        tvUrl = v.findViewById(R.id.tvUrl);

        DataItem i = getItem(position);
        assert i != null;
        tvUsername.setText(i.getUsername());
        tvPassword.setText(i.getPassword());
        tvUrl.setText(i.getUrl());

        return v;
    }

    @Nullable
    @Override
    public DataItem getItem(int position) {
        return super.getItem(position);
    }
}