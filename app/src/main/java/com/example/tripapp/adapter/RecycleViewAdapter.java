package com.example.tripapp.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripapp.R;
import com.example.tripapp.model.Item;

import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Item> list;
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView id, name, destination, date, risk;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            id = view.findViewById(R.id.trip_id_txt);
            name = view.findViewById(R.id.trip_name_txt);
            destination = view.findViewById(R.id.trip_destination_txt);
            date = view.findViewById(R.id.trip_date_txt);
            risk = view.findViewById(R.id.trip_risk_txt);
        }
    }
}

