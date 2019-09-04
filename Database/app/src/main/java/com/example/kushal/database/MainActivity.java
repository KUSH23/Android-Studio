package com.example.kushal.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        try{
            SQLiteDatabase myDatabase = this.openOrCreateDatabase("Users", MODE_PRIVATE,null);
            myDatabase.execSQL("CREATE TABLE IF NOT EXISTS newusers (name VARCHAR,age INT(3),id INT PRIMARY KEY)");

            myDatabase.execSQL("INSERT INTO newusers (name,age) VALUES ('kushal',19)");
            myDatabase.execSQL("INSERT INTO newusers (name,age) VALUES ('abhishek',20)");

            Cursor c=myDatabase.rawQuery("SELECT * FROM newusers",null);

            int nameIndex=c.getColumnIndex("name");
            int ageIndex=c.getColumnIndex("age");
            int idIndex=c.getColumnIndex("id");

            c.moveToFirst();
            while (c!=null){
                Log.i("Result--name",c.getString(nameIndex));
                Log.i("Result--age",Integer.toString(c.getInt(ageIndex)));
                Log.i("Result--id",Integer.toString(c.getInt(idIndex)));
                c.moveToNext();
            }


        }
        catch (Exception e){
            e.printStackTrace();
        }

    }
}
