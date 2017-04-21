package com.example.myapplication;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.menu.ListMenuItemView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.view.Menu;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemClickListener;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity{


    public List<String> values = new ArrayList<String>();
    final String LOG_TAG = "myLogs";
    ListView listView;
    DB db;
    Cursor cursor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        db = new DB(this);
        db.open();

        cursor = db.getAllData();
        if(cursor!=null && cursor.moveToFirst()){
            do{
                String name  = cursor.getString(cursor.getColumnIndexOrThrow (db.NAME_ZAM));
                values.add(name);
            }while(cursor.moveToNext());
        }


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1, values);

        listView = (ListView) findViewById(R.id.list_item);
        listView.setAdapter(adapter);
        listView.setOnClickListener(new AdapterView.OnItemClickListener() {
          @Override
            public void onItemClick(AdapterView<?> parent, View itemClicked, int position, long id) {
               TextView textView = (TextView) itemClicked;
                String strText = textView.getText().toString(); // получаем текст нажатого элемента
                String sql = "SELECT " + db.TEXT_ZAM + " FROM " + db.TB_NAME + " WHERE "
                        + db.NAME_ZAM + " = " + strText + ";";
                SQLiteDatabase database = db.mDBHelper.getWritableDatabase();
                Cursor text_zametki = database.execSQL(sql);
                Intent intent = new Intent(this, Create.class);
                intent.putExtra("text", text_zametki);
                startActivity(intent);
            }
        }
    );
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.iCreate: {
                SQLiteDatabase database = db.mDBHelper.getWritableDatabase();
                startActivity(new Intent(this, Edit.class));
                cursor.close();
                return true;
            }
            default:
                break;
        }
        return super.onOptionsItemSelected(item);


    }



}

