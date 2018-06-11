package com.media.kbs.mobileproject;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        foodListParsing();

        myListAdapter = new MyListAdapter(ListActivity.this, listItemArrayList);
        myListAdapter.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(ListActivity.this, DiaryActivity.class);
                intent.putExtra("name",listItemArrayList.get(position).getFoodName());
                intent.putExtra("total",listItemArrayList.get(position).getTotalKcal());
                intent.putExtra("carbon",listItemArrayList.get(position).getCarbonKcal());
                intent.putExtra("protein",listItemArrayList.get(position).getProteinKcal());
                intent.putExtra("fat",listItemArrayList.get(position).getFatKcal());
                intent.putExtra("sodium",listItemArrayList.get(position).getSodiumKcal());
                setResult(RESULT_OK, intent);
                finish();
            }
        });
        recyclerView.setAdapter(myListAdapter);
    }
    public void foodListParsing(){
        String response = "";
        try {
            AssetManager assetManager = getResources().getAssets();
            InputStream inStream = assetManager.open("foodList.json", AssetManager.ACCESS_BUFFER);

            String line = "";
            InputStreamReader isr = new InputStreamReader(inStream);
            BufferedReader reader = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            response = sb.toString();
            isr.close();
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("items");
            for(int i=0;i<jsonArray.length();i++) {

                JSONObject item = jsonArray.getJSONObject(i);

                String parsingFoodName = item.getString("foodName");
                double parsingFoodCal = Double.parseDouble(item.getString("foodCal"));
                double parsingFoodCarbon = Double.parseDouble(item.getString("foodCarbon"));
                double parsingFoodProtein = Double.parseDouble(item.getString("foodProtein"));
                double parsingFoodFat = Double.parseDouble(item.getString("foodFat"));
                double parsingFoodSodium = Double.parseDouble(item.getString("foodSodium"));

                listItem = new ListItem();
                listItem.setFoodName(parsingFoodName);
                listItem.setTotalKcal(parsingFoodCal);
                listItem.setCarbonKcal(parsingFoodCarbon);
                listItem.setProteinKcal(parsingFoodProtein);
                listItem.setFatKcal(parsingFoodFat);
                listItem.setSodiumKcal(parsingFoodSodium);
                listItemArrayList.add(listItem);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
