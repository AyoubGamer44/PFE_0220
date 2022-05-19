package com.example.pfe_0220.Planning.SubFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pfe_0220.Departement.DepartementViewModel;
import com.example.pfe_0220.Planning.PlanningViewModel;
import com.example.pfe_0220.R;

public class StepperSecondFragment extends Fragment {


    NumberPicker sectionPicker, groupPicker;

    AutoCompleteTextView levelPicker;
    ArrayAdapter<String> levelsAdapter;

    PlanningViewModel planningViewModel;
    DepartementViewModel departementViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stepper_fragment_second_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        planningViewModel = new ViewModelProvider(requireActivity()).get(PlanningViewModel.class);
        departementViewModel = new ViewModelProvider(requireActivity()).get(DepartementViewModel.class);


        // picker section
        sectionPicker = view.findViewById(R.id.section_picker_edit_student);
        levelPicker = view.findViewById(R.id.level_drop_down_list);
        groupPicker = view.findViewById(R.id.group_picker_edit_student);


        //picker config
        sectionPicker.setMinValue(1);
        sectionPicker.setMaxValue(3);
        groupPicker.setMinValue(1);
        groupPicker.setMaxValue(6);


        levelsAdapter = new ArrayAdapter<>(getContext(), R.layout.node_simple_drop_down, departementViewModel.departementRepository.getAvailableLevels());
        levelPicker.setAdapter(levelsAdapter);

        planningViewModel.newSchoolClass.setSection(sectionPicker.getValue());
        planningViewModel.newSchoolClass.setSchool_group(groupPicker.getValue());


        levelPicker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                planningViewModel.newSchoolClass.setLevel(position);
            }
        });

        sectionPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                planningViewModel.newSchoolClass.setSection(newVal);

            }
        });

        groupPicker.setOnValueChangedListener(new NumberPicker.OnValueChangeListener() {
            @Override
            public void onValueChange(NumberPicker picker, int oldVal, int newVal) {
                planningViewModel.newSchoolClass.setSchool_group(newVal);
            }
        });

    }
}
