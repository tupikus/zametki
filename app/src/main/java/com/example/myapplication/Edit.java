package com.example.myapplication;

import android.content.ClipData;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.MenuView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;


public class Edit extends AppCompatActivity {

    EditText etNameZ;
    EditText etZam;
    DB db;
    final String LOG_TAG = "myLog";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        etNameZ = (EditText) findViewById(R.id.etNameZ);
        etZam = (EditText) findViewById(R.id.etZam);

        db = new DB(this);
        db.open();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.edit_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        String namez = etNameZ.getText().toString();
        String zam = etZam.getText().toString();
        //SQLiteDatabase database = db.mDBHelper.getWritableDatabase();
        switch (item.getItemId()) {
            case R.id.iSave: {
                Log.d(LOG_TAG, namez + zam);
                db.addRec(namez, zam);
                startActivity(new Intent(this, MainActivity.class));
                return true;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

}

