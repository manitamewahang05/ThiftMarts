package com.example.thiftmarts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thiftmarts.User.BuyProduct;
import com.example.thiftmarts.User.Profile;
import com.example.thiftmarts.User.SellProduct;
import com.example.thiftmarts.User.ViewProductActivity;

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
   buttonlogout.setOnClickListener(this);
  //  }
 }
 @Override
 public void onClick(View v) {
  if (v.getId() == R.id.btnsell) {

   Intent intent = new Intent(Dashboard.this,SellProduct.class);
   startActivity(intent);
  }
  if (v.getId() == R.id.btnbuy) {

   Intent intent = new Intent(Dashboard.this, BuyProduct.class);
   startActivity(intent);

  }
  if (v.getId() == R.id.btnhome) {
   Intent intent = new Intent(Dashboard.this,MapsActivity .class);
   startActivity(intent);


  }
  if (v.getId() == R.id.btnviewitem) {
   Intent intent = new Intent(this, ViewProductActivity.class);
   startActivity(intent);
  }

  if (v.getId() == R.id.btnprofile) {
   Intent intent = new Intent(Dashboard.this, Profile.class);
  startActivity(intent);
  }

  if (v.getId() == R.id.btnlogout) {
   Intent intent = new Intent(this, LoginActivity.class);
   startActivity(intent);
  }
 }

}