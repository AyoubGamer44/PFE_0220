package com.example.pfe_0220.Planning.SubFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import com.example.pfe_0220.Planning.Adapter.Planification_FragmentHolderAdapter;
import com.example.pfe_0220.Planning.Models.SchoolClass;
import com.example.pfe_0220.Planning.PlanningViewModel;
import com.example.pfe_0220.R;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

public class AddPlanificationFragment extends Fragment {


    ViewPager fragmentsHolder;
    Planification_FragmentHolderAdapter planification_fragmentHolderAdapter;
    ArrayList<Fragment> fragments = new ArrayList<>();
    TabLayout tabLayout;
    PlanningViewModel planningViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.stepper_add_planification_fragments_holder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        planningViewModel = new ViewModelProvider(requireActivity()).get(PlanningViewModel.class);
        planningViewModel.newSchoolClass = new SchoolClass();

        //instantiate fragments to be used in the view pager
        fragmentsHolder = view.findViewById(R.id.addplanning_fragment_holder);
        fragmentsHolder.setOffscreenPageLimit(1);
        Fragment firstPage = new StepperFirstFragment();
        Fragment secondPage = new StepperSecondFragment();
        Fragment thirdPage = new StepperThirdFragment();
        Fragment fourthPage = new StepperFourthFragment();
        fragments.add(firstPage);
        fragments.add(secondPage);
        fragments.add(thirdPage);
        fragments.add(fourthPage);

        planification_fragmentHolderAdapter = new Planification_FragmentHolderAdapter(getChildFragmentManager(), fragments);
        fragmentsHolder.setAdapter(planification_fragmentHolderAdapter);
        tabLayout = view.findViewById(R.id.add_planification_tab);
        tabLayout.setupWithViewPager(fragmentsHolder);
        tabLayout.getTabAt(0).setText("Module Selection");
        tabLayout.getTabAt(1).setText("Level Selection");
        tabLayout.getTabAt(2).setText("Time Selection");
        tabLayout.getTabAt(3).setText("Teacher Selection");

    }


}
