package com.androidhive.pushnotifications;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class TopRatedFragment extends Fragment {
    ArrayList arrayList;
    TextView setup_txtUsername;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		View rootView = inflater.inflate(R.layout.fragment_top_rated, container, false);
        arrayList = new ArrayList<String>();
        arrayList.add("Github");
//        arrayList.add("Dorpbox");
//        arrayList.add("Line");
//        arrayList.add("Text");

        setup_txtUsername = (TextView)rootView.findViewById(R.id.setup_txtUsername);
        setup_txtUsername.setText("Username: "+setup.User);

        ListView list=(ListView)rootView.findViewById(R.id.listView);

//        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                MSG_Dialog(getActivity(),arrayList.get(position).toString());
//            }
//        });


        // Getting adapter by passing xml data ArrayList
        LazyAdapter adapter=new LazyAdapter(getActivity());
        list.setAdapter(adapter);

        return rootView;
	}

    public class LazyAdapter extends BaseAdapter {
        Activity context;
        private Activity activity;
        private  LayoutInflater inflater=null;
        public LazyAdapter(Activity context) {
            activity = context;
            inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        public int getCount() {
            return arrayList.size();
        }

        public Object getItem(int position) {
            return position;
        }

        public long getItemId(int position) {
            return position;
        }

        public View getView(final int position, View convertView, ViewGroup parent) {
            View vi=convertView;
            if(convertView==null)
            vi = inflater.inflate(R.layout.row_setup, null);
            TextView title = (TextView)vi.findViewById(R.id.textView2); // title

            Switch switch1 = (Switch)vi.findViewById(R.id.switch1);
            switch1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MSG_Dialog(getActivity(),arrayList.get(position).toString());
                }
            });


            title.setText(arrayList.get(position).toString());
            return vi;
        }
    }
    protected void MSG_Dialog(Activity context,String Title) {
        final Dialog dialogbox;
        dialogbox = new Dialog(context);
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
        OkButton.setTextSize(16);
        CanButton.setTextSize(16);

            txtTitle.setText(Title);
            txtDetail.setText("Allow "+Title+" ?");


        CanButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogbox.dismiss();

               // finish();
            }
        });

        OkButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialogbox.dismiss();
                Intent myIntent = new Intent(getActivity(), Allow.class);
                //myIntent.putExtra("UserLogin", txtUser.getText().toString()); //Optional parameters
                startActivity(myIntent);
               // finish();
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
