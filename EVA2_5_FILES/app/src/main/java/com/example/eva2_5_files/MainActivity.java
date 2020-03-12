package com.example.eva2_5_files;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    EditText textView;
    Button leer, guardar;
    final String archivo="w.txt";
    final int PERMISO_ESCRITURA = 1000;
    String sRutaSD;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        //AQUÍ HABRÍA QUE TOMAT DECISIONES SOBRE LOS PERMISOS
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.editText);
        leer = findViewById(R.id.buttonread);
        guardar = findViewById(R.id.buttonsave);
        sRutaSD = Environment.getExternalStorageDirectory().getAbsolutePath();//Obtener la ruta externa
        Toast.makeText(this,sRutaSD,Toast.LENGTH_SHORT).show();
        if(ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            //Arreglo de permisos porque pueden ser varios permisos
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, PERMISO_ESCRITURA);
        }

    }

    public void onRead(View v)  {
        try {
            //InputStream is = openFileInput(archivo); //para memoria interna
            //Para tarjeta memoria
            FileInputStream fis = new FileInputStream(sRutaSD +"/"+ archivo);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            String sCade;

            try {
                while ((sCade = br.readLine()) != null) {
                    textView.append(sCade);

                }
                br.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    public void onSave(View v){
        try {
            //OutputStream os = openFileOutput(archivo, 0); //Para memeoria interna
            //para SD
            FileOutputStream fos = new FileOutputStream(sRutaSD+"/"+archivo);
            Toast.makeText(this,sRutaSD+"/"+archivo,Toast.LENGTH_SHORT).show();
            OutputStreamWriter osw = new OutputStreamWriter(fos);
            BufferedWriter bw = new BufferedWriter(osw);
            try{
            bw.write(textView.getText().toString());
            bw.close();
        }catch (FileNotFoundException e){
                e.printStackTrace();
            }} catch (Exception e) {
            e.printStackTrace();
        }
    }
}
