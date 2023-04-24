package com.example.localdatabasetest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class MainActivity extends AppCompatActivity {

    private EditText fname,lname,mname;
    private Button insert;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fname = (EditText) findViewById(R.id.fn);
        lname = (EditText) findViewById(R.id.ln);
        mname = (EditText) findViewById(R.id.mn);
        insert = (Button) findViewById(R.id.Insert);


        insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String firstname = fname.getText().toString();
                String middlename = mname.getText().toString();
                String lastname = lname.getText().toString();

                Connection conn = DbConnection.getConnection();
                if (conn != null) {

                    try {
                        // Nag insert ug data
                        String sql = "INSERT INTO info (firstname, middlename, lastname) VALUES (?, ?, ?)";
                        PreparedStatement statement = conn.prepareStatement(sql);
                        statement.setString(1, firstname);
                        statement.setString(2, middlename);
                        statement.setString(3, lastname);
                        int rowsInserted = statement.executeUpdate();
                         if (rowsInserted > 0) {
                            Toast.makeText(MainActivity.this,"Inserted Successfully!", Toast.LENGTH_SHORT).show();
                         }else{
                            Toast.makeText(MainActivity.this,"Insert Unsuccessful!", Toast.LENGTH_SHORT).show();
                            }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    } finally {
                        // Gi close ang connection
                        try {
                            conn.close();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                }
                } else {
                    Toast.makeText(MainActivity.this,"Connection Error", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }
}