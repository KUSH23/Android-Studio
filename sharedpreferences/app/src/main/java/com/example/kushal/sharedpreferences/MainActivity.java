package com.example.kushal.sharedpreferences;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPreferences=this.getSharedPreferences("com.example.kushal.sharedpreferences", Context.MODE_PRIVATE);
        //sharedPreferences.edit().putString("username","kushal").apply();
        //String username=sharedPreferences.getString("username","");
        //Log.i("username",username);
        ArrayList<String> friends=new ArrayList<>();
        friends.add("jain");
        friends.add("kale");
        try {
            sharedPreferences.edit().putString("friends",ObjectSerializer.serialize(friends)).apply();
        } catch (IOException e) {
            e.printStackTrace();
        }
        ArrayList<String> newfriends=new ArrayList<>();
        try {
            newfriends=(ArrayList<String>) ObjectSerializer.deserialize(sharedPreferences.getString("friends", ObjectSerializer.serialize(new ArrayList<>())));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.i("newfriends",newfriends.toString());

        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Are you sure")
                .setMessage("Do you want to delete this")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(MainActivity.this,"Done",Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("No",null)
                .show();
    }
}
