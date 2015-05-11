package com.androidhive.pushnotifications;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class Login extends Activity {
private EditText txtUser;
    public static final int DIALOG_DOWNLOAD_JSON_PROGRESS = 0;
    public static final int DIALOG_DOWNLOAD_FULL_PHOTO_PROGRESS = 1;
    private ProgressDialog mProgressDialog;
    private StringBuilder str;// str = new StringBuilder();
    ArrayList<HashMap<String, Object>> MyArrList;
    SharedPreferences sharedpreferences;
    public static String API_KEY,API_SECRET,TOKEN;
    public static String LIMIT = "100";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUser =  (EditText)findViewById(R.id.txtUsername);
        str = new StringBuilder();
        Button btn = (Button) findViewById(R.id.bntlogin);
        btn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                if(txtUser.getText().toString().length()<=0){
                    return;
                }
//
                //new DownloadJSONFileAsync().execute();

                new DownloadJSONFileAsync2().execute();


                //HttpClient client = new DefaultHttpClient();
                //HttpGet request = new HttpGet("https://qav2api.pbapp.net/Service/recentActivities?offset=0&limit=10&api_key=abc");

//                StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
//                StrictMode.setThreadPolicy(policy);
//
//                try {
//                    HttpClient client = new DefaultHttpClient();
//                    String getURL = "https://qav2api.pbapp.net/Service/recentActivities?offset=0&limit=10&api_key=abc";
//                    HttpGet get = new HttpGet(getURL);
//                    HttpResponse responseGet = client.execute(get);
//                    HttpEntity resEntityGet = responseGet.getEntity();
//                    if (resEntityGet != null) {
//                        // do something with the response
//                        String response = EntityUtils.toString(resEntityGet);
//                        Log.i("GET RESPONSE", response);
//                    }
//                } catch (Exception e) {
//                    e.printStackTrace();
//                }

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
//    public void onclick_login(View v){
////        Intent myIntent = new Intent(Login.this, setup.class);
////        myIntent.putExtra("UserLogin", txtUser.getText().toString()); //Optional parameters
////        Login.this.startActivity(myIntent);
//    }

    public void onclick_login2(View v) {
        // does something very interesting
    }

    public class DownloadJSONFileAsync extends AsyncTask<String, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
        }

        @Override
        protected Void doInBackground(String... params) {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("https://api.pbapp.net/Auth");

                try {
                    // Add your data
                    List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
                    API_KEY = "abc";
                    API_SECRET = "abcde";
                    nameValuePairs.add(new BasicNameValuePair("api_key", API_KEY));
                    nameValuePairs.add(new BasicNameValuePair("api_secret", API_SECRET));
                    httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

                    // Execute HTTP Post Request
                    HttpResponse response = httpclient.execute(httppost);

                   // HttpResponse response = client.execute(httpGet);
                    StatusLine statusLine = response.getStatusLine();
                    int statusCode = statusLine.getStatusCode();
                    if (statusCode == 200) { // Download OK
                        HttpEntity entity = response.getEntity();
                        InputStream content = entity.getContent();
                        BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                        String line;
                        while ((line = reader.readLine()) != null) {
                            str.append(line);
                        }
                    } else {
                        Log.e("Log", "Failed to download file..");
                    }


                } catch (ClientProtocolException e) {
                    // TODO Auto-generated catch block
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                }




            return null;
        }

        protected void onPostExecute(Void unused) {
            dismissDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
            removeDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
            JSONArray data;
            try {
                data = new JSONArray("["+str.toString()+"]");
                JSONObject c2 = data.getJSONObject(0);
                data = new JSONArray("["+c2.getString("response")+"]");
                MyArrList = new ArrayList<HashMap<String, Object>>();
                HashMap<String, Object> map;
                    JSONObject c = data.getJSONObject(0);
                    map = new HashMap<String, Object>();
                TOKEN=(String)c.getString("token");
                    map.put("token", (String)c.getString("token"));
                    MyArrList.add(map);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            System.out.println("====================================");
            System.out.println(MyArrList);
            System.out.println(str.toString());
            System.out.println("====================================");
        Intent myIntent = new Intent(Login.this, setup.class);
        myIntent.putExtra("UserLogin", txtUser.getText().toString()); //Optional parameters
        Login.this.startActivity(myIntent);

        }


    }
    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_JSON_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Downloading.....");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(true);
                mProgressDialog.show();
                return mProgressDialog;
            case DIALOG_DOWNLOAD_FULL_PHOTO_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Downloading.....");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(true);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }
    private void savePreferences(String key, String value) {
//        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        Editor editor = sharedPreferences.edit();
//        editor.putString(key, value);
//        editor.commit();
    }
    public class DownloadJSONFileAsync2 extends AsyncTask<String, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
        }

        @Override
        protected Void doInBackground(String... params) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);


            StringBuilder str = new StringBuilder();
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet("https://qav2api.pbapp.net/Service/recentActivities?offset=0&limit=10&api_key=abc");
            try {
                HttpResponse response = client.execute(httpGet);
                StatusLine statusLine = response.getStatusLine();
                int statusCode = statusLine.getStatusCode();
                if (statusCode == 200) { // Download OK
                    HttpEntity entity = response.getEntity();
                    InputStream content = entity.getContent();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(content));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        str.append(line);
                    }
                } else {
                    Log.e("Log", "Failed to download file..");
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
System.out.println(str.toString());
            //Log.i(str.toString()+"");
            return null;
        }

        protected void onPostExecute(Void unused) {
            dismissDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
            removeDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);

        }


    }

}
