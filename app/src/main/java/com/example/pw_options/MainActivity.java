package com.example.pw_options;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity {

BottomNavigationView bottomNavigationView;
private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bottomNavigationView = findViewById(R.id.bottom_nav);
        setFragment(new FirstFragment());

        bottomNavigationView.setOnItemSelectedListener(new BottomNavigationView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int idItem = item.getItemId();
                if (idItem == R.id.first_item) {
                    setFragment(new FirstFragment());
                    return true;
                } else if (idItem == R.id.second_item) {
                    setFragment(new SecondFragment());
                    return true;
                } else if (idItem == R.id.third_item) {
                    setFragment(new ThirdFragment());
                    return true;
                }
                return false;
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int idItem = item.getItemId();
        if (idItem == R.id.action_settings) {
            Toast.makeText(this ,"Настройки", Toast.LENGTH_LONG).show();
            return true;
        } else if (idItem == R.id.save_settings) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
            if (currentFragment instanceof FirstFragment) {
                TextView textView = currentFragment.getView().findViewById(R.id.textView);
                textView.setText("Спаси, но не сохраняй");
            }
            return true;
        } else if (idItem == R.id.open_settings) {
            Fragment currentFragment = getSupportFragmentManager().findFragmentById(R.id.fragment_layout);
            if (currentFragment instanceof FirstFragment) {
                TextView textView = currentFragment.getView().findViewById(R.id.textView);
                textView.setText("Открой душу");
            }
        }
        return super.onOptionsItemSelected(item);
    }

    public void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_layout, fragment, null)
                .commit();
    }

}