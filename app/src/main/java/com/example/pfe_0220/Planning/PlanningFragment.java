package com.example.pfe_0220.Planning;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.pfe_0220.MainActivity;
import com.example.pfe_0220.Planning.Adapter.ClassesFragmentViewPagerAdapter;
import com.example.pfe_0220.Planning.Adapter.SchoolYearDaysAdapter;
import com.example.pfe_0220.Planning.SubFragment.AddPlanificationFragment;
import com.example.pfe_0220.R;
import com.example.pfe_0220.databinding.StepperAddPlanificationFragmentsHolderBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.Calendar;

public class PlanningFragment extends Fragment {

    PlanningViewModel vm;
    RecyclerView school_year_days_holder;
    SchoolYearDaysAdapter schoolYearDaysAdapter;
    TextView school_year;
    TabLayout tabLayout;
    ViewPager classesFragmentsHolder;
    ClassesFragmentViewPagerAdapter schoolClassesAdapter;

    FloatingActionButton addnewPlanningBtn;


    /**
     * this is autogenrated method to create the view responsible of rendering the UI
     *
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_planning, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        vm = new ViewModelProvider(requireActivity()).get(PlanningViewModel.class);

        DaysScroolBarConfiguration(view);
        TabPanelsConfiguration(view);
        LinkViews(view);


        addnewPlanningBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).ShowFragment(new AddPlanificationFragment(), "add new Planning");
            }
        });


        vm.school_year_days.observe(getViewLifecycleOwner(), new Observer<ArrayList<Calendar>>() {
            @Override
            public void onChanged(ArrayList<Calendar> days) {
                UpdateDaysScroolBar(days);
            }
        });


    }

    private void LinkViews(View view) {

        addnewPlanningBtn = view.findViewById(R.id.add_planing_btn);

    }

    private void TabPanelsConfiguration(View view) {

        tabLayout = view.findViewById(R.id.tab_class_type);

        classesFragmentsHolder = view.findViewById(R.id.view_pager_class_fragment_holder);
        schoolClassesAdapter = new ClassesFragmentViewPagerAdapter(getChildFragmentManager());

        classesFragmentsHolder.setAdapter(schoolClassesAdapter);

        tabLayout.setupWithViewPager(classesFragmentsHolder);
        tabLayout.getTabAt(0).setText("All");
        tabLayout.getTabAt(1).setText("Cours");
        tabLayout.getTabAt(2).setText("TD");
        tabLayout.getTabAt(3).setText("TP");
        tabLayout.getTabAt(4).setText("Exams");

    }

    private void UpdateDaysScroolBar(ArrayList<Calendar> days) {


        schoolYearDaysAdapter.UpdateSchoolYearAdapter(days);

        int current_year = vm.current_school_day.get(Calendar.YEAR);

        int next_year = current_year + 1;

        school_year.setText(current_year + " - " + next_year);
    }

    private void DaysScroolBarConfiguration(View view) {

        school_year_days_holder = view.findViewById(R.id.days_container);
        school_year = view.findViewById(R.id.school_year);

        schoolYearDaysAdapter = new SchoolYearDaysAdapter();
        school_year_days_holder.setHasFixedSize(true);
        LinearLayoutManager mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        school_year_days_holder.setLayoutManager(mlayoutManager);
        school_year_days_holder.setAdapter(schoolYearDaysAdapter);
    }


}
