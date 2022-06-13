package com.example.youlasearcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youlasearcher.activities.BannerState;
import com.example.youlasearcher.activities.BannerStateAdapter;
import com.example.youlasearcher.activities.HelpGuideActivity;
import com.example.youlasearcher.activities.NotificationSettings;
import com.example.youlasearcher.activities.SearchingTaskActivity;
import com.example.youlasearcher.activities.State;
import com.example.youlasearcher.models.dialogFragments.GuideDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences sharedPreferences;
    private final static String FILE_NAME = "content.txt";
    private List<BannerState> states;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] array = readData().split("\n");
        if (array.length == 0 || array[0].equals("")){
            setContentView(R.layout.activity_main);
        }else{
            setContentView(R.layout.activity_main_list_view);
            ListView listView = findViewById(R.id.searching_list);
            states = new ArrayList<>();
            setInitialData();
            BannerStateAdapter adapter = new BannerStateAdapter(this, R.layout.activity_main_list_item, states);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    State selectedState = (State) adapterView.getItemAtPosition(i);
                    System.out.println("Кнопка нажата");
                }
            });


        }


        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        if (!sharedPreferences.contains("vibration")){
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("vibration", true);
            edit.putBoolean("wifi_searching", false);
            edit.apply();
        }

        FloatingActionButton addSearchingBtn = findViewById(R.id.add_searching_btn);

        addSearchingBtn.setOnClickListener(view -> {
            startActivity(new Intent(MainActivity.this, SearchingTaskActivity.class));
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.stop_searching:
                // логика остановки поиска
                return true;
            case R.id.recreate_searching:
                // пересоздать поиски
                return true;
            case R.id.help:
                // помощь
                Intent helpActivity = new Intent(MainActivity.this, HelpGuideActivity.class);
                startActivity(helpActivity);
                return true;
            case R.id.instruction:
                // руководство
                GuideDialogFragment guideDialogFragment = new GuideDialogFragment();
                guideDialogFragment.show(getSupportFragmentManager(), "guide");
                return true;
            case R.id.notification_settings:
                // action
                startActivity(new Intent(MainActivity.this, NotificationSettings.class));
                return true;

        }
        //headerView.setText(item.getTitle());
        return super.onOptionsItemSelected(item);
    }

    public String readData() {
        FileInputStream fin = null;
        try {
            fin = openFileInput(FILE_NAME);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            return new String(bytes);

        } catch (IOException ex) {

            ex.printStackTrace();
        } finally {

            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
        return null;
    }
    private void setInitialData(){
        String[] rows = readData().split("\n");
        for (String row : rows){
            if (row.length() != 0){
                String[] elements = row.split("@");
                String name = elements[0];
                String schedule = elements[1];
                String period = elements[2];
                String url = elements[3];
                states.add(new BannerState(name, schedule, period, url));
            }
        }
    }
}