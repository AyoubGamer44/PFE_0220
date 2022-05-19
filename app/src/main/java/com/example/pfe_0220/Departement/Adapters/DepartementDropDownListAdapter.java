package com.example.pfe_0220.Departement.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.R;

import java.util.ArrayList;
import java.util.List;

public class DepartementDropDownListAdapter extends ArrayAdapter<Departement> {


    public DepartementDropDownListAdapter(@NonNull Context context, @NonNull List<Departement> departements) {
        super(context, 0, departements);
    }


    public void UpdateElement(ArrayList<Departement> _departements){
        clear();
        for (Departement d:
             _departements) {
   insert(d,0);
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.node_drop_down,parent,false);
            TextView elementName = convertView.findViewById(R.id.elementName);
            Departement departement = getItem(position);
            elementName.setText(departement.name);

        }
        return convertView;
    }
}
