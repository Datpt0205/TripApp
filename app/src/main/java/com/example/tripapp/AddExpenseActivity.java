package com.example.tripapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tripapp.database.DatabaseHelper;

import java.util.ArrayList;
import java.util.Calendar;

public class AddExpenseActivity extends AppCompatActivity implements View.OnClickListener {
    public Spinner sp;
    String trip_id;
    private EditText eAmount, eTime;
    private Button addDetailExpense, btnExpenseCancel;
    ArrayList<String> Id_expense, type, mount, time, tripId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        initView();
        btnExpenseCancel.setOnClickListener(this);
        addDetailExpense.setOnClickListener(this);
        trip_id = getIntent().getStringExtra("get_id");
        getTripId();
        eTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(AddExpenseActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker date, int y, int m, int d) {
                        String time = "";
                        if (m > 8) {
                            time = d + "/" + (m + 1) + "/" + y;
                        } else {
                            time = d + "/0" + (m + 1) + "/" + y;
                        }
                        eTime.setText(time);
                    }
                }, year, month, day);
                dialog.show();
            }
        });
    }
    private void initView() {
        sp = findViewById(R.id.expenseAddType);
        eAmount = findViewById(R.id.expenseAddAmount);
        eTime = findViewById(R.id.expenseAddDate);
        addDetailExpense = findViewById(R.id.addDetailExpense);
        btnExpenseCancel = findViewById(R.id.btnExpenseCancel);
        sp.setAdapter(new ArrayAdapter<String>(this, R.layout.item_spinner,
                getResources().getStringArray(R.array.category)));
    }

    @Override
    public void onClick(View view) {
        if (view == btnExpenseCancel) {
            finish();
        }
        if (view == addDetailExpense) {
            String id = trip_id;
            String expenseType = sp.getSelectedItem().toString();
            String expenseAmount = eAmount.getText().toString();
            String expenseTime = eTime.getText().toString();
         if (!expenseType.isEmpty() && !expenseAmount.isEmpty() && !expenseTime.isEmpty()) {
                DatabaseHelper db = new DatabaseHelper(AddExpenseActivity.this);
                db.addExpense(id, expenseType, expenseAmount, expenseTime);
                Intent new_intent = new Intent(AddExpenseActivity.this,Activity_Expense.class);
                new_intent.putExtra("get_trip_id",trip_id);
                startActivity(new_intent);
            }else{
                Toast.makeText(this, "You need to fill all required field", Toast.LENGTH_SHORT).show();
            }
        }
    }

    void getTripId() {
        if (getIntent().hasExtra("get_id")) {
            trip_id = getIntent().getStringExtra("get_id");
        } else {
            Toast.makeText(this, "No Id Trip", Toast.LENGTH_LONG).show();
        }
    }
}