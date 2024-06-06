package com.example.virtualshop.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.virtualshop.R;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class SearchItemRecyclerAdapter extends FirestoreRecyclerAdapter<UserModel, SearchItemRecyclerAdapter, SearchItemRecyclerAdapter.ItemModelViewHolder> {
    Context context;
    public SearchItemRecyclerAdapter(@NonNull FirestoreRecyclerOptions<UserModel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SearchItemRecyclerAdapter holder, int position, @NonNull UserModel model) {

    }

    @NonNull
    @Override
    public SearchItemRecyclerAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

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
