package com.example.tripapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.example.tripapp.model.Item;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private Context context;

    public DatabaseHelper(@Nullable Context context) {
        super(context, "TripApp.db", null, 2);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE items(Id INTEGER PRIMARY KEY AUTOINCREMENT ," +
                "name TEXT, destination TEXT, date TEXT, risk TEXT, description TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS items");
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }

    //Get all order by date descending
    public List<Item> getAll(){
        List<Item> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        //Date giảm dần, mới nhất thì lên trên
        String order= "date DESC";
        Cursor rs = st.query("items", null, null,
                null, null,null, order);
        while(rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name= rs.getString(1);
            String destination= rs.getString(2);
            String date= rs.getString(3);
            String risk= rs.getString(4);
            String description= rs.getString(5);
            //add to
            list.add(new Item(id,name, destination,date, risk, description));
        }
        return list;
    }
    //Add
    public long addItem(Item i){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("destination", i.getDestination());
        values.put("date", i.getDate());
        values.put("risk", i.getRisk());
        values.put("description", i.getDescription());
        return sqLiteDatabase.insert("items", null, values);
    }
}
