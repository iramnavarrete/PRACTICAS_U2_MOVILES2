package com.example.eva2_8_sqlite3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    EditText txtNombre, txtTelefono;
    Button btnIngresa, btnConsulta, btnElimina;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtNombre = findViewById(R.id.editTextNombre);
        txtTelefono = findViewById(R.id.editTextTel);
        btnIngresa = findViewById(R.id.buttonIngresa);
        btnConsulta = findViewById(R.id.buttonConsulta);
        btnElimina = findViewById(R.id.buttonElimina);

        final SQLiteDatabase db = this.openOrCreateDatabase(
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
            db.setTransactionSuccessful(); //commit your changes
            Toast.makeText(getApplicationContext(),"Se creó la tabla",Toast.LENGTH_SHORT).show();
        }
        catch (SQLiteException e) {
            //report problem
            Toast.makeText(getApplicationContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
        }
        finally {
            db.endTransaction();
        }


        btnIngresa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.beginTransaction();
                try {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("name",txtNombre.getText().toString());
                    contentValues.put("phone",txtTelefono.getText().toString());
                    db.insert("tblAMIGO", null, contentValues);
                    db.setTransactionSuccessful(); //commit your changes
                    Toast.makeText(getApplicationContext(),"Se pudieron agregar los datos",Toast.LENGTH_SHORT).show();
                    txtNombre.setText("");
                    txtTelefono.setText("");
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
                intent.putExtra("action", "edit");
                startActivity(intent);
            }
        });

        btnElimina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Consulta.class);
                intent.putExtra("action","delete");
                startActivity(intent);
            }
        });
    }
}
