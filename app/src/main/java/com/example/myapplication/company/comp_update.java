package com.example.myapplication.company;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.Data.DatabaseHelper;
import com.example.myapplication.R;
import com.example.myapplication.jobseekers.hiree_login;
import com.example.myapplication.jobseekers.hiree_update;

import java.net.PasswordAuthentication;

public class comp_update extends AppCompatActivity {
    SQLiteDatabase myDb;
    DatabaseHelper sqLiteHelper;
    EditText ed1, ed2, ed3,ed4,ed5 ;
    Button upd;

    String get_ID,getName,getID,getUsername,getPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comp_update);
        DatabaseHelper sqLiteHelper = new DatabaseHelper(this);

        Intent i =getIntent();
        getUsername = i.getStringExtra(company_login.Username);

        myDb=sqLiteHelper.getWritableDatabase();
        Cursor resultSet = myDb.rawQuery("Select * from "+DatabaseHelper.TABLE_NAME+" where username="+"'"+getUsername+"'",null);
        resultSet.moveToFirst();
        get_ID  = resultSet.getString(0);
        getName = resultSet.getString(1);
        getID = resultSet.getString(2);
        getUsername = resultSet.getString(3);
        getPassword = resultSet.getString(4);

        ed1 = (EditText) findViewById(R.id.editText);
        ed2 = (EditText) findViewById(R.id.editText2);
        ed3 = (EditText) findViewById(R.id.editText3);
        ed4 = (EditText) findViewById(R.id.editText4);
        upd=(Button)findViewById(R.id.save);

        ed1.setText(getName, TextView.BufferType.EDITABLE);
        ed2.setText(getID, TextView.BufferType.EDITABLE);
        ed3.setText(getPassword, TextView.BufferType.EDITABLE);



        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking EditText is empty or Not.
                if(CheckEditTextStatus())
                       UpdateData();
                else
                {
                    // Printing toast message if any of EditText is empty.
                    Toast.makeText(comp_update.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    // Method to check EditText is empty or Not.
    public boolean CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        getName = ed1.getText().toString();
        getID = ed2.getText().toString();
        getUsername = ed3.getText().toString();
        getPassword = ed4.getText().toString();


        if(TextUtils.isEmpty(getName) || TextUtils.isEmpty(getID) || TextUtils.isEmpty(getUsername) ||TextUtils.isEmpty(getPassword))
            return false;
        else
            return true;

    }

    //Method to update user record
    public void UpdateData() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", getName);
        contentValues.put("comp_id", getID);
        contentValues.put("username", getUsername);
        contentValues.put("password", getPassword);

        myDb.update(DatabaseHelper.TABLE_NAME, contentValues, "username = ? ", new String[] {getUsername} );
        finish();
    }
    }

