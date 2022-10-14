package com.example.tripapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import com.example.tripapp.database.DatabaseHelper;
import com.example.tripapp.model.Item;

import java.util.Calendar;

public class UpdateAndDeleteActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText eName, eDestination, eDate, eRisk, eDescription;
    private Button btnAdd, btnBack, btnDelete;
    private Item item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_and_delete);
        initView();
        btnBack.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        eDate.setOnClickListener(this);
        Intent intent = getIntent();
        item =(Item) intent.getSerializableExtra("item");
        eName.setText(item.getName());
        eDestination.setText(item.getDestination());
        eDate.setText(item.getDate());
        eRisk.setText(item.getRisk());
        eDescription.setText(item.getDescription());
    }

    private void initView() {
        eName = findViewById(R.id.addName);
        eDestination = findViewById(R.id.addDestination);
        eDate = findViewById(R.id.addDate);
        eRisk = findViewById(R.id.addRisk);
        eDescription = findViewById(R.id.addDescription);
        btnAdd = findViewById(R.id.btnAdd);
        btnBack = findViewById(R.id.btnBack);
        btnDelete = findViewById(R.id.btnDelete);
    }

    @Override
    public void onClick(View view) {
        if(view == eDate){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(UpdateAndDeleteActivity.this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if(m >8){
                        date= d+"/"+(m+1)+"/"+y;
                    }else{
                        date= d+"/0"+(m+1)+"/"+y;
                    }
                    eDate.setText(date);
                }
            },year, month, day);
            dialog.show();
        }
        if(view==btnBack){
            finish();
        }
        if(view == btnAdd){
            String name = eName.getText().toString();
            String destination = eDestination.getText().toString();
            String date = eDate.getText().toString();
            String risk = eRisk.getText().toString();
            String description = eDescription.getText().toString();
            if(!name.isEmpty() && !destination.isEmpty() && !date.isEmpty() && !risk.isEmpty()) {
                int id=item.getId();
                Item i = new Item(id, name, destination, date, risk, description);
                DatabaseHelper db = new DatabaseHelper(this);
                db.update(i);
                finish();
            }
            }else{
                Toast.makeText(UpdateAndDeleteActivity.this, "You need input all information", Toast.LENGTH_SHORT ).show();
            }
            if(view==btnDelete){
                int id=item.getId();
                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("Deleted");
                builder.setMessage("Are you sure to delete "+item.getName()+"?");
                builder.setIcon(R.drawable.ic_baseline_delete_24);
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DatabaseHelper bb =new DatabaseHelper(getApplicationContext());
                        bb.delete(id);
                        finish();
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
        }
    }
}