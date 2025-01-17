package com.example.listycity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ArrayList<String> cityList;
    private ArrayAdapter<String> adapter;
    private ListView cityListView;
    private LinearLayout addCityLayout;
    private EditText cityInput;
    private int selectedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        cityListView = findViewById(R.id.city_list);
        addCityLayout = findViewById(R.id.add_city_layout);
        cityInput = findViewById(R.id.city_input);
        TextView addCityTextView = findViewById(R.id.add_city_textview);
        TextView deleteCityTextView = findViewById(R.id.delete_city_textview);
        TextView confirmTextView = findViewById(R.id.confirm_textview);

        cityList = new ArrayList<>();
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, cityList) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = super.getView(position, convertView, parent);

                if (position == selectedPosition) {
                    view.setBackgroundColor(getResources().getColor(android.R.color.darker_gray));
                } else {
                    view.setBackgroundColor(getResources().getColor(android.R.color.white));
                }

                return view;
            }
        };
        cityListView.setAdapter(adapter);

        addCityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCityLayout.setVisibility(View.VISIBLE);
            }
        });

        confirmTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cityName = cityInput.getText().toString().trim();
                if (!cityName.isEmpty()) {
                    cityList.add(cityName);
                    adapter.notifyDataSetChanged();
                    cityInput.setText("");
                    addCityLayout.setVisibility(View.GONE);
                }
            }
        });

        deleteCityTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (selectedPosition == -1) {
                    return;
                }

                cityList.remove(selectedPosition);
                selectedPosition = -1;
                adapter.notifyDataSetChanged();
            }
        });

        cityListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectedPosition = position;
                adapter.notifyDataSetChanged();
            }
        });
    }
}
