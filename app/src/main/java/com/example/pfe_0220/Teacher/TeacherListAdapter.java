package com.example.pfe_0220.Teacher;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.R;
import com.example.pfe_0220.Teacher.Model.Teacher;

import java.util.ArrayList;

public class TeacherListAdapter extends RecyclerView.Adapter<TeacherListAdapter.ViewHolder> {

    ArrayList<Teacher> teachers = new ArrayList<>();

    public void UpdateTeacherList(ArrayList<Teacher> _teachers) {
        teachers = _teachers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_teacher, parent, false);

        return new TeacherListAdapter.ViewHolder(view);
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

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView name, adress, email, phone;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.teacherName);
            adress = itemView.findViewById(R.id.teacherAdress);
            email = itemView.findViewById(R.id.teacherMail);
            phone = itemView.findViewById(R.id.teacherPhone);

        }

        public void SetUpView(Teacher t) {
            name.setText(t.firstName + " " + t.lastName);
            adress.setText(t.adress);
            email.setText(t.email);
            phone.setText(t.phonbeNumber);
        }
    }
}
