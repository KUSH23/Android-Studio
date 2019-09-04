package com.example.kushal.dbconnection;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editText;
    private static final String DB_URL = "jdbc:mysql://35.227.79.163/leandigit";
    private static final String USER ="root";
    private static final String PASS = "leandigitkgp";

    String dbName = "leandigit";
    String dbUserName = "root";
    String dbPassword = "leandigitkgp";
    String connectionString = "jdbc:mysql://35.227.79.163/" + dbName + "?user=" + dbUserName + "&password=" + dbPassword + "&useUnicode=true&characterEncoding=UTF-8";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView= (TextView) findViewById(R.id.textView);
        editText= (EditText) findViewById(R.id.editText);
    }

    public void btnConn(View view){
        Send objSend = new Send();
        objSend.execute("");
    }

    @SuppressLint("StaticFieldLeak")
    public class Send extends AsyncTask<String,String,String> {

        String msg = "";
        String text = editText.getText().toString();

        @SuppressLint("SetTextI18n")
        @Override
        protected void onPreExecute() {textView.setText("Please Wait Inserting Data...");}

        @SuppressLint("SetTextI18n")
        @Override
        protected String doInBackground(String... strings) {

            try {
                Class.forName("com.mysql.jdbc.Driver").newInstance();
                Connection conn = DriverManager.getConnection(connectionString);
                if (conn == null) {
                    msg = "Connection goes wrong";
                } else {
                    textView.setText("Connection Sucessful...");
                    String query = "INSERT INTO `Users` (`username`) VALUES (text)";
                    Statement stmt = conn.createStatement();
                    stmt.executeQuery(query);
                    msg = "Inserting Sucessful!!!";

                }
                if (conn != null) {
                    conn.close();
                }

            } catch (Exception e) {
                msg = "Connection goes wrong 2";
                e.printStackTrace();
            }
            return msg;
        }

        @Override
        protected void onPostExecute(String msg) {textView.setText(msg);}

    }
}
