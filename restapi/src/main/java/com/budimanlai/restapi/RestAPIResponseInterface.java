package com.budimanlai.restapi;

import org.json.JSONArray;
import org.json.JSONObject;

public interface RestAPIResponseInterface {
    public void onSuccess(JSONObject jsonObject, String jsonString);
    public void onSuccess(JSONArray jsonObject, String jsonString);
    public void onError(String message, JSONObject jsonObject);
}