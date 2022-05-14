package com.example.pfe_0220.Planning.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Planning.SchoolClass;
import com.example.pfe_0220.R;

import java.util.ArrayList;

public class SchoolClassesAdapter extends RecyclerView.Adapter<SchoolClassesAdapter.ViewHolder> {

ArrayList<SchoolClass> classes;
    private ItemClickListener clickListener;
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

    public void setClickListener(ItemClickListener itemClickListener) {
       this. clickListener = itemClickListener;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
        }
    }

    public interface ItemClickListener  {
        public void onClick(View view, int position);
    }

}
