package com.androidhive.pushnotifications;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;

import org.eclipse.egit.github.core.Authorization;
import org.eclipse.egit.github.core.Gist;
import org.eclipse.egit.github.core.GistFile;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.GistService;
import org.eclipse.egit.github.core.service.OAuthService;

import java.util.Arrays;
import java.util.Collections;


public class Allow extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allow);
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        GitHubClient client = new GitHubClient();
        client.setCredentials("sakchai.c@playbasis.com", "MyPayLogin@123");
System.out.println(client.getUser());
        System.out.println("------------------------------------------------------");
//        this.username = ConfigurationParser.parse("username");
//        this.password  = ConfigurationParser.parse("password");
       // GitHubService gitHubService = new GitHubService();
        try {
            OAuthService oauthService = new OAuthService();

            // Replace with actual login and password
            //oauthService.getClient().setCredentials("sakchai.c@playbasis.com", "MyPayLogin@123");
            //oauthService.getClient().setHeaderAccept("");
            //oauthService.getClient().setOAuth2Token()
            // Create authorization with 'gist' scope only
            oauthService.getAuthorizations();
            Authorization auth = new Authorization();
            auth.setScopes(Arrays.asList("user","repo","admin:repo_hook"));
            //auth.setNoteUrl();
            auth = oauthService.createAuthorization(auth);

            // Create Gist service configured with OAuth2 token
            GistService gistService = new GistService();
            gistService.getClient().setOAuth2Token(auth.getToken());

            // Create Gist
            Gist gist = new Gist();
            gist.setPublic(false);
            gist.setDescription("Created using OAuth2 token via Java API");
            GistFile file = new GistFile();
            file.setContent("Gist!");
            file.setFilename("gist.txt");
            gist.setFiles(Collections.singletonMap(file.getFilename(), file));
            gist = gistService.createGist(gist);
            System.out.println("Created Gist at " + gist.getHtmlUrl());
        }catch (Throwable e) {
            e.printStackTrace();
            System.out.println(e+"");
        }

//        OAuthService oauthService = new OAuthService();
//        oauthService.getClient().setCredentials("sakchai.c@playbasis.com", "MyPayLogin@123");
//        Authorization auth = new Authorization();
//        try {
//            auth = oauthService.createAuthorization(auth);
//        } catch (IOException e) {
//            System.out.println(e+"");
//            e.printStackTrace();
//        }
        //System.out.println(auth.getApp().getName());

//        oauthService.getClient().setOAuth2Token(auth.getToken());
//        System.out.println("===========================================");
//        System.out.println(auth.getToken());

//        try {
//            Authorization authorization = new Authorization();
//            ArrayList<String> itemsList = new ArrayList<String> ( ) ;
//            itemsList.add ( "user" ) ;
//            itemsList.add ( "repo" ) ;
//            itemsList.add ( "admin:repo_hook" ) ;
//
//
//            authorization.setScopes(itemsList);
//
//            UserService userService = new UserService();
//            OAuthService oAuthService = new OAuthService();
//            System.out.println(userService.getUser());
//            oAuthService.createAuthorization(authorization);
//        } catch (Throwable e) {
//            e.printStackTrace();
//            System.out.println(e+"");
//        }
//        try {
//            RepositoryService service = new RepositoryService();
//            for (Repository repo : service.getRepositories("deevipb"))
//                System.out.println(repo.getName() + " Watchers: " + repo.getWatchers());
//        } catch (Throwable e) {
//            e.printStackTrace();
//            System.out.println(e+"");
//        }
        //client.setCredentials("deevipb", "MyPayLogin@");
//        try {
//            RepositoryService service = new RepositoryService();
//            for (Repository repo : service.getRepositories("deevipb"))
//                System.out.println(repo.getName() + " Watchers: " + repo.getWatchers());
//        } catch (Throwable e) {
//            e.printStackTrace();
//            System.out.println(e+"");
//        }



        //client.setScopes(List <String> scopes)

//        String url = "https://github.com/login/oauth/authorize?scope=user,repo,admin:repo_hook&client_id=40ecdc7aaccd8e68fe78";
//        getWindow().setFeatureInt( Window.FEATURE_PROGRESS, Window.PROGRESS_VISIBILITY_ON);
//        WebView webView=(WebView)findViewById(R.id.webViewLoad);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.getSettings().setSupportZoom(true);
//        webView.getSettings().setBuiltInZoomControls(true);
//        webView.loadUrl(url);
//
//
//
//        webView.setWebViewClient(new WebViewClient() {
//            ProgressDialog progressDialog=new ProgressDialog(Allow.this);
//
//            @Override
//            public void onPageStarted(WebView view, String url, Bitmap favicon) {
//                super.onPageStarted(view, url, favicon);
//                Log.e("I am  loading Here ", "Start");
//                progressDialog.setTitle("Loading");
//                progressDialog.setMessage("Please wait....");
//                progressDialog.show();
//
//            }
//
//            @Override
//            public boolean shouldOverrideUrlLoading(WebView view, String url)
//            {
//                Log.e("I am  loading Here ","Override");
//                view.loadUrl(url);
//                return true;
//
//            }
//
//            @Override
//            public void onPageFinished(WebView view, String url) {
//                progressDialog.dismiss();
//            }
//
//        });


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_allow, menu);
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
}
