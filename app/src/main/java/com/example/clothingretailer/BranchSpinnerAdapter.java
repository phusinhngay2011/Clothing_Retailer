package com.example.clothingretailer;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class BranchSpinnerAdapter extends ArrayAdapter<String> {

    LayoutInflater layoutInflater;

    public BranchSpinnerAdapter(@NonNull Context context, int resource) {
        super(context, resource);
        layoutInflater = LayoutInflater.from(context);
    }

    public BranchSpinnerAdapter(@NonNull Context context, int resource, @NonNull String[] objects) {
        super(context, resource, objects);
        this.layoutInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View rowView = layoutInflater.inflate(R.layout.custom_branch_spinner, null, true);
        TextView branchTV = (TextView) rowView.findViewById(R.id.branchName);
        branchTV.setText(getItem(position));
        return rowView;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView = layoutInflater.inflate(R.layout.custom_branch_spinner, parent, false);
        }
        TextView branchTV = (TextView) convertView.findViewById(R.id.branchName);
        branchTV.setText(getItem(position));
        return convertView;
    }
}
