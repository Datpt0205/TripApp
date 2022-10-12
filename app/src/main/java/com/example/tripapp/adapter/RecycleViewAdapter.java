package com.example.tripapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripapp.R;
import com.example.tripapp.model.Item;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.HomeViewHolder> {
    private List<Item> list;
    private ItemListener itemListener;

    public RecycleViewAdapter(){
        list = new ArrayList<>();
    }

    public void setItemListener(ItemListener itemListener){
        this.itemListener = itemListener;
    }

    public void setList(List<Item>list){
        this.list = list;
        notifyDataSetChanged();
    }

    public Item getItem(int position){
        return list.get(position);
    }
    @NonNull
    @Override
    public HomeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HomeViewHolder holder, int position) {
        Item item = list.get(position);
        holder.name.setText(item.getName());
        holder.destination.setText(item.getDestination());
        holder.date.setText(item.getDate());
        holder.risk.setText(item.getRisk());
        holder.description.setText(item.getDescription());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView name, destination, date, risk, description;
        public HomeViewHolder(@NonNull View view) {
            super(view);
            name = view.findViewById(R.id.trip_name_txt);
            destination = view.findViewById(R.id.trip_destination_txt);
            date = view.findViewById(R.id.trip_date_txt);
            risk = view.findViewById(R.id.trip_risk_txt);
            description = view.findViewById(R.id.trip_description_txt);
            view.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if(itemListener != null){
                itemListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemListener{
        void onItemClick(View view, int position);
    }
}

