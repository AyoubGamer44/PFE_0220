package com.example.pfe_0220.CommunModels;

import android.content.Context;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DropDownListAdapter extends ArrayAdapter {

ArrayList<String> objects;

    public DropDownListAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return objects.get(position);
    }

    public void UpdateAdapter(ArrayList<String> _objects){
        objects = _objects;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        if(objects != null)
        return objects.size();
        else    return 0;
    }
}
