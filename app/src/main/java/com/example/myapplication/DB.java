package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * Created by 101 on 20.04.2017.
 */

public class DB {
    public static final String DB_NAME = "database_2";
    public static final int DB_VERSION = 2;
    public static final String TB_NAME = "Zam";

    public static final String COLUMN_ID = "_id";
    public static final String NAME_ZAM = "NAME_ZAM";
    public static final String TEXT_ZAM = "TEXT_ZAM";

    public static final String DB_CREATE = "CREATE TABLE " + TB_NAME + "("
            + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," + TEXT_ZAM + " TEXT,"
            + NAME_ZAM + " TEXT" + ");";

    public  final Context mCtx;

    public DBHelper mDBHelper;
    public SQLiteDatabase mDB;

    public DB(Context ctx) {
        mCtx = ctx;
    }

    public void open() {
        mDBHelper = new DBHelper(mCtx, DB_NAME, null, DB_VERSION);
        mDB = mDBHelper.getWritableDatabase();
    }

    public void close() {
        if (mDBHelper != null)
                mDBHelper.close();
    }

    public Cursor getAllData() {

        return mDB.query(TB_NAME, null, null, null, null, null, null);
    }

    public void addRec(String txt_1, String txt_2) {
        ContentValues cv = new ContentValues();
        cv.put(NAME_ZAM, txt_1);
        cv.put(TEXT_ZAM, txt_2);
        mDB.insert(TB_NAME, null, cv);
    }

    public void delRec(long id) {

        mDB.delete(TB_NAME, COLUMN_ID + " = " + id, null);
    }

    public class DBHelper extends SQLiteOpenHelper {

        public DBHelper(Context context, String name, CursorFactory factory,
                        int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(DB_CREATE);
        }
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        }
    }
}
