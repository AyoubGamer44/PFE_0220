package com.example.pfe_0220.Planning.SubFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Planning.Adapter.ResponsibleAttendencesNodeAdapter;
import com.example.pfe_0220.Planning.Models.AttendenceNode;
import com.example.pfe_0220.Planning.ViewModels.SchoolClassesViewModel;
import com.example.pfe_0220.R;

import java.util.ArrayList;
import java.util.List;

public class SchoolClassResponsibleAttendencesFragment extends Fragment {


    RecyclerView responsible_attendences_holder;
    ResponsibleAttendencesNodeAdapter responsibleAttendencesNodeAdapter;
    RecyclerView.LayoutManager mlayoutManager;

    SchoolClassesViewModel schoolClassesViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_school_class_person_holder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        schoolClassesViewModel = new ViewModelProvider(requireActivity()).get(SchoolClassesViewModel.class);

        responsible_attendences_holder = view.findViewById(R.id.student_attendences_holder);

        responsibleAttendencesNodeAdapter = new ResponsibleAttendencesNodeAdapter();
        mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        responsible_attendences_holder.setLayoutManager(mlayoutManager);
        responsible_attendences_holder.setAdapter(responsibleAttendencesNodeAdapter);

        schoolClassesViewModel.schoolClassRepository.responsibleAttendence.observe(getViewLifecycleOwner(), new Observer<List<AttendenceNode>>() {
            @Override
            public void onChanged(List<AttendenceNode> attendenceNodes) {
                responsibleAttendencesNodeAdapter.UpdateResponsibleList((ArrayList<AttendenceNode>) attendenceNodes);
            }
        });

    }
}
