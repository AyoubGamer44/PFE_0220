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
import com.example.pfe_0220.Departement.Models.Speciality;
import com.example.pfe_0220.R;

import java.util.ArrayList;
import java.util.List;

public class SpecialitiesDropDownListAdapter extends ArrayAdapter<Speciality> {
    public SpecialitiesDropDownListAdapter(@NonNull Context context, @NonNull List<Speciality> specialities) {
        super(context, 0, specialities);
    }


    public void UpdateElement(ArrayList<Speciality> _specialities){
        clear();
        for (Speciality d:
             _specialities) {
            insert(d,getCount());
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.node_drop_down,parent,false);
            TextView elementName = convertView.findViewById(R.id.elementName);
            Speciality speciality = getItem(position);
            elementName.setText(speciality.name);

        }
        return convertView;
    }
}
