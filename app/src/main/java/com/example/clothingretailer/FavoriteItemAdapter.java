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
        holder.mImageItem.setImageResource(Integer.valueOf(item.getImage_path()));
        holder.mTextPrice.setText(ShoppingCartActivity.formatPriceString((int)item.getPrice()));
        //holder.mTextRate.setText(String.valueOf(item.getRate()) + " (" + String.valueOf(item.getRate_count()) + ")");
    }

    @Override
    public int getItemCount() {
        return mFavoriteItems.size();
    }

    public void removeHolder(int index) {
        mFavoriteItems.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, mFavoriteItems.size());
    }

}
