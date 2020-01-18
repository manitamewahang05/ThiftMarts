package com.example.thiftmarts.User;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thiftmarts.MapsActivity;
import com.example.thiftmarts.R;

import java.util.Map;

public class Profile  extends AppCompatActivity {

    private TextView  nameTxtView;
    private  TextView addressTxtView;

    private TextView emailTxtView,GenderView;
    private ImageView UserImage;

    private final String TAG = this.getClass().getName().toUpperCase();
    private Map<String, String> userMap;
    private String email;
    private String userid;
    private static final String USERS = "users";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userprofile);
        //receive data from login screen
        Intent intent = getIntent();
        email = intent.getStringExtra("email");



        nameTxtView = findViewById(R.id.etname);
        addressTxtView = findViewById(R.id.et_address);
        emailTxtView = findViewById(R.id.et_email);
        GenderView = findViewById(R.id.et_gender);
        UserImage = findViewById(R.id.image);
        UserImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Profile.this, MapsActivity.class);
                startActivity(intent);
                finish();
            }

        });


    }

}
