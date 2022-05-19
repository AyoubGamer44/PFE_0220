package com.example.pfe_0220.Departement.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Space;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Departement.Models.Speciality;
import com.example.pfe_0220.R;

import java.util.ArrayList;

public class SpecialitiesListAdapter extends RecyclerView.Adapter<SpecialitiesListAdapter.ViewHolder> {

    public interface ItemClickListener  {
        public void onClick(View view, int position);
    }

public ArrayList<Speciality> specialities  = new ArrayList<>();
    private ItemClickListener clickListener;


    public void setClickListener(ItemClickListener itemClickListener) {
        this. clickListener = itemClickListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_speciality, parent, false);

        return new SpecialitiesListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
holder.SetUpView(specialities.get(position));
    }

    @Override
    public int getItemCount() {
        if(specialities != null)
        return specialities.size();
        else    return 0 ;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView specialityName;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            specialityName = itemView.findViewById(R.id.speciality_name);
        }
public  void SetUpView(Speciality s){
            specialityName.setText(s.name);
}
        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }

    public void UpdateSpecialitiesList(ArrayList<Speciality> _specialities){
        specialities = _specialities;
        notifyDataSetChanged();
    }

}
