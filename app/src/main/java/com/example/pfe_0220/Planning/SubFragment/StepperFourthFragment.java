package com.example.pfe_0220.Planning.SubFragment;

import android.app.AlertDialog;
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

import com.example.pfe_0220.Planning.Adapter.TeacherCheckBoxListAdapter;
import com.example.pfe_0220.Planning.PlanningViewModel;
import com.example.pfe_0220.Planning.ViewModels.SchoolClassesViewModel;
import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.Model.Student;
import com.example.pfe_0220.Teacher.Model.Teacher;

import java.util.ArrayList;
import java.util.List;

public class StepperFourthFragment extends Fragment implements TeacherCheckBoxListAdapter.SelectedTeacherListner {

    RecyclerView teacher_list_holder;
    TeacherCheckBoxListAdapter teacherCheckBoxListAdapter;
    RecyclerView.LayoutManager layoutManager;
    Button saveBtn;
    PlanningViewModel planningViewModel;
    SchoolClassesViewModel schoolClassesViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.stepper_fragment_fourth_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        planningViewModel = new ViewModelProvider(requireActivity()).get(PlanningViewModel.class);
        schoolClassesViewModel = new ViewModelProvider(requireActivity()).get(SchoolClassesViewModel.class);
        Configuration(view);






    }

    private void Configuration(View view) {

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        teacher_list_holder = view.findViewById(R.id.teacher_check_nodes_holder);
        teacherCheckBoxListAdapter = new TeacherCheckBoxListAdapter();
        teacher_list_holder.setLayoutManager(layoutManager);
        teacher_list_holder.setAdapter(teacherCheckBoxListAdapter);
        ArrayList<Teacher> teachers = new ArrayList<>();


        teacherCheckBoxListAdapter.SetTeacherSelectionListner(this);
        saveBtn = view.findViewById(R.id.planification_save_btn);





        planningViewModel.teacherRepository.teachers.observe(getViewLifecycleOwner(), new Observer<List<Teacher>>() {
            @Override
            public void onChanged(List<Teacher> teachers) {
                teacherCheckBoxListAdapter.UpdateTeacherList((ArrayList<Teacher>) teachers);
            }
        });

    }


    /**
     * wait for the view to be visible before linking the views
     *
     * @param isVisibleToUser
     */

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            try {

               planningViewModel.studentRepository.getStudentOf(planningViewModel.newSchoolClass.departement_id,
                       planningViewModel.newSchoolClass.speciality_id,planningViewModel.newSchoolClass.level,planningViewModel.newSchoolClass.section,planningViewModel.newSchoolClass.school_group);




            } catch (Exception e) {
                Toast.makeText(getContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            }


            planningViewModel.studentRepository.foundStudent.observe(getViewLifecycleOwner(), new Observer<List<Student>>() {
                @Override
                public void onChanged(List<Student> students) {
                    StringBuffer foundStudents = new StringBuffer();
                    foundStudents.append("departement "+planningViewModel.newSchoolClass.departement_id+" spec "+planningViewModel.newSchoolClass.speciality_id+" level "+
                            planningViewModel.newSchoolClass.level+" group "+planningViewModel.newSchoolClass.school_group+" section  "+planningViewModel.newSchoolClass.section+ " there is" +students.size()+"\n");

                    for (Student s:students
                    ) {
                        foundStudents.append(s.firstName+"\n");
                    }

                    new AlertDialog.Builder(getContext()).setMessage(foundStudents.toString()).create().show();

                    planningViewModel.newSchoolClass.students_of_class .addAll((ArrayList<Student>) students);
                }
            });


            saveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    planningViewModel.PlaneNewSchoolClass(planningViewModel.newSchoolClass, teacherCheckBoxListAdapter.selected_teachers);


                }
            });

        }
    }

    @Override
    public void onTeacherSelected(ArrayList<Teacher> teachers) {
        StringBuffer stringBuffer = new StringBuffer();
        for (Teacher t : teachers
        ) {
            stringBuffer.append(t.firstName + " " + t.lastName + "\n");
        }
        Toast.makeText(getContext(), stringBuffer.toString(), Toast.LENGTH_SHORT).show();
    }
}
