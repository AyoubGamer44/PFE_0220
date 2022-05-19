package com.example.pfe_0220.Planning.SubFragment;

import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pfe_0220.Departement.DepartementViewModel;
import com.example.pfe_0220.Planning.Models.SchoolClass;
import com.example.pfe_0220.Planning.PlanningViewModel;
import com.example.pfe_0220.R;

import java.util.ArrayList;
import java.util.Calendar;

public class StepperThirdFragment extends Fragment {


    TextView start_time_picker, end_time__picker;
    ArrayAdapter<String> schoolClassesTypeAdapter;

    AutoCompleteTextView schoolClassesTypeHolder;

    PlanningViewModel planningViewModel;
    DepartementViewModel departementViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.stepper_fragment_third_page, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        LinkViews(view);
        planningViewModel = new ViewModelProvider(requireActivity()).get(PlanningViewModel.class);
        departementViewModel = new ViewModelProvider(requireActivity()).get(DepartementViewModel.class);




        start_time_picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                      start_time_picker.setText(hourOfDay +" : "+minute);
                      Calendar startTime = Calendar.getInstance();
                      startTime.set(Calendar.HOUR,hourOfDay);
                      startTime.set(Calendar.MINUTE,minute);
                      planningViewModel.newSchoolClass.setStart_time(startTime);
                    }
                }, 8, 00, true);
                timePickerDialog.create();
                timePickerDialog.show();
            }
        });

        end_time__picker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        end_time__picker.setText(hourOfDay +" : "+minute);

                        Calendar time = Calendar.getInstance();
                        time.set(Calendar.HOUR,hourOfDay);
                        time.set(Calendar.MINUTE,minute);
                        planningViewModel.newSchoolClass.setEnd_time(time);

                    }
                }, 8, 00, true);
                timePickerDialog.create();
                timePickerDialog.show();
            }
        });


        schoolClassesTypeHolder.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                planningViewModel.newSchoolClass.setSchool_classtype(position);
            }
        });

    }



    private void LinkViews(View view) {

        schoolClassesTypeHolder = view.findViewById(R.id.school_type_drop_down_list);
        start_time_picker = view.findViewById(R.id.start_time_picker);
        end_time__picker = view.findViewById(R.id.end_time_picker);
        schoolClassesTypeHolder = view.findViewById(R.id.school_type_drop_down_list);
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
            schoolClassesTypeAdapter = new ArrayAdapter<String>(this.getContext(), R.layout.drop_down_list_item, departementViewModel.departementRepository.getSchoolClassType());
            schoolClassesTypeHolder.setAdapter(schoolClassesTypeAdapter);

        }
    }
}
