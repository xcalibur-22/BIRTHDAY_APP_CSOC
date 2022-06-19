package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DataBaseHelper extends SQLiteOpenHelper {
    public DataBaseHelper(@Nullable Context context) {
        super(context,Params.DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+Params.TABLE_NAME+
                " ("+Params.KEY_ID +" INTEGER PRIMARY KEY AUTOINCREMENT, " +Params.KEY_NAME+ " TEXT, "+Params.KEY_YEAR+" INT, "
                +Params.KEY_MONTH+" INT, "+Params.KEY_DATE+" INT)";
               db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public void addBirthday(Birthday birthday){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put(Params.KEY_NAME, birthday.getName());
        cv.put(Params.KEY_YEAR, birthday.getYear());
        cv.put(Params.KEY_MONTH, birthday.getMonth());
        cv.put(Params.KEY_DATE, birthday.getDate());
        db.insert(Params.TABLE_NAME,null,cv);
        Log.d("rituInsert","successfully inserted");
        db.close();
    }

    public List<Birthday> getAllBirthdays(){
        List<Birthday> birthdayList= new ArrayList<>();
        SQLiteDatabase db=this.getReadableDatabase();
        String select="SELECT * FROM " +Params.TABLE_NAME;
        Cursor cursor=db.rawQuery(select,null);

        if(cursor.moveToFirst()){
            do {
                Birthday birthday = new Birthday();
                birthday.setId(Integer.parseInt(cursor.getString(0)));
                birthday.setName(cursor.getString(1));
                birthday.setYear(Integer.parseInt(cursor.getString(2)));
                birthday.setMonth(Integer.parseInt(cursor.getString(3)));
                birthday.setDate(Integer.parseInt(cursor.getString(4)));
                birthdayList.add(birthday);
            }while(cursor.moveToNext());

        }
        return birthdayList;
    }

    public int updateBday(Birthday birthday){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv= new ContentValues();
        cv.put(Params.KEY_NAME, birthday.getName());
        cv.put(Params.KEY_YEAR, birthday.getYear());
        cv.put(Params.KEY_MONTH, birthday.getMonth());
        cv.put(Params.KEY_DATE, birthday.getDate());

        return db.update(Params.TABLE_NAME,cv,Params.KEY_ID+"=?",new String[]{String.valueOf(birthday.getId())});

    }
    public void deleteBday(int id){
        SQLiteDatabase db=this.getWritableDatabase();
        db.delete(Params.TABLE_NAME,Params.KEY_ID+"=?",new String[]{String.valueOf(id)});
        db.close();
    }

}
