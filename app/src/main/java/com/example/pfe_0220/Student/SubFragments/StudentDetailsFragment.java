package com.example.pfe_0220.Student.SubFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.Adapters.StudentDetailPanelsAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class StudentDetailsFragment extends Fragment {

    ViewPager panelsHolder;
    StudentDetailPanelsAdapter studentDetailPanelsAdapter;
    TabLayout panelsTab;
    ArrayList<Fragment> fragments;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_student_details, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        panelsHolder = view.findViewById(R.id.student_panel_holder);
        StudentHistoryFragment historyFragment = new StudentHistoryFragment();
        StudentProfileFragment studentProfileFragment = new StudentProfileFragment();
        fragments = new ArrayList<Fragment>();

        fragments.add(studentProfileFragment);
        fragments.add(historyFragment);
        studentDetailPanelsAdapter = new StudentDetailPanelsAdapter(getChildFragmentManager(), fragments);
        panelsHolder.setAdapter(studentDetailPanelsAdapter);
        panelsTab = view.findViewById(R.id.student_panel_tablayout);
        panelsTab.setupWithViewPager(panelsHolder);
        panelsTab.getTabAt(0).setText("General Info");
        panelsTab.getTabAt(1).setText("Attendence History");

    }
}
