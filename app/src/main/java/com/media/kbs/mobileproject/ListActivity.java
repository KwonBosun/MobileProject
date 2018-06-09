package com.media.kbs.mobileproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity{
    public LinearLayoutManager layoutManager;
    public MyListAdapter myListAdapter;
    private ListItem listItem;
    public ArrayList<ListItem> listItemArrayList;
    public RecyclerView recyclerView;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_view);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        listItemArrayList = new ArrayList<ListItem>();

        listItem = new ListItem();
        listItem.setFoodName("라면");
        listItem.setTotalKcal("365" + "kcal");
        listItem.setCarbonKcal("30" + "kcal");
        listItem.setProteinKcal("1" + "kcal");
        listItem.setFatKcal("3" + "kcal");
        listItem.setSodiumKcal("2000" + "mg");
        listItemArrayList.add(listItem);

        myListAdapter = new MyListAdapter(ListActivity.this, listItemArrayList);
        myListAdapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ListActivity.this, DiaryActivity.class);
                intent.putExtra("name",listItemArrayList.get(position).getFoodName());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        recyclerView.setAdapter(myListAdapter);



    }
}
