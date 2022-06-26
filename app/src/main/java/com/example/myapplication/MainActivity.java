package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    rituAdapter ad;
    public static List<Birthday> allBirthdays1;
    ArrayList<Birthday> allBirthdays;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().setTitle("BIRTHDAYS");

         listView=findViewById(R.id.listview);
        DataBaseHelper db=new DataBaseHelper(MainActivity.this);

         allBirthdays1=db.getAllBirthdays();
        for (Birthday birthday: allBirthdays1){
//            Toast.makeText(this, birthday.toString(), Toast.LENGTH_SHORT).show();
            Log.d("rituDisplay",birthday.toString());
        }
        allBirthdays=new ArrayList<>(allBirthdays1);
         ad=new rituAdapter(MainActivity.this, allBirthdays);
        listView.setAdapter(ad);
        listView.setClickable(true);
        listView.setTextFilterEnabled(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long l) {
                Intent intent=new Intent(MainActivity.this,MainActivity3.class);
                intent.putExtra("id",Integer.toString(allBirthdays.get(pos).getId()));
                intent.putExtra("name",allBirthdays.get(pos).getName());
                intent.putExtra("dd",Integer.toString(allBirthdays.get(pos).getDate()));
                intent.putExtra("mm",Integer.toString(allBirthdays.get(pos).getMonth()));
                intent.putExtra("yy",Integer.toString(allBirthdays.get(pos).getYear()));


                startActivity(intent);
                MainActivity.this.finish();
            }
        });

    }
    public void openActivity(View view){
        Intent intent=new Intent(this,MainActivity2.class);
        startActivity(intent);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        MenuItem myActionMenuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)myActionMenuItem.getActionView();
        searchView.setQueryHint("Type here to search");

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                s=s.toLowerCase(Locale.ROOT);
                ad.getFilter().filter(s);
                Log.d("search",s);
                return true;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}