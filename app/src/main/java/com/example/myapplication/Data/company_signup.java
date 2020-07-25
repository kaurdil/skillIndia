package com.example.myapplication.Data;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.myapplication.R;


public class company_signup extends AppCompatActivity {

    EditText ed1, ed2, ed3, ed4 ;
    Button reg;
    String NameHolder, idHolder,user, PasswordHolder;
    Boolean EditTextEmptyHolder;
    SQLiteDatabase sqLiteDatabaseObj;
    String SQLiteDataBaseQueryHolder ;
    DatabaseHelper sqLiteHelper;
    Cursor cursor;
    String F_Result = "Not_Found";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company_signup);

         ed1 = (EditText) findViewById(R.id.e1);
         ed2 = (EditText) findViewById(R.id.e2);
         ed3 = (EditText) findViewById(R.id.e3);
         ed4 = (EditText) findViewById(R.id.e);
         reg = (Button) findViewById(R.id.b2);


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

    }
//    public void goToFragment(View view) {
//        mydetails fragment = mydetails.newInstance( NameHolder, idHolder,user);
//
//        replaceFragment(fragment, "mydetails");
//        view.setVisibility(View.GONE);
//    }
//
//
//
//    public void replaceFragment(Fragment fragment, String tag) {
//
//
//
//            FragmentManager fragmentManager = getSupportFragmentManager();
//            fragmentManager.beginTransaction().replace(R.id.frag1, fragment, tag).commit();
//
//
//
//    }


    // SQLite database build method.
    public void SQLiteDataBaseBuild(){

        sqLiteDatabaseObj = openOrCreateDatabase( DatabaseHelper.DATABASE_NAME, Context.MODE_PRIVATE, null);

    }

    // SQLite table build method.
    public void SQLiteTableBuild() {

        sqLiteDatabaseObj.execSQL("CREATE TABLE IF NOT EXISTS " +  DatabaseHelper.TABLE_NAME + "(" +  DatabaseHelper.Table_Column_ID + " PRIMARY KEY AUTOINCREMENT NOT NULL, " +  DatabaseHelper.Table_Column_1_Name + " VARCHAR, " +  DatabaseHelper.Table_Column_2_COMPID + " VARCHAR, " +  DatabaseHelper.Table_Column_3_username + " VARCHAR, " +  DatabaseHelper.Table_Column_4_Password + " VARCHAR);");

    }

    // Insert data into SQLite database method.
    public void InsertDataIntoSQLiteDatabase(){

        // If editText is not empty then this block will executed.
        if(EditTextEmptyHolder == true)
        {

            // SQLite query to insert data into table.
            SQLiteDataBaseQueryHolder = "INSERT INTO "+ DatabaseHelper.TABLE_NAME+" (name,comp_id,username,password) VALUES('"+NameHolder+"', '"+idHolder+"', '"+user+"','"+PasswordHolder+"');";

            // Executing query.
            sqLiteDatabaseObj.execSQL(SQLiteDataBaseQueryHolder);

            // Closing SQLite database object.
            sqLiteDatabaseObj.close();

            // Printing toast message after done inserting.
            Toast.makeText(company_signup.this,"User Registered Successfully", Toast.LENGTH_LONG).show();

        }
        // This block will execute if any of the registration EditText is empty.
        else {

            // Printing toast message if any of EditText is empty.
            Toast.makeText(company_signup.this,"Please Fill All The Required Fields.", Toast.LENGTH_LONG).show();

        }

    }

    // Empty edittext after done inserting process method.
    public void EmptyEditTextAfterDataInsert(){

        ed1.getText().clear();

        ed2.getText().clear();

        ed3.getText().clear();

        ed4.getText().clear();

    }

    // Method to check EditText is empty or Not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        NameHolder = ed1.getText().toString() ;
        idHolder = ed2.getText().toString();
        user=ed3.getText().toString();
        PasswordHolder = ed4.getText().toString();

        if(TextUtils.isEmpty(NameHolder) || TextUtils.isEmpty(idHolder) || TextUtils.isEmpty(user) ||TextUtils.isEmpty(PasswordHolder)){

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
        cursor = sqLiteDatabaseObj.query( DatabaseHelper.TABLE_NAME, null, " " +  DatabaseHelper.Table_Column_3_username + "=?", new String[]{user}, null, null, null);

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
            Toast.makeText(company_signup.this,"username Already Exists",Toast.LENGTH_LONG).show();

        }
        else {

            // If email already dose n't exists then user registration details will entered to SQLite database.
            InsertDataIntoSQLiteDatabase();

        }

        F_Result = "Not_Found" ;

    }

}