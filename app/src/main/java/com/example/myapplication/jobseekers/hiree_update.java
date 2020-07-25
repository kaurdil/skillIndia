package com.example.myapplication.jobseekers;

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

public class hiree_update extends AppCompatActivity {

    SQLiteDatabase myDb;
    DatabaseHelper sqLiteHelper;
    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7 ;
    Button upd;
    RadioButton r1,r2;
    RadioGroup rg;
    String getName,getAge,getGender,getSkill,getExperience,getPhone,getUsername,getPassword;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiree_update);

        DatabaseHelper sqLiteHelper = new DatabaseHelper(this);

        Intent i =getIntent();
        getUsername = i.getStringExtra(hiree_login.Username);

        myDb=sqLiteHelper.getWritableDatabase();
        Cursor resultSet = myDb.rawQuery("Select * from "+DatabaseHelper.TABLE2_NAME+" where username="+"'"+getUsername+"'",null);
        resultSet.moveToFirst();
        getName = resultSet.getString(1);
        getAge = resultSet.getString(2);
        getGender = resultSet.getString(3);
        getSkill = resultSet.getString(4);
        getExperience = resultSet.getString(5);
        getPhone = resultSet.getString(6);
        getPassword = resultSet.getString(8);

        ed1 = (EditText) findViewById(R.id.name);
        ed2 = (EditText) findViewById(R.id.age);
        ed3 = (EditText) findViewById(R.id.skill);
        ed4 = (EditText) findViewById(R.id.experience);
        ed5 = (EditText) findViewById(R.id.phone);
        ed6 = (EditText) findViewById(R.id.username);
        ed7 = (EditText) findViewById(R.id.password);
        r1=(RadioButton)findViewById(R.id.rb1);
        r2=(RadioButton)findViewById(R.id.rb2);
        rg=(RadioGroup)findViewById(R.id.rg2);
        upd=(Button)findViewById(R.id.button3);

        ed1.setText(getName, TextView.BufferType.EDITABLE);
        ed2.setText(getAge, TextView.BufferType.EDITABLE);
        ed3.setText(getSkill, TextView.BufferType.EDITABLE);
        ed4.setText(getExperience, TextView.BufferType.EDITABLE);
        ed5.setText(getPhone, TextView.BufferType.EDITABLE);
        ed6.setText(getUsername, TextView.BufferType.EDITABLE);
        ed7.setText(getPassword, TextView.BufferType.EDITABLE);
        if(getGender.equalsIgnoreCase("male"))
            r1.setChecked(true);
        else
            r2.setChecked(true);

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Checking EditText is empty or Not.
                if(CheckEditTextStatus())
                    updateUser();
                else
                {
                    // Printing toast message if any of EditText is empty.
                    Toast.makeText(hiree_update.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    // Method to check EditText is empty or Not.
    public boolean CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        getName = ed1.getText().toString();
        getAge = ed2.getText().toString();
        getSkill = ed3.getText().toString();
        getExperience = ed4.getText().toString();
        getPhone = ed5.getText().toString();
        getUsername = ed6.getText().toString();
        getPassword = ed7.getText().toString();
        if(r1.isChecked())
            getGender="male";
        if(r2.isChecked())
            getGender="female";

        if(TextUtils.isEmpty(getName) || TextUtils.isEmpty(getAge) || TextUtils.isEmpty(getSkill) ||TextUtils.isEmpty(getExperience) || TextUtils.isEmpty(getPhone) || TextUtils.isEmpty(getUsername) || TextUtils.isEmpty(getPassword) || rg.getCheckedRadioButtonId() == -1)
            return false;
        else
            return true;

    }

    //Method to update user record
    public void updateUser()
    {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", getName);
        contentValues.put("age", getAge);
        contentValues.put("gender", getGender);
        contentValues.put("skill", getSkill);
        contentValues.put("experience", getExperience);
        contentValues.put("phone", getPhone);
        contentValues.put("username", getUsername);
        contentValues.put("password", getPassword);

        myDb.update(DatabaseHelper.TABLE2_NAME, contentValues, "username = ? ", new String[] {getUsername} );
        finish();

    }
}



