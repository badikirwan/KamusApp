package com.badikirwan.dicoding.kamusapp.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.badikirwan.dicoding.kamusapp.activity.PreloadActivity;

import static android.provider.BaseColumns._ID;
import static com.badikirwan.dicoding.kamusapp.db.DatabaseContract.KamusColums.ARTI;
import static com.badikirwan.dicoding.kamusapp.db.DatabaseContract.KamusColums.KATA;
import static com.badikirwan.dicoding.kamusapp.db.DatabaseContract.TABLE_ENG_TO_IND;
import static com.badikirwan.dicoding.kamusapp.db.DatabaseContract.TABLE_IND_TO_ENG;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "dbkamus";
    private static final int DATABASE_VERSION = 1;

    private static final String CREATE_TABLE_IND_TO_ENG = "create table "+ TABLE_IND_TO_ENG +"" +
            "("+ _ID +" integer primary key autoincrement,"+ KATA + "text not null," + ARTI + " text not null);";

    private static final String CREATE_TABLE_ENG_TO_IND = "create table "+ TABLE_ENG_TO_IND +"" +
            "("+ _ID +" integer primary key autoincrement,"+ KATA + "text not null," + ARTI + " text not null);";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_IND_TO_ENG);
        db.execSQL(CREATE_TABLE_ENG_TO_IND);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_IND_TO_ENG );
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_ENG_TO_IND);
        onCreate(db);
    }
}
