package com.example.pfe_0220.Planning.SubFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Planning.Adapter.SchoolYearDaysAdapter;
import com.example.pfe_0220.Planning.SchoolClass;
import com.example.pfe_0220.R;

import java.util.ArrayList;

public class SchoolClassesAdapter extends RecyclerView.Adapter<SchoolClassesAdapter.ViewHolder> {

ArrayList<SchoolClass> classes;
    @NonNull
    @Override
    public SchoolClassesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_scholl_class, parent, false);

        return new SchoolClassesAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolClassesAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 10;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
