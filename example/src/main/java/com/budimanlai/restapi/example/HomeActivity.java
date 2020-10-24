package com.budimanlai.restapi.example;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.budimanlai.restapi.RestAPIResponseHandler;
import com.budimanlai.restapi.example.adapter.CountryAdapter;
import com.budimanlai.restapi.example.library.MyAppCompatActivity;
import com.budimanlai.restapi.example.library.MyApplication;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Objects;

public class HomeActivity extends MyAppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        final ArrayList<String> countries = new ArrayList<>();
        final CountryAdapter adapter = new CountryAdapter(countries);

        RecyclerView rvCountry = findViewById(R.id.rvCountry);
        rvCountry.setLayoutManager(new LinearLayoutManager(this));
        rvCountry.setAdapter(adapter);

        MyApplication.getInstance().api.getJSON("country", new RestAPIResponseHandler(){
            @Override
            public void onSuccess(JSONObject jsonObject, String jsonString) {
                try {
                    JSONArray items = jsonObject.getJSONArray("data");
                    for(int i=0; i<items.length(); i++) {
                        JSONObject item = items.getJSONObject(i);
                        countries.add(item.getString("name"));
                    }

                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    Log.d("TAG", Objects.requireNonNull(e.getMessage()));
                }
            }

            @Override
            public void onError(String message, JSONObject jsonObject) {
                showDialog(message);
            }
        });
    }
}
