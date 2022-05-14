package com.example.pfe_0220;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import com.example.pfe_0220.Departement.DepartementFragment;
import com.example.pfe_0220.Planning.PlanningFragment;
import com.example.pfe_0220.Profile.ProfileFragment;
import com.example.pfe_0220.Student.StudentFragment;
import com.example.pfe_0220.Teacher.TeacherFragment;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    DrawerLayout drawer;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToolBarConfiguration();

        NavigationConfiguration();

        if (savedInstanceState == null) {
            InitialiseActivity();
        }


    } // end of activity

    public void InitialiseActivity() {
        ShowFragment(new PlanningFragment(),"Planning");
        navigationView.setCheckedItem(R.id.planinng);
    }

    /**
     * configure the nav drawer buttons
     */
    private void NavigationConfiguration() {
        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    /**
     * give the app custom navigation bar that contain toggle to open and close it
     */
    private void ToolBarConfiguration() {

         toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.nav_open, R.string.nav_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    /**
     * hundle click event of the nav drawer
     * @param item
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.planinng:
                ShowFragment(new PlanningFragment(),"planing");
                break;
            case R.id.student:
                ShowFragment(new StudentFragment(),"Students List");
                break;
            case R.id.teachers:
                ShowFragment(new TeacherFragment(),"Teacher list");
                break;
            case R.id.departement:
                ShowFragment(new DepartementFragment(),"Departements");
                break;
            case R.id.profile:
                ShowFragment(new ProfileFragment(),"User Profile");
                break;
            case R.id.add_planing:
                CreateNewPlanning();
                break;
            case R.id.logout:
                Logout();
                break;
        }
        return true;
    }

    private void Logout() {
        Toast.makeText(this, "logout", Toast.LENGTH_SHORT).show();
        drawer.closeDrawer(GravityCompat.START);
        //launch the welcome activity
        Intent intent = new Intent(this,WelcomeActivity.class);
        startActivity(intent);
    }

    private void CreateNewPlanning() {
        Toast.makeText(this, "add new planning", Toast.LENGTH_SHORT).show();
        drawer.closeDrawer(GravityCompat.START);
    }

    public void ShowFragment(Fragment fragment,String fragment_name) {
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragment).commit();
        toolbar.setTitle(fragment_name);
        drawer.closeDrawer(GravityCompat.START);
    }
}