package com.example.pfe_0220.Departement.SubFragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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

import com.example.pfe_0220.CommunModels.DropDownListAdapter;
import com.example.pfe_0220.Departement.Adapters.SpecialitiesListAdapter;
import com.example.pfe_0220.Departement.DepartementViewModel;
import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.Models.Speciality;
import com.example.pfe_0220.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class SpecialitiesFragment extends Fragment implements SpecialitiesListAdapter.ItemClickListener {


    SpecialitiesListAdapter specialitiesListAdapter;
    RecyclerView specialitiesListHolder;
    RecyclerView.LayoutManager layoutManager;

DepartementViewModel departementViewModel;


    FloatingActionButton addSpeciality;
    ArrayList<Departement> departementList = new ArrayList<>();
    ArrayAdapter<String> adapterItems;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_speciailties,container,false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        departementViewModel = new ViewModelProvider(requireActivity()).get(DepartementViewModel.class);


        specialitiesListHolder = view.findViewById(R.id.specialitiesListHolder);
        specialitiesListAdapter = new SpecialitiesListAdapter();
        layoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.VERTICAL,false);
        specialitiesListHolder.setLayoutManager(layoutManager);
        specialitiesListHolder.setAdapter(specialitiesListAdapter);

        specialitiesListAdapter.setClickListener(this);

        addSpeciality = view.findViewById(R.id.addSpeciality);
        addSpeciality.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAddSpecialityDialog();
            }
        });



departementViewModel.departementRepository.savedSpecialities.observe(getViewLifecycleOwner(), new Observer<List<Speciality>>() {
    @Override
    public void onChanged(List<Speciality> specialities) {
        specialitiesListAdapter.UpdateSpecialitiesList((ArrayList<Speciality>) specialities);

    }
});











        departementViewModel.departementRepository.savedDepartements.observe(getViewLifecycleOwner(), new Observer<List<Departement>>() {
            @Override
            public void onChanged(List<Departement> departements) {

              departementList = (ArrayList<Departement>) departements;


            }
        });





    }

    @Override
    public void onClick(View view, int position) {
        Toast.makeText(getContext(), "show info about the selected speciality", Toast.LENGTH_SHORT).show();
    }



    public void ShowAddSpecialityDialog() {


            Dialog addDepartementDialog = new Dialog(getContext());
            addDepartementDialog.setContentView(R.layout.dialog_add_speciality);
        addDepartementDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            EditText speciality_name_input;
            Button save, cancel;
        AutoCompleteTextView departemrnts_autoCompleteTextView;
        speciality_name_input = addDepartementDialog.findViewById(R.id.speciality_input);
            save = addDepartementDialog.findViewById(R.id.speciality_save_btn);
            cancel = addDepartementDialog.findViewById(R.id.speciality_canel_btn);

        departemrnts_autoCompleteTextView = addDepartementDialog.findViewById(R.id.depratement_drop_down_list);
        adapterItems = new ArrayAdapter<String>(getContext(),R.layout.drop_down_list_item,getDepartementNames());
        departemrnts_autoCompleteTextView.setAdapter(adapterItems);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Speciality s = new Speciality(speciality_name_input.getText().toString(),departementViewModel.selected_departement);
                try {
                    departementViewModel.InsertSpeciality(s);
                    Toast.makeText(getContext(), "saved successfull", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                addDepartementDialog.dismiss();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                addDepartementDialog.dismiss();
            }

        });


            addDepartementDialog.create();
            addDepartementDialog.show();

    }


    public ArrayList<String> getDepartementNames(){
        ArrayList<String> stringList = new ArrayList<>();
        for (Departement d:
                departementList) {
            stringList.add(d.name);
        }
        return  stringList;
    }




}
