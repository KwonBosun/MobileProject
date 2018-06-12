package com.media.kbs.mobileproject;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MyExerList extends RecyclerView.Adapter<MyExerList.ItemViewHolder>  {
    Context context;
    ArrayList<ListItem> listExerArrayList;
    private OnItemClickListener listener;
    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public MyExerList(Context context, ArrayList<ListItem> listExerArrayList) {
        this.context = context;
        this.listExerArrayList = listExerArrayList;
    }
    public ArrayList<ListItem> getListExerArrayList() {
        return listExerArrayList;
    }
    public void setListExerArrayList(ArrayList<ListItem> listExerArrayList) {
        this.listExerArrayList = listExerArrayList;
    }
    @Override
    public MyExerList.ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.exer_item, parent, false);
        return new MyExerList.ItemViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull MyExerList.ItemViewHolder holder, final int position) {
        holder.exerView.setText(listExerArrayList.get(position).getNameExer());
        holder.calView.setText(listExerArrayList.get(position).getExerKcal()+"kcal");
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(position);
            }
        });


    }

    @Override
    public int getItemCount() {
        return listExerArrayList.size();
    }
    class ItemViewHolder extends RecyclerView.ViewHolder{
        private TextView exerView;
        private TextView calView;

        public ItemViewHolder(View itemView) {
            super(itemView);
            exerView = (TextView)itemView.findViewById(R.id.exer_view);
            calView = (TextView)itemView.findViewById(R.id.cal_view);

        }
    }
}
