package com.example.thiftmarts.User;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.thiftmarts.R;

public class ViewProductt extends AppCompatActivity {
    ImageView imgview;
    TextView txtname,txtprice,txtdesc,txtcategory;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemview);
        txtname=findViewById(R.id.product_name);
        txtcategory=findViewById(R.id.product_category);
        txtdesc=findViewById(R.id.product_description);
        txtprice=findViewById(R.id.product_category);



        Bundle bundle =getIntent().getExtras();
        if(bundle !=null){
            txtname.setText(bundle.getString("name"));
            txtcategory.setText(bundle.getString("category"));
            txtprice.setText("रू "+bundle.getString("price"));
            txtdesc.setText(bundle.getString("desc"));

        }
    }

}
