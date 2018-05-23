package com.media.kbs.mobileproject;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class TrainingListActivity  extends AppCompatActivity {
    ListView list;
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
            "47kcal",
            "140kcal",
            "279kcal",
            "129kcal",
            "387kcal",
            "773kcal",
            "123kcal",
            "368kcal",
            "735kcal",
            "98kcal",
            "294kcal",
            "600kcal"
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.training_list_view);
        TrainingListActivity.CustomList adapter = new TrainingListActivity.CustomList(TrainingListActivity.this);
        list=(ListView)findViewById(R.id.training_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), trainingmenu[+position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList(Activity context) {
            super(context, R.layout.training_list_item, trainingmenu);
            this.context = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.training_list_item, null, true);
            TextView training = (TextView) rowView.findViewById(R.id.training_view);
            training.setText(trainingmenu[position]);
            TextView trainingcalorie = (TextView) rowView.findViewById(R.id.training_calorie_view);
            trainingcalorie.setText(trainingcaloriemenu[position]);
            return rowView;
        }
    }
}
