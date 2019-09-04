package com.example.kushal.higherorlower;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    int randomNumber;
    int attempt = 0;

    public void guess(View view) {
        EditText guessEditText = (EditText) findViewById(R.id.guessEditText);
        TextView editTextOut = (TextView) findViewById(R.id.editTextOut);

        int guessInt = Integer.parseInt(guessEditText.getText().toString());

        if(guessInt<randomNumber){
            Toast.makeText(MainActivity.this,"Try Higher!",Toast.LENGTH_SHORT).show();
            attempt= attempt+1;
        }
        else if(guessInt>randomNumber){
            Toast.makeText(MainActivity.this,"Try Lower!",Toast.LENGTH_SHORT).show();
            attempt= attempt+1;
        }
        else if (guessInt==randomNumber){
            Toast.makeText(MainActivity.this,"That's right! Try Again!",Toast.LENGTH_SHORT).show();

            attempt= 0;
            Random rand = new Random();
            randomNumber = rand.nextInt(20)+1;
        }

        guessEditText.getText().clear();
        editTextOut.setText(String.valueOf(attempt));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Random rand = new Random();
        randomNumber = rand.nextInt(20)+1;
    }
}
