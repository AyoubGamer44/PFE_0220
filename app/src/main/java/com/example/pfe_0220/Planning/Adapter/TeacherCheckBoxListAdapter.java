package com.example.pfe_0220.Planning.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.R;
import com.example.pfe_0220.Teacher.Model.Teacher;

import java.util.ArrayList;

public class TeacherCheckBoxListAdapter extends RecyclerView.Adapter<TeacherCheckBoxListAdapter.ViewHolder> {


    public interface SelectedTeacherListner {
        void onTeacherSelected(ArrayList<Teacher> teachers);
    }

    SelectedTeacherListner selectedTeacherListner;

    ArrayList<Teacher> teachers = new ArrayList<>();
  public  ArrayList<Teacher> selected_teachers = new ArrayList<>();

public void SetTeacherSelectionListner(SelectedTeacherListner _selectedTeacherListner){
    selectedTeacherListner = _selectedTeacherListner;
}
    public void UpdateTeacherList(ArrayList<Teacher> _teachers) {
        teachers = _teachers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_prof_selection_checkbox, parent, false);

        return new TeacherCheckBoxListAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SetUpView(teachers.get(position));
    }

    @Override
    public int getItemCount() {
        if (teachers != null) return teachers.size();
        else return 0;
    }


    public class ViewHolder extends RecyclerView.ViewHolder implements  CompoundButton.OnCheckedChangeListener {
        CheckBox name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.teacher_name_checkBox);
            name.setOnCheckedChangeListener(this);

        }

        public void SetUpView(Teacher t) {
            name.setText(t.firstName + " " + t.lastName);
            if (name.isChecked()) {
                selected_teachers.add(t);
            } else {
                selected_teachers.remove(t);
            }


        }




        @Override
        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            if (selectedTeacherListner != null) selectedTeacherListner.onTeacherSelected(selected_teachers);
            if(isChecked){
                selected_teachers.add(teachers.get(getAdapterPosition()));
            }
            else{
                selected_teachers.remove(teachers.get(getAdapterPosition()));
            }
        }
    }
}
