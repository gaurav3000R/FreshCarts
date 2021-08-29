package com.example.freshcarts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.freshcarts.ui.login.LoginActivityfrash;

public class Splashscren extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscren);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Thread td =new  Thread(){
        public void run(){
            try {sleep(4000);

            }
            catch (Exception ex){
                ex.printStackTrace();
            }

            finally {
                Intent intent = new Intent(Splashscren.this, LoginActivityfrash.class);
                startActivity(intent);
                finish();
            }
            }
            };td.start();







    }
}
