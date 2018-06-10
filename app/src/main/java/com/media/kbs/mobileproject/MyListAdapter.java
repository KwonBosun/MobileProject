package com.media.kbs.mobileproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends RecyclerView.Adapter<MyListAdapter.ItemViewHolder>  {
    Context context;
    ArrayList<ListItem> listItemArrayList;

    public MyListAdapter(Context context, ArrayList<ListItem> listItemArrayList) {
        this.context = context;
        this.listItemArrayList = listItemArrayList;
    }
    public ArrayList<ListItem> getListItemArrayList() {
        return listItemArrayList;
    }

    public void setListItemArrayList(ArrayList<ListItem> listItemArrayList) {
        this.listItemArrayList = listItemArrayList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyListAdapter.ItemViewHolder holder, final int position) {
        holder.foodName.setText(listItemArrayList.get(position).getFoodName());
        holder.totalKcal.setText(listItemArrayList.get(position).getTotalKcal());
    }

    @Override
    public int getItemCount() {
        return listItemArrayList.size();
    }
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView foodName;
        private TextView totalKcal;

        public ItemViewHolder(View itemView) {
            super(itemView);
            foodName = (TextView)itemView.findViewById(R.id.food_view);
            totalKcal = (TextView)itemView.findViewById(R.id.calorie_view);

        }
    }
}
