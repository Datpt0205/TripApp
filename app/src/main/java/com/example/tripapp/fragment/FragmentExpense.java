//package com.example.tripapp.fragment;
//
//import android.content.Intent;
//import android.database.Cursor;
//import android.os.Bundle;
//import android.view.View;
//import android.widget.Toast;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.tripapp.AddExpenseActivity;
//import com.example.tripapp.R;
//import com.example.tripapp.adapter.ExpenseAdapter;
//import com.example.tripapp.database.DatabaseHelper;
//import com.example.tripapp.model.Item;
//import com.google.android.material.floatingactionbutton.FloatingActionButton;
//
//import java.util.ArrayList;
//
//public class FragmentExpense extends AppCompatActivity {
//    FloatingActionButton fab_expense;
//    RecyclerView recyclerView;
//    ArrayList<String> Id_expense, Type, Amount, Time, Trip_id;
//    ExpenseAdapter expenseAdapter;
//    DatabaseHelper myDb;
//    Item item;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_expense);
//        recyclerView = findViewById(R.id.expenseView);
//        fab_expense = findViewById(R.id.fab_expense);
//        fab_expense.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String id = String.valueOf(item.getId());
//                Intent intent = new Intent(FragmentExpense.this, AddExpenseActivity.class);
//                intent.putExtra("get_trip_id",id);
//                startActivity(intent);
//            }
//        });
//        Id_expense = new ArrayList<>();
//        Type = new ArrayList<>();
//        Amount = new ArrayList<>();
//        Time = new ArrayList<>();
//        Trip_id = new ArrayList<>();
//        expenseAdapter = new ExpenseAdapter(FragmentExpense.this, Id_expense, Type, Amount, Time, Trip_id);
//        recyclerView.setAdapter(expenseAdapter);
//        recyclerView.setLayoutManager(new LinearLayoutManager(FragmentExpense.this));
//    }
//
//    void getData() {
//        Cursor cursor = myDb.expenseData();
//        if (cursor.getCount() == 0) {
//            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
//        } else {
//            cursor.moveToFirst();
//            while (!cursor.isAfterLast()) {
//                Id_expense.add(cursor.getString(0));
//                Type.add(cursor.getString(1));
//                Amount.add(cursor.getString(2));
//                Time.add(cursor.getString(3));
//                Trip_id.add(cursor.getString(4));
//                cursor.moveToNext();
//            }
//        }
//    }
//}
