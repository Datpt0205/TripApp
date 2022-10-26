package com.example.tripapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.MutableLiveData;
import androidx.viewpager.widget.ViewPager;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tripapp.adapter.ViewPagerAdapter;
import com.example.tripapp.database.DatabaseHelper;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView navigationView;
    private ViewPager viewPager;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (android.os.Build.VERSION.SDK_INT > 9) {
//            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//            StrictMode.setThreadPolicy(policy);
//        }
//        TextView textView = findViewById(R.id.respond);
//        findViewById(R.id.send).setOnClickListener(click -> {
//            Request.Builder builder = new Request.Builder();
//            Request request = builder
//                    .url("https://cwservice1786.herokuapp.com/sendPayLoad")
//                    .get()
//                    .build();
//            OkHttpClient client = new OkHttpClient().newBuilder().build();
//
//            try{
//                Response respond = client.newCall(request).execute();
//                assert request.body() != null;
//                textView.setText(request.body().toString());
//            }catch(IOException e){
//                e.printStackTrace();
//            }
//        });

        navigationView = findViewById(R.id.bottom_nav);
        viewPager = findViewById(R.id.viewPaper);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);

            }
        });
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager(),
                FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT);
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener(){
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels){

            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0: navigationView.getMenu().findItem(R.id.myHome).setChecked(true);
                    break;
                    case 1: navigationView.getMenu().findItem(R.id.myHistory).setChecked(true);
                    break;
                    case 2: navigationView.getMenu().findItem(R.id.mySearch).setChecked(true);
                    break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }

        });
        navigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.myHome:viewPager.setCurrentItem(0);
                    break;
                    case R.id.myHistory:viewPager.setCurrentItem(1);
                        break;
                    case R.id.mySearch:viewPager.setCurrentItem(2);
                        break;
                };
                return true;
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public  boolean onOptionsItemSelected(MenuItem item){
        if(item.getItemId() == R.id.delete_all){
            Toast.makeText(this,"Deleted all", Toast.LENGTH_SHORT).show();
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("DELETED ALL");
        builder.setMessage("Are you sure to delete all?");
        builder.setIcon(R.drawable.ic_baseline_delete_24);
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DatabaseHelper myDb = new DatabaseHelper(MainActivity.this);
                myDb.deleteAll();
                //Refresh
                Intent intent = new Intent(MainActivity.this, MainActivity.class);
                startActivity(intent);
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