package com.example.academate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class OwnedAdapter extends RecyclerView.Adapter<OwnedAdapter.OwnedViewHolder> {
    private ArrayList<Item> ownedList;
    private Context context;
    private OnItemClickListener listener;

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public OwnedAdapter(Context context, ArrayList<Item> ownedList){
        this.context = context;
        this.ownedList = ownedList;
    }

    @NonNull
    @Override
    public OwnedViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_display, parent, false);
        return new OwnedViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OwnedViewHolder holder, int position){
        Item currentItem = ownedList.get(position);
        holder.username.setText(currentItem.getUsername());
        holder.itemname.setText(currentItem.getItemName());
        holder.itemdescription.setText(currentItem.getItemDescription());
    }

    @Override
    public int getItemCount(){
        return ownedList.size();
    }

    public class OwnedViewHolder extends RecyclerView.ViewHolder{
        TextView username;
        TextView itemname;
        TextView itemdescription;

        public OwnedViewHolder(@NonNull View itemView){
            super(itemView);
            username = itemView.findViewById(R.id.itemOwner);
            itemname = itemView.findViewById(R.id.itemTitle);
            itemdescription = itemView.findViewById(R.id.itemDescription);

            itemView.setOnClickListener(v -> {
                if (listener != null) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(ownedList.get(position));
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Item item);
    }
}
