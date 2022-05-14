package com.example.pfe_0220.Teacher;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.R;
import com.example.pfe_0220.Teacher.Model.Teacher;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class TeacherFragment extends Fragment {


    RecyclerView teacherListHolder;
    TeacherListAdapter teacherListAdapter;
    RecyclerView.LayoutManager layoutManager;
    TeacherViewModel teacherViewModel;
    FloatingActionButton addTeacherBtn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_teachers, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        teacherViewModel = new ViewModelProvider(requireActivity()).get(TeacherViewModel.class);
        teacherListHolder = view.findViewById(R.id.teacher_list_holder);
        teacherListAdapter = new TeacherListAdapter();
        layoutManager = new LinearLayoutManager(view.getContext(), LinearLayoutManager.VERTICAL, false);
        teacherListHolder.setLayoutManager(layoutManager);
        teacherListHolder.setAdapter(teacherListAdapter);
        addTeacherBtn = view.findViewById(R.id.addteacherbtn);
addTeacherBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AddTeacherDialog();
    }
});

        teacherViewModel.teacherRepository.teachers.observe(getViewLifecycleOwner(), new Observer<List<Teacher>>() {
            @Override
            public void onChanged(List<Teacher> teachers) {
                teacherListAdapter.UpdateTeacherList((ArrayList<Teacher>) teachers);
            }
        });


    }

    public void AddTeacherDialog() {
        Dialog addTeacherDialog = new Dialog(getContext());
        addTeacherDialog.setContentView(R.layout.dialog_add_teacher);
        addTeacherDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        Button savebtn, cancelbtn;
        EditText firstName, lastName, email, adress, phoneNumber;

        savebtn = addTeacherDialog.findViewById(R.id.validate_add_teacher);
        cancelbtn = addTeacherDialog.findViewById(R.id.cancel_teacher_btn);
        firstName = addTeacherDialog.findViewById(R.id.teacher_first_name_in);
        lastName = addTeacherDialog.findViewById(R.id.teacher_last_name_in);
        email = addTeacherDialog.findViewById(R.id.teacher_email_in);
        adress = addTeacherDialog.findViewById(R.id.teacher_adress_in);
        phoneNumber = addTeacherDialog.findViewById(R.id.teacher_phonenumber_in);


        cancelbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTeacherDialog.dismiss();
            }
        });
        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    teacherViewModel.InsertTeacher(new Teacher(firstName.getText().toString(),

                            lastName.getText().toString(),
                            email.getText().toString(),
                            phoneNumber.getText().toString(),
                            adress.getText().toString()));
                    Toast.makeText(getContext(), "saved", Toast.LENGTH_SHORT).show();
                    addTeacherDialog.dismiss();
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        addTeacherDialog.create();
        addTeacherDialog.show();
    }


}

