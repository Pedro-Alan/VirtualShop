package com.example.virtualshop.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualshop.R;
import com.example.virtualshop.model.ItemModel;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SearchItemRecyclerAdapter extends FirestoreRecyclerAdapter<ItemModel, SearchItemRecyclerAdapter.ItemModelViewHolder> {
    Context context;

    public SearchItemRecyclerAdapter(@NonNull FirestoreRecyclerOptions<ItemModel> options, Context context) {
        super(options);
        this.context = context;
    }

    @Override
    protected void onBindViewHolder(@NonNull ItemModelViewHolder holder, int position, @NonNull ItemModel model) {
        holder.itemNameView.setText(model.getItemName());
        holder.itemPriceView.setText(model.getItemPrice());

    }

    @NonNull
    @Override
    public ItemModelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.search_item_recycler_row, parent, false);
        return new ItemModelViewHolder(v);
    }


    class ItemModelViewHolder extends RecyclerView.ViewHolder {
        TextView itemNameView, itemPriceView;
        ImageView itemImgView;
        public ItemModelViewHolder(@NonNull View itemView) {
            super(itemView);
            itemNameView = itemView.findViewById(R.id.itemNameView);
            itemPriceView = itemView.findViewById(R.id.itemPriceView);
            itemImgView = itemView.findViewById(R.id.itemImgView);
        }
    }
}
