package com.androidhive.pushnotifications;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
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
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;


public class Login extends Activity {
    private EditText txtUser;
    public static final int DIALOG_DOWNLOAD_JSON_PROGRESS = 0;
    public static final int DIALOG_DOWNLOAD_FULL_PHOTO_PROGRESS = 1;
    private ProgressDialog mProgressDialog;
    private StringBuilder str, strget;// str = new StringBuilder();
    ArrayList<HashMap<String, Object>> MyArrList;
    public static ArrayList<HashMap<String, Object>> ArrListFREED;
    SharedPreferences sharedpreferences;
    public static String API_KEY, API_SECRET, TOKEN;
    public static String LIMIT = "100";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        txtUser = (EditText) findViewById(R.id.txtUsername);
        str = new StringBuilder();
        strget = new StringBuilder();
        Button btn = (Button) findViewById(R.id.bntlogin);
        btn.setOnClickListener(new android.view.View.OnClickListener() {
            @Override
            public void onClick(android.view.View v) {
                test();
//                if (txtUser.getText().toString().length() <= 0) {
//                    return;
//                }
//
                //new DownloadJSONFileAsync().execute();

                //new DownloadJSONFileAsync2().execute();


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
public void test(){
    StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

    StrictMode.setThreadPolicy(policy);

    //String password= AccountManager.get(mContext).getPassword(account);
    //Authorization auth=null;
//    try {
//        GitHubClient client=new GitHubClient();
//        client.setUserAgent(USER_AGENT_STRING);
//        client.setCredentials(account.name,password);
//        OAuthService service=new OAuthService(client);
//        for (    Authorization a : service.getAuthorizations()) {
//            if (a != null && a.getNote() != null) {
//                if (a.getNote().equals(DESCRIPTION_CLIENT)) {
//                    auth=a;
//                }
//            }
//        }
//        if (auth == null) {
//            auth=new Authorization();
//            auth.setNote(DESCRIPTION_CLIENT);
//            auth.setNoteUrl(CLIENT_URL);
//            List<String> scopes=new ArrayList<String>();
//            scopes.add("user");
//            scopes.add("repo");
//            scopes.add("gist");
//            auth.setScopes(scopes);
//            auth=service.createAuthorization(auth);
//        }
//    }
//    catch (  IOException e) {
//        System.out.println();
//        //throw new NetworkErrorException(e);
//    }
    //String oauthToken=auth.getToken();

//    GitHubClient client = new GitHubClient();
//    client.setCredentials("sakchai.c@playbasis.com", "MyPayLogin@123");
//    OAuthService oauthService = new OAuthService();
//    try {
//    // Replace with actual login and password
//    oauthService.getClient().setCredentials("sakchai.c@playbasis.com", "MyPayLogin@123");
//
//    // Create authorization with 'gist' scope only
//    Authorization auth = new Authorization();
//        auth.addScopes(1,"");
//    auth.setScopes(Arrays.asList("user","repo","admin:repo_hook"));
//        //auth.setApp(Login.this);
//
//
//
//    auth = oauthService.createAuthorization(auth);
//System.out.println(auth.getToken());
    // Create Gist service configured with OAuth2 token
//    GistService gistService = new GistService();
//    gistService.getClient().setOAuth2Token(auth.getToken());
//
//    // Create Gist
//    Gist gist = new Gist();
//    gist.setPublic(false);
//    gist.setDescription("Created using OAuth2 token via Java API");
//    GistFile file = new GistFile();
//    file.setContent("Gist!");
//    file.setFilename("gist.txt");
//    gist.setFiles(Collections.singletonMap(file.getFilename(), file));
//    gist = gistService.createGist(gist);
//    System.out.println("Created Gist at " + gist.getHtmlUrl());
    //} catch (IOException e) {
    //    System.out.println(e+"");
    //}
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
                data = new JSONArray("[" + str.toString() + "]");
                JSONObject c2 = data.getJSONObject(0);
                data = new JSONArray("[" + c2.getString("response") + "]");
                MyArrList = new ArrayList<HashMap<String, Object>>();
                HashMap<String, Object> map;
                JSONObject c = data.getJSONObject(0);
                map = new HashMap<String, Object>();
                TOKEN = (String) c.getString("token");
                map.put("token", (String) c.getString("token"));
                MyArrList.add(map);
            } catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
           // System.out.println("====================================");
           // System.out.println(MyArrList);
           // System.out.println(str.toString());
           // System.out.println("====================================");

            new DownloadJSONFileAsync2().execute();
//            Intent myIntent = new Intent(Login.this, setup.class);
//            myIntent.putExtra("UserLogin", txtUser.getText().toString()); //Optional parameters
//            Login.this.startActivity(myIntent);

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
            //StringBuilder str = new StringBuilder();
            HttpClient client = getNewHttpClient();
            HttpGet httpGet = new HttpGet("https://qav2api.pbapp.net/Service/recentActivities?offset=0&limit=20&api_key=abc");
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
                        strget.append(line);
                    }
                } else {
                    Log.e("Log", "Failed to download file..");
                }
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println(strget.toString());
////////////////////////////////////////////////
            try {
                JSONArray data = new JSONArray("[" + strget.toString() + "]");
                JSONObject c2 = data.getJSONObject(0);
                data = new JSONArray("[" + c2.getString("response") + "]");

                JSONObject c3 = data.getJSONObject(0);
                JSONArray dataa = new JSONArray((String) c3.getString("activities"));
                ArrListFREED = new ArrayList<HashMap<String, Object>>();
                HashMap<String, Object> map;
                for(int i = 0; i < dataa.length(); i++){
                    JSONObject c = dataa.getJSONObject(i);
                    map = new HashMap<String, Object>();
                    //System.out.println((String)c.getString("event_type"));
                    //System.out.println(c);
                    map.put("event_type", (String)c.getString("event_type"));
                    if(((String)c.getString("event_type")).equals("REWARD")){
                        map.put("message", (String)c.getString("message"));
                    }else{
                        map.put("message", "");
                    }
                    map.put("action_name", (String)c.getString("action_name"));
                   // map.put("url", (String)c.getString("url"));
                    map.put("date_added", (String)c.getString("date_added"));
                    map.put("id", (String)c.getString("id"));
                    map.put("string_filter", (String)c.getString("string_filter"));
                    map.put("action_icon", (String)c.getString("action_icon"));
                    JSONArray data3 = new JSONArray("["+(String) c.getString("player")+"]");
                    JSONObject cplay = data3.getJSONObject(0);
                    map.put("cl_player_id", (String)cplay.getString("cl_player_id"));
                    map.put("image", (Bitmap)loadBitmap(cplay.getString("image")));
                    map.put("username", (String)cplay.getString("username"));
                    map.put("exp", (String)cplay.getString("exp"));
                    map.put("level", (String)cplay.getString("level"));
                    map.put("first_name", (String)cplay.getString("first_name"));
                    map.put("last_name", (String)cplay.getString("last_name"));
                    map.put("gender", (String)cplay.getString("gender"));
                    ArrListFREED.add(map);
                }
                System.out.println("=======================================================");
                //System.out.println(ArrListFREED);
                System.out.println(dataa);



            } catch (JSONException e) {
                e.printStackTrace();
            }

////////////////////////////////////////////////
//            JSONArray data;
//            try {
//                data = new JSONArray("["+strget.toString()+"]");
//                MyArrList = new ArrayList<HashMap<String, Object>>();
//                HashMap<String, Object> map;
//
//                for(int i = 0; i < data.length(); i++){
//                    JSONObject c = data.getJSONObject(i);
//                    map = new HashMap<String, Object>();
//                    map.put("ImageID", (String)c.getString("ImageID"));
//                    map.put("ImageName", (String)c.getString("ImageName"));
//
//                    // Thumbnail Get ImageBitmap To Object
//                    map.put("ImagePathThum", (String)c.getString("ImagePath_Thumbnail"));
//                    map.put("ImageThumBitmap", (Bitmap)loadBitmap(c.getString("ImagePath_Thumbnail")));
//
//                    // Full (for View Popup)
//                    map.put("ImagePathFull", (String)c.getString("ImagePath_FullPhoto"));
//
//                    MyArrList.add(map);
//                }
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }

            //////////////////////////
            return null;
        }

        public HttpClient getNewHttpClient() {
            try {
                KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
                trustStore.load(null, null);

                SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
                sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

                HttpParams params = new BasicHttpParams();
                HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
                HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

                SchemeRegistry registry = new SchemeRegistry();
                registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
                registry.register(new Scheme("https", sf, 443));

                ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

                return new DefaultHttpClient(ccm, params);
            } catch (Exception e) {
                return new DefaultHttpClient();
            }
        }

        protected void onPostExecute(Void unused) {
            dismissDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
            removeDialog(DIALOG_DOWNLOAD_JSON_PROGRESS);
            Intent myIntent = new Intent(Login.this, setup.class);
            myIntent.putExtra("UserLogin", txtUser.getText().toString()); //Optional parameters
            Login.this.startActivity(myIntent);

        }

        private static final String TAG = "Image";
        private static final int IO_BUFFER_SIZE = 4 * 1024;

        public Bitmap loadBitmap(String url) {
            Bitmap bitmap = null;
            InputStream in = null;
            BufferedOutputStream out = null;

            try {
                in = new BufferedInputStream(new URL(url).openStream(), IO_BUFFER_SIZE);

                final ByteArrayOutputStream dataStream = new ByteArrayOutputStream();
                out = new BufferedOutputStream(dataStream, IO_BUFFER_SIZE);
                copy(in, out);
                out.flush();

                final byte[] data = dataStream.toByteArray();
                BitmapFactory.Options options = new BitmapFactory.Options();
                //options.inSampleSize = 1;

                bitmap = BitmapFactory.decodeByteArray(data, 0, data.length, options);
            } catch (IOException e) {
                Log.e(TAG, "Could not load Bitmap from: " + url);
            } finally {
                closeStream(in);
                closeStream(out);
            }

            return bitmap;
        }

        private void closeStream(Closeable stream) {
            if (stream != null) {
                try {
                    stream.close();
                } catch (IOException e) {
                    android.util.Log.e(TAG, "Could not close stream", e);
                }
            }
        }

        private void copy(InputStream in, OutputStream out) throws IOException {
            byte[] b = new byte[IO_BUFFER_SIZE];
            int read;
            while ((read = in.read(b)) != -1) {
                out.write(b, 0, read);
            }
        }

        public class MySSLSocketFactory extends SSLSocketFactory {
            SSLContext sslContext = SSLContext.getInstance("TLS");

            public MySSLSocketFactory(KeyStore truststore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
                super(truststore);

                TrustManager tm = new X509TrustManager() {
                    public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                    }

                    public X509Certificate[] getAcceptedIssuers() {
                        return null;
                    }
                };

                sslContext.init(null, new TrustManager[]{tm}, null);
            }

            @Override
            public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
                return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
            }

            @Override
            public Socket createSocket() throws IOException {
                return sslContext.getSocketFactory().createSocket();
            }
        }
    }
}
