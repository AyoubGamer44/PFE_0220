package com.example.pfe_0220;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ShowMessage();
    }

    private void ShowMessage() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("update succeed");
        builder.setTitle("hint from ibrahim ");
        builder.create().show();
    }
}