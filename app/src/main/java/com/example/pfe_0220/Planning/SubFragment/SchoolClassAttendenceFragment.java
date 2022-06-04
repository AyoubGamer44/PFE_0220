package com.example.pfe_0220.Planning.SubFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.example.pfe_0220.Departement.DepartementRepository;
import com.example.pfe_0220.Planning.Adapter.PersonPagerAdapter;
import com.example.pfe_0220.Planning.Models.Attendence;
import com.example.pfe_0220.Planning.Models.AttendenceNode;
import com.example.pfe_0220.Planning.Models.SchoolClassNode;
import com.example.pfe_0220.Planning.PlanningViewModel;
import com.example.pfe_0220.Planning.ViewModels.SchoolClassesViewModel;
import com.example.pfe_0220.Profile.ProfileViewModel;
import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.Model.Student;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.Result;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


enum ScanState {
    student, error, scanning
}

public class SchoolClassAttendenceFragment extends Fragment {

    TabLayout personSelectorTab;
    ViewPager personPager;
    PersonPagerAdapter personPagerAdapter;

    TextView scanningMesage;
    LinearLayout studentNode;

    FloatingActionButton scan_btn;

    SchoolClassesViewModel schoolClassesViewModel;

    ArrayList<Fragment> fragements = new ArrayList<>();

    PlanningViewModel planningViewModel;
    ProfileViewModel profileViewModel;
    TextView planifierName;

    TextView moduleName, speciaityName, departementName, eventType, timing, level, group;

    int attendence_id;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        schoolClassesViewModel = new ViewModelProvider(requireActivity()).get(SchoolClassesViewModel.class);
        planningViewModel = new ViewModelProvider(requireActivity()).get(PlanningViewModel.class);
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);


        InitialiseFragments();
        LinkViews(view);

        ConstructScannerDialog();




        SetUpClassInfo(planningViewModel.selected_school_class);


        try {
            schoolClassesViewModel.GetResponsibleOf(planningViewModel.selected_school_class.id);
            //todo :  get student of this class
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    private void SetUpClassInfo(SchoolClassNode selected_school_class) {
        moduleName.setText(selected_school_class.module);
        speciaityName.setText(selected_school_class.speciality);
        departementName.setText(selected_school_class.departement);
        eventType.setText(DepartementRepository.getSchoolClassType().get(selected_school_class.school_classtype));
        timing.setText(selected_school_class.getStartTime() + " - " + selected_school_class.getEndTime());
        level.setText(DepartementRepository.getAvailableLevels().get(selected_school_class.level));
        group.setText("group N :" + selected_school_class.school_group + "");
   planifierName.setText("planified on : " +selected_school_class.start_time.get(Calendar.YEAR)+" / "+selected_school_class.start_time.get(Calendar.MONTH)+" / "+ selected_school_class.start_time.get(Calendar.DAY_OF_MONTH));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_class_attendence, container, false);
    }

    private void LinkViews(View v) {

        personSelectorTab = v.findViewById(R.id.personSelectorTab);
        personPager = v.findViewById(R.id.personPager);
        scan_btn = v.findViewById(R.id.scan_button);

planifierName = v.findViewById(R.id.planifer_name);
        personPagerAdapter = new PersonPagerAdapter(getChildFragmentManager(), fragements);
        personPager.setAdapter(personPagerAdapter);

        personSelectorTab.setupWithViewPager(personPager);
        personSelectorTab.getTabAt(0).setText("students");
        personSelectorTab.getTabAt(0).setIcon(R.drawable.icon_student);
        personSelectorTab.getTabAt(1).setIcon(R.drawable.icon_teacher);
        personSelectorTab.getTabAt(1).setText("responsibles");


        moduleName = v.findViewById(R.id.module_name);
        speciaityName = v.findViewById(R.id.speciality_name);
        departementName = v.findViewById(R.id.departement_id);
        eventType = v.findViewById(R.id.event_type);
        timing = v.findViewById(R.id.timing);
        level = v.findViewById(R.id.level);
        group = v.findViewById(R.id.group);

    }

    private void ConstructScannerDialog() {
        Dialog codebarDialog = new Dialog(getContext());
        codebarDialog.setContentView(R.layout.dialog_bar_code_scanner);
        codebarDialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        Button cancel_btn = codebarDialog.findViewById(R.id.cancel_btn);


        CodeScannerView scannerView;
        CodeScanner codeScanner;
        scannerView = codebarDialog.findViewById(R.id.scanner_view);
        scanningMesage = codebarDialog.findViewById(R.id.scanning_message);
        studentNode = codebarDialog.findViewById(R.id.student_node);
        codeScanner = new CodeScanner(codebarDialog.getContext(), scannerView);

        codeScanner.setCamera(CodeScanner.CAMERA_BACK);
        codeScanner.setFormats(CodeScanner.ALL_FORMATS);
        codeScanner.setAutoFocusMode(AutoFocusMode.SAFE);
        codeScanner.setScanMode(ScanMode.SINGLE);
        codeScanner.setAutoFocusEnabled(true);
        codeScanner.setFlashEnabled(false);
        codeScanner.startPreview();

        codeScanner.setDecodeCallback(new DecodeCallback() {
            @Override
            public void onDecoded(@NonNull Result result) {


                getActivity().runOnUiThread(new Runnable() {

                    @Override
                    public void run() {

                        String scanned_id = result.getText().toString();

                        try {
                            schoolClassesViewModel.schoolClassRepository.studentsAttendence.observe(getViewLifecycleOwner(), new Observer<List<AttendenceNode>>() {
                                @Override
                                public void onChanged(List<AttendenceNode> attendenceNodes) {
                                    attendence_id = AttendenceNode.getAttendenceof(scanned_id, (ArrayList<AttendenceNode>) attendenceNodes);
                                }
                            });
                            Attendence attendence = schoolClassesViewModel.getAttendenceOf(attendence_id);
                            Student scannedStudent = planningViewModel.studentRepository.getStudentwithId(scanned_id);
                            ToggleScanState(ScanState.student, scannedStudent);
                            Toast.makeText(getContext(), scannedStudent.firstName + " " + scannedStudent.lastName, Toast.LENGTH_SHORT).show();
                            attendence.state = Attendence.getNextAttendence(attendence.state);

                            schoolClassesViewModel.UpdateStudentAttendence(attendence);
                        } catch (Exception e) {
                            Toast.makeText(getContext(), "an error occured", Toast.LENGTH_SHORT).show();
                        }


                    }
                });


            }
        });

        codeScanner.setErrorCallback(new ErrorCallback() {
            @Override
            public void onError(@NonNull Throwable thrown) {
                ToggleScanState(ScanState.scanning, null);
            }
        });
        codebarDialog.create();


        cancel_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codebarDialog.dismiss();
                codeScanner.releaseResources();

            }
        });


        scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                codebarDialog.show();
                codeScanner.startPreview();
            }
        });
    }

    private void InitialiseFragments() {


        SchoolClassStudentAttendencesFragment schoolClassStudentAttendencesFragment = new SchoolClassStudentAttendencesFragment();
        SchoolClassResponsibleAttendencesFragment schoolClassResponsibleAttendencesFragment = new SchoolClassResponsibleAttendencesFragment();
        fragements.add(schoolClassStudentAttendencesFragment);
        fragements.add(schoolClassResponsibleAttendencesFragment);
    }

    private void ToggleScanState(ScanState student, Student scannedStudent) {
        switch (student) {
            case student:
                studentNode.setVisibility(View.VISIBLE);
                scanningMesage.setVisibility(View.GONE);
                TextView studentName = studentNode.findViewById(R.id.studentName);
                TextView student_id = studentNode.findViewById(R.id.studentId);
                TextView time = studentNode.findViewById(R.id.enter_time);

                studentName.setText(scannedStudent.firstName + " " + scannedStudent.lastName);
                student_id.setText(scannedStudent.id + "");
                time.setText("Now");
                break;
            case scanning:
                scanningMesage.setVisibility(View.VISIBLE);
                studentNode.setVisibility(View.GONE);
                break;
        }
    }
}