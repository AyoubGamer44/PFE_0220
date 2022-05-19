package com.example.pfe_0220.Planning.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Departement.DepartementRepository;
import com.example.pfe_0220.Planning.Models.SchoolClassNode;
import com.example.pfe_0220.R;

import java.util.ArrayList;

public class SchoolClassesAdapter extends RecyclerView.Adapter<SchoolClassesAdapter.ViewHolder> {

    public ArrayList<SchoolClassNode> classes = new ArrayList<>();
    private ItemClickListener clickListener;

    @NonNull
    @Override
    public SchoolClassesAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_scholl_class, parent, false);

        return new SchoolClassesAdapter.ViewHolder(view);
    }

    public void UpdateClassesList(ArrayList<SchoolClassNode> _classes) {
        classes = _classes;
        notifyDataSetChanged();
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolClassesAdapter.ViewHolder holder, int position) {
        holder.SetUpNode(classes.get(position));
    }

    @Override
    public int getItemCount() {
        return classes.size();
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView startTime, EndTime, moduleName, specialityName, departementName, levelName, sectionName, groupName, schoolTypeName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            startTime = itemView.findViewById(R.id.start_time);
            EndTime = itemView.findViewById(R.id.end_time);
            moduleName = itemView.findViewById(R.id.module_name);
            specialityName = itemView.findViewById(R.id.speciality_name);
            departementName = itemView.findViewById(R.id.departement_name);
            levelName = itemView.findViewById(R.id.level_name);
            sectionName = itemView.findViewById(R.id.section_name);
            groupName = itemView.findViewById(R.id.group_name);
            schoolTypeName = itemView.findViewById(R.id.school_class_type_name);


        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }

        public void SetUpNode(SchoolClassNode node) {

            startTime.setText(node.getStartTime());
            EndTime.setText(node.getEndTime());
            moduleName.setText(node.module);
            specialityName.setText(node.speciality);
            departementName.setText(node.departement);
            levelName.setText(DepartementRepository.getAvailableLevels().get(node.level));
            sectionName.setText("Section :" + node.section);
            groupName.setText("group N :" + node.school_group);
            schoolTypeName.setText(DepartementRepository.getSchoolClassType().get(node.school_classtype));
        }
    }

    public interface ItemClickListener {
        public void onClick(View view, int position);
    }

}
