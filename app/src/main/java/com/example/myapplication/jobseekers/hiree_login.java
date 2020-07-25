package com.example.myapplication.jobseekers;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.myapplication.MainActivity;
import com.example.myapplication.R;

public class hiree_login extends AppCompatActivity {

    String nameHolder;
    TextView u;
    Button logOut,upd;
    public static final String Username="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hiree_login);


        u = (TextView)findViewById(R.id.textView2);
        upd = (Button)findViewById(R.id.button);
        logOut = (Button)findViewById(R.id.button1);

        Intent parentIntent = getIntent();

        // Receiving User Email Send By MainActivity.
        nameHolder = parentIntent.getStringExtra(MainActivity.Username);

        // Setting up received email to TextView.
        u.setText(u.getText().toString()+ nameHolder);

        // Adding click listener to Log Out button.
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Finishing current DashBoard activity on button click.
                finish();

                Toast.makeText(hiree_login.this,"Log Out Successfull", Toast.LENGTH_LONG).show();

            }
        });

        upd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(hiree_login.this, hiree_update.class);

                // Sending Email to Dashboard Activity using intent.
                intent.putExtra(Username, nameHolder);
                startActivity(intent);
            }
        });
    }
}
