package com.example.thiftmarts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thiftmarts.User.SellProduct;
import com.example.thiftmarts.User.ViewProductt;

public class Dashboard extends AppCompatActivity implements  View.OnClickListener {

   Button buttonadd, buttonsell, buttonview, buttondas, buttonlogout;

 @Override
 protected void onCreate(Bundle savedInstanceState) {
  super.onCreate(savedInstanceState);
  setContentView(R.layout.activity_dashboard);

  buttonsell = findViewById(R.id.btnsell);
  buttonadd = findViewById(R.id.btnprofile);
  buttonview = findViewById(R.id.btnviewitem);
  buttondas = findViewById(R.id.btnhome);
  buttonlogout = findViewById(R.id.btnlogout);

 buttonadd.setOnClickListener(this);
  buttonview.setOnClickListener(this);
  buttondas.setOnClickListener(this);
  buttonsell.setOnClickListener(this);
  //  btnlogout=findViewById(this);
  //  }
 }
 @Override
 public void onClick(View v) {
  if (v.getId() == R.id.btnsell) {

   Intent intent = new Intent(Dashboard.this, SellProduct.class);
   startActivity(intent);
  }


  if (v.getId() == R.id.btnhome) {
   Intent intent = new Intent(Dashboard.this,SignUp .class);
   startActivity(intent);


  }
  if (v.getId() == R.id.btnviewitem) {
   Intent intent = new Intent(this, ViewProductt.class);
   startActivity(intent);
  }

 }

}