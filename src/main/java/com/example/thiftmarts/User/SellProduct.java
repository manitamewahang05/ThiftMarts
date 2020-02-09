package com.example.thiftmarts.User;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Build;
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
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.content.CursorLoader;

import com.example.thiftmarts.API.AppAPI;
import com.example.thiftmarts.Dashboard;
import com.example.thiftmarts.MainActivity;
import com.example.thiftmarts.Model.ImageResponse;
import com.example.thiftmarts.R;
import com.example.thiftmarts.RecyclerProducts;
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

import static android.widget.Toast.*;

public class SellProduct extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    private EditText txtname, txtdesc, txtcategory, txtprice;
    String imagePath;
    String imageName;

    private static final int PICK_IMAGE = 1;
    private static final int REQUEST_READ_GALLERY = 1;
    // private static final int RESULT_CODE= 1;
    private static final int MY_REQUEST_GALLERY = 1;

    private Button btnadd, btnback, btnchoseimage;
    Spinner spinner;
    private ImageView imgProfile;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sell);


        txtname = findViewById(R.id.product_name);
        txtdesc = findViewById(R.id.product_description);
        txtprice = findViewById(R.id.product_price);
        btnadd = findViewById(R.id.add_new_product);

        btnback = findViewById(R.id.btnback);
        btnchoseimage = findViewById(R.id.btnchoseimages);

        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SellProduct.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        imgProfile = findViewById(R.id.select_product_image);
        btnchoseimage = findViewById(R.id.btnchoseimages);
        btnchoseimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Save();

            }
        });
        imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BrowseImage();
            }

            private void BrowseImage() {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, 0);
            }


        });



    //  btnchoseimage.setOnClickListener(new View.OnClickListener() {
    //   @Override
    //  public void onClick(View v) {
    // Intent intent=new Intent(Intent.ACTION_PICK);
    // intent.setType("images/*");
    // String[]mimeTypes={"images/jpeg","images/png"};
    // Intent pickIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

    //intent.setType("images/*");
    // Intent chooserIntent=Intent.createChooser(getIntent(),"Select Image");
    // chooserIntent.putExtra(Intent.EXTRA_INITIAL_INTENTS,new Intent[]{pickIntent});

    //   intent.setAction(Intent.ACTION_GET_CONTENT);
    //  startActivityForResult(intent,PICK_IMAGE);
    // }


    // });




        spinner = (Spinner) findViewById(R.id.product_category);

        List<String> category = new ArrayList<String>();
        category.add("Furniture");
        category.add("Clothes");
        category.add("Accessories");
        category.add("Utensils");
        category.add("Food");
        category.add("Stationries");

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, category);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(arrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                spinner.setSelection(position);
                makeText(SellProduct.this, "Select Your Category", LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);



            if (resultCode==RESULT_OK){
                if (data==null){
                    Toast.makeText(this, "Please select an image", Toast.LENGTH_SHORT).show();
                }
            }
            Uri uri=data.getData();
            imagePath=getRealPathFromUri(uri);
            previewImage(imagePath);
        }
    private String getRealPathFromUri(Uri uri) {
        String[] projection ={MediaStore.Images.Media.DATA};
        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection,
                null, null, null);
        Cursor cursor = loader.loadInBackground();
        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(colIndex);
        cursor.close();
        return result;
    }
    private void previewImage(String imagePath) {
        File imgFile = new File(imagePath);
        if (imgFile.exists()){
            Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
            imgProfile.setImageBitmap(myBitmap);
        }
    }
    private void StrictMode(){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private void SaveImageOnly() {


        File file = new File(imagePath);

        RequestBody requestBody = RequestBody.create(MediaType.parse("multipart/form-data"), file);
        MultipartBody.Part body = MultipartBody.Part.createFormData("imageFile", file.getName(), requestBody);
        AppAPI productapi = url.getInstance().create(AppAPI.class);
        Call<ImageResponse> responseBodyCall = AppAPI.upload(url.token, body);

        StrictMode();
        try {
            Response<ImageResponse> imageResponseResponse = responseBodyCall.execute();
            imageName = imageResponseResponse.body().getFilename();
            Toast.makeText(this, imageName, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
        private void Save() {
            SaveImageOnly();
            String name = txtname.getText().toString();
            String price = txtprice.getText().toString();
            String description = txtdesc.getText().toString();
            String category=txtcategory.getText().toString();



            AppAPI appAPI = url.getInstance().create(AppAPI.class);
            Call<Void> heroesCall = appAPI.addproduct(url.token,name,category,price,description,imageName);
            heroesCall.enqueue(new Callback<Void>() {
                @Override
                public void onResponse(Call<Void> call, Response<Void> response) {
                    if (!response.isSuccessful()) {
                        Toast.makeText(SellProduct.this, "code " + response.code(), Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Toast.makeText(SellProduct.this, "Successfully Added", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(SellProduct.this, RecyclerProducts.class);
                    startActivity(intent);
                    finish();
                }

                @Override
                public void onFailure(Call<Void> call, Throwable t) {
                    Toast.makeText(SellProduct.this, "Error " + t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        }
    }



        // if(reprivate String getRealPathFromUri(Uri uri) {
    //     String[] projection ={MediaStore.Images.Media.DATA};
    //        CursorLoader loader = new CursorLoader(getApplicationContext(), uri, projection,
    //                null, null, null);
    //        Cursor cursor = loader.loadInBackground();
    //        int colIndex = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    //        cursor.moveToFirst();
    //        String result = cursor.getString(colIndex);
    //        cursor.close();
    //        return result;
    //    requestCode== Activity.RESULT_OK)
          //   switch(requestCode) {
           // case MY_REQUEST_GALLERY:
            //    Uri selectedImage= data.getData();
             //   String[] filePathColumn={MediaStore.Images.Media.DATA};
              //  Cursor cursor=getContentResolver().query(selectedImage,filePathColumn,null,null,null);
          //      cursor.moveToFirst();
              //  int columnIndex=cursor.getColumnIndex(filePathColumn[0]);
               // String imgDecodableString=cursor.getString(columnIndex);
               // cursor.close();
              //  imgProfile.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
               // break;

      //  }





