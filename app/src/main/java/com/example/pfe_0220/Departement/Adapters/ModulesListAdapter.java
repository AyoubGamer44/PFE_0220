package com.example.pfe_0220.Departement.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Departement.Models.Module;
import com.example.pfe_0220.R;

import java.util.ArrayList;

public class ModulesListAdapter extends RecyclerView.Adapter<ModulesListAdapter.ViewHolder> {

    public interface ItemClickListener {
        public void onClick(View view, int position);
    }

    ArrayList<Module> modules = new ArrayList<>();
    private ItemClickListener clickListener;


    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_speciality, parent, false);

        return new ModulesListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SetUpView(modules.get(position));
    }

    @Override
    public int getItemCount() {
        return modules.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView specialityName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            specialityName = itemView.findViewById(R.id.speciality_name);
        }

        public void SetUpView(Module m) {
            specialityName.setText(m.name);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void UpdateModulesList(ArrayList<Module> _modules) {
        modules = _modules;
        notifyDataSetChanged();
    }

}
