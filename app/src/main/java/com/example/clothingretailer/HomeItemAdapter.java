package com.example.clothingretailer;

import android.annotation.SuppressLint;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class HomeItemAdapter extends RecyclerView.Adapter<HomeItemAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageItem;
        private TextView mTextName;
        private TextView mTextPrice;
        private TextView mTextRate;
        private CheckBox mHeartButton;
        private int index;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageItem = itemView.findViewById(R.id.item_image_home);
            mTextName = itemView.findViewById(R.id.item_name_home);
            mTextPrice = itemView.findViewById(R.id.item_price_home);
            mHeartButton = itemView.findViewById(R.id.item_heart_button_home);
            mTextRate = itemView.findViewById(R.id.item_rate_home);
            index = -1;
        }

    }

    private Context mContext;
    private ArrayList<Item> mHomeItems;

    public HomeItemAdapter(Context mContext, ArrayList<Item> mHomeItems) {
        this.mContext = mContext;
        this.mHomeItems = mHomeItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.item_recyclerview_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Item item = mHomeItems.get(position);
        holder.index = position;
        holder.mTextName.setText(item.getName());
        if (item.getImage_path() != null && item.getImage_path().length() > 0)
        {
            ArrayList<String> urls = (ArrayList<String>) StringHdr.getURLImgs(item.getImage_path());
            if (urls.size() > 0)
            {
                Glide.with(holder.itemView.getContext()).load(urls.get(0)).into(holder.mImageItem);
            }
        }
        holder.mImageItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVars.selected_item = item;
                toProductDetails(view);
            }
        });
        holder.mTextName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GlobalVars.selected_item = item;
                toProductDetails(view);
            }
        });

        holder.mTextPrice.setText(ShoppingCartActivity.formatPriceString((int)item.getPrice()));
        //holder.mTextRate.setText(String.valueOf(item.getRate()) + " (" + String.valueOf(item.getRate_count()) + ")");
        holder.mTextRate.setText("Rating avg.");
    }

    @Override
    public int getItemCount() {
        return mHomeItems.size();
    }

    public void toProductDetails(View view)
    {
        Intent switchActivityIntent = new Intent(this.mContext, ProductDetailsActivity.class);
        mContext.startActivity(switchActivityIntent);
    }

}
