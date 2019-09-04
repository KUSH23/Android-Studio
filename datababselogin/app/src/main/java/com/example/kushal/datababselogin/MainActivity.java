package com.example.kushal.datababselogin;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity{

   /** Boolean signUpModeActive=true;
    TextView changeSignupModeTextView;
    Button signUpButton;

    Connection con;
    String username,password,db,ip;

    @Override
    public void onClick(View view) {
        if (view.getId()==R.id.changeSignupModeTextView){

            signUpButton=(Button)findViewById(R.id.signupButton);

            if (signUpModeActive){

                signUpModeActive=false;
                signUpButton.setText("Login");
                changeSignupModeTextView.setText("Or, SignUp");

            }else{

                signUpModeActive=true;
                signUpButton.setText("SignUp");
                changeSignupModeTextView.setText("Or, Login");

            }

        }
    }

    public void signUp(View view){
        EditText username=(EditText)findViewById(R.id.usernameEditText);
        EditText password=(EditText)findViewById(R.id.passwordEditText);

        if(username.getText().toString().matches("")||password.getText().toString().matches("")){
            Toast.makeText(this,"A Username and password is required",Toast.LENGTH_SHORT).show();
        }else {

            if (signUpModeActive){

                //create new user



         //   }else{

                //login

                // }


            }



        }
    }**/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**DBconnect dBconnect=new DBconnect();

        dBconnect.getdata();


       changeSignupModeTextView=(TextView)findViewById(R.id.changeSignupModeTextView);

        changeSignupModeTextView.setOnClickListener(this);

        ip="http://35.227.79.163/phpmyadmin/";
        db="leandigit";
        username="root";
        password="leandigitkgp";


        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }

 //   public class signUp extends AsyncTask<String,String,String>{

  //  }**/

    }
}
