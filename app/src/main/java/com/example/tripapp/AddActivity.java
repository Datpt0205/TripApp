package com.example.tripapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tripapp.database.DatabaseHelper;
import com.example.tripapp.model.Item;

import java.util.Calendar;

public class AddActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText eName, eDestination, eDate, eRisk, eDescription;
    private Button btnAdd, btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        initView();
        btnCancel.setOnClickListener(this);
        btnAdd.setOnClickListener(this);
        eDate.setOnClickListener(this);
    }

    private void initView(){
        eName = findViewById(R.id.addName);
        eDestination = findViewById(R.id.addDestination);
        eDate = findViewById(R.id.addDate);
        eRisk = findViewById(R.id.addRisk);
        eDescription = findViewById(R.id.addDescription);
        btnAdd = findViewById(R.id.btnAdd);
        btnCancel = findViewById(R.id.btnCancel);

    }

    @Override
    public void onClick(View view) {
        if(view == eDate){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
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
        if(view==btnCancel){
            finish();
        }
        if(view == btnAdd){
            String name = eName.getText().toString();
            String destination = eDestination.getText().toString();
            String date = eDate.getText().toString();
            String risk = eRisk.getText().toString();
            String description = eDescription.getText().toString();
            if(!name.isEmpty() && !destination.isEmpty() && !date.isEmpty() && !risk.isEmpty()){
                Item i = new Item(name, destination, date, risk, description);
                DatabaseHelper db = new DatabaseHelper(this);
                db.addItem(i);
                finish();
            }else{
                Toast.makeText(AddActivity.this, "You need input all information", Toast.LENGTH_SHORT ).show();
            }
        }
    }
}