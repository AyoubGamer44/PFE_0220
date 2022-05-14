package com.example.pfe_0220.Departement;

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

import com.example.pfe_0220.Departement.Adapters.DepartementListAdapter;
import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.SubFragments.SpecialitiesFragment;
import com.example.pfe_0220.MainActivity;
import com.example.pfe_0220.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class DepartementFragment extends Fragment implements DepartementListAdapter.ItemClickListener {


    DepartementListAdapter departementListAdapter;
    RecyclerView departementListHolder;
    RecyclerView.LayoutManager layoutManager;
    FloatingActionButton addDepartementBtn;

    DepartementViewModel departementViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_departement, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        departementViewModel =new ViewModelProvider(requireActivity()).get(DepartementViewModel.class);

        departementListHolder = view.findViewById(R.id.departmentListHolder);
        addDepartementBtn = view.findViewById(R.id.add_departement_btn);
        departementListAdapter = new DepartementListAdapter();
        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        departementListHolder.setLayoutManager(layoutManager);
        departementListHolder.setAdapter(departementListAdapter);

        departementListAdapter.setClickListener(this);

        addDepartementBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ShowAddDepartementDialog();
            }
        });



departementViewModel.departementRepository.savedDepartements.observe(getViewLifecycleOwner(), new Observer<List<Departement>>() {
    @Override
    public void onChanged(List<Departement> departements) {
        departementListAdapter.UpdateDepartementList((ArrayList<Departement>) departements);
    }
});

//observe database operation
        departementViewModel.status.observe(getViewLifecycleOwner(), new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean aBoolean) {
                if (aBoolean){
                    Toast.makeText(getContext(), "saved", Toast.LENGTH_SHORT).show();
                }
                else Toast.makeText(getContext() , "failed", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void ShowAddDepartementDialog() {
        Dialog addDepartementDialog = new Dialog(getContext());
        addDepartementDialog.setContentView(R.layout.dialog_add_departement);
        EditText departementName;
        Button save, cancel;

        departementName = addDepartementDialog.findViewById(R.id.departement_input);
        save = addDepartementDialog.findViewById(R.id.departement_save_btn);
        cancel = addDepartementDialog.findViewById(R.id.departement_canel_btn);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {
                    departementViewModel.InsertDepartement(new Departement(departementName.getText().toString()));
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


    @Override
    public void onClick(View view, int position) {
        try {
            departementViewModel.selected_departement = departementListAdapter.departements.get(position).id;
            departementViewModel.departementRepository.getSpecialitiesOf( departementListAdapter.departements.get(position).id);
            ((MainActivity) getActivity()).ShowFragment(new SpecialitiesFragment(), "Speciality list");
        } catch (Exception e) {
            Toast.makeText(getContext(), "no speciality to show", Toast.LENGTH_SHORT).show();
        }

    }
}
