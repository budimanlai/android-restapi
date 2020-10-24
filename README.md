Android Rest API is volley http client for API Restful

Install:

Step 1. Add the JitPack repository to your build file
Add it in your root build.gradle at the end of repositories:

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

Step 2. Add the dependency
```
dependencies {
    implementation 'com.github.budimanlai:android-restapi:1.0'
}
```

How to use GET method:
```
import com.budimanlai.restapi.RestAPIBase;

RestAPIBase api = new RestAPIBase(this, "base_url");
api.get("country", new RestAPIResponseHandler(){
	@Override
	public void onSuccess(JSONObject jsonObject, String jsonString) {
		// process your data
		Log.d("TAG", jsonString);
	}

	@Override
		public void onError(String message, JSONObject jsonObject) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
});
```

How to use POST JSON method:

```
import com.budimanlai.restapi.RestAPIBase;

Map<String, String> params = new HashMap<>();
// or you can use JSONObject as paramenter
// JSONObject params = new JSONObject();

params.put("username", "demo");
params.put("password", "demo123");

RestAPIBase api = new RestAPIBase(this, "base_url");
api.postJSON("login", params, new RestAPIResponseHandler(){
	@Override
	public void onSuccess(JSONObject jsonObject, String jsonString) {
		// process your data
		Log.d("TAG", jsonString);
	}

	@Override
		public void onError(String message, JSONObject jsonObject) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}
});
```
