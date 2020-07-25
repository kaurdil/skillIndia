package com.example.myapplication.Data;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.myapplication.R;

public class signup_form2 extends AppCompatActivity {

    private static int RESULT_LOAD_IMAGE = 1;

    EditText ed1, ed2, ed3, ed4, ed5, ed6, ed7 ;
    Button reg,b4;
    RadioButton r1,r2;
    RadioGroup rg;
    String getName,getAge,getGender,getSkill,getExperience,getPhone,getUsername,getPassword;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    DatabaseHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form2);

        ed1 = (EditText) findViewById(R.id.name);
        ed2 = (EditText) findViewById(R.id.age);
        ed3 = (EditText) findViewById(R.id.skill);
        ed4 = (EditText) findViewById(R.id.experience);
        ed5 = (EditText) findViewById(R.id.phone);
        ed6 = (EditText) findViewById(R.id.username);
        ed7 = (EditText) findViewById(R.id.password);
        r1=(RadioButton)findViewById(R.id.radioButton2);
        r2=(RadioButton)findViewById(R.id.radioButton3);
        rg=(RadioGroup)findViewById(R.id.rg);
        reg = (Button) findViewById(R.id.b23);
        b4 = (Button)findViewById(R.id.bt4);


        sqLiteHelper = new  DatabaseHelper(this);


        // Adding click listener to register button.
        reg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // Creating SQLite database if dose n't exists
                SQLiteDataBaseBuild();

                // Creating SQLite table if dose n't exists.
                SQLiteTableBuild();

                // Checking EditText is empty or Not.
                CheckEditTextStatus();

                // Method to check Email is already exists or not.
                CheckingEmailAlreadyExistsOrNot();

                // Empty EditText After done inserting process.
                EmptyEditTextAfterDataInsert();


            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(i,RESULT_LOAD_IMAGE);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = { MediaStore.Images.Media.DATA };

            assert selectedImage != null;
            Cursor cursor = getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
            assert cursor != null;
            cursor.moveToFirst();

            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String picturePath = cursor.getString(columnIndex);
            cursor.close();

            ImageView imageView = (ImageView) findViewById(R.id.iv1);
            imageView.setImageBitmap(BitmapFactory.decodeFile(picturePath));

        }
    }

    // SQLite database build method.
    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase( DatabaseHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    // SQLite table build method.
    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " +  DatabaseHelper.TABLE2_NAME + "(" +  DatabaseHelper.Table_Col_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " +  DatabaseHelper.Table_Col1 + " VARCHAR, " +  DatabaseHelper.Table_Col2 + " INTEGER, " +  DatabaseHelper.Table_Col3 + " VARCHAR, " +  DatabaseHelper.Table_Col4 + " VARCHAR, " +  DatabaseHelper.Table_Col5 + " VARCHAR, " +  DatabaseHelper.Table_Col6 + " BIGINT, " +  DatabaseHelper.Table_Col7 + " VARCHAR," +  DatabaseHelper.Table_Col8 + " VARCHAR);");

    }

    // Insert data into SQLite database method.
    public void InsertDataIntoSQLiteDatabase(){

        // If editText is not empty then this block will executed.
        if(EditTextEmptyHolder == true)
        {

            // SQLite query to insert data into table.
            SQLiteDataBaseQueryHolder = "INSERT INTO "+ DatabaseHelper.TABLE2_NAME+" (name,age,gender,skill,experience,phone,username,password) VALUES('"+getName+"', '"+getAge+"', '"+getGender+"','"+getSkill+"','"+getExperience+"', '"+getPhone+"', '"+getUsername+"', '"+getPassword+"');";

            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            // Closing SQLite database object.
            sqLiteDatabaseObj.close();

            // Printing toast message after done inserting.
            Toast.makeText(signup_form2.this,"User Registered Successfully", Toast.LENGTH_LONG).show();

        }
        // This block will execute if any of the registration EditText is empty.
        else {

            // Printing toast message if any of EditText is empty.
            Toast.makeText(signup_form2.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }

    // Empty edittext after done inserting process method.
    public void EmptyEditTextAfterDataInsert(){

        ed1.getText().clear();

        ed2.getText().clear();

        ed3.getText().clear();

        ed4.getText().clear();

        ed5.getText().clear();

        ed6.getText().clear();

        ed7.getText().clear();

        rg.clearCheck();

    }

    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        getName = ed1.getText().toString();
        getAge = ed2.getText().toString();
        getSkill = ed3.getText().toString();
        getExperience = ed4.getText().toString();
        getPhone = ed5.getText().toString();
        getUsername = ed6.getText().toString();
        getPassword = ed7.getText().toString();
        if(r1.isChecked())
            getGender="female";
        if(r2.isChecked())
            getGender="male";

        if(TextUtils.isEmpty(getName) || TextUtils.isEmpty(getAge) || TextUtils.isEmpty(getSkill) ||TextUtils.isEmpty(getExperience) || TextUtils.isEmpty(getPhone) || TextUtils.isEmpty(getUsername) || TextUtils.isEmpty(getPassword) || rg.getCheckedRadioButtonId() == -1){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    // Checking Email is already exists or not.
    public void CheckingEmailAlreadyExistsOrNot(){

        // Opening SQLite database write permission.
        sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

        // Adding search email query to cursor.
        cursor = sqLiteDatabaseObj.query( DatabaseHelper.TABLE2_NAME, null, " " +  DatabaseHelper.Table_Col7 + "=?", new String[]{getUsername}, null, null, null);

        while (cursor.moveToNext()) {

            if (cursor.isFirst()) {

                cursor.moveToFirst();

                // If Email is already exists then Result variable value set as Email Found.
                F_Result = "username Found";

                // Closing cursor.
                cursor.close();
            }
        }

        // Calling method to check final result and insert data into SQLite database.
        CheckFinalResult();

    }


    // Checking result
    public void CheckFinalResult(){

        // Checking whether email is already exists or not.
        if(F_Result.equalsIgnoreCase("username Found"))
        {

            // If email is exists then toast msg will display.
            Toast.makeText(signup_form2.this,"username Already Exists",Toast.LENGTH_LONG).show();

        }
        else {

            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();

        }

        F_Result = "Not_Found" ;

    }

}