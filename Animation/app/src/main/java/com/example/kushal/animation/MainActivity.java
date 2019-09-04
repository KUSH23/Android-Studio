package com.example.kushal.animation;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    public  void fade(View view) {

        ImageView goku= (ImageView) findViewById(R.id.goku);
        goku.animate().translationX(-1000f).setDuration(2000);

        //ImageView goku2= (ImageView) findViewById(R.id.goku2);
        //goku2.animate().alpha(1f).setDuration(2000);
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
