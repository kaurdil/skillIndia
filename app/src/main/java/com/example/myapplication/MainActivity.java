package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.example.myapplication.Data.DatabaseHelper;
import com.example.myapplication.Data.company_signup;
import com.example.myapplication.Data.signup_form2;
import com.example.myapplication.company.company_login;
import com.example.myapplication.jobseekers.hiree_login;


public class MainActivity extends AppCompatActivity {
    SQLiteDatabase sqLiteDatabaseObj;
    EditText u,p ;
    DatabaseHelper sqLiteHelper;
    String user, PasswordHolder;
    Boolean EditTextEmptyHolder;
    Cursor cursor;
    String TempPassword = "NOT_FOUND" ;

    public static final String Username = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        u= (EditText) findViewById(R.id.u1);
        p = (EditText) findViewById(R.id.p1);
       final RadioButton r1=(RadioButton)findViewById(R.id.r1);
        final RadioButton r2=(RadioButton)findViewById(R.id.r2);
        final RadioGroup rg=(RadioGroup)findViewById(R.id.rgg);
        Button b1=(Button)findViewById(R.id.button2);
        Button b2=(Button)findViewById(R.id.button4);
        final ImageView i =(ImageView) findViewById(R.id.imageView);
        sqLiteHelper = new DatabaseHelper(this);

        i.setImageResource(R.drawable.logo);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 // Calling EditText is empty or no method.
                if(r1.isChecked())
                {
                CheckEditTextStatus();

                // Calling login method.
                LoginFunction();
                }
               else if(r2.isChecked())
               {
                   CheckEditTextStatus();

                   // Calling login method.
                   LoginFunction2();
               }



            }
        });


        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(r1.isChecked()){

                    Intent intent = new Intent(MainActivity.this, company_signup.class);
                    startActivity(intent);
                }
                else if(r2.isChecked()){
                    Intent intent = new Intent(MainActivity.this, signup_form2.class);
                    startActivity(intent);
                }
            }
        });

    }
    public void LoginFunction(){

        if(EditTextEmptyHolder) {

            // Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            // Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(DatabaseHelper.TABLE_NAME, null, " " + DatabaseHelper.Table_Column_3_username + "=?", new String[]{user}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Table_Column_4_Password));

                    // Closing cursor.
                    cursor.close();
                }
            }

            // Calling method to check final result ..
            CheckFinalResult();

        }
        else {

            //If any of login EditText empty then this block will be executed.
            Toast.makeText(MainActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }

    }
    public void LoginFunction2(){

        if(EditTextEmptyHolder) {

            // Opening SQLite database write permission.
            sqLiteDatabaseObj = sqLiteHelper.getWritableDatabase();

            // Adding search email query to cursor.
            cursor = sqLiteDatabaseObj.query(DatabaseHelper.TABLE2_NAME, null, " " + DatabaseHelper.Table_Col7+ "=?", new String[]{user}, null, null, null);

            while (cursor.moveToNext()) {

                if (cursor.isFirst()) {

                    cursor.moveToFirst();

                    // Storing Password associated with entered email.
                    TempPassword = cursor.getString(cursor.getColumnIndex(DatabaseHelper.Table_Col8));

                    // Closing cursor.
                    cursor.close();
                }
            }

            // Calling method to check final result ..
            CheckFinalResult2();

        }
        else {

            //If any of login EditText empty then this block will be executed.
            Toast.makeText(MainActivity.this,"Please Enter UserName or Password.",Toast.LENGTH_LONG).show();

        }

    }

    // Checking EditText is empty or not.
    public void CheckEditTextStatus(){

        // Getting value from All EditText and storing into String Variables.
        user = u.getText().toString();
        PasswordHolder = p.getText().toString();

        // Checking EditText is empty or no using TextUtils.
        if( TextUtils.isEmpty(user) || TextUtils.isEmpty(PasswordHolder)){

            EditTextEmptyHolder = false ;

        }
        else {

            EditTextEmptyHolder = true ;
        }
    }

    // Checking entered password from SQLite database email associated password.
    public void CheckFinalResult(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {

            Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(MainActivity.this, company_login.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(Username, user);

            startActivity(intent);


        }
        else {

            Toast.makeText(MainActivity.this,"UserName or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND" ;

    }
    public void CheckFinalResult2(){

        if(TempPassword.equalsIgnoreCase(PasswordHolder))
        {

            Toast.makeText(MainActivity.this,"Login Successfully",Toast.LENGTH_LONG).show();

            // Going to Dashboard activity after login success message.
            Intent intent = new Intent(MainActivity.this, hiree_login.class);

            // Sending Email to Dashboard Activity using intent.
            intent.putExtra(Username, user);

            startActivity(intent);


        }
        else {

            Toast.makeText(MainActivity.this,"UserName or Password is Wrong, Please Try Again.",Toast.LENGTH_LONG).show();

        }
        TempPassword = "NOT_FOUND" ;

    }

}

