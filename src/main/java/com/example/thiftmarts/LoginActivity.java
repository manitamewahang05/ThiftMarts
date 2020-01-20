package com.example.thiftmarts;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.VibrationEffect;
import android.os.Vibrator;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thiftmarts.API.AppAPI;
import com.example.thiftmarts.Model.LoginResponse;
import com.example.thiftmarts.URL.url;
import com.example.thiftmarts.User.SellProduct;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    Button btnlogin, btnregis;
    Vibrator vibrator;
    private EditText txtusername, txtpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        txtusername = findViewById(R.id.et_username);
        txtpassword = findViewById(R.id.et_password);

        vibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        btnregis = findViewById(R.id.btn_register);
        btnregis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (v.getId() == R.id.btn_register) {

                    Intent intent = new Intent(LoginActivity.this, SignUp.class);
                    startActivity(intent);
                }
            }
        });

        AppAPI restAPI = url.getInstance().create(AppAPI.class);

        btnlogin = findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameinput = txtusername.getText().toString();
                String passwordinput = txtpassword.getText().toString();

                if (TextUtils.isEmpty(usernameinput)) {
                    Toast.makeText(LoginActivity.this, "Email and password is empty", Toast.LENGTH_SHORT).show();

                    return;
                }
                if (TextUtils.isEmpty(passwordinput)) {
                    Toast.makeText(LoginActivity.this, "Password is empty", Toast.LENGTH_SHORT).show();

                    return;
                }

                restAPI.checkUser(usernameinput, passwordinput).enqueue(new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if (response.isSuccessful()) {
                            if (Build.VERSION.SDK_INT >= 26) {
                                vibrator.vibrate(VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE));
                            } else {
                                vibrator.vibrate(200);
                            }
                            startActivity(new Intent(LoginActivity.this, Dashboard.class));
                        } else {
                            Toast.makeText(LoginActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this, "Username or password is incorrect", Toast.LENGTH_SHORT).show();

                    }
                });
            }


        });


    }

    @Override
    public void onClick(View v) {

    }
}









