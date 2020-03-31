package com.example.eva2_9_sqlite4;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {


    EditText txtNombre, txtTelefono;
    Button btnIngresa, btnConsulta;
    String sRutaSD;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.editTextNombre);
        txtTelefono = findViewById(R.id.editTextTel);
        btnIngresa = findViewById(R.id.buttonIngresa);
        btnConsulta = findViewById(R.id.buttonConsulta);



        sRutaSD = getExternalFilesDir(null).getPath();//Para crear una carpeta en Android/data y guardarlo ahí
        Toast.makeText(this,sRutaSD+"/myfriendsDB-db",Toast.LENGTH_SHORT).show();
        final SQLiteDatabase db = this.openOrCreateDatabase(
                sRutaSD+"/myfriendsDB-db",
                MODE_PRIVATE,
                null);
        db.beginTransaction();
        try {
            //perform your database operations here ...
            db.execSQL("create table tblAMIGO ("
                    + " recID integer PRIMARY KEY autoincrement, "
                    + " name text, "
                    + " phone text ); " );
            db.setTransactionSuccessful(); //commit your changes
            Toast.makeText(getApplicationContext(),"Se creó la tabla",Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteException e) {
            //report problem
            //Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally {
            db.endTransaction();
        }


        btnIngresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.beginTransaction();
                try {
                    db.execSQL( "insert into tblAMIGO(name, phone) values ('"+txtNombre.getText().toString()+"', '"+txtTelefono.getText().toString()+"');" );
                    db.setTransactionSuccessful(); //commit your changes
                    Toast.makeText(getApplicationContext(),"Se pudieron agregar los datos",Toast.LENGTH_SHORT).show();
                }
                catch (SQLiteException e) {
                    //report problem

                    Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
                }
                finally {
                    db.endTransaction();
                }
            }
        });

        btnConsulta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Consulta.class);
                startActivity(intent);
            }
        });
    }
}
