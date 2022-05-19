package com.example.pfe_0220.Student.SubFragments;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.Adapters.ModuleListAdapter;
import com.example.pfe_0220.Student.Model.ModuleReportNode;
import com.example.pfe_0220.Student.StudentViewModel;

import java.util.ArrayList;

public class StudentHistoryFragment extends Fragment {

    RecyclerView moduleHistoryHolder;
    ModuleListAdapter moduleListAdapter;
    RecyclerView.LayoutManager layoutManager;

    StudentViewModel studentViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fargment_attendence_history, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinkViews(view);


        studentViewModel = new ViewModelProvider(requireActivity()).get(StudentViewModel.class);


        try {
            studentViewModel.getReportOfStudent();
        } catch (Exception e) {
            e.printStackTrace();
        }

        studentViewModel.studentRepository.selected_student_report.observe(getViewLifecycleOwner(), new Observer<ArrayList<ModuleReportNode>>() {
            @Override
            public void onChanged(ArrayList<ModuleReportNode> moduleReportNodes) {
             StringBuffer nodesString = new StringBuffer();
                for (ModuleReportNode n: moduleReportNodes
                     ) {
                    nodesString.append(n.moduleName +" has "+ n.attendenceReportNodes.size());
                }

                new AlertDialog.Builder(getContext()).setMessage(nodesString.toString()).create().show();
                moduleListAdapter.UpdateReports(moduleReportNodes);
            }
        });


    }

    private void LinkViews(View v) {
        moduleHistoryHolder = v.findViewById(R.id.module_history_holder);

        layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        moduleListAdapter = new ModuleListAdapter();
        moduleHistoryHolder.setLayoutManager(layoutManager);
        moduleHistoryHolder.setAdapter(moduleListAdapter);


    }
}
