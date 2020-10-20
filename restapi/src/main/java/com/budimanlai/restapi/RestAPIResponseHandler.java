package com.budimanlai.restapi;

import org.json.JSONArray;
import org.json.JSONObject;

public class RestAPIResponseHandler implements RestAPIResponseInterface {
    @Override
    public void onSuccess(JSONObject jsonObject, String jsonString) {

    }

    @Override
    public void onSuccess(JSONArray jsonObject, String jsonString) {

    }

    @Override
    public void onError(String message, JSONObject jsonObject) {

    }
}
