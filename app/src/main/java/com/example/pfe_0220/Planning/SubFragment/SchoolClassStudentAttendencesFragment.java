package com.example.pfe_0220.Planning.SubFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Planning.Adapter.StudentAttendencesNodeAdapter;
import com.example.pfe_0220.Planning.Models.AttendenceNode;
import com.example.pfe_0220.Planning.PlanningViewModel;
import com.example.pfe_0220.Planning.ViewModels.SchoolClassesViewModel;
import com.example.pfe_0220.R;

import java.util.ArrayList;
import java.util.List;

public class SchoolClassStudentAttendencesFragment extends Fragment {


    RecyclerView student_attendences_holder;
    StudentAttendencesNodeAdapter studentAttendencesNodeAdapter;
    RecyclerView.LayoutManager mlayoutManager;


    SchoolClassesViewModel schoolClassesViewModel;
PlanningViewModel planningViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_school_class_person_holder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        schoolClassesViewModel = new ViewModelProvider(requireActivity()).get(SchoolClassesViewModel.class);
        planningViewModel = new ViewModelProvider(requireActivity()).get(PlanningViewModel.class);


        student_attendences_holder = view.findViewById(R.id.student_attendences_holder);

        studentAttendencesNodeAdapter = new StudentAttendencesNodeAdapter();
        mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        student_attendences_holder.setLayoutManager(mlayoutManager);
        student_attendences_holder.setAdapter(studentAttendencesNodeAdapter);


        try {
            schoolClassesViewModel.GetStudentOf(planningViewModel.selected_school_class.id);

        } catch (Exception e) {
            e.printStackTrace();
        }

        schoolClassesViewModel.schoolClassRepository.studentsAttendence.observe(getViewLifecycleOwner(), new Observer<List<AttendenceNode>>() {
            @Override
            public void onChanged(List<AttendenceNode> attendenceNodes) {
           studentAttendencesNodeAdapter.UpdateList((ArrayList<AttendenceNode>) attendenceNodes);
            }
        });




    }
}
