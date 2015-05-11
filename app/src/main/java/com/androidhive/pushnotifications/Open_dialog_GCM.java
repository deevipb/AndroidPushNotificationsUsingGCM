package com.androidhive.pushnotifications;


import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.TextView;

public class Open_dialog_GCM extends Activity {
    private String KEY_MSG = "", str_num = "";
    private MediaPlayer mpBgm;
    private int i_coutnCH = 0;
    public static final String PREFS_NAME = "PrefsFileTON";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.msg_open_dialog);
        final SharedPreferences GetKey = getSharedPreferences(PREFS_NAME, 0);
        Intent intent = getIntent();
        KEY_MSG = intent.getStringExtra("message");
        MSG_Print();
    }

    protected void MSG_Print() {
        final Dialog dialogbox;
        dialogbox = new Dialog(Open_dialog_GCM.this);
        dialogbox.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialogbox.setContentView(R.layout.activity_open_dialog__gcm);
        dialogbox.setCancelable(false);
        dialogbox.getWindow().setBackgroundDrawable(
                new ColorDrawable(android.graphics.Color.TRANSPARENT));
        Button CanButton = (Button) dialogbox.findViewById(R.id.CanButton);
        Button OkButton = (Button) dialogbox.findViewById(R.id.OkButton);
        TextView txtTitle = (TextView) dialogbox.findViewById(R.id.dialogTitle);
        TextView txtNumber = (TextView) dialogbox.findViewById(R.id.dialogNumber);
        TextView txtDetail = (TextView) dialogbox.findViewById(R.id.dialogMessage);
		final String[] separated = KEY_MSG.split(";");

        try {
            txtTitle.setText("action = "+separated[0]+"\n"+"title = "+separated[1]+"\n"+"comment = "+separated[2]);
            txtDetail.setText("repository = "+separated[3]+"\n login = "+separated[4]);
        } catch (Exception e) {
            txtTitle.setText("action = "+separated[0]);
        }

        CanButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogbox.dismiss();
                finish();
            }
        });

        OkButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogbox.dismiss();

                finish();
//				if(separated[0].equals("1")){
//					finish();
//					Intent i = new Intent(getApplicationContext(),Main_TabHost.class);
//					startActivity(i);
//
//				}else if(separated[0].equals("2")){
//					finish();
//					Intent i = new Intent(getApplicationContext(),Main_TabHost_tab2.class);
//					startActivity(i);
//
//				}else if(separated[0].equals("3")){
//					finish();
//					Intent i = new Intent(getApplicationContext(),Update_Version.class);
//					startActivity(i);
//
//				}
            }
        });


        dialogbox.show();
    }
}