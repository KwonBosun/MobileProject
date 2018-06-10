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

public class FoodListActivity extends AppCompatActivity {
    ListView list;
    String[] foodmenu = {
            "세트메뉴1",
            "세트메뉴2",
            "세트메뉴3",
            "학식메뉴1",
            "학식메뉴2",
            "학식메뉴3",
            "학식메뉴4",
            "바나나",
            "감자",
            "닭가슴살",
            "메뉴 1",
            "메뉴 2"
    } ;

    String[] caloriemenu = {
            "600kcal",
            "732kcal",
            "923kcal",
            "532kcal",
            "465kcal",
            "570kcal",
            "420kcal",
            "92kcal",
            "63kcal",
            "98kcal",
            "600kcal",
            "600kcal"
    } ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_list_view);
        CustomList adapter = new CustomList(FoodListActivity.this);
        list=(ListView)findViewById(R.id.food_list);
        list.setAdapter(adapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getBaseContext(), foodmenu[+position],
                        Toast.LENGTH_SHORT).show();
            }
        });
    }

    public class CustomList extends ArrayAdapter<String> {
        private final Activity context;
        public CustomList(Activity context) {
            super(context, R.layout.food_list_item, foodmenu);
            this.context = context;
        }

        @Override
        public View getView(int position, View view, ViewGroup parent) {
            LayoutInflater inflater = context.getLayoutInflater();
            View rowView= inflater.inflate(R.layout.food_list_item, null, true);
            TextView foods = (TextView) rowView.findViewById(R.id.food_view);
            foods.setText(foodmenu[position]);
            TextView calorie = (TextView) rowView.findViewById(R.id.calorie_view);
            calorie.setText(caloriemenu[position]);
            return rowView;
        }
    }
}
