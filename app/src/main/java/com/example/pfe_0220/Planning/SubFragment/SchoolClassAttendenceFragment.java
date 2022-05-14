package com.example.pfe_0220.Planning.SubFragment;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.budiyev.android.codescanner.AutoFocusMode;
import com.budiyev.android.codescanner.CodeScanner;
import com.budiyev.android.codescanner.CodeScannerView;
import com.budiyev.android.codescanner.DecodeCallback;
import com.budiyev.android.codescanner.ErrorCallback;
import com.budiyev.android.codescanner.ScanMode;
import com.example.pfe_0220.Planning.Adapter.PersonPagerAdapter;
import com.example.pfe_0220.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;
import com.google.zxing.Result;


 enum ScanState{
    student,error,scanning
}

public class SchoolClassAttendenceFragment extends Fragment {
TextView text;
    TabLayout personSelectorTab;
   ViewPager personPager;
   PersonPagerAdapter personPagerAdapter;

    TextView scanningMesage;
    LinearLayout studentNode;

FloatingActionButton scan_btn;
    public SchoolClassAttendenceFragment() {
        // Required empty public constructor
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinkViews(view);


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
        codeScanner.setScanMode(ScanMode.CONTINUOUS);
        codeScanner.setAutoFocusEnabled(true);
        codeScanner.setFlashEnabled(false);
        codeScanner.startPreview();

codeScanner.setDecodeCallback(new DecodeCallback() {
    @Override
    public void onDecoded(@NonNull Result result) {


    getActivity().runOnUiThread(new Runnable() {

            @Override
            public void run() {
             ToggleScanState(ScanState.student);
            }
        });



    }
});

codeScanner.setErrorCallback(new ErrorCallback() {
    @Override
    public void onError(@NonNull Throwable thrown) {
        ToggleScanState(ScanState.scanning);
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

    private void ToggleScanState(ScanState student) {
        switch (student){
            case student:
                studentNode.setVisibility(View.VISIBLE);
                scanningMesage.setVisibility(View.GONE);
                break;
            case scanning:
                scanningMesage.setVisibility(View.VISIBLE);
                studentNode.setVisibility(View.GONE);
                break;
        }
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
       scan_btn = v.findViewById(R.id.scan_button);


    personPagerAdapter = new PersonPagerAdapter(getChildFragmentManager());
      personPager.setAdapter(personPagerAdapter);

      personSelectorTab.setupWithViewPager(personPager);
      personSelectorTab.getTabAt(0).setText("students");
      personSelectorTab.getTabAt(1).setText("responsibles");


    }

}