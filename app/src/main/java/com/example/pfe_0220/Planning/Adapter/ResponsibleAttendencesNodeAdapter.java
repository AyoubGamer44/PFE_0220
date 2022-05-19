package com.example.pfe_0220.Planning.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Planning.Models.AttendenceNode;
import com.example.pfe_0220.R;
import com.example.pfe_0220.Teacher.Model.Teacher;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ResponsibleAttendencesNodeAdapter extends RecyclerView.Adapter<ResponsibleAttendencesNodeAdapter.ViewHolder> {

    ArrayList<AttendenceNode> responsibles = new ArrayList<>();


    public void UpdateResponsibleList(ArrayList<AttendenceNode> _responsibles){
        responsibles = _responsibles;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_responsible_attendence, parent, false);

        return new ResponsibleAttendencesNodeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return responsibles.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
