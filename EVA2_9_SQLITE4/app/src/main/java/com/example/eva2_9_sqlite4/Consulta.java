package com.example.eva2_9_sqlite4;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Consulta extends AppCompatActivity {

    ListView listView;

    ArrayAdapter adaptador;

    String sRutaSD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consulta);
        listView = findViewById(R.id.lista);
        ArrayList<String> lista = new ArrayList<>();
        sRutaSD = getExternalFilesDir(null).getPath();//Para crear una carpeta en Android/data y guardarlo ahí
        SQLiteDatabase db = this.openOrCreateDatabase(
                sRutaSD+"/myfriendsDB-db",
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
