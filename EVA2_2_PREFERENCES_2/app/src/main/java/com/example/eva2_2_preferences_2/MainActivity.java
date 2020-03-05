package com.example.eva2_2_preferences_2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(android.R.id.content, new MisPreferencias());
        ft.commit();
        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        Toast.makeText(this, sharedPreferences.getBoolean("checkbox_preference_screen_2", false) +"",Toast.LENGTH_SHORT).show();

    }

    public static class MisPreferencias extends PreferenceFragmentCompat {

        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {    //La llave que recibe es necesaria en el setPreferenceFromResource
            setPreferencesFromResource(R.xml.preference_screen, rootKey);
            
        }
    }
}
