package com.example.thiftmarts.Model;

import java.util.List;

public class Category {
    private Integer id;
    private String code;
    private String title;
    private List<Product> products;

    public Category(String code, String title, List<Product> products) {
        this.code = code;
        this.title = title;
        this.products = products;
    }




}
