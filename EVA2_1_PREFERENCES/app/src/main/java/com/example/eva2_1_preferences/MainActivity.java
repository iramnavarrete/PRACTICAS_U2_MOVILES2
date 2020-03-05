package com.example.eva2_1_preferences;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.app.FragmentTransaction;
import android.os.Bundle;
import android.preference.PreferenceFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getFragmentManager().beginTransaction();
        ft.replace(android.R.id.content, new MisPreferencias());
        ft.commit();

    }

    public static class MisPreferencias extends PreferenceFragment{
        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            addPreferencesFromResource(R.xml.preferences_screen);

        }
    }
}


