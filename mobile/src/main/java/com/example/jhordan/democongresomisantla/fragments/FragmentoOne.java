package com.example.jhordan.democongresomisantla.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jhordan.democongresomisantla.R;
import com.example.jhordan.democongresomisantla.ui.BusProvider;
import com.google.android.gms.wearable.DataMap;
import com.mariux.teleport.lib.TeleportClient;
import com.squareup.otto.Subscribe;

import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jhordan on 07/11/14.
 */
public class FragmentoOne extends Fragment  {

    public FragmentoOne() {
    }


    public static FragmentoOne newInstance(int position) {

       FragmentoOne fragmentCercanos = new FragmentoOne();
        Bundle extraArguments = new Bundle();



        String value = Integer.toString(position);
        Log.i("fragmento one ", value);

        fragmentCercanos.setArguments(extraArguments);
        return fragmentCercanos;

    }

    TeleportClient mTeleportClient;
    TextView textView ,txtResponse;





    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mTeleportClient = new TeleportClient(getActivity());



        mTeleportClient.setOnSyncDataItemTask(new ShowToastHelloWorldTask());



    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.one, container, false);

        textView = (TextView)v.findViewById(R.id.txt_bundle_data);
       txtResponse = (TextView)v.findViewById(R.id.txt_response);


        Log.i("pager","se creo Fragment One");

        BusProvider.getInstance().register(this);

        onCustomStringEvent("dato");

        return v;
    }



    @Override
    public void onStart() {
        super.onStart();
        mTeleportClient.connect();
    }

    @Override
    public void onStop() {
        super.onStop();
        mTeleportClient.disconnect();

    }


    public class ShowToastHelloWorldTask extends TeleportClient.OnSyncDataItemTask {

        @Override
        protected void onPostExecute(DataMap dataMap) {


            String hello = dataMap.getString("bundle");

            textView.setText(hello);

            registerData(hello);

            // Toast.makeText(getActivity(), hello, Toast.LENGTH_SHORT).show();

            mTeleportClient.setOnSyncDataItemTask(new ShowToastHelloWorldTask());
            txtResponse.setText("");
        }
    }





    private void registerData(final String name){


        RequestQueue rq = Volley.newRequestQueue(getActivity());
        String url = "https://script.google.com/macros/s/AKfycbxp6Pjm3_M2Y1SmMeY17Z_jlUcciRG24GtPRWvEBME6Gcu1AMyU/exec";

        StringRequest postReq = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {

            @Override
            public void onResponse(String response) {


                try {

                    JSONObject jsonObject = new JSONObject(response);
                    String g = jsonObject.getString("status");

                    Log.i("Response", response);
                    Log.i("Response", g);

                    if(g.equals("exito 1 post")){

                        txtResponse.setText("SE HA REGISTRADO CORRECTAMENTE!");
                    }else{
                        txtResponse.setText("ERROR AL REGISTRAR!");
                    }


                } catch (Exception e) {
                    Log.i("ERROR", "ERROR");

                }

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println("Error [" + error + "]");


            }
        }) {

            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<String, String>();


                params.put("nombre", name);


                return params;
            }

        };

        rq.add(postReq);

    }

    @Subscribe public void onCustomStringEvent(String event) {

        if(event.equals("0")){
            Toast.makeText(getActivity(),event + "Material" ,Toast.LENGTH_SHORT).show();
        }
        Log.i("otto listener", event);
    }

}
