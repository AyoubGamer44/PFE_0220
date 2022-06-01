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

import com.example.pfe_0220.Profile.ProfileViewModel;
import com.example.pfe_0220.R;
import com.example.pfe_0220.WelcomeActivity;
import com.google.android.material.button.MaterialButton;

public class LogInFragment extends Fragment {

    TextView registre;

    MaterialButton logIn;
    EditText password, email;
    ProfileViewModel profileViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_login, container, false);

    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinkViews(view);

        profileViewModel = new ViewModelProvider(requireActivity()).get(ProfileViewModel.class);


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    profileViewModel.LogIn(email.getText().toString(), password.getText().toString());
                    if (profileViewModel.profileRepository.currentUser != null) {
                        ((WelcomeActivity)getActivity()).LogIn();
                    }
                    else
                    {

                        Toast.makeText(getContext(), "wrong information , no user found", Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e) {
                    Toast.makeText(getContext(), "Ooops ... something went wrong", Toast.LENGTH_SHORT).show();
                }
            }
        });


        registre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((WelcomeActivity) getActivity()).ChangeFragment(WelcomeActivity.fragmentType.registre);
            }
        });

    }

    public void LinkViews(View v) {
        registre = v.findViewById(R.id.registre);
        logIn = v.findViewById(R.id.loginbtn);
        email = v.findViewById(R.id.email);
        password = v.findViewById(R.id.password);
    }

}
