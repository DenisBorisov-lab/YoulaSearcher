package com.example.youlasearcher;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.youlasearcher.activities.HelpGuideActivity;
import com.example.youlasearcher.activities.NotificationSettings;
import com.example.youlasearcher.activities.SearchingTaskActivity;
import com.example.youlasearcher.models.dialogFragments.GuideDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        switch(id){
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
}