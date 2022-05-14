package com.example.pfe_0220.Planning.SubFragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.MainActivity;
import com.example.pfe_0220.Planning.Adapter.SchoolClassesAdapter;
import com.example.pfe_0220.R;

public class SchoolClassFragment extends Fragment implements SchoolClassesAdapter.ItemClickListener {


    RecyclerView schoolClassesHolder;
    SchoolClassesAdapter schoolClassesAdapter;
    RecyclerView.LayoutManager mlayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_school_class, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        schoolClassesAdapter = new SchoolClassesAdapter();
        schoolClassesHolder = view.findViewById(R.id.classes_holder);
        schoolClassesHolder.setLayoutManager(mlayoutManager);
        schoolClassesHolder.setAdapter(schoolClassesAdapter);
        schoolClassesAdapter.setClickListener(this);


    }

    @Override
    public void onClick(View view, int position) {

        ((MainActivity)getActivity()).ShowFragment(new SchoolClassAttendenceFragment()," classes attendences ");
    }
}
