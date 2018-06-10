package com.media.kbs.mobileproject;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

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


        final ListDialogFragment listDialogFragment = new ListDialogFragment();


        myListAdapter = new MyListAdapter(ListActivity.this, listItemArrayList);

        //리스너 동작 부분입니다.
        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView, new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(),position+"번 째 아이템 클릭", Toast.LENGTH_SHORT).show();
                        //선택한 포지션 번호로 BD에서 Diary에다 계산
                        //액티비티 닫고 Diary로 돌아가기
                    }

                    @Override
                    public void onLongItemClick(View view, int position) {
                        Toast.makeText(getApplicationContext(),position+"번 째 아이템 롱 클릭",Toast.LENGTH_SHORT).show();
                        listDialogFragment.show(getFragmentManager(),"ListDialogFragment");
                    }
                }));

        recyclerView.setAdapter(myListAdapter);
    }
}
