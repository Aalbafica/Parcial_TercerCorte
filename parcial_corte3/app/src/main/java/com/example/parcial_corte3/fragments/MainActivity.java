package com.example.parcial_corte3.fragments;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.parcial_corte3.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    // inicializacion del primer fragment
    Home inicio1 = new Home();
    // inicializacion del 2do fragment
    Solicitar_fragment soli1 = new Solicitar_fragment();
    // inicializacion del 3er fragment
    Config_fragment3 config1 = new Config_fragment3();

    FrameLayout frm_home;
    BottomNavigationView btn_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        frm_home = findViewById(R.id.frm_home);
        btn_menu = findViewById(R.id.btn_menu);

        btn_menu.setOnItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                if(item.getItemId() == R.id.naf_home){
                    loadFragment(inicio1);
                    return true;
                }else{
                    if(item.getItemId() == R.id.naf_solicitar){
                        loadFragment(soli1);
                    }else{
                        if(item.getItemId() == R.id.naf_config){
                            loadFragment(config1);
                        }
                    }
                }


                return false;
            }
        });

    }

    private void loadFragment(Fragment fr) {
        FragmentTransaction tr = getSupportFragmentManager().beginTransaction();
        tr.replace(R.id.frm_home,fr);
        tr.commit();
    }
}