package com.example.kushal.eatit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.graphics.Typeface;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kushal.eatit.Commom.Common;
import com.example.kushal.eatit.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.paperdb.Paper;

public class MainActivity extends AppCompatActivity {

    Button btnSignUp, btnSignIn;
    TextView txtSlogan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        printKeyHash();

        btnSignIn=(Button)findViewById(R.id.btnsignIn);
        btnSignUp=(Button)findViewById(R.id.btnsignUp);
        txtSlogan=(TextView)findViewById(R.id.txtSlogan);

        Typeface face=Typeface.createFromAsset(getAssets(),"fonts/NABILA.TTF");
        txtSlogan.setTypeface(face);

        Paper.init(this);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signIn=new Intent(MainActivity.this,SignIn.class);
                startActivity(signIn);

            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signup=new Intent(MainActivity.this,SignUp.class);
                startActivity(signup);
            }
        });

        String user=Paper.book().read(Common.USER_KEY);
        String pwd=Paper.book().read(Common.PWD_KEY);

        if (user!=null&&pwd!=null){
            if (!user.isEmpty()&&!pwd.isEmpty()){
                login(user,pwd);
            }
        }
    }

    private void printKeyHash() {
        try{
            PackageInfo info=getPackageManager().getPackageInfo("com.example.kushal.eatit",PackageManager.GET_SIGNATURES);
            for (Signature signature:info.signatures){
                MessageDigest md=MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash", Base64.encodeToString(md.digest(),Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    private void login(final String phone, final String pwd) {

        final FirebaseDatabase database=FirebaseDatabase.getInstance();
        final DatabaseReference table_user=database.getReference("User");

        if (Common.isConnectedToInternet(getBaseContext())) {

            final ProgressDialog progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.show();

            table_user.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child(phone).exists()) {
                        progressDialog.dismiss();
                        User user = dataSnapshot.child(phone).getValue(User.class);

                        user.setPhone(phone);
                        if (user.getPassword().equals(pwd)) {
                            Toast.makeText(MainActivity.this, "Sign In successfully  !", Toast.LENGTH_SHORT).show();
                            Intent home = new Intent(MainActivity.this, Home.class);
                            Common.currentUser = user;
                            startActivity(home);
                            finish();
                        } else {
                            Toast.makeText(MainActivity.this, "Sign In Failed !!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        progressDialog.dismiss();
                        Toast.makeText(MainActivity.this, "User does not exists", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }else {
            Toast.makeText(MainActivity.this,"Please check your connection!!",Toast.LENGTH_SHORT).show();
        }
    }

}
