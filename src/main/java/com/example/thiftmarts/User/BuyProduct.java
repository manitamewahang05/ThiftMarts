package com.example.thiftmarts.User;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.example.thiftmarts.R;

import java.util.ArrayList;
import java.util.List;

public class BuyProduct extends AppCompatActivity {
    SearchView mysearchView;
    ListView mylist;

    ArrayList<String> list;
    ArrayAdapter<String> adapter;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy);
        list = new ArrayList<String>();
        list.add("Clothes");
        list.add("Food");
        list.add("Utensils");

         mysearchView=findViewById(R.id.mySearchImage);
         mylist=findViewById(R.id.myList);

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list);
        mylist.setAdapter(adapter);
        mysearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
             // adapter.getFilter(query);
                return false;
            }



        });
}
}








 



