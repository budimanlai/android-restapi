package com.budimanlai.restapi.example.library;

import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

public class MyAppCompatActivity extends AppCompatActivity {

    public void showDialog(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
