package com.example.pfe_0220.Student.Adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Planning.Adapter.SchoolClassesAdapter;
import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.Model.Student;

import java.util.ArrayList;

public class StudentListHolderAdapter extends RecyclerView.Adapter<StudentListHolderAdapter.ViewHolder> {


    ArrayList<Student> students = new ArrayList<>();

    private ItemClickListener clickListener;

    public void setClickListener(ItemClickListener itemClickListener) {
        this. clickListener = itemClickListener;
    }

public void UpdateStudentList(ArrayList<Student> _students){
        students.clear();
        students = _students;
        notifyDataSetChanged();
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_student, parent, false);

        return new StudentListHolderAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {

        return students.size();
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
