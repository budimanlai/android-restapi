package com.budimanlai.restapi;

import android.util.Log;

import com.android.volley.NetworkError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class RestAPIJSONListener implements RestAPIListenerInterface {
    @Override
    public void onSuccessHandler(String response, RestAPIResponseHandler handler) {
        try {
            JSONObject jsonObject = new JSONObject(response);

            if (jsonObject.getBoolean("success")) {
                Object object = jsonObject.get("data");
                if (object instanceof JSONObject) {
                    handler.onSuccess((JSONObject) object, jsonObject.toString());
                } else if (object instanceof JSONArray) {
                    handler.onSuccess((JSONArray) object, jsonObject.toString());
                } else {
                    handler.onError("Data format must is JSON Object or JSON Array", jsonObject);
                }
            } else {
                handler.onError(jsonObject.getString("message"), jsonObject);
            }
        } catch (JSONException e) {
            handler.onError(e.getMessage(), null);
        }
    }

    @Override
    public void onErrorHandler(VolleyError error,  RestAPIResponseHandler handler) {
        String msgError = "";
        JSONObject jsonObject = null;

        if (error instanceof NetworkError) {
            msgError = "Failed to connect to server";
        } else if (error instanceof TimeoutError) {
            msgError = "Timeout for connection exceeded";
        } else {
            if (error.networkResponse != null && error.networkResponse.data != null) {
                msgError = new String(error.networkResponse.data, StandardCharsets.UTF_8);

                if (isJSONObject(msgError)) {
                    try {
                        jsonObject = new JSONObject(msgError);
                        msgError = jsonObject.getString("message");
                    } catch (JSONException e) {
                        Log.e("TAG", "JSONException: " + e.getMessage());
                        jsonObject = null;
                        msgError = e.getMessage();
                    }
                }
            } else {
                msgError = error.getMessage();
            }
        }

        handler.onError(msgError, jsonObject);
    }

    public boolean isJSONObject(String test) {
        try {
            new JSONObject(test);
        } catch (JSONException ex) {
            // edited, to include @Arthur's comment
            // e.g. in case JSONArray is valid as well...
            try {
                new JSONArray(test);
            } catch (JSONException ex1) {
                return false;
            }
        }
        return true;
    }
}