package com.example.android53_btvn_buoi8;

import static android.content.ContentValues.TAG;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    public static final String BASE_URL = "https://dummyjson.com/";
    private RecyclerView rvProducts;
    private Retrofit mRetrofit;
    private List<Product> productList;
    private ProductAdapter productAdapter;
    private ProductService productService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initRecyclerView();
        initRetrofit();
    }
    private void initRecyclerView() {
        rvProducts = findViewById(R.id.rvProducts);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,true);
//        rvProducts.setLayoutManager(linearLayoutManager);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);
        rvProducts.setLayoutManager(gridLayoutManager);
    }

    private void initRetrofit() {
        mRetrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        productService = mRetrofit.create(ProductService.class);
        productService.getProducts().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()){
                        ProductResponse productResponse = response.body();
                        productList = productResponse.getProducts();
                        productAdapter = new ProductAdapter(productList);
                        rvProducts.setAdapter(productAdapter);
                }

            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Thất bại", Toast.LENGTH_SHORT).show();
            }
        });
    }

}