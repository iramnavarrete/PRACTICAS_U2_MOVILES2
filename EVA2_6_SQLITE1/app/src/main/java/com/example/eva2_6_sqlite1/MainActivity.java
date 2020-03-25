package com.example.eva2_6_sqlite1;

import androidx.appcompat.app.AppCompatActivity;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SQLiteDatabase db = this.openOrCreateDatabase(
                "myfriendsDB-db",
                MODE_PRIVATE,
                null);
        db.beginTransaction();
        try {
            //perform your database operations here ...
            db.execSQL("create table tblAMIGO ("
                    + " recID integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " phone text ); " );

            db.execSQL( "insert into tblAMIGO(name, phone) values ('AAA', '555-1111');" );
            db.execSQL( "insert into tblAMIGO(name, phone) values ('BBB', '555-2222');" );
            db.execSQL( "insert into tblAMIGO(name, phone) values ('CCC', '555-3333');" );
            db.setTransactionSuccessful(); //commit your changes
            Toast.makeText(this,"Se pudieron agregar los datos",Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteException e) {
            //report problem
            Toast.makeText(this,"No se oudieron agregar los datos",Toast.LENGTH_SHORT).show();
        }
        finally {
            db.endTransaction();
        }
    }
}
