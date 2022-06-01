package com.example.pfe_0220;

import android.content.Intent;
import android.os.Bundle;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pfe_0220.Welcome.LogInFragment;
import com.example.pfe_0220.Welcome.RegistreFragment;
import com.google.android.material.button.MaterialButton;


public class WelcomeActivity extends AppCompatActivity {



    public enum fragmentType {
        log_in, registre
    }

    FrameLayout fragmentHolder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);


            ChangeFragment(fragmentType.log_in);



    }


    public void ChangeFragment(fragmentType type) {

    switch(type){
        case log_in:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new LogInFragment()).commit();
            break;
        case registre:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragmentHolder, new RegistreFragment()).commit();
            break;
    }



    }


    public void LogIn() {

        Intent intent = new Intent(this , MainActivity.class);
        startActivity(intent);


    }

}


