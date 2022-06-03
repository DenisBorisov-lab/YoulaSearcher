package com.example.youlasearcher.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youlasearcher.R;

import java.util.ArrayList;
import java.util.List;

public class NotificationSettings extends AppCompatActivity {

    private static SettingsAdapter adapter;
    private ListView listView;
    private List<SettingsState> states = new ArrayList<>();
    private SettingsState ringtone = new SettingsState("Рингтон", "По умолчанию (Неизвестная мелодия)", false);
    private SettingsState vibration = new SettingsState("Вибрация", "Вибрация при получении уведомлений", true);
    private SettingsState wifiSearching = new SettingsState("Искать только по Wi-Fi", "Поиск осуществляется только при подулючении к Wi-Fi", false);
    private SettingsState openingWay = new SettingsState("Способ открытия уведомлений", "В какой программе будут открываться найденные уведомления", false);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_settings);

        setInitialData();
        listView = findViewById(R.id.settings);
        adapter = new SettingsAdapter(this, R.layout.settings_list_item, states);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SettingsState selectedState = (SettingsState) adapterView.getItemAtPosition(i);
                System.out.println(selectedState.isChecked());
            }
        });


    }

    private void setInitialData() {
        states.add(ringtone);
        states.add(vibration);
        states.add(wifiSearching);
        states.add(openingWay);
    }
}
