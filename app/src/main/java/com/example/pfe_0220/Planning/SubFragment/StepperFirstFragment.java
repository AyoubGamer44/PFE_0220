package com.example.pfe_0220.Planning.SubFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AutoCompleteTextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pfe_0220.Departement.Adapters.DepartementDropDownListAdapter;
import com.example.pfe_0220.Departement.Adapters.ModulesDropDownListAdapter;
import com.example.pfe_0220.Departement.Adapters.SpecialitiesDropDownListAdapter;
import com.example.pfe_0220.Departement.DepartementViewModel;
import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.Models.Module;
import com.example.pfe_0220.Departement.Models.Speciality;
import com.example.pfe_0220.Planning.PlanningViewModel;
import com.example.pfe_0220.R;

import java.util.ArrayList;
import java.util.List;

public class StepperFirstFragment extends Fragment {


    PlanningViewModel planningViewModel;
    DepartementViewModel departementViewModel;

    DepartementDropDownListAdapter departement_drop_down_list_adapter;
    SpecialitiesDropDownListAdapter specialities_drop_down_list_adapter;
    ModulesDropDownListAdapter modules_drop_down_list_adapter;

    AutoCompleteTextView departementlist_holder, specialities_holder, modules_holder;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stepper_fragment_first_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        planningViewModel = new ViewModelProvider(requireActivity()).get(PlanningViewModel.class);
        departementViewModel = new ViewModelProvider(requireActivity()).get(DepartementViewModel.class);

        ArrayList<Departement> departements = new ArrayList<>();
        departements.add(new Departement("dep name"));


        ArrayList<Speciality> specialities = new ArrayList<>();


        ArrayList<Module> modules = new ArrayList<>();


        departementlist_holder = view.findViewById(R.id.departement_drop_down_list);
        specialities_holder = view.findViewById(R.id.speciality_drop_down_list);
        modules_holder = view.findViewById(R.id.Modules_drop_down_list);

        departement_drop_down_list_adapter = new DepartementDropDownListAdapter(getContext(), departements);
        specialities_drop_down_list_adapter = new SpecialitiesDropDownListAdapter(getContext(), specialities);
        modules_drop_down_list_adapter = new ModulesDropDownListAdapter(getContext(), modules);

        departementlist_holder.setAdapter(departement_drop_down_list_adapter);
        specialities_holder.setAdapter(specialities_drop_down_list_adapter);
        modules_holder.setAdapter(modules_drop_down_list_adapter);


        departementViewModel.departementRepository.savedDepartements.observe(getViewLifecycleOwner(), new Observer<List<Departement>>() {
            @Override
            public void onChanged(List<Departement> departements) {
                departement_drop_down_list_adapter.UpdateElement((ArrayList<Departement>) departements);

            }
        });


        departementlist_holder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int selected_departement_id = departement_drop_down_list_adapter.getItem(position).id;

                try {
                    departementViewModel.departementRepository.getSpecialitiesOf(selected_departement_id);
                } catch (Exception e) {
                    e.printStackTrace();
                }
//upload specialities of that departement
                departementViewModel.departementRepository.savedSpecialities.observe(getViewLifecycleOwner(), new Observer<List<Speciality>>() {
                    @Override
                    public void onChanged(List<Speciality> specialities) {
                        specialities_drop_down_list_adapter.UpdateElement((ArrayList<Speciality>) specialities);
                    }
                });

                planningViewModel.newSchoolClass.setDepartement_id(selected_departement_id);

            }
        });


        specialities_holder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selected_speciality_id = specialities_drop_down_list_adapter.getItem(position).id;
                try {
                    departementViewModel.departementRepository.getModuleof(selected_speciality_id);
                } catch (Exception e) {
                    e.printStackTrace();
                }

                departementViewModel.departementRepository.savedModules.observe(getViewLifecycleOwner(), new Observer<List<Module>>() {
                    @Override
                    public void onChanged(List<Module> modules) {
                        modules_drop_down_list_adapter.UpdateElement((ArrayList<Module>) modules);
                    }
                });
                planningViewModel.newSchoolClass.setSpeciality_id(selected_speciality_id);

            }
        });

        modules_holder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                planningViewModel.newSchoolClass.setModule_id(modules_drop_down_list_adapter.getItem(position).id);
            }
        });


    }
}
