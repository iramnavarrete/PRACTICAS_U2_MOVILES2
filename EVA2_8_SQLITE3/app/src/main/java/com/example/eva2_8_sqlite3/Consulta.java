package com.example.eva2_8_sqlite3;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Consulta extends AppCompatActivity {

    ListView listView;

    ArrayAdapter adaptador;

    @Override
    protected void onRestart() {
        super.onRestart();
        recreate();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        listView = findViewById(R.id.lista);
        ArrayList<String> lista = new ArrayList<>();
        final SQLiteDatabase db = this.openOrCreateDatabase(
                "myfriendsDB-db",
                MODE_PRIVATE,
                null);


        String consulta = "select * from tblAMIGO";
        final Cursor c1 = db.rawQuery(consulta,null);

        c1.moveToPosition(-1);
        while ( c1.moveToNext() ){
            int recId = c1.getInt(0);
            String name = c1.getString(1);
            String phone = c1.getString(c1.getColumnIndex("phone"));
            lista.add(recId+", "+name+", "+phone);
        }

        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        listView.setAdapter(adaptador);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(getIntent().getStringExtra("action").equals("edit")){
                    c1.moveToPosition(position);
                    Intent intent = new Intent(getApplicationContext(), Actualiza.class);
                    intent.putExtra("id",c1.getString(0));
                    intent.putExtra("name",c1.getString(1));
                    intent.putExtra("phone",c1.getString(2));
                    startActivity(intent);
                }
                else if(getIntent().getStringExtra("action").equals("delete")){
                    db.beginTransaction();
                    try {
                        c1.moveToPosition(position);
                        String[] whereArgs = {c1.getString(0)};
                        db.delete("tblAMIGO", "recID=?",whereArgs);
                        db.setTransactionSuccessful(); //commit your changes
                        Toast.makeText(getApplicationContext(),"Se pudo eliminar el registro",Toast.LENGTH_SHORT).show();
                        onBackPressed();
                    }
                    catch (SQLiteException e) {
                        //report problem

                        Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                    finally {
                        db.endTransaction();
                    }
                }
            }
        });

    }
}
