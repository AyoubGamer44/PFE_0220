package com.example.pfe_0220.Student.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.R;

public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ViewHolder> {
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_module_attendence, parent, false);

        return new ModuleListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView schoolClassHolder;
        ModuleClassesChildListAdapter moduleClassesChildListAdapter;
        RecyclerView.LayoutManager layoutManager;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            schoolClassHolder = itemView.findViewById(R.id.child_recyclerview_module_node);
            moduleClassesChildListAdapter = new ModuleClassesChildListAdapter();
            layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);

            schoolClassHolder.setLayoutManager(layoutManager);
            schoolClassHolder.setAdapter(moduleClassesChildListAdapter);

        }
    }
}
