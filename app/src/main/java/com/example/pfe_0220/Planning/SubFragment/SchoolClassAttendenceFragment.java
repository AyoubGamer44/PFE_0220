package com.example.pfe_0220.Planning.SubFragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.pfe_0220.Planning.Adapter.PersonPagerAdapter;
import com.example.pfe_0220.R;
import com.google.android.material.tabs.TabLayout;


public class SchoolClassAttendenceFragment extends Fragment {
TextView text;
    TabLayout personSelectorTab;
   ViewPager personPager;
   PersonPagerAdapter personPagerAdapter;

    public SchoolClassAttendenceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinkViews(view);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_school_class_attendence, container, false);
    }

    private void LinkViews(View v){

    personSelectorTab = v.findViewById(R.id.personSelectorTab);
       personPager = v.findViewById(R.id.personPager);
    personPagerAdapter = new PersonPagerAdapter(getChildFragmentManager());
      personPager.setAdapter(personPagerAdapter);

      personSelectorTab.setupWithViewPager(personPager);
      personSelectorTab.getTabAt(0).setText("students");
      personSelectorTab.getTabAt(1).setText("responsibles");


    }

}