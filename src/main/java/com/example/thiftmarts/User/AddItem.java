package com.example.thiftmarts.User;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thiftmarts.R;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class AddItem extends AppCompatActivity {
    private EditText etName,etPrice,etDescription;
    private Spinner spinImg;
    private Button btnView,btnAdd;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        etName = findViewById(R.id.etname);
        etPrice = findViewById(R.id.etPrice);
        etDescription = findViewById(R.id.etDescription);
        spinImg = findViewById(R.id.spinImg);
        btnView = findViewById(R.id.btnView);
        btnAdd = findViewById(R.id.btnAdd);
        img = findViewById(R.id.img);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                save();
            }
        });


        String image[] = {"img1","img2","img3","img4","img5","img6","img7","img8","img9","img10"};
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,image);
        spinImg.setAdapter(adapter);

        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String spinnerString = spinImg.getSelectedItem().toString();

                switch (spinnerString){
                    case "img1":
                        img.setImageResource(R.drawable.img1);
                        break;
                    case "img2":
                        img.setImageResource(R.drawable.img2);
                        break;
                    case "img3":
                        img.setImageResource(R.drawable.img3);
                        break;
                    case "img4":
                        img.setImageResource(R.drawable.img4);
                        break;
                    case "img5":
                        img.setImageResource(R.drawable.img5);
                        break;
                    case "img6":
                        img.setImageResource(R.drawable.img6);
                        break;


                }
            }
        });

    }
    private void save(){
        PrintStream printStream = null;
        try {
            printStream = new PrintStream(openFileOutput("items.txt",MODE_PRIVATE | MODE_APPEND));
            printStream.println(etName.getText().toString() + "->" + etPrice.getText().toString()+ "->" + spinImg.getSelectedItem().toString()+ "->" + etDescription.getText().toString());
            Toast.makeText(this,"saved to " +getFilesDir(), Toast.LENGTH_LONG).show();
            printStream.close();
        } catch (FileNotFoundException e) {
            Log.d("Dictionary app" , "Error: "+e.toString());
            e.printStackTrace();
        }

    }


}
