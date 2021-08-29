package com.example.freshcarts.adpter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.freshcarts.R;
import com.example.freshcarts.model.DiscountedProduct;

import java.util.List;

public class DiscounteProductAdpter extends RecyclerView.Adapter<DiscounteProductAdpter.DiscountedProductViewHolder> {

        Context context;

        List<DiscountedProduct> discountedProductsList;

        public DiscounteProductAdpter(Context context, List<DiscountedProduct> discountedProductsList) {
            this.context = context;
            this.discountedProductsList = discountedProductsList;
        }

        @NonNull
        @Override
        public DiscountedProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(context).inflate(R.layout.discounted_row_items, parent, false);
            return new DiscountedProductViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull DiscountedProductViewHolder holder, int position) {

            holder.discountImageView.setImageResource (discountedProductsList.get(position).getimageurl());
        }

        @Override
        public int getItemCount() {
            return discountedProductsList.size();
        }

        public static class DiscountedProductViewHolder extends  RecyclerView.ViewHolder{

            ImageView discountImageView;

            public DiscountedProductViewHolder(@NonNull View itemView) {
                super(itemView);

                discountImageView = itemView.findViewById(R.id.discountImage);

            }
        }
    }

