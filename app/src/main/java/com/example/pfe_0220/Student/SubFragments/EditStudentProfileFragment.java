package com.example.pfe_0220.Student.SubFragments;

import static com.example.pfe_0220.Departement.DepartementRepository.FIRST_GROUP;
import static com.example.pfe_0220.Departement.DepartementRepository.FIRST_SECTION;
import static com.example.pfe_0220.Departement.DepartementRepository.LAST_GROUP;
import static com.example.pfe_0220.Departement.DepartementRepository.LAST_SECTION;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.example.pfe_0220.Departement.Adapters.DepartementDropDownListAdapter;
import com.example.pfe_0220.Departement.Adapters.SpecialitiesDropDownListAdapter;
import com.example.pfe_0220.Departement.DepartementViewModel;
import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.Models.Speciality;
import com.example.pfe_0220.MainActivity;
import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.Model.Student;
import com.example.pfe_0220.Student.StudentFragment;
import com.example.pfe_0220.Student.StudentViewModel;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class EditStudentProfileFragment extends Fragment {

    DepartementDropDownListAdapter departement_drop_down_list_adapter;
    SpecialitiesDropDownListAdapter specialities_drop_down_list_adapter;
    AutoCompleteTextView departementlist_holder, specialities_holder, levelPicker;

    DepartementViewModel departementViewModel;
    StudentViewModel studentViewModel;

    ArrayAdapter<String> levelsAdapter;

    Button addBtn;


    EditText firstName, lastName, student_id, adress, email, phone;
    TextView birthDate;
    NumberPicker sectionPicker, groupPicker;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_edit_student_profile, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        departementViewModel = new ViewModelProvider(requireActivity()).get(DepartementViewModel.class);
        studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);
        LinkViews(view);
        ArrayList<Departement> departements = new ArrayList<>();
        ArrayList<Speciality> specialities = new ArrayList<>();


        departement_drop_down_list_adapter = new DepartementDropDownListAdapter(getContext(), departements);
        specialities_drop_down_list_adapter = new SpecialitiesDropDownListAdapter(getContext(), specialities);
        departementlist_holder.setAdapter(departement_drop_down_list_adapter);
        specialities_holder.setAdapter(specialities_drop_down_list_adapter);

        studentViewModel.newStudent = new Student();

        levelsAdapter = new ArrayAdapter<>(getContext(), R.layout.node_simple_drop_down, departementViewModel.departementRepository.getAvailableLevels());
        levelPicker.setAdapter(levelsAdapter);


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
                studentViewModel.newStudent.department_id = selected_departement_id;


            }
        });


        specialities_holder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selected_speciality_id = specialities_drop_down_list_adapter.getItem(position).id;


                studentViewModel.newStudent.speciality_id = selected_speciality_id;

            }
        });


        sectionPicker.setMinValue(FIRST_SECTION);
        sectionPicker.setMaxValue(LAST_SECTION);
        groupPicker.setMinValue(FIRST_GROUP);
        groupPicker.setMaxValue(LAST_GROUP);
        levelPicker.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                studentViewModel.newStudent.level = position;
            }
        });


        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                studentViewModel.newStudent.firstName = firstName.getText().toString();
                studentViewModel.newStudent.lastName = lastName.getText().toString();
                studentViewModel.newStudent.adress = adress.getText().toString();

                studentViewModel.newStudent.id = Integer.parseInt(student_id.getText().toString());
                studentViewModel.newStudent.email = email.getText().toString();
                studentViewModel.newStudent.phone_number = phone.getText().toString();
                studentViewModel.newStudent.school_group = groupPicker.getValue();
                studentViewModel.newStudent.section = sectionPicker.getValue();
                studentViewModel.InsertStudent(studentViewModel.newStudent);

new AlertDialog.Builder(getContext()).setMessage(studentViewModel.newStudent.school_group+" section"+groupPicker.getValue()).create().show();
             //   ((MainActivity) getActivity()).ShowFragment(new StudentFragment(), "Student fragment");
            }
        });



        //group


        //group

        birthDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                        Calendar day = Calendar.getInstance();
                        day.set(year, month, dayOfMonth);
                        studentViewModel.newStudent.birthDate = day;
                        birthDate.setText(dayOfMonth + " / " + month + " / " + year);

                    }
                }, 2022, 6, 6);


                datePickerDialog.create();
                datePickerDialog.show();
            }

        });


    }

    private void LinkViews(View v) {
        addBtn = v.findViewById(R.id.save_btn);
        firstName = v.findViewById(R.id.studentFirstName_input);
        lastName = v.findViewById(R.id.studentLastName_input);
        firstName = v.findViewById(R.id.studentFirstName_input);
        lastName = v.findViewById(R.id.studentLastName_input);
        birthDate = v.findViewById(R.id.birth_date);
        student_id = v.findViewById(R.id.student_id);
        adress = v.findViewById(R.id.adress);
        phone = v.findViewById(R.id.phone);
        email = v.findViewById(R.id.email);
        sectionPicker = v.findViewById(R.id.section_picker_edit_student);
        groupPicker = v.findViewById(R.id.group_picker_edit_student);
        departementlist_holder = v.findViewById(R.id.departement_drop_down_list_edit_profile);
        specialities_holder = v.findViewById(R.id.specialities_drop_down_list_edit_student);
        levelPicker = v.findViewById(R.id.levels_drop_down_list_edit_student);

    }
}