package com.example.eva2_7_sqlite2;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Consulta extends AppCompatActivity {

    ListView listView;

    ArrayAdapter adaptador;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        listView = findViewById(R.id.lista);
        ArrayList<String> lista = new ArrayList<>();
        SQLiteDatabase db = this.openOrCreateDatabase(
                "myfriendsDB-db",
                MODE_PRIVATE,
                null);


        String consulta = "select * from tblAMIGO";
        Cursor c1 = db.rawQuery(consulta,null);

        c1.moveToPosition(-1);
        while ( c1.moveToNext() ){
            int recId = c1.getInt(0);
            String name = c1.getString(1);
            String phone = c1.getString(c1.getColumnIndex("phone"));
            lista.add(recId+", "+name+", "+phone);
        }

        adaptador = new ArrayAdapter(this, android.R.layout.simple_list_item_1,lista);
        listView.setAdapter(adaptador);
    }
}
