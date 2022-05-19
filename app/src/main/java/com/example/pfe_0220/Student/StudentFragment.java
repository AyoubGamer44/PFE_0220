package com.example.pfe_0220.Student;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Departement.DepartementRepository;
import com.example.pfe_0220.Departement.DepartementViewModel;
import com.example.pfe_0220.Departement.Models.Departement;
import com.example.pfe_0220.Departement.Models.Speciality;
import com.example.pfe_0220.MainActivity;
import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.Adapters.StudentListHolderAdapter;
import com.example.pfe_0220.Student.Dialog.FilterDialog;
import com.example.pfe_0220.Student.Model.Student;
import com.example.pfe_0220.Student.SubFragments.EditStudentProfileFragment;
import com.example.pfe_0220.Student.SubFragments.StudentDetailsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;


public class StudentFragment extends Fragment implements StudentListHolderAdapter.ItemClickListener {

    // UI componenets
    RecyclerView studentListHolder;
    StudentListHolderAdapter studentListHolderAdapter;
    RecyclerView.LayoutManager mlayoutManager;
    Button changeGroupBtn;

    DepartementViewModel departementViewModel;
    //Data variables
    StudentViewModel studentViewModel;


    TextView departementTV,SpecialityTV,LevelTV,SecionTV,groupTV;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_students, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);

        departementViewModel = new ViewModelProvider(requireActivity()).get(DepartementViewModel.class);
        studentListHolder = view.findViewById(R.id.student_list_holder);
        mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        studentListHolder.setLayoutManager(mlayoutManager);
        studentListHolderAdapter = new StudentListHolderAdapter();
        studentListHolder.setAdapter(studentListHolderAdapter);


        FilterDialog filterDialog = new FilterDialog(getContext());
        filterDialog.create();

        studentListHolderAdapter.setClickListener(this);

        try {
            departementViewModel.departementRepository.getDepartement();


        } catch (Exception e) {
            e.printStackTrace();
        }


        departementViewModel.departementRepository.savedDepartements.observe(getViewLifecycleOwner(), new Observer<List<Departement>>() {
            @Override
            public void onChanged(List<Departement> departements) {
                filterDialog.departement_drop_down_list_adapter.UpdateElement((ArrayList<Departement>) departements);


            }
        });


        filterDialog.departementlist_holder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int selected_departement_id = position;

                try {
                    departementViewModel.departementRepository.getSpecialitiesOf(selected_departement_id);
                } catch (Exception e) {
                    e.printStackTrace();
                }

//upload specialities of that departement
                departementViewModel.departementRepository.savedSpecialities.observe(getViewLifecycleOwner(), new Observer<List<Speciality>>() {
                    @Override
                    public void onChanged(List<Speciality> specialities) {
                        filterDialog.specialities_drop_down_list_adapter.UpdateElement((ArrayList<Speciality>) specialities);
                    }
                });
                filterDialog.selected_departement = selected_departement_id;


            }
        });


        filterDialog.specialities_holder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int selected_speciality_id = position + 1;

                filterDialog.selected_speciality = selected_speciality_id;


            }
        });


        changeGroupBtn = view.findViewById(R.id.group_filter_button);
        changeGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                filterDialog.show();

            }
        });

        filterDialog.InjectListner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                try {


                    studentViewModel.getStudentof(filterDialog.selected_departement, filterDialog.selected_speciality, filterDialog.selected_section, filterDialog.selected_level, filterDialog.selected_group);

                    studentViewModel.studentRepository.foundStudent.observe(getViewLifecycleOwner(), new Observer<List<Student>>() {
                        @Override
                        public void onChanged(List<Student> students) {
                            filterDialog.selected_group = filterDialog.groupPicker.getValue();
                            filterDialog.selected_section = filterDialog.sectionPicker.getValue();


                            studentListHolderAdapter.UpdateStudentList((ArrayList<Student>) students);

                        }
                    });

                    LevelTV.setText(DepartementRepository.getAvailableLevels().get(filterDialog.selected_level));
                    SecionTV.setText("Section N : "+filterDialog.selected_section);
                    groupTV.setText("Group N : "+filterDialog.selected_group);
                    departementTV.setText( filterDialog.departement_drop_down_list_adapter.getItem(filterDialog.selected_departement).name);
                    SpecialityTV.setText( filterDialog.specialities_drop_down_list_adapter.getItem(filterDialog.selected_speciality).name);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                filterDialog.dismiss();
            }
        });

        FloatingActionButton add_student_btn;
        add_student_btn = view.findViewById(R.id.add_student_btn);
        add_student_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).ShowFragment(new EditStudentProfileFragment(), "Add New Student");
            }
        });

        LinkViews(view);

    }



    public void LinkViews(View v){
        departementTV = v.findViewById(R.id.departement);
                SpecialityTV= v.findViewById(R.id.speciality_name);
        LevelTV= v.findViewById(R.id.level);
                SecionTV= v.findViewById(R.id.section);
        groupTV= v.findViewById(R.id.group);
    }

    @Override
    public void onClick(View view, int position) {
        studentViewModel.selectedStudent_id = studentListHolderAdapter.students.get(position).id;
        ((MainActivity) getActivity()).ShowFragment(new StudentDetailsFragment(), "student list");
    }
}
