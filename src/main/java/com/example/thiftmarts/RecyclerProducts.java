package com.example.thiftmarts;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thiftmarts.Model.Products;
import com.example.thiftmarts.User.SellProduct;

import java.util.List;

import retrofit2.Call;

public class RecyclerProducts extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<Products> productsListt;
    private Button btnadd;
    private Bundle savedInstanceState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.savedInstanceState = savedInstanceState;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler);
        recyclerView=findViewById(R.id.recyclerView);
        btnadd=findViewById(R.id.btnAdd);
        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RecyclerProducts.this, SellProduct.class);
                startActivity(intent);
                finish();
            }
        });
//        clothesList= new ArrayList<>();
     //   AppAPI AppAPI = Url.getInstance().create(AppAPI.class);
       // Call<List<Products>> call = AppAPI.getAllProducts(Url.token);
      //  call.enqueue(new Callback<List<Products>>() {
          //  @Override
          //  public void onResponse(Call<List<Products>> call, Response<List<Products>> response) {

              //  productsListt = response.body();
               // pr clothesAdapter = new ProductAdapter(productsListt, RecyclerProducts.this);
               // recyclerView.setAdapter(ProductAdapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(RecyclerProducts.this));
            }

          //  @Override
            public void onFailure(Call<List<Products>> call, Throwable t) {

            }
       // });






    }


