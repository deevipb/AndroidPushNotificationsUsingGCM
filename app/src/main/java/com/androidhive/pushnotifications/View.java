package com.androidhive.pushnotifications;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.AlertDialog;
import android.content.DialogInterface;import android.app.AlertDialog;
import android.content.DialogInterface;

public class View extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Message")
                .setTitle("Title")
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    public void onClick(DialogInterface dialog, int id)
                    {
                       // MainActivity.this.finish();
                    }
                });


        AlertDialog alert = builder.create();
        alert.show();
    }





}
