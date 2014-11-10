package com.example.jhordan.democongresomisantla.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.jhordan.democongresomisantla.R;
import com.example.jhordan.democongresomisantla.adapters.PersonaAdapter;
import com.example.jhordan.democongresomisantla.model.Persona;
import com.example.jhordan.democongresomisantla.ui.BusProvider;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Jhordan on 07/11/14.
 */
public class FragmentoTwo extends Fragment {

    public FragmentoTwo() {
    }

    public static FragmentoTwo newInstance(int position) {

        FragmentoTwo fragmentCercanos = new FragmentoTwo();
        Bundle extraArguments = new Bundle();

        fragmentCercanos.setArguments(extraArguments);
        return fragmentCercanos;
    }

    ListView listView;
    PersonaAdapter personaAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     //   getRegisterData();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.two, container, false);

        listView = (ListView) v.findViewById(R.id.listView);


        Log.i("pager", "se creo Fragment Two");


        return v;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


        BusProvider.getInstance().register(this);

        onCustomStringEvent("dato");

     /*   ArrayList<Persona> personas = new ArrayList<Persona>();
        PersonaAdapter personaAdapter = new PersonaAdapter(getActivity(),personas);
        Persona p = new Persona();

        for(int i = 1 ; i<=10 ; i++){



            p.setNombre("erik");

            personas.add(p);
        }


        listView.setAdapter(personaAdapter);*/

    }

    private void getRegisterData() {


        RequestQueue rq = Volley.newRequestQueue(getActivity());
        String url = "https://script.googleusercontent.com/macros/echo?user_content_key=UJ1Sw4_P1zb1dFq41jS38rSMAAJ7zJJGyqNUeWrM_aWTmaKWwvdSa2DCaL7tW86UZ_jTF04CfU0PgKfie7Mg9T1nrcC3W7Awm5_BxDlH2jW0nuo2oDemN9CCS2h10ox_1xSncGQajx_ryfhECjZEnNxZxk8USnSFmLs4WwYMhGrF4NzRBAygyKrF_oOt-FK2r13Dps0iSdwbxvD-jgCgced34BJOHFZS&lib=Mo9y3rMe01NYdkhVdhfdY-HN83SZ-dHky";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.i("response", response.toString());

               personaAdapter = new PersonaAdapter(getActivity(),parserData(response));


                listView.setAdapter(personaAdapter );

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("on Error Response",error.toString());

            }
        });


        rq.add(jsonArrayRequest);

    }

    public ArrayList<Persona> parserData(JSONArray response){

        ArrayList<Persona> personas = new ArrayList<Persona>();



        for(int i = 0 ; i<response.length() ; i++){
            Persona persona = new Persona();
            JSONObject jsonObject;

              try{
                 jsonObject = (JSONObject) response.get(i);

                   persona.setNombre(jsonObject.getString("namePersona"));

                  Log.i("nombre",jsonObject.getString("namePersona"));

                  personas.add(persona);

              }catch (JSONException e){

                  Log.i("error parsear" , e.toString());

              }


          }


        for(Persona p:personas){
            System.out.println(p.getNombre());


        }



        return personas;
    }

    @Subscribe
    public void onCustomStringEvent(String event) {

        if(event.equals("1")){
            Toast.makeText(getActivity(), event + "Design", Toast.LENGTH_SHORT).show();
            getRegisterData();
        }
        Log.i("otto listener", event);
    }

}

