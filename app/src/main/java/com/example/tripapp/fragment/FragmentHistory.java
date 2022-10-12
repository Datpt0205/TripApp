package com.example.tripapp.fragment;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tripapp.R;
import com.example.tripapp.UpdateAndDeleteActivity;
import com.example.tripapp.adapter.RecycleViewAdapter;
import com.example.tripapp.database.DatabaseHelper;
import com.example.tripapp.model.Item;

import java.util.List;

public class FragmentHistory extends Fragment implements RecycleViewAdapter.ItemListener {

    private RecycleViewAdapter adapter;
    private RecyclerView recyclerView;
    private DatabaseHelper db;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_history, container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recyclerView);
        adapter = new RecycleViewAdapter();
        db = new DatabaseHelper(getContext());
        //get All database
        List<Item> list = db.getAll();
        adapter.setList(list);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        adapter.setItemListener(this);
    }

    //Click Item
    @Override
    public void onItemClick(View view, int position) {
        Item item= adapter.getItem(position);
        //Move to Update class
        Intent intent = new Intent(getActivity(), UpdateAndDeleteActivity.class);
        intent.putExtra("item",item);
        startActivity(intent);
    }
    //làm mới mỗi khi sửa database
    @Override
    public void onResume(){
        super.onResume();
        List<Item> list = db.getAll();
        adapter.setList(list);
    }
}
