package com.example.thiftmarts.Admin;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.thiftmarts.R;

public class AdminDashboard extends AppCompatActivity implements  View.OnClickListener {

    Button buttonadd, buttonsell, buttonview, buttondas, buttonlogout;



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_dashboard);

        buttonsell = findViewById(R.id.btnsell);
        buttonadd = findViewById(R.id.btnprofile);
        buttonview = findViewById(R.id.btnviewitem);
        buttondas = findViewById(R.id.btnhome);
        buttonlogout = findViewById(R.id.btnlogout);

        buttonadd = findViewById(R.id.btnprofile);
        buttonview.setOnClickListener(this);
        buttondas.setOnClickListener(this);
        buttonsell.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btnsell) {
            Intent intent = new Intent(AdminDashboard.this, Addproduct.class);
            startActivity(intent);
        }

        if (v.getId() == R.id.btnprofile) {
            Intent intent = new Intent(this, Addproduct.class);
            startActivity(intent);

        }
        if (v.getId() == R.id.btnhome) {
            Intent intent = new Intent(this, Addproduct.class);
            startActivity(intent);


    }

        if (v.getId() == R.id.btnviewitem) {
            Intent intent = new Intent(this, Addproduct.class);
            startActivity(intent);
        }

        }

    }