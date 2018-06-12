package com.media.kbs.mobileproject;

import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static com.media.kbs.mobileproject.DiaryActivity.dbHelper;

public class ListActivity extends AppCompatActivity{
    public LinearLayoutManager layoutManager;
    public MyListAdapter myListAdapter;
    private ListItem listItem;
    private ListItem newList;
    private ListItem number;
    private ListItem number2;
    public ArrayList<ListItem> listItemArrayList;
    public RecyclerView recyclerView;
    private final static int LIST_ADD = 1;
    private String list;
    private double index=0.00;


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
//        add_list();


        final ListDialogFragment listDialogFragment = new ListDialogFragment();


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
//    public void add_list(){
//
//        if(dbHelper.getDataInfo("number")==null) {
//            dbHelper.insertDataInfo("number", index, 0.0, 0.0, 0.0, 0.0, null, null, null);
//            number=dbHelper.getDataInfo("number");
//
//        }else {
//            number=dbHelper.getDataInfo("number");
//            index=number.getTotalKcal()+1.00;
//            dbHelper.insertDataInfo("number", index, 0.0, 0.0, 0.0, 0.0, null, null, null);
//            number=dbHelper.getDataInfo("number");
//
//        }
//        listItem = new ListItem();
//        if(newList==null) {
//            newList = dbHelper.getDataInfo(String.format("%.2f", number.getTotalKcal()));
//        }
//        else {
//            for(double i = 0;i<Double.parseDouble(newList.getNameLunch())+1;i++) {
//                newList=dbHelper.getDataInfo(String.format("%.2f",(double)i));
//                listItem.setFoodName(newList.getNameBreak());
//                listItem.setTotalKcal(newList.getTotalKcal());
//                listItem.setCarbonKcal(newList.getCarbonKcal());
//                listItem.setProteinKcal(newList.getProteinKcal());
//                listItem.setFatKcal(newList.getFatKcal());
//                listItem.setSodiumKcal(newList.getSodiumKcal());
//                listItemArrayList.add(listItem);
//            }
//        }
//    }
    @Override
    protected void onResume() {
        super.onResume();
//        add_list();


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.changeFood) {
            startActivityForResult(new Intent(ListActivity.this, ChangeFoodListActivity.class), LIST_ADD);


        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode==LIST_ADD){
            if(resultCode==RESULT_OK){
                list = data.getStringExtra("list");
            }
        }
    }
}
