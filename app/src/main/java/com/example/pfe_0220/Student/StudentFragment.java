package com.example.pfe_0220.Student;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.MainActivity;
import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.Adapters.StudentListHolderAdapter;
import com.example.pfe_0220.Student.Dialog.BottomSheetFilter;
import com.example.pfe_0220.Student.Model.Student;
import com.example.pfe_0220.Student.SubFragments.EditStudentProfileFragment;
import com.example.pfe_0220.Student.SubFragments.StudentDetailsFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;


public class StudentFragment extends Fragment implements StudentListHolderAdapter.ItemClickListener {

    // UI componenets
    RecyclerView studentListHolder;
    StudentListHolderAdapter studentListHolderAdapter;
    RecyclerView.LayoutManager mlayoutManager;
    Button changeGroupBtn;


    //Data variables
    StudentViewModel studentViewModel;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_students, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);


        studentListHolder = view.findViewById(R.id.student_list_holder);
        mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        studentListHolder.setLayoutManager(mlayoutManager);
        studentListHolderAdapter = new StudentListHolderAdapter();
        studentListHolder.setAdapter(studentListHolderAdapter);


        studentViewModel.studentRepository.selectedStudents.observe(getViewLifecycleOwner(), new Observer<ArrayList<Student>>() {
            @Override
            public void onChanged(ArrayList<Student> students) {
                studentListHolderAdapter.UpdateStudentList(students);
            }
        });


       BottomSheetFilter bottomSheetDialog = new BottomSheetFilter(getContext(), null);


        studentListHolderAdapter.setClickListener(this);


        bottomSheetDialog.create();

        changeGroupBtn = view.findViewById(R.id.group_filter_button);
        changeGroupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                bottomSheetDialog.show();

            }
        });

        bottomSheetDialog.InjectListner(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              studentViewModel.getStudentof(1, 1, 1, 1, "A");
                bottomSheetDialog.dismiss();
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


    }

    @Override
    public void onClick(View view, int position) {
        ((MainActivity) getActivity()).ShowFragment(new StudentDetailsFragment(), "student list");
    }
}
