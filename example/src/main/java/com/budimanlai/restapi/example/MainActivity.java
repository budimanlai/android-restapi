package com.budimanlai.restapi.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.budimanlai.restapi.RestAPIResponseHandler;
import com.budimanlai.restapi.example.library.MyAppCompatActivity;
import com.budimanlai.restapi.example.library.MyApplication;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends MyAppCompatActivity {

    private EditText etUsername;
    private EditText etPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etUsername = findViewById(R.id.etUsername);
        etPassword = findViewById(R.id.etPassword);

        final Button btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(mLogin);

        etUsername.setText("demo");
        etPassword.setText("demo123");
    }

    private View.OnClickListener mLogin = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String username = etUsername.getText().toString();
            String password = etPassword.getText().toString();

            if (username.isEmpty()) {
                showDialog("Username can't blank");
                return;
            }

            if (password.isEmpty()) {
                showDialog("Password can't blank");
                return;
            }

            Map<String, String> params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);

            MyApplication.getInstance().api.postJSON("login", params, new RestAPIResponseHandler(){
                @Override
                public void onSuccess(JSONObject jsonObject, String jsonString) {
                    Log.d("TAG", jsonString);

                    Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                    startActivity(intent);
                }

                @Override
                public void onError(String message, JSONObject jsonObject) {
                    showDialog(message);
                }
            });
        }
    };
}