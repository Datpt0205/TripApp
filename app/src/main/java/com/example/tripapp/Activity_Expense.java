package com.example.tripapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.tripapp.adapter.ExpenseAdapter;
import com.example.tripapp.database.DatabaseHelper;
import com.example.tripapp.model.Item;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class Activity_Expense extends AppCompatActivity {

    FloatingActionButton fab_expense;
    RecyclerView recyclerView;
    ArrayList<String> Id_expense;
    ArrayList<String> Type;
    ArrayList<String> Amount;
    ArrayList<String> Time;
    ArrayList<String> Trip_id;
    String trip_id;
    ExpenseAdapter expenseAdapter;
    DatabaseHelper myDb;
    Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_epense);
        recyclerView = findViewById(R.id.expenseView);
        trip_id = getIntent().getStringExtra("get_trip_id");
        fab_expense = findViewById(R.id.fab_expense);
        fab_expense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //String Id = String.valueOf(item.getId());
                Intent intent = new Intent(Activity_Expense.this, AddExpenseActivity.class);
                intent.putExtra("get_id", trip_id);
                startActivity(intent);
            }
        });
        myDb = new DatabaseHelper(Activity_Expense.this);
        Id_expense = new ArrayList<>();
        Type = new ArrayList<>();
        Amount = new ArrayList<>();
        Time = new ArrayList<>();
        Trip_id = new ArrayList<>();
        getData();
        expenseAdapter = new ExpenseAdapter(Activity_Expense.this, Id_expense, Type, Amount, Time, Trip_id);
        recyclerView.setAdapter(expenseAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(Activity_Expense.this));
    }

    void getData() {
        Cursor cursor = myDb.expenseData(trip_id);
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            //cursor.moveToFirst();
            while (cursor.moveToNext()) {
                Id_expense.add(cursor.getString(0));
                Type.add(cursor.getString(1));
                Amount.add(cursor.getString(2));
                Time.add(cursor.getString(3));
                Trip_id.add(cursor.getString(4));
                //cursor.moveToNext();
            }
        }
    }
}