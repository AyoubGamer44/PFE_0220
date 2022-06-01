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

import com.example.pfe_0220.MainActivity;
import com.example.pfe_0220.Planning.Adapter.SchoolClassesAdapter;
import com.example.pfe_0220.Planning.Models.SchoolClassNode;
import com.example.pfe_0220.Planning.PlanningViewModel;
import com.example.pfe_0220.Planning.ViewModels.SchoolClassesViewModel;
import com.example.pfe_0220.R;

import java.util.ArrayList;
import java.util.List;

public class SchoolClassFragment extends Fragment implements SchoolClassesAdapter.ItemClickListener {


    RecyclerView schoolClassesHolder;
    SchoolClassesAdapter schoolClassesAdapter;
    RecyclerView.LayoutManager mlayoutManager;
    PlanningViewModel planningViewModel;
SchoolClassesViewModel schoolClassesViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_school_class, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        planningViewModel = new ViewModelProvider(requireActivity()).get(PlanningViewModel.class);

        mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        schoolClassesAdapter = new SchoolClassesAdapter();
        schoolClassesHolder = view.findViewById(R.id.classes_holder);
        schoolClassesHolder.setLayoutManager(mlayoutManager);
        schoolClassesHolder.setAdapter(schoolClassesAdapter);
        schoolClassesAdapter.setClickListener(this);

        schoolClassesViewModel = new ViewModelProvider(requireActivity()).get(SchoolClassesViewModel.class);

        planningViewModel.planningRepository.plannedSchoolClasses.observe(getViewLifecycleOwner(), new Observer<List<SchoolClassNode>>() {
            @Override
            public void onChanged(List<SchoolClassNode> schoolClassNodes) {
                schoolClassesAdapter.UpdateClassesList((ArrayList<SchoolClassNode>) schoolClassNodes);
            }
        });

    }

    @Override
    public void onClick(View view, int position) {
        planningViewModel.selected_school_class = schoolClassesAdapter.classes.get(position);
        try {
            schoolClassesViewModel.GetStudentOf(planningViewModel.selected_school_class.id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ((MainActivity) getActivity()).ShowFragment(new SchoolClassAttendenceFragment(), " classes attendences ");
    }
}
