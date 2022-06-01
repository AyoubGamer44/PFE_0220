package com.example.pfe_0220.Welcome;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.pfe_0220.Profile.Models.ManagerProfile;
import com.example.pfe_0220.Profile.ProfileViewModel;
import com.example.pfe_0220.R;
import com.example.pfe_0220.WelcomeActivity;
import com.google.android.material.button.MaterialButton;

public class RegistreFragment extends Fragment {


    EditText firstName, lastName, password, email, phoneNumber;
    MaterialButton registreBtn;
    TextView logInBtn;

    ProfileViewModel profileViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registre, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinkViews(view);
        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);


        registreBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ManagerProfile newProfile = new ManagerProfile(firstName.getText().toString(),
                        lastName.getText().toString(),
                      phoneNumber.getText().toString(),
                        email.getText().toString(),
                        password.getText().toString());

                try {
                    if (profileViewModel.InsertProfile(newProfile)) {
                        ((WelcomeActivity)getActivity()).LogIn();

                    } else {
                        Toast.makeText(getContext(), "failed to insert data , something went wrong", Toast.LENGTH_SHORT).show();

                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        });


    }

    public void LinkViews(View v) {
        firstName = v.findViewById(R.id.firstName);
        lastName = v.findViewById(R.id.lastName);

        password = v.findViewById(R.id.password);
        email = v.findViewById(R.id.email);
        phoneNumber = v.findViewById(R.id.phoneNUmber);


        registreBtn = v.findViewById(R.id.registre);
        logInBtn = v.findViewById(R.id.logIN);
    }


}
