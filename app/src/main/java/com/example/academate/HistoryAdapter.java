package com.example.academate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ItemViewHolder> {
    private ArrayList<Item> itemList;
    private Context context;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public HistoryAdapter(Context context, ArrayList<Item> itemList){
        this.context = context;
        this.itemList = itemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_display, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position){
        Item currentItem = itemList.get(position);
        holder.username.setText(currentItem.getUsername());
        holder.itemname.setText(currentItem.getItemName());
        holder.itemdescription.setText(currentItem.getItemDescription());
    }

    @Override
    public int getItemCount(){
        return itemList.size();
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView itemname;
        TextView itemdescription;

        public ItemViewHolder(@NonNull View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.itemOwner);
            itemname = itemView.findViewById(R.id.itemTitle);
            itemdescription = itemView.findViewById(R.id.itemDescription);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(itemList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }
}
