package com.example.thiftmarts.User;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.loader.content.CursorLoader;

import com.example.thiftmarts.Dashboard;
import com.example.thiftmarts.Model.Image;
import com.example.thiftmarts.Model.Product;
import com.example.thiftmarts.R;
import com.example.thiftmarts.URL.url;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class SellProduct extends AppCompatActivity {
    Context context;

    private  static final  int PICK_IMAGE=1;
    private  static final  int REQUEST_CODE=1;
    private EditText text_productName, text_productCategory, text_productPrice,text_productDescription;
    private Button buttonSellProduct, btnback, btnphotoadd;
     Spinner spinner;
    private ImageView imgProfile;
    String imagePath = "";
    String imageName = "";
    String Category = "";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);
        text_productName = findViewById(R.id.product_name);
        text_productDescription = findViewById(R.id.product_description);
        text_productPrice = findViewById(R.id.product_price);
        buttonSellProduct = findViewById(R.id.button_Sell);
        btnback = findViewById(R.id.btnback);
        btnphotoadd = findViewById(R.id.add_image);
        context = this;
        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellProduct.this, Dashboard.class);
                startActivity(intent);
                finish();
            }


        });

        btnphotoadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"image",Toast.LENGTH_SHORT).show();
                CheckPermission();
                openImage();
            }
        });

        spinner = findViewById(R.id.product_category);

        List<String> list = new ArrayList<String>();
        list.add("Utensils");
        list.add("Stationries");
        list.add("Clothes");
        list.add("Furniture");
        list.add("Electronics");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, list);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setSelection(1);
        Category = list.get(1);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Category = list.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        buttonSellProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SaveImageOnly();
                Call<Product> AddProduct =  url.getApi().AddProdut(
                        text_productName.getText().toString(),
                        Category,
                        text_productPrice.getText().toString(),
                        text_productDescription.getText().toString(),
                        imageName
                );
                AddProduct.enqueue(new Callback<Product>() {
                    @Override
                    public void onResponse(Call<Product> call, Response<Product> response) {
                        if(response.code() == 201){
                            Toast.makeText(context,"Product Added Successfully",Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(context,"Failed to Add Product",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Product> call, Throwable t) {
                        Toast.makeText(context,"Error " + t.getLocalizedMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


        imgProfile = (ImageView) findViewById(R.id.select_product_image);
        btnphotoadd = findViewById(R.id.add_image);
        btnphotoadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                String[] mimiTypes = {"image/jpeg", "image/png"};
                intent.putExtra(Intent.EXTRA_MIME_TYPES, mimiTypes);
                startActivityForResult(intent.createChooser(intent, "Pick An Image"),0);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if (data == null) {
                Toast.makeText(this, "Please select an image ", Toast.LENGTH_SHORT).show();
            }
        }
        Uri uri = data.getData();
        imagePath = getRealPathFromUri(uri);
//        previewImage(imagePath);
    }
    private void StrictMode() {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void openImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, 1);
    }

        private void CheckPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                Manifest.permission.READ_EXTERNAL_STORAGE)) {

            new AlertDialog.Builder(this)
                    .setTitle("Permission needed")
                    .setMessage("This permission is needed because of this and that")
                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(SellProduct.this,
                                    new String[] {Manifest.permission.READ_EXTERNAL_STORAGE}, 1);
                        }
                    })
                    .setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                        @Override


                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create().show();
        }
    }

    private String getRealPathFromUri(Uri uri) {
        String[] projection = {MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(SellProduct.this, uri, projection, null,
                null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }

//    private String getRealPathFromUri(Uri uri) {
//        String[] projection = {MediaStore.Images.Media.DATA};
//        CursorLoader loader = new CursorLoader(SellProduct.this,
//                uri, projection, null, null, null);
//        Cursor cursor = loader.loadInBackground();
//        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
//        cursor.moveToFirst();
//        String result = cursor.getString(colIndex);
//        cursor.close();
//        return result;
//    }

    private void SaveImageOnly() {

        File file = new File(imagePath);

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);

        Call<Image> PostImage = url.getApi().UploadImage(body);

        try {
            StrictMode();
            Response<Image> imageResponseResponse = PostImage.execute();
            // After saving an image, retrieve the current name of the image
            imageName = imageResponseResponse.body().getFilename();
        } catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}






