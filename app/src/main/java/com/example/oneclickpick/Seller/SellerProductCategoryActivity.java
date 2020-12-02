package com.example.oneclickpick.Seller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.oneclickpick.R;

public class SellerProductCategoryActivity extends AppCompatActivity {
    private ImageView soap, socks, bags;
    private ImageView perfume, candles, eo;
    private ImageView vegetable, fruits, decor;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_seller_product_category);

        soap = (ImageView) findViewById(R.id.soap);
        socks = (ImageView) findViewById(R.id.socks);
        bags = (ImageView) findViewById(R.id.bags);

        perfume = (ImageView) findViewById(R.id.perfume);
        candles = (ImageView) findViewById(R.id.candles);
        eo = (ImageView) findViewById(R.id.eo);

        vegetable = (ImageView) findViewById(R.id.vegetable);
        fruits = (ImageView) findViewById(R.id.fruits);
        decor = (ImageView) findViewById(R.id.decor);


        soap.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("Category", "soap");
                startActivity(intent);
            }
        });
        socks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("Category", "socks");
                startActivity(intent);
            }
        });
        bags.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("Category", "bags");
                startActivity(intent);
            }
        });
        perfume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("Category", "perfume");
                startActivity(intent);
            }
        });
        candles.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("Category", "candles");
                startActivity(intent);
            }
        });
        eo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("Category", "eo");
                startActivity(intent);
            }
        });
        vegetable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("Category", "vegetable");
                startActivity(intent);
            }
        });
        fruits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("Category", "fruits");
                startActivity(intent);
            }
        });
        decor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SellerProductCategoryActivity.this, SellerAddNewProductActivity.class);
                intent.putExtra("Category", "decor");
                startActivity(intent);
            }
        });
    }
}