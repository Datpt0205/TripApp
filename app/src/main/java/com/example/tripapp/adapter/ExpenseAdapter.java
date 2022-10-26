package com.example.tripapp.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripapp.R;

import java.util.ArrayList;

public class ExpenseAdapter extends RecyclerView.Adapter<ExpenseAdapter.MyViewHolder>{
    private Context context;
    private ArrayList id,name,date,amount,trip_id;
    public ExpenseAdapter(
            Context context,
            ArrayList id,
            ArrayList name,
            ArrayList amount,
            ArrayList date,
            ArrayList trip_id){

        this.context = context;
        this.id = id;
        this.name = name;
        this.date = date;
        this.amount = amount;
        this.trip_id = trip_id;
    }

    @NonNull
    @Override
    public ExpenseAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.expense, parent,false);
        return new ExpenseAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExpenseAdapter.MyViewHolder holder, int position) {
        holder.expense_type.setText(String.valueOf(name.get(position)));
        holder.expense_date.setText(String.valueOf(date.get(position)));
        holder.expense_amount.setText(String.valueOf(amount.get(position)));

    }

    @Override
    public int getItemCount() {
        return id.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView expense_type, expense_date, expense_amount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            expense_type = itemView.findViewById(R.id.expense_type);
            expense_date = itemView.findViewById(R.id.expense_date);
            expense_amount = itemView.findViewById(R.id.expense_amount);
        }
    }
}
