package com.example.clothingretailer;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
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

public class ProductItemAdapter extends RecyclerView.Adapter<ProductItemAdapter.ViewHolder> {
    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageItem;
        private TextView mTextName;
        private TextView mTextSizeColor;
        private TextView mTextPrice;
        private TextView mTextCount;
        private ImageButton mMinusButton;
        private ImageButton mPlusButton;
        private int index;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            mImageItem = itemView.findViewById(R.id.item_image_cart);
            mTextName = itemView.findViewById(R.id.item_name_cart);
            mTextSizeColor = itemView.findViewById(R.id.item_size_color);
            mTextPrice = itemView.findViewById(R.id.item_price);
            mTextCount = itemView.findViewById(R.id.item_count);
            mMinusButton = itemView.findViewById(R.id.minus_button);
            mPlusButton = itemView.findViewById(R.id.plus_button);
            index = -1;

            mMinusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int newCount = Integer.valueOf(mTextCount.getText().toString()) - 1;
                    mTextCount.setText(String.valueOf(newCount));
                    mProductItems.get(index).setCount(newCount);
                    ShoppingCartActivity.updateCart();
                    if (newCount == 0)
                    {
                        removeHolder(index);
                        for (int i = 0; i < GlobalVars.current_cart_items.size(); i++)
                        {
                            Item mItem = GlobalVars.current_cart_items.get(i);
                            if (mItem.getName() == mTextName.getText() &&
                                    mTextSizeColor.getText().equals(String.format("Color: %s - Size: %s", GlobalVars.current_cart_colors.get(i), GlobalVars.current_cart_sizes.get(i))) &&
                                    GlobalVars.current_cart_item_counts.get(i) == 1)
                            {
                                GlobalVars.current_cart_items.remove(i);
                                GlobalVars.current_cart_sizes.remove(i);
                                GlobalVars.current_cart_colors.remove(i);
                                GlobalVars.current_cart_item_counts.remove(i);
                                Log.d("tag", "global cart updated");
                                break;
                            }

                        }
                    }
                    else
                    {
                        for (int i = 0; i < GlobalVars.current_cart_items.size(); i++)
                        {
                            Item mItem = GlobalVars.current_cart_items.get(i);
                            if (mItem.getName() == mTextName.getText() &&
                                    mTextSizeColor.getText().equals(String.format("Color: %s - Size: %s", GlobalVars.current_cart_colors.get(i), GlobalVars.current_cart_sizes.get(i))))
                            {
                                Integer tmp = GlobalVars.current_cart_item_counts.get(i) - 1;
                                GlobalVars.current_cart_item_counts.set(i, tmp);
                                Log.d("tag", "global cart updated");
                                break;
                            }
                        }
                    }
                }
            });

            mPlusButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int newCount = Integer.valueOf(mTextCount.getText().toString()) + 1;
                    mTextCount.setText(String.valueOf(newCount));
                    mProductItems.get(index).setCount(newCount);
                    ShoppingCartActivity.updateCart();
                    for (int i = 0; i < GlobalVars.current_cart_items.size(); i++)
                    {
                        Item mItem = GlobalVars.current_cart_items.get(i);
                        if (mItem.getName() == mTextName.getText() &&
                                mTextSizeColor.getText().equals(String.format("Color: %s - Size: %s", GlobalVars.current_cart_colors.get(i), GlobalVars.current_cart_sizes.get(i))))
                        {
                            Integer tmp = GlobalVars.current_cart_item_counts.get(i) + 1;
                            GlobalVars.current_cart_item_counts.set(i, tmp);
                            Log.d("tag", "global cart updated");
                            break;
                        }
                    }
                }
            });

        }

    }

    private Context mContext;
    private ArrayList<ProductItemInCart> mProductItems;

    public ProductItemAdapter(Context mContext, ArrayList<ProductItemInCart> mProductItems) {
        this.mContext = mContext;
        this.mProductItems = mProductItems;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View itemView = inflater.inflate(R.layout.item_recyclerview_cart, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        position = holder.getAdapterPosition();
        ProductItemInCart item = mProductItems.get(position);
        holder.index = position;
        holder.mTextName.setText(item.getName());
        Glide.with(holder.itemView.getContext()).load(item.getImage_path()).into(holder.mImageItem);
        //holder.mImageItem.setImageResource(item.getImage_path());
        holder.mTextCount.setText(String.valueOf(item.getCount()));
        holder.mTextSizeColor.setText(String.format("Color: %s - Size: %s", item.getColor(), item.getSize()));
        holder.mTextPrice.setText(ShoppingCartActivity.formatPriceString(item.getPrice()));
    }

    @Override
    public int getItemCount() {
        return mProductItems.size();
    }

    public void removeHolder(int index) {
        mProductItems.remove(index);
        notifyItemRemoved(index);
        notifyItemRangeChanged(index, mProductItems.size());
    }

}
