package com.example.android53_btvn_buoi8;


import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ProductService {

    @GET("products")
    Call<ProductResponse> getProducts();
}
