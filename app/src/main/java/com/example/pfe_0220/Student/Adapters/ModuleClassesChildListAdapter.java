package com.example.pfe_0220.Student.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Departement.DepartementRepository;
import com.example.pfe_0220.Planning.Models.AttendenceNode;
import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.Model.AttendenceReportNode;

import java.util.ArrayList;

public class ModuleClassesChildListAdapter extends RecyclerView.Adapter<ModuleClassesChildListAdapter.ViewHolder> {

    ArrayList<AttendenceReportNode> nodes = new ArrayList<>();

    public void UpdateReportsNode(ArrayList<AttendenceReportNode> nodes) {
        this.nodes = nodes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.child_node_school_class_node_module_attendence_node, parent, false);

        return new ModuleClassesChildListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SetUpView(nodes.get(position));
    }

    @Override
    public int getItemCount() {
        return nodes.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView schollClassType, date, time, counter;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            schollClassType = itemView.findViewById(R.id.school_class_type_name);
            date = itemView.findViewById(R.id.eventDate);
            time = itemView.findViewById(R.id.eventTime);
            counter = itemView.findViewById(R.id.absence_and_presence);

        }

        public void SetUpView(AttendenceReportNode node) {
            schollClassType.setText(DepartementRepository.getSchoolClassType().get(node.school_classtype));
            date.setText(AttendenceNode.getDate(node.start_time));
            time.setText(AttendenceNode.getAtendenceTime(node.start_time, node.end_time));
            counter.setText(node.presenceCount + " / " + node.absenceCount);

        }

    }


}
