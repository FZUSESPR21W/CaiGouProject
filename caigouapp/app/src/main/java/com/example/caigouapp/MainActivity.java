package com.example.caigouapp;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import org.litepal.LitePal;

public class MainActivity extends AppCompatActivity {

    //通知购物车刷新界面
    public int flush = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupWithNavController(navView, navController);
        //LitePal.initialize(this);
        LitePal.getDatabase();
        if(getIntent().getIntExtra("shoppingCar",0) != 0){
            navController.navigate(R.id.navigation_shopping);
        }
    }

}