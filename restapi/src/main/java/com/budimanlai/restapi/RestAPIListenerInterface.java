package com.budimanlai.restapi;

import com.android.volley.VolleyError;

public interface RestAPIListenerInterface {

    /**
     * Process the response if HTTP Status is 200
     */
    void onSuccessHandler(String response, RestAPIResponseInterface handler);

    /**
     * Process the response if HTTP Status is not 200 or error
     */
    void onErrorHandler(VolleyError error, RestAPIResponseInterface handler);
}
