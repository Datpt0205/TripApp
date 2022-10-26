package com.example.tripapp.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

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

        db.execSQL("CREATE TABLE expense(Id INTEGER PRIMARY KEY AUTOINCREMENT , " +
                " type TEXT, time TEXT, amount INTEGER, tripId INTEGER, FOREIGN KEY (tripId) " +
                " REFERENCES items (Id))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS items");
        db.execSQL("DROP TABLE IF EXISTS expense");
        onCreate(db);
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }

    //Get all order by date ascending
    public List<Item> getAll(){
        List<Item> list = new ArrayList<>();
        SQLiteDatabase st = getReadableDatabase();
        String order= "date ASC";
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
    //Add expense
    public long addExpense(String tripId, String type, String amount, String time){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("type", type);
        cv.put("amount", amount);
        cv.put("time", time);
        cv.put("tripId", tripId);
        long target_expense = sqLiteDatabase.insert("expense", null, cv);
        if(target_expense == -1){
            Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Added Success", Toast.LENGTH_SHORT).show();
        }
        return target_expense;
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
        long target = sqLiteDatabase.insert("items", null, values);
        if (target == -1)
        {
            Toast.makeText(context, "Failed to add", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Added Success", Toast.LENGTH_SHORT).show();
        }
        return target;
    }

    //get expense Data
    public Cursor expenseData(String _id){
        String query = "SELECT * FROM " + "expense" + " WHERE " + "tripId" + "='" + _id + "'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query,null);
        }
        return cursor;
    }

    //Get items by Date
    public List<Item> getByDate(String date){
        List<Item> list = new ArrayList<>();
        String whereClause = "date like?";
        String[] whereArgs = {date};
        SQLiteDatabase st = getReadableDatabase();
         Cursor rs = st.query("items", null, whereClause, whereArgs, null,null,null);
         while (rs != null && rs.moveToNext()){
             int id = rs.getInt(0);
             String name= rs.getString(1);
             String destination= rs.getString(2);
             String risk= rs.getString(3);
             String description= rs.getString(4);
             list.add(new Item(id,name, destination,date, risk, description));
         }
        return list;
    }
    //update
    public int update(Item i){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", i.getName());
        values.put("destination", i.getDestination());
        values.put("date", i.getDate());
        values.put("risk", i.getRisk());
        values.put("description", i.getDescription());
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(i.getId())};
        int target2 = sqLiteDatabase.update("items", values, whereClause, whereArgs);
        if(target2 == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Updated Success", Toast.LENGTH_SHORT).show();
        }
        return target2;
    }
    //delete
    public int delete(int id){
        String whereClause = "id = ?";
        String[] whereArgs = {Integer.toString(id)};
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        int deleteSuccess = sqLiteDatabase.delete("items", whereClause, whereArgs);
        if(deleteSuccess == -1){
            Toast.makeText(context, "Failed to Deleted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "Deleted Success", Toast.LENGTH_SHORT).show();
        }
        return deleteSuccess;
    }

    public void deleteAll(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        sqLiteDatabase.execSQL(" DELETE FROM " + "items");
//        int deleteAllSuccess = sqLiteDatabase.delete("items", null, null);
//        if(deleteAllSuccess == -1){
//            Toast.makeText(context, "Failed to Deleted", Toast.LENGTH_SHORT).show();
//        }
//        else
//        {
//            Toast.makeText(context, "Deleted Success", Toast.LENGTH_SHORT).show();
//        }
//        return deleteAllSuccess;
    }

    //Search by key word
    public List<Item> searchByKey(String key){
        List<Item> list = new ArrayList<>();
        String whereClause = "name like ?";
        String[] whereArgs = {"%"+key+"%"};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null, whereClause, whereArgs, null,null,null);
        while (rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name= rs.getString(1);
            String destination= rs.getString(2);
            String risk= rs.getString(3);
            String description= rs.getString(4);
            String date = rs.getString(5);
            list.add(new Item(id,name, destination,date, risk, description));
        }
        return list;
    }

    //Search by date
    public List<Item> searchByDate(String from, String to){
        List<Item> list = new ArrayList<>();
        String whereClause = "date BETWEEN ? AND ?";
        String[] whereArgs = {from.trim(), to.trim()};
        SQLiteDatabase st = getReadableDatabase();
        Cursor rs = st.query("items", null, whereClause, whereArgs, null,null,null);
        while (rs != null && rs.moveToNext()){
            int id = rs.getInt(0);
            String name= rs.getString(1);
            String destination= rs.getString(2);
            String risk= rs.getString(3);
            String description= rs.getString(4);
            String date = rs.getString(5);
            list.add(new Item(id, name, destination, date, risk, description));
        }
        return list;
    }


}
