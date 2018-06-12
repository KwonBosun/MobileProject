package com.media.kbs.mobileproject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class TrainingListActivity  extends AppCompatActivity {
    public LinearLayoutManager layoutManager;
    RecyclerView recyclerView2;
    public MyExerList myExerList;

    public ArrayList<ListItem> listExerArrayList;
    public ArrayList<String>aList=new ArrayList<String>();
    public ArrayList<String>bList=new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.exer_view);
        recyclerView2 = (RecyclerView)findViewById(R.id.recyclerView2);
        layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(layoutManager);
        listExerArrayList = new ArrayList<ListItem>();

        String[] trainingmenu = {
                "걷기 10분",
                "걷기 30분",
                "걷기 1시간",
                "런닝머신 10분",
                "런닝머신 30분",
                "런닝머신 1시간",
                "줄넘기 10분",
                "줄넘기 30분",
                "줄넘기 1시간",
                "자전거 10분",
                "자전거 30분",
                "자전거 1시간"
        } ;

        String[] trainingcaloriemenu = {
                "47",
                "140",
                "279",
                "129",
                "387",
                "773",
                "123",
                "368",
                "735",
                "98",
                "294",
                "600"
        } ;
        Collections.addAll(aList,trainingmenu);
        Collections.addAll(bList,trainingcaloriemenu);
        for(int i = 0; i<12; i++){
            ListItem listItem3=new ListItem();
            listItem3.setNameExer(aList.get(i));
            listItem3.setExerKcal(Double.parseDouble(bList.get(i)));
            listExerArrayList.add(listItem3);
        }
        myExerList = new MyExerList(TrainingListActivity.this, listExerArrayList);
        myExerList.setListener(new OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Intent intent = new Intent(TrainingListActivity.this, DiaryActivity.class);
                intent.putExtra("exername",listExerArrayList.get(position).getNameExer());
                intent.putExtra("exerkcal",listExerArrayList.get(position).getExerKcal());

                setResult(RESULT_OK, intent);
                finish();
            }
        });
        recyclerView2.setAdapter(myExerList);

    }


}
