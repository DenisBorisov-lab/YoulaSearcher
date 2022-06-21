package com.denisbrisov.youlasearcher;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.denisbrisov.youlasearcher.activities.BannerState;
import com.denisbrisov.youlasearcher.activities.BannerStateAdapter;
import com.denisbrisov.youlasearcher.activities.HelpGuideActivity;
import com.denisbrisov.youlasearcher.activities.NotificationSettings;
import com.denisbrisov.youlasearcher.activities.SearchingTaskActivity;
import com.denisbrisov.youlasearcher.models.Modes;
import com.denisbrisov.youlasearcher.models.dialogFragments.GuideDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final static String FILE_NAME = "content.txt";
    private SharedPreferences sharedPreferences;
    private List<BannerState> states;
    private BannerStateAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String[] array = readData().split("\n");
        if (array.length == 0 || array[0].equals("")) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_list_view);
            ListView listView = findViewById(R.id.searching_list);
            states = new ArrayList<>();
            setInitialData();
            adapter = new BannerStateAdapter(this, R.layout.activity_main_list_item, states);
            listView.setAdapter(adapter);
        }


        sharedPreferences = getSharedPreferences("settings", Context.MODE_PRIVATE);
        if (!sharedPreferences.contains("vibration")) {
            SharedPreferences.Editor edit = sharedPreferences.edit();
            edit.putBoolean("vibration", true);
            edit.putBoolean("wifi_searching", false);
            edit.apply();
        }

        FloatingActionButton addSearchingBtn = findViewById(R.id.add_searching_btn);

        addSearchingBtn.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, SearchingTaskActivity.class);
            intent.putExtra("name", "");
            intent.putExtra("period", "Каждые 5 минут");
            intent.putExtra("schedule", "Круглосуточно");
            intent.putExtra("url", "Нажмите для настройки");
            intent.putExtra("id", "");
            intent.putExtra("active", "true");
            startActivity(intent);
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
                stopSearching();
                // TODO: 19.06.2022 остановить поиски
                return true;
            case R.id.recreate_searching:
                startSearching();
                // TODO: 19.06.2022 пересоздать поиски
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
        return "";
    }

    private void setInitialData() {
        String[] rows = readData().split("\n");
        for (String row : rows) {
            if (row.length() != 0) {
                String[] elements = row.split("@");
                String name = elements[0];
                String schedule = elements[1];
                String period = elements[2];
                String url = elements[3];
                String id = elements[4];
                String activeString = elements[5];
                boolean active = true;
                if (activeString.equals("false")) {
                    active = false;
                }
                states.add(new BannerState(name, schedule, period, url, id, active));
            }
        }
    }

    private void stopSearching() {
        if (states == null){
            return;
        }
        for (BannerState state : states) {
            if (state == null){
                continue;
            }
            state.setActive(false);
            setActive(state);
            Intent serviceIntent = new Intent(MainActivity.this, NotificationService.class);

            serviceIntent.putExtra("mode", Modes.DELETE);
            serviceIntent.putExtra("name", state.getTitle());
            serviceIntent.putExtra("url", state.getUrl());
            serviceIntent.putExtra("workTime", state.getWorkSubTitle());
            serviceIntent.putExtra("time", state.getPeriodSubTitle());
            serviceIntent.putExtra("id", state.getId());
            serviceIntent.putExtra("active", state.isActive() ? "true" : "false");
            startService(serviceIntent);
        }
        adapter.notifyDataSetChanged();
    }

    public void setActive(BannerState state) {
        String oldData = readData() == null ? "" : readData();
        String data = "";
        String activeString = "true";
        if (!state.isActive()) {
            activeString = "false";
        }
        if (oldData.length() != 0) {
            for (String row : oldData.split("\n")) {
                String[] elements = row.split("@");
                if (elements[4].equals(state.getId())) {
                    String newRow = state.getTitle() + "@" + state.getWorkSubTitle() + "@" + state.getPeriodSubTitle() + "@" + state.getUrl() + "@" + state.getId() + "@" + activeString + "\n";
                    data += newRow;
                } else {
                    data += row + "\n";
                }
            }
        }
        writeData(data);

    }

    public void writeData(String data) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(data.getBytes());
        } catch (IOException ex) {

            ex.printStackTrace();
        } finally {
            try {
                if (fos != null)
                    fos.close();
            } catch (IOException ex) {

                ex.printStackTrace();
            }
        }
    }

    private void startSearching() {
        if (states == null){
            return;
        }
        for (BannerState state : states) {
            if (state == null){
                continue;
            }
            state.setActive(true);
            setActive(state);
            Intent serviceIntent = new Intent(MainActivity.this, NotificationService.class);

            serviceIntent.putExtra("mode", Modes.CREATE);
            serviceIntent.putExtra("name", state.getTitle());
            serviceIntent.putExtra("url", state.getUrl());
            serviceIntent.putExtra("workTime", state.getWorkSubTitle());
            serviceIntent.putExtra("time", state.getPeriodSubTitle());
            serviceIntent.putExtra("id", state.getId());
            serviceIntent.putExtra("active", state.isActive() ? "true" : "false");
            startService(serviceIntent);
        }
        adapter.notifyDataSetChanged();
    }

}