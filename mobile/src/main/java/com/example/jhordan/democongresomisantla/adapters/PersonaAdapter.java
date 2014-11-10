package com.example.jhordan.democongresomisantla.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jhordan.democongresomisantla.R;
import com.example.jhordan.democongresomisantla.model.Persona;

import java.util.ArrayList;

/**
 * Created by Jhordan on 10/11/14.
 */
public class PersonaAdapter extends ArrayAdapter<Persona> {

    ArrayList<Persona> data;
    LayoutInflater inflater;

    public PersonaAdapter(Context context, ArrayList<Persona> data) {
        super(context, -1, data);
        this.data = data;
        this.inflater = LayoutInflater.from(context);

    }


    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;

        final Persona nombresPersonas = data.get(position);
        int layout = R.layout.item_list;

        if (convertView == null) {

            convertView = inflater.inflate(layout, null);
            holder = new ViewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.textView);


            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        holder.name.setText(nombresPersonas.getNombre());


        return convertView;

    }


    public static class ViewHolder {

        TextView name;

    }
}