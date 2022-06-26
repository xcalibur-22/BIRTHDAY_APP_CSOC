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
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity2 extends AppCompatActivity {
    Button date;
    Button add;
    EditText et;
    TextView tv;
    static int day;
    static int month;
    static int year;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        date=findViewById(R.id.button);
        add=findViewById(R.id.button2);
        et=findViewById(R.id.edittext);
        tv=findViewById(R.id.textView);
        tv.setText("dd/mm/yy");
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DataBaseHelper db=new DataBaseHelper(MainActivity2.this);
                    Birthday ob1=new Birthday();
                    String s=et.getText().toString();
                    ob1.setName(s);
                    ob1.setYear(year);
                    ob1.setMonth(month);
                    ob1.setDate(day);
                    if(!s.isEmpty()){
                    db.addBirthday(ob1);
                    Intent intent=new Intent(MainActivity2.this,MainActivity.class);
                    startActivity(intent);
                    MainActivity2.this.finish();
                    }
                    else{
                        Toast.makeText(MainActivity2.this, "Name cannot be empty", Toast.LENGTH_SHORT).show();
                    }
                }
                catch (Exception e){

                }
            }
        });
    }
    public void addDate(View view){
        Calendar calendar=Calendar.getInstance();

        int y=calendar.get(Calendar.YEAR);
        int m=calendar.get(Calendar.MONTH);
        int d=calendar.get(Calendar.DATE);
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