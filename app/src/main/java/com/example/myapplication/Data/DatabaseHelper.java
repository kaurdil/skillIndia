package com.example.myapplication.Data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {


    static String DATABASE_NAME="UserDataBase";

    public static final String TABLE_NAME="companyusers";

    public static final String Table_Column_ID="id";

    public static final String Table_Column_1_Name="name";

    public static final String Table_Column_2_COMPID="comp_id";

    public static final String Table_Column_3_username="username";

    public static final String Table_Column_4_Password="password";


    public static final String TABLE2_NAME="jobseekers";
    public static final String Table_Col_ID="id";
    public static final String Table_Col1="name";
    public static final String Table_Col2="age";
    public static final String Table_Col3="gender";
    public static final String Table_Col4="skill";
    public static final String Table_Col5="experience";
    public static final String Table_Col6="phone";
    public static final String Table_Col7="username";
    public static final String Table_Col8="password";

    public DatabaseHelper(Context context) {

        super(context, DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase database) {

        String CREATE_TABLE="CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+Table_Column_ID+" INTEGER PRIMARY KEY, "+Table_Column_1_Name+" VARCHAR, "+Table_Column_2_COMPID+" VARCHAR, "+Table_Column_3_username+" VARCHAR,"+Table_Column_4_Password+" VARCHAR)";
        database.execSQL(CREATE_TABLE);
        String CREATE_TABLE2="CREATE TABLE IF NOT EXISTS "+TABLE2_NAME+" ("+Table_Col_ID+" INTEGER PRIMARY KEY, "+Table_Col1+" VARCHAR, "+Table_Col2+" INTEGER, "+Table_Col3+" VARCHAR,"+Table_Col4+" VARCHAR, "+Table_Col5+" VARCHAR, "+Table_Col6+" BIGINT, "+Table_Col7+" VARCHAR, "+Table_Col8+" VARCHAR)";
        database.execSQL(CREATE_TABLE2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS "+TABLE2_NAME);
        onCreate(db);

    }
    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE2_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }




}