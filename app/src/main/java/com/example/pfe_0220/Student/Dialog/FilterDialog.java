package com.example.pfe_0220.Student.Dialog;

import static com.example.pfe_0220.Departement.DepartementRepository.FIRST_GROUP;
import static com.example.pfe_0220.Departement.DepartementRepository.FIRST_SECTION;
import static com.example.pfe_0220.Departement.DepartementRepository.LAST_GROUP;
import static com.example.pfe_0220.Departement.DepartementRepository.LAST_SECTION;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.NumberPicker;

import androidx.annotation.NonNull;

import com.example.pfe_0220.Departement.Adapters.DepartementDropDownListAdapter;
import com.example.pfe_0220.Departement.Adapters.SpecialitiesDropDownListAdapter;
import com.example.pfe_0220.Departement.DepartementRepository;
import com.example.pfe_0220.Departement.DepartementViewModel;
import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.Models.Speciality;
import com.example.pfe_0220.R;

import java.util.ArrayList;

public class FilterDialog extends Dialog {


    public AutoCompleteTextView departementlist_holder, specialities_holder, levelPicker;

    public DepartementDropDownListAdapter departement_drop_down_list_adapter;
    public SpecialitiesDropDownListAdapter specialities_drop_down_list_adapter;
    ArrayAdapter<String> levelsAdapter;

    DepartementViewModel departementViewModel;

    Button validate_btn;


    public NumberPicker sectionPicker, groupPicker;


    public int selected_departement, selected_speciality, selected_level, selected_section, selected_group;


    public FilterDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_filter);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinkViews();
        ArrayList<Departement> departements = new ArrayList<>();
        ArrayList<Speciality> specialities = new ArrayList<>();


        departement_drop_down_list_adapter = new DepartementDropDownListAdapter(getContext(), departements);
        specialities_drop_down_list_adapter = new SpecialitiesDropDownListAdapter(getContext(), specialities);


        validate_btn = findViewById(R.id.validate_btn);


        levelsAdapter = new ArrayAdapter<>(getContext(), R.layout.node_simple_drop_down, DepartementRepository.getAvailableLevels());
        levelPicker.setAdapter(levelsAdapter);


        departementlist_holder.setAdapter(departement_drop_down_list_adapter);
        specialities_holder.setAdapter(specialities_drop_down_list_adapter);


        sectionPicker.setMinValue(FIRST_SECTION);
        sectionPicker.setMaxValue(LAST_SECTION);
        groupPicker.setMinValue(FIRST_GROUP);
        groupPicker.setMaxValue(LAST_GROUP);


        //picker config
        levelPicker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected_level = position;
            }
        });


    }

    public void InjectListner(View.OnClickListener clickListener) {

        validate_btn.setOnClickListener(clickListener);
    }

    private void LinkViews() {
        sectionPicker = findViewById(R.id.section_picker);
        groupPicker = findViewById(R.id.group_picker);
        departementlist_holder = findViewById(R.id.depatement_drop_down_list);
        specialities_holder = findViewById(R.id.specialities_drop_down_list);
        levelPicker = findViewById(R.id.levels_drop_down_list_edit_student);
    }


}
