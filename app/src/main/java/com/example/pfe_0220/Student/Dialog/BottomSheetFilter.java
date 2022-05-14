package com.example.pfe_0220.Student.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.NumberPicker;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import com.example.pfe_0220.Departement.DepartementRepository;
import com.example.pfe_0220.MainActivity;
import com.example.pfe_0220.R;
import com.example.pfe_0220.Student.StudentViewModel;

import java.util.ArrayList;

public class BottomSheetFilter extends Dialog {



  public  AutoCompleteTextView departemrnts_autoCompleteTextView,specialites_departemrnts_autoCompleteTextView;
    ArrayAdapter<String> adapterItems;
    ArrayList<String> departementList;
StudentViewModel studentViewModel;
    Button validate_btn;


public    NumberPicker sectionPicker, levelPicker, groupPicker;

    public BottomSheetFilter(@NonNull Context context,  ArrayList<String> departementList) {
        super(context);
        setContentView(R.layout.dialog_filter);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        this.departementList = departementList;

    }


    public BottomSheetFilter(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_filter);
        getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapterItems = new ArrayAdapter<String>(getContext(),R.layout.drop_down_list_item,departementList);
        departemrnts_autoCompleteTextView = findViewById(R.id.depatement_drop_down_list);
        validate_btn = findViewById(R.id.validate_btn);
        departemrnts_autoCompleteTextView.setAdapter(adapterItems);



      // picker section
        sectionPicker = findViewById(R.id.section_picker);
                levelPicker = findViewById(R.id.level_picker);
                groupPicker = findViewById(R.id.group_picker);


        //picker config
        sectionPicker.setMinValue(1);
        sectionPicker.setMaxValue(3);
        groupPicker.setMinValue(1);
        groupPicker.setMaxValue(6);

        levelPicker.setMinValue(0);
        levelPicker.setMinValue(DepartementRepository.Levels.length-1);
        levelPicker.setDisplayedValues(DepartementRepository.Levels);






    }

public void InjectListner(View.OnClickListener clickListener){

        validate_btn.setOnClickListener(clickListener);
}

}
