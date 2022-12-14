package com.example.tripapp.fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripapp.AddActivity;
import com.example.tripapp.R;
import com.example.tripapp.adapter.RecycleViewAdapter;
import com.example.tripapp.database.DatabaseHelper;
import com.example.tripapp.model.Item;

import java.util.Calendar;
import java.util.List;

public class FragmentSearch extends Fragment implements View.OnClickListener {

    private RecyclerView recyclerView;
    private Button btnSearch;
    private SearchView searchView;
    private EditText eFrom, eTo;
    private RecycleViewAdapter adapter;
    private DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        adapter = new RecycleViewAdapter();
        db = new DatabaseHelper(getContext());
        List<Item> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                List<Item> list=db.searchByKey(s);
                adapter.setList(list);
                return true;
            }
        });
        eFrom.setOnClickListener(this);
        eTo.setOnClickListener(this);
        btnSearch.setOnClickListener(this);
    }

    private void initView(View view){
        recyclerView= view.findViewById(R.id.recyclerView);
        btnSearch = view.findViewById(R.id.btnSearch);
        searchView = view.findViewById(R.id.search);
        eFrom = view.findViewById(R.id.eFrom);
        eTo = view.findViewById(R.id.eTo);
    }

    @Override
    public void onClick(View view) {
        if(view==eFrom){
                final Calendar c = Calendar.getInstance();
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);
                int day = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                        String date = "";
                        if(m >8){
                            date= d+"/"+(m+1)+"/"+y;
                        }else{
                            date= d+"/0"+(m+1)+"/"+y;
                        }
                        eFrom.setText(date);
                    }
                },year, month, day);
                dialog.show();
        }
        if(view==eTo){
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            DatePickerDialog dialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker datePicker, int y, int m, int d) {
                    String date = "";
                    if(m >8){
                        date= d+"/"+(m+1)+"/"+y;
                    }else{
                        date= d+"/0"+(m+1)+"/"+y;
                    }
                    eTo.setText(date);
                }
            },year, month, day);
            dialog.show();
        }

        if(view==btnSearch){
            String from = eFrom.getText().toString();
            String to = eTo.getText().toString();
            if(from.isEmpty() && to.isEmpty()){
                Toast.makeText(getContext().getApplicationContext(), "You need to choose From day and To day", Toast.LENGTH_SHORT).show();
            }else{
                List<Item> list = db.searchByDate(from, to);
                adapter.setList(list);
            }
        }
    }
}
