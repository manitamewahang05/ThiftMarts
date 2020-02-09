package com.example.thiftmarts;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.thiftmarts.Model.User;

import java.util.ArrayList;
import java.util.List;

public class SignUp extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener, View.OnClickListener {
    EditText txtname,txtaddress,txtemail,txtgender,txtpass;
    RadioGroup radioGrp;
    Button btnregis;

    String name,address,email,gender,password;

    List<User> userList = new ArrayList<>();
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
            txtpass=findViewById(R.id.et_password);
            radioGrp = findViewById(R.id.rgGender);
            btnregis = findViewById(R.id.btn_register);

            radioGrp.setOnCheckedChangeListener(this);
            btnregis.setOnClickListener(this);
        }



    @Override
    public void onClick(View v) {
        name = txtname.getText().toString();
        address = txtaddress.getText().toString();
        email = txtemail.getText().toString();
        gender=txtgender.getText().toString();
        password = txtpass.getText().toString();

        if(v.getId() == R.id.btn_register){
            if(validate()){
                userList.add(new User(name,address,email,gender,password));
                Toast.makeText(this, "User Added", Toast.LENGTH_SHORT).show();
            }
        }
    }
    private boolean validate(){
        if(TextUtils.isEmpty(name)){
            txtname.setError("Enter Your full name");
            txtname.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(address)){
            txtaddress.setError("Enter your Address");
            txtaddress.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(email)){
            txtemail.setError("Enter Email");
            txtemail.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(gender)){
            Toast.makeText(this, "Plz select Gender", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            txtemail.setError("Invalid Email");
            txtemail.requestFocus();
            return false;
        }
        if(TextUtils.isEmpty(password)){
            txtpass.setError("Enter your password");
            txtpass.requestFocus();
            return false;
        }
        return true;
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

    }


