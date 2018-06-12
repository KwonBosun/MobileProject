package com.media.kbs.mobileproject;

import android.content.Context;
import android.content.Intent;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import static android.app.PendingIntent.getActivity;
import static com.media.kbs.mobileproject.DiaryActivity.date;
import static com.media.kbs.mobileproject.DiaryActivity.dbHelper;

public class ChangeFoodListActivity extends AppCompatActivity {
    private EditText ChangeName;
    private EditText ChangeCal;
    private EditText ChangeCarbon;
    private EditText ChangeProtein;
    private EditText ChangeFat;
    private EditText ChangeSodium;
    private Button selected;
    private ListItem listItem2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_foodlist);
        ChangeName = findViewById(R.id.changeFood);
        ChangeCal =findViewById(R.id.changeCal);

        ChangeCarbon=findViewById(R.id.changeCarbon);
        ChangeProtein=findViewById(R.id.changeProtein);
        ChangeFat=findViewById(R.id.changeFat);
        ChangeSodium=findViewById(R.id.changeSodium);
        selected=findViewById(R.id.selected);

        listItem2=dbHelper.getDataInfo("number");
//        selected.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(ChangeFoodListActivity.this,ListActivity.class);
//                dbHelper.insertDataInfo(String.format("%.2f",listItem2.getTotalKcal()), Double.parseDouble(ChangeCal.getText().toString()),
//                        Double.parseDouble(ChangeCal.getText().toString()),
//                        Double.parseDouble(ChangeProtein.getText().toString()),
//                        Double.parseDouble(ChangeFat.getText().toString()),
//                        Double.parseDouble(ChangeSodium.getText().toString()),
//                        ChangeName.getText().toString(),String.format("%2f",listItem2.getTotalKcal()),null);
//                intent.putExtra("list", ChangeName.getText().toString());
//                setResult(RESULT_OK, intent);
//                finish();
//            }
//        });

    }

}
