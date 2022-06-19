package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity3 extends AppCompatActivity {
    Button date;
    Button add;
    EditText et;
    TextView tv;
    static int day;
    static int month;
    static int year;
    Button bt_del;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent=this.getIntent();
        String x=intent.getStringExtra("id");
        String name=intent.getStringExtra("name");
        String d=intent.getStringExtra("date");
        day=Integer.parseInt(intent.getStringExtra("dd"));
        month=Integer.parseInt(intent.getStringExtra("mm"));
        year=Integer.parseInt(intent.getStringExtra("yy"));
        int ID=Integer.parseInt(x);
        date=findViewById(R.id.button);
        add=findViewById(R.id.button2);
        et=findViewById(R.id.edittext);
        tv=findViewById(R.id.textView);
        et.setText(name);
        tv.setText(day+"/"+month+"/"+year);
        bt_del=findViewById(R.id.bt_del);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DataBaseHelper db=new DataBaseHelper(MainActivity3.this);
                    Birthday ob1=new Birthday();
                    String s=et.getText().toString();
                    ob1.setId(ID);
                    ob1.setName(s);
                    ob1.setYear(year);
                    ob1.setMonth(month);
                    ob1.setDate(day);
                    db.updateBday(ob1);
                    Intent intent=new Intent(MainActivity3.this,MainActivity.class);
                    startActivity(intent);
                }
                catch (Exception e){

                }
            }
        });
        bt_del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataBaseHelper db=new DataBaseHelper(MainActivity3.this);
                db.deleteBday(ID);
                Intent intent=new Intent(MainActivity3.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void addDate(View view){

        int y=year;
        int m=month;
        int d=day;
        DatePickerDialog datePickerDialog=new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                year=i;
                month=i1+1;
                day=i2;
                tv.setText(day+"/"+month+"/"+year);
            }
        },y,m,d);
        datePickerDialog.show();
    }
}