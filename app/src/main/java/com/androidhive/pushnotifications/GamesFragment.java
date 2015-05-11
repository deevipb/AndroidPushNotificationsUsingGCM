package com.androidhive.pushnotifications;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class GamesFragment extends Fragment {
    public static final int DIALOG_DOWNLOAD_JSON_PROGRESS = 0;
    public static final int DIALOG_DOWNLOAD_FULL_PHOTO_PROGRESS = 1;
    public static String URL;
    private ProgressDialog mProgressDialog;
    private StringBuilder str;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
        str = new StringBuilder();
		View rootView = inflater.inflate(R.layout.fragment_games, container, false);

//new DownloadJSONFileAsync().execute();
		return rootView;
	}

    public class DownloadJSONFileAsync extends AsyncTask<String, Void, Void> {

        protected void onPreExecute() {
            super.onPreExecute();
            //showDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
        }

        @Override
        protected Void doInBackground(String... params) {
            Login.LIMIT="100";
            Login.API_KEY="abc";
           // URL="https://qav2api.pbapp.net//Service/recentActivities?offset=0&limit="+Login.LIMIT+"&api_key="+Login.API_KEY;
            URL="https://qav2api.pbapp.net//Service/recentActivities?offset=0&limit=100&api_key=abc";
            HttpClient client = new DefaultHttpClient();
            HttpGet httpGet = new HttpGet(URL);
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


            return null;
        }

        protected void onPostExecute(Void unused) {
            //dismissDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
            //removeDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
            System.out.println(str.toString());
            /*
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
            */
            //System.out.println(MyArrList);


        }


    }

    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_JSON_PROGRESS:
                mProgressDialog = new ProgressDialog(getActivity());
                mProgressDialog.setMessage("Downloading.....");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(true);
                mProgressDialog.show();
                return mProgressDialog;
            case DIALOG_DOWNLOAD_FULL_PHOTO_PROGRESS:
                mProgressDialog = new ProgressDialog(getActivity());
                mProgressDialog.setMessage("Downloading.....");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mProgressDialog.setCancelable(true);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }
}
