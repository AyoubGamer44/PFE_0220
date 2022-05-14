package com.example.pfe_0220.Planning.SubFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pfe_0220.Planning.Adapter.StudentAttendencesNodeAdapter;
import com.example.pfe_0220.R;

public class SchoolClassPersonFragment extends Fragment {


    RecyclerView student_attendences_holder;
    StudentAttendencesNodeAdapter studentAttendencesNodeAdapter;
    RecyclerView.LayoutManager mlayoutManager;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_school_class_person_holder, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        student_attendences_holder = view.findViewById(R.id.student_attendences_holder);

        studentAttendencesNodeAdapter = new StudentAttendencesNodeAdapter();
        mlayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        student_attendences_holder.setLayoutManager(mlayoutManager);
        student_attendences_holder.setAdapter(studentAttendencesNodeAdapter);
        Dialog dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.dialog_bar_code_scanner);
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);


//       CodeScanner mCodeScanner;
//       CodeScannerView scannerView = dialog.findViewById(R.id.scanner_view);
//        mCodeScanner = new CodeScanner(dialog.getContext(), scannerView);
//        mCodeScanner.setDecodeCallback(new DecodeCallback() {
//            @Override
//            public void onDecoded(@NonNull Result result) {
//                Toast.makeText(getContext(), "hello", Toast.LENGTH_SHORT).show();
//            }
//        });
//        scannerView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                mCodeScanner.startPreview();
//            }
//        });
//
//
//        mCodeScanner.startPreview();
//
//
//        dialog.create();
//dialog.show();
//


    }
}
