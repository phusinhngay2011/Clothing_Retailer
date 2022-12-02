package com.example.clothingretailer;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class SeachRVAdapter extends RecyclerView.Adapter<SeachRVAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<Item> items;

    public SeachRVAdapter(Context context, ArrayList<Item> items) {
        this.mContext = context;
        this.items = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.item_recyclerview_search, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Item item = items.get(position);
        holder.index = position;
        holder.mTextName.setText(item.getName());
        holder.mTextPrice.setText(ShoppingCartActivity.formatPriceString(item.getPrice()));
        if (item.getImage_path() != null && item.getImage_path().length() > 0)
        {
            ArrayList<String> urls = (ArrayList<String>) StringHdr.getURLImgs(item.getImage_path());
            if (urls.size() > 0)
            {
                Glide.with(holder.itemView.getContext()).load(urls.get(0)).into(holder.mImageItem);
            }
        }
        holder.mTextRate.setText("");

        holder.mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVars.selected_item = item;
                toProductDetails(view);
            }
        });
        holder.mImageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVars.selected_item = item;
                toProductDetails(view);
            }
        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void toProductDetails(View view)
    {
        Intent switchActivityIntent = new Intent(view.getContext(), ProductDetailsActivity.class);
        mContext.startActivity(switchActivityIntent);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageItem;
        private TextView mTextName;
        private TextView mTextPrice;
        private TextView mTextRate;
        private int index;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageItem = itemView.findViewById(R.id.item_image_search);
            mTextName = itemView.findViewById(R.id.item_name_search);
            mTextPrice = itemView.findViewById(R.id.item_price_search);
            mTextRate = itemView.findViewById(R.id.item_rate_search);
            index = -1;

        }

    }




}
