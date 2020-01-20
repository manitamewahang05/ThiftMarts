package com.example.thiftmarts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.thiftmarts.API.AppAPI;
import com.example.thiftmarts.API.Strict;
import com.example.thiftmarts.Model.LoginResponse;
import com.example.thiftmarts.Model.User;
import com.example.thiftmarts.URL.url;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUp extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener{
    EditText txtname,txtaddress,txtemail,txtgender,txtpass,txtconfrim;
    String usrType="";
    RadioGroup radioGrp;
    Button btnregis,buttonsign;

    String name,address,email,gender,password,new_password;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState, @Nullable PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

            txtname = findViewById(R.id.et_fname);
            txtaddress= findViewById(R.id.et_address);
            txtemail = findViewById(R.id.et_email);
            txtpass = findViewById(R.id.et_pass);
            radioGrp = findViewById(R.id.rgGender);
            btnregis = findViewById(R.id.btn_register1);
            buttonsign=findViewById(R.id.btn_regis);
        buttonsign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.btn_regis) {

                    Intent intent = new Intent(SignUp.this, LoginActivity.class);
                    startActivity(intent);
                }
            }
        });

            radioGrp.setOnCheckedChangeListener(this);
            btnregis.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    name = txtname.getText().toString();
                    address = txtaddress.getText().toString();
                    email = txtemail.getText().toString();
                    password = txtpass.getText().toString();
                    //usrType = "User";

                    onSignupSucess();
                }
            });
        }

    @Override
    public void onCheckedChanged(RadioGroup group, int checkedId) {
        if (checkedId == R.id.rbMale){
            gender = "Male";
        }
        if (checkedId == R.id.rbFemale){
            gender = "Female";
        }
        if (checkedId == R.id.rbOthers) {
            gender = "Other";

        }
    }

    public void onSignupSucess(){
        if(validate()) {
            AppAPI restAPI = url.getInstance().create(AppAPI.class);
            Call<Void> call = restAPI.register(name, address, email, gender, password, usrType);
            Strict.strictMode();
            try {
                Response<Void> response = call.execute();
                if (response.isSuccessful()) {
                    Toast.makeText(this, "Registration Successful!", Toast.LENGTH_LONG).show();
                    startActivity(new Intent(this, LoginActivity.class));
                } else {
                    Toast.makeText(this, "Something went wrong!", Toast.LENGTH_LONG).show();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

         public boolean validate(){

            if(TextUtils.isEmpty(name)){
                txtname.setError("Enter Your Full Name");
                txtname.requestFocus();
                return false;
            }
            if(TextUtils.isEmpty(address)){
                txtaddress.setError("Enter your Address");
                txtaddress.requestFocus();
                return false;
            }
            if(TextUtils.isEmpty(email)||!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                txtemail.setError("Enter a Valid Email");
                txtemail.requestFocus();
                return false;
            }
            if(TextUtils.isEmpty(gender)){
                Toast.makeText(this, "Plz select Gender", Toast.LENGTH_SHORT).show();
                return false;
            }

            if(TextUtils.isEmpty(name)||password.length()>6){
                txtpass.setError("Enter a valid  password");
                txtpass.requestFocus();
                return false;
            }

        return true;
    }

}


