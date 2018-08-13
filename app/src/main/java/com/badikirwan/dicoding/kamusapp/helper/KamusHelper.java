package com.badikirwan.dicoding.kamusapp.helper;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;

import com.badikirwan.dicoding.kamusapp.R;
import com.badikirwan.dicoding.kamusapp.activity.PreloadActivity;
import com.badikirwan.dicoding.kamusapp.db.DatabaseHelper;
import com.badikirwan.dicoding.kamusapp.model.KamusModel;

import java.util.ArrayList;

import static android.provider.BaseColumns._ID;
import static com.badikirwan.dicoding.kamusapp.db.DatabaseContract.KamusColums.ARTI;
import static com.badikirwan.dicoding.kamusapp.db.DatabaseContract.KamusColums.KATA;
import static com.badikirwan.dicoding.kamusapp.db.DatabaseContract.TABLE_ENG_TO_IND;
import static com.badikirwan.dicoding.kamusapp.db.DatabaseContract.TABLE_IND_TO_ENG;

public class KamusHelper {

    private Context context;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private String table;

    public KamusHelper(Context context) {
        this.context = context;
    }

    public KamusHelper open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void beginTransaction() {
        database.beginTransaction();
    }

    public void setTransactionSuccess() {
        database.setTransactionSuccessful();
    }

    public void endTransaction() {
        database.endTransaction();
    }

    public ArrayList<KamusModel> getDataByName(String search, String lang) {
        if (lang.equals("ENG")) {
            table = TABLE_ENG_TO_IND;
        } else {
            table = TABLE_IND_TO_ENG;
        }

        Cursor cursor = database.query(table,null,KATA+" LIKE ?",new String[]{search.trim()+"%"},null,null,_ID + " ASC",null);
        cursor.moveToFirst();

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public ArrayList<KamusModel> getAllData(String lang) {
        if (lang.equals("ENG")) {
            table = TABLE_ENG_TO_IND;
        } else {
            table = TABLE_IND_TO_ENG;
        }
        Cursor cursor = database.query(table,null,null,null,null,null,_ID+ " ASC",null);
        cursor.moveToFirst();

        ArrayList<KamusModel> arrayList = new ArrayList<>();
        KamusModel kamusModel;
        if (cursor.getCount() > 0) {
            do {
                kamusModel = new KamusModel();
                kamusModel.setId(cursor.getInt(cursor.getColumnIndexOrThrow(_ID)));
                kamusModel.setWord(cursor.getString(cursor.getColumnIndexOrThrow(KATA)));
                kamusModel.setTranslate(cursor.getString(cursor.getColumnIndexOrThrow(ARTI)));

                arrayList.add(kamusModel);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public void insertTransaction(KamusModel kamusModel, String lang) {
        if (lang.equals("ENG")) {
            table = TABLE_ENG_TO_IND;
        } else {
            table = TABLE_IND_TO_ENG;
        }
        String sql = "INSERT INTO "+table+" ("+KATA+", "+ARTI+") VALUES (?, ?)";
        SQLiteStatement stmt = database.compileStatement(sql);
        stmt.bindString(1, kamusModel.getWord());
        stmt.bindString(2, kamusModel.getTranslate());
        stmt.execute();
        stmt.clearBindings();
    }

    public long insert(KamusModel kamusModel, String lang) {
        if (lang.equals("ENG")) {
            table = TABLE_ENG_TO_IND;
        } else {
            table = TABLE_IND_TO_ENG;
        }
        ContentValues initialValues =  new ContentValues();
        initialValues.put(KATA, kamusModel.getWord());
        initialValues.put(ARTI, kamusModel.getTranslate());
        return database.insert(table, null, initialValues);
    }

    public int update(KamusModel kamusModel, String lang) {
        if (lang.equals("ENG")) {
            table = TABLE_ENG_TO_IND;
        } else {
            table = TABLE_IND_TO_ENG;
        }
        ContentValues args = new ContentValues();
        args.put(KATA, kamusModel.getWord());
        args.put(ARTI, kamusModel.getTranslate());
        return database.update(table, args, _ID + "= '" + kamusModel.getId() + "'", null);
    }

    public int delete(int id, String lang) {
        if (lang.equals("ENG")) {
            table = TABLE_ENG_TO_IND;
        } else {
            table = TABLE_IND_TO_ENG;
        }
        return database.delete(table, _ID + " = '"+id+"'", null);
    }

}
