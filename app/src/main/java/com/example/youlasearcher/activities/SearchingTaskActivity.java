package com.example.youlasearcher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youlasearcher.MainActivity;
import com.example.youlasearcher.R;

public class SearchingTaskActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_searching_task);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.new_searching_task_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.delete_searching_task) {
            // удалить автописк и вернуться в начало
            startActivity(new Intent(SearchingTaskActivity.this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
