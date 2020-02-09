package com.example.thiftmarts;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.makeText;


public class LoginActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText txtusername, txtpassword;

    Spinner spinner;

    Button btnlogin,btnregis;
   Vibrator vibrator;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    txtusername=findViewById(R.id.et_username);
    txtpassword=findViewById(R.id.et_password);



        btnlogin=findViewById(R.id.btn_login);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameinput = txtusername.getText().toString();
                String passwordinput = txtpassword.getText().toString();

                if (txtusername.getText().toString().equals("admin") &&
                        txtpassword.getText().toString().equals("admin")) {
                    //  Toast.makeText(getApplicationContext(), "Hello admin!",Toast.LENGTH_SHORT).show();

                    Toast.makeText(LoginActivity.this, "Hello Admin!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "Invalid name or password",
                            Toast.LENGTH_SHORT).show();
                }
            }


            });

                spinner = (Spinner) findViewById(R.id.usertype);

                List<String> list = new ArrayList<String>();
                list.add("User");
                list.add("Admin");

                ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,list);
                arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spinner.setAdapter(arrayAdapter);
                spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        spinner.setSelection(position);
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) {

                    }
                });

            }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinner.setSelection(position);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}









