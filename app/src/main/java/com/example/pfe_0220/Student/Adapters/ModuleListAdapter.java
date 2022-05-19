package com.example.pfe_0220.Student.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.Model.AttendenceReportNode;
import com.example.pfe_0220.Student.Model.ModuleReportNode;

import java.util.ArrayList;

public class ModuleListAdapter extends RecyclerView.Adapter<ModuleListAdapter.ViewHolder> {


    ArrayList<ModuleReportNode> reports = new ArrayList<>();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_module_attendence, parent, false);

        return new ModuleListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SetUpView(reports.get(position));
    }

    @Override
    public int getItemCount() {
        return reports.size();
    }

    public void UpdateReports(ArrayList<ModuleReportNode> moduleReportNodes) {
        reports = moduleReportNodes;

        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        RecyclerView schoolClassHolder;
        ModuleClassesChildListAdapter moduleClassesChildListAdapter;
        RecyclerView.LayoutManager layoutManager;
        TextView moduleName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            moduleName = itemView.findViewById(R.id.moduleName);
            schoolClassHolder = itemView.findViewById(R.id.child_recyclerview_module_node);
            moduleClassesChildListAdapter = new ModuleClassesChildListAdapter();
            layoutManager = new LinearLayoutManager(itemView.getContext(), LinearLayoutManager.VERTICAL, false);

            schoolClassHolder.setLayoutManager(layoutManager);
            schoolClassHolder.setAdapter(moduleClassesChildListAdapter);

        }


        public void SetUpView(ModuleReportNode node){

            moduleName.setText(node.moduleName);
            moduleClassesChildListAdapter.UpdateReportsNode((ArrayList<AttendenceReportNode>) node.attendenceReportNodes);

        }
    }
}
