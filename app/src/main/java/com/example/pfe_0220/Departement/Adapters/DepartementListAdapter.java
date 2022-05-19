package com.example.pfe_0220.Departement.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.R;

import java.util.ArrayList;

public class DepartementListAdapter extends RecyclerView.Adapter<DepartementListAdapter.ViewHolder> {


    public ArrayList<Departement> departements;

    public interface ItemClickListener {
        public void onClick(View view, int position);
    }

    public void UpdateDepartementList(ArrayList<Departement> _departemens) {
        departements = _departemens;
        notifyDataSetChanged();
    }

    private ItemClickListener clickListener;


    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_departement_node, parent, false);

        return new DepartementListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SetUpView(departements.get(position));
    }

    @Override
    public int getItemCount() {
        if (departements != null)
            return departements.size();
        else return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView departement_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            departement_name = itemView.findViewById(R.id.departement_name);


        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }

        public void SetUpView(Departement departement) {
            departement_name.setText(departement.name);

        }
    }
}
