package com.example.thiftmarts.User;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.thiftmarts.Model.Product;
import com.example.thiftmarts.R;
import com.example.thiftmarts.URL.url;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewProductActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Context context;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_itemview);
        context = this;
        recyclerView = findViewById(R.id.ProductListRecyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        Call<List<Product>> getAllProduct = url.getApi().getAllProducts();
        getAllProduct.enqueue(new Callback<List<Product>>() {
            @Override
            public void onResponse(Call<List<Product>> call, Response<List<Product>> response) {
                if(response.code()==200){
                    recyclerView.setAdapter(new ProductListAdpater(context,response.body()));
                }else {
                    Toast.makeText(context,"Unalbe to Fetch Product List",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Product>> call, Throwable t) {
                Toast.makeText(context,"Error : " + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();

            }
        });


    }

    private class ProductListAdpater extends RecyclerView.Adapter<ProductViewHolder> {
        Context context;
        List<Product> ProductList;

        public ProductListAdpater(Context context, List<Product> productList) {
            this.context = context;
            ProductList = productList;
        }

        @NonNull
        @Override
        public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(context).inflate(R.layout.layout_productviewholder,parent,false);
            return new ProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
            holder.textViewName.setText(ProductList.get(position).getName());
            holder.textViewCategory.setText(ProductList.get(position).getCategory());
            holder.getTextViewPrice().setText(String.valueOf(ProductList.get(position).getPrice()));
            holder.getTextViewDescription().setText(ProductList.get(position).getDescription());
            String imageUrl = url.BASE_URL + "upload/" + ProductList.get(position).getImage();
//            holder.imageView.setImageURI(url.BASE_URL + "upload" + ProductList.get(position).getName());
            Picasso.get().load(imageUrl).into(holder.imageView);

        }

        @Override
        public int getItemCount() {
            return ProductList.size();
        }
    }

    private class ProductViewHolder extends RecyclerView.ViewHolder {
        TextView textViewName,textViewCategory,textViewPrice,textViewDescription;
        ImageView imageView;
        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.viewholer_imageview);
            textViewName = itemView.findViewById(R.id.viewholder_product_name);
            textViewCategory = itemView.findViewById(R.id.viewholder_product_category);
            textViewPrice = itemView.findViewById(R.id.viewholder_product_price);
            textViewDescription = itemView.findViewById(R.id.viewholder_product_description);
        }

        public TextView getTextViewName() {
            return textViewName;
        }

        public void setTextViewName(TextView textViewName) {
            this.textViewName = textViewName;
        }

        public TextView getTextViewCategory() {
            return textViewCategory;
        }

        public void setTextViewCategory(TextView textViewCategory) {
            this.textViewCategory = textViewCategory;
        }

        public TextView getTextViewPrice() {
            return textViewPrice;
        }

        public void setTextViewPrice(TextView textViewPrice) {
            this.textViewPrice = textViewPrice;
        }

        public TextView getTextViewDescription() {
            return textViewDescription;
        }

        public void setTextViewDescription(TextView textViewDescription) {
            this.textViewDescription = textViewDescription;
        }
    }
}