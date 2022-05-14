package com.example.pfe_0220.Student.SubFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.Adapters.ModuleListAdapter;

public class StudentHistoryFragment extends Fragment {

RecyclerView moduleHistoryHolder;
ModuleListAdapter moduleListAdapter;
RecyclerView.LayoutManager layoutManager;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fargment_attendence_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinkViews(view);





    }

    private void LinkViews(View v) {
        moduleHistoryHolder = v.findViewById(R.id.module_history_holder);

        layoutManager =new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        moduleListAdapter = new ModuleListAdapter();
        moduleHistoryHolder.setLayoutManager(layoutManager);
        moduleHistoryHolder.setAdapter(moduleListAdapter);


    }
}
