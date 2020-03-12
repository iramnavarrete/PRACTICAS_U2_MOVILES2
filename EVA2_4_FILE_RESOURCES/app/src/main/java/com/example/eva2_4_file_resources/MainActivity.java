package com.example.eva2_4_file_resources;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class MainActivity extends AppCompatActivity {

    TextView txtlorem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtlorem = findViewById(R.id.textlorem);
    }

    @Override
    protected void onStart() {
        super.onStart();
        InputStream is = getResources().openRawResource(R.raw.xxx);
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);
        String sCade;

            try {
                while ((sCade = br.readLine())!= null) {
                    txtlorem.append(sCade);
                }
                br.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



    }
}
