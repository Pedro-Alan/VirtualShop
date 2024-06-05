package com.example.virtualshop.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualshop.R;

public class SearchItemRecyclerAdapter {
    class ItemModelViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameView, itemDescView;
        ImageView itemImgView;
        public ItemModelViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameView = itemView.findViewById(R.id.itemNameView);
            itemDescView = itemView.findViewById(R.id.itemDescView);
            itemImgView = itemView.findViewById(R.id.itemImgView);
        }
    }
}
