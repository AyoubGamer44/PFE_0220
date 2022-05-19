package com.example.pfe_0220.Departement.SubFragments;

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

import com.example.pfe_0220.Departement.Adapters.ModulesListAdapter;
import com.example.pfe_0220.Departement.DepartementViewModel;
import com.example.pfe_0220.Departement.Models.Module;
import com.example.pfe_0220.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class ModulesFragment extends Fragment implements ModulesListAdapter.ItemClickListener {


    ModulesListAdapter modulesListAdapter;
    RecyclerView modulesListHolder;
    RecyclerView.LayoutManager layoutManager;

DepartementViewModel departementViewModel;


    FloatingActionButton addModule;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_speciailties,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        departementViewModel = new ViewModelProvider(requireActivity()).get(DepartementViewModel.class);


        modulesListHolder = view.findViewById(R.id.specialitiesListHolder);
        modulesListAdapter = new ModulesListAdapter();
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        modulesListHolder.setLayoutManager(layoutManager);
        modulesListHolder.setAdapter(modulesListAdapter);

        modulesListAdapter.setClickListener(this);

        addModule = view.findViewById(R.id.addSpeciality);
        addModule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAddModuleDialog();
            }
        });



departementViewModel.departementRepository.savedModules.observe(getViewLifecycleOwner(), new Observer<List<Module>>() {
    @Override
    public void onChanged(List<Module> modules) {
        modulesListAdapter.UpdateModulesList((ArrayList<Module>) modules);
    }
});
















    }

    @Override
    public void onClick(View view, int position) {
        Toast.makeText(getContext(), "show info about the selected modules", Toast.LENGTH_SHORT).show();
    }



    public void ShowAddModuleDialog() {


            Dialog addModuleDialog = new Dialog(getContext());
        addModuleDialog.setContentView(R.layout.dialog_add_module);
        addModuleDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            EditText module_name_input;
            Button save, cancel;

        module_name_input = addModuleDialog.findViewById(R.id.module_input);
            save = addModuleDialog.findViewById(R.id.module_save_btn);
            cancel = addModuleDialog.findViewById(R.id.module_canel_btn);




        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Module m = new Module(module_name_input.getText().toString(),departementViewModel.selected_speciality);
                try {
                    departementViewModel.InsertModule(m);
                    Toast.makeText(getContext(), "saved successfull", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                addModuleDialog.dismiss();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addModuleDialog.dismiss();
            }

        });


        addModuleDialog.create();
        addModuleDialog.show();

    }







}
