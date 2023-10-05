package com.example.android53_btvn_buoi8;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;


public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ViewHodel> {
    private List<Product> mProductList;
    private Context mContext;

    public ProductAdapter(List<Product> productList) {
        this.mProductList = productList;
    }


    @NonNull
    @Override
    public ProductAdapter.ViewHodel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        this.mContext = parent.getContext();
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_item_product,parent, false);
        return new ViewHodel(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductAdapter.ViewHodel holder, int position) {
        Product product = mProductList.get(position);
        holder.tvNameProduct.setText(product.getTitle());
        holder.tvPrice.setText(product.getPrice() + "");
        holder.tvRating.setText(product.getRating() + "");
        Glide.with(mContext).load(product.getThumbnail()).centerCrop().into(holder.imgProduct);

    }

    @Override
    public int getItemCount() {
        return mProductList != null ? mProductList.size() : 0 ;
    }

    public class ViewHodel extends RecyclerView.ViewHolder {
        private TextView tvNameProduct, tvPrice, tvRating;
        private ImageView imgProduct;
        public ViewHodel(@NonNull View itemView) {
            super(itemView);


            tvNameProduct = itemView.findViewById(R.id.tvNameProduct);
            tvPrice = itemView.findViewById(R.id.tvPrice);
            tvRating = itemView.findViewById(R.id.tvRating);
            imgProduct = itemView.findViewById(R.id.imgProduct);

        }
    }
}
