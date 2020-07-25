package com.example.myapplication.company;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import android.text.TextUtils;
import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;
import com.example.myapplication.jobseekers.hiree_login;
import com.example.myapplication.jobseekers.hiree_update;


public class company_login extends AppCompatActivity {

    String nameHolder;
    TextView u;
    Button LogOUT,update,find;

    public static String Username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_login);
        u= (TextView)findViewById(R.id.textView8);
        update = (Button)findViewById(R.id.button6);
        find = (Button)findViewById(R.id.button7);
        LogOUT = (Button)findViewById(R.id.button8);

        Intent parentIntent = getIntent();

        // Receiving User Email Send By MainActivity.
        nameHolder = parentIntent.getStringExtra(MainActivity.Username);

        // Setting up received email to TextView.
        u.setText(u.getText().toString()+  nameHolder);

        // Adding click listener to Log Out button.
        LogOUT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(company_login.this,"Log Out Successfull", Toast.LENGTH_LONG).show();

            }
        });

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(company_login.this,comp_update.class);

                // Sending Email to Dashboard Activity using intent.
                intent.putExtra(Username, nameHolder);
                startActivity(intent);
            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(company_login.this,search_jobseekers.class);
                startActivity(intent);
            }
        });
    }
}




