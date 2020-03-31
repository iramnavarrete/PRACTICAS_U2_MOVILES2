package com.example.eva2_8_sqlite3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Actualiza extends AppCompatActivity {

    EditText editTextNombre, editTextTelefono;
    Button buttonActualiza;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualiza);
        editTextNombre = findViewById(R.id.editTextNombreA);
        editTextTelefono = findViewById(R.id.editTextTelA);
        buttonActualiza = findViewById(R.id.buttonActualiza);

        editTextNombre.setText(getIntent().getStringExtra("name"));
        editTextTelefono.setText(getIntent().getStringExtra("phone"));


        final SQLiteDatabase db = this.openOrCreateDatabase(
                "myfriendsDB-db",
                MODE_PRIVATE,
                null);

        buttonActualiza.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.beginTransaction();
                try {
                    ContentValues updateValues = new ContentValues();
                    updateValues.put("name",editTextNombre.getText().toString());
                    updateValues.put("phone",editTextTelefono.getText().toString());
                    String[] whereArgs = {getIntent().getStringExtra("id")};
                    db.update("tblAMIGO", updateValues, "recID=?",whereArgs);
                    db.setTransactionSuccessful(); //commit your changes
                    Toast.makeText(getApplicationContext(),"Se pudieron actuializar los datos",Toast.LENGTH_SHORT).show();
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
        });

    }
}
