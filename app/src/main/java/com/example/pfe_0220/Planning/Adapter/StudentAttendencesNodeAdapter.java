package com.example.pfe_0220.Planning.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Planning.Models.Attendence;
import com.example.pfe_0220.Planning.Models.AttendenceNode;
import com.example.pfe_0220.R;

import java.util.ArrayList;

public class StudentAttendencesNodeAdapter extends RecyclerView.Adapter<StudentAttendencesNodeAdapter.ViewHolder> {

    ArrayList<AttendenceNode> studentAttendences = new ArrayList<>();


    public void UpdateList(ArrayList<AttendenceNode> attendenceNodes) {
        studentAttendences = attendenceNodes;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.node_student_attendence, parent, false);

        return new StudentAttendencesNodeAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.SetUpView(studentAttendences.get(position));
    }

    @Override
    public int getItemCount() {
        return studentAttendences.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView studentName, studentId, enterTime, presenceState;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            studentName = itemView.findViewById(R.id.studentName);
            studentId = itemView.findViewById(R.id.studentId);
            enterTime = itemView.findViewById(R.id.enter_time);
            presenceState = itemView.findViewById(R.id.presence_state);


        }

        public void SetUpView(AttendenceNode node) {
            studentName.setText(node.first_name + " " + node.last_name);
            studentId.setText(node.student_id + "");

            presenceState.setText(Attendence.shortpresenceType[node.state]);
        }

    }
}
