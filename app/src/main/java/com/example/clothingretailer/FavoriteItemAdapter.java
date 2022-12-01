package com.example.clothingretailer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class FavoriteItemAdapter extends RecyclerView.Adapter<FavoriteItemAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageItem;
        private TextView mTextName;
        private TextView mTextPrice;
        private TextView mTextRate;
        private ImageButton mHeartButton;
        private int index;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageItem = itemView.findViewById(R.id.item_image_favorite);
            mTextName = itemView.findViewById(R.id.item_name_favorite);
            mTextPrice = itemView.findViewById(R.id.item_price_favorite);
            mHeartButton = itemView.findViewById(R.id.item_heart_button_favorite);
            mTextRate = itemView.findViewById(R.id.item_rate_favorite);
            index = -1;

            mHeartButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mHeartButton.setImageResource(R.drawable.checkbox_like);

                    removeHolder(index);
                }
            });
        }

    }

    private Context mContext;
    private ArrayList<Item> mFavoriteItems;

    public FavoriteItemAdapter(Context mContext, ArrayList<Item> mFavoriteItems) {
        this.mContext = mContext;
        this.mFavoriteItems = mFavoriteItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.item_recyclerview_favorite, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        Item item = mFavoriteItems.get(position);
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
        holder.mTextPrice.setText(ShoppingCartActivity.formatPriceString((int)item.getPrice()));
        holder.mTextRate.setText("Average rating");
    }

    @Override
    public int getItemCount() {
        return mFavoriteItems.size();
    }

    public void removeHolder(int index) {
        mFavoriteItems.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, mFavoriteItems.size());
        FavoritesActivity.checkEmpty();
    }

}
