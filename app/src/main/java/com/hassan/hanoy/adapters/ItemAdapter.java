package com.hassan.hanoy.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hassan.hanoy.R;
import com.hassan.hanoy.models.Item;

import java.util.ArrayList;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    private final ArrayList<Item> mItems;

    public ItemAdapter(ArrayList<Item> items) {
        this.mItems = items;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = this.mItems.get(position);
        holder.mTvName.setText(item.getName());
        holder.mTvResultBon.setText(item.getResultBon3());
        holder.mTvResultLang.setText(item.getResultLang2());
    }

    @Override
    public int getItemCount() {
        return this.mItems.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView mTvName, mTvResultBon, mTvResultLang;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.mTvName = itemView.findViewById(R.id.name);
            this.mTvResultBon = itemView.findViewById(R.id.result_bon);
            this.mTvResultLang = itemView.findViewById(R.id.result_lang);
        }
    }
}
