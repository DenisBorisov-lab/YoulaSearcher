package com.example.youlasearcher.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youlasearcher.MainActivity;
import com.example.youlasearcher.R;

import java.util.ArrayList;
import java.util.List;

public class SearchingTaskActivity extends AppCompatActivity {

    List<State> states = new ArrayList<>();
    ListView symbolsList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_searching_task);

        setInitialData();

        symbolsList = findViewById(R.id.optionsList);
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_item, states);
        symbolsList.setAdapter(stateAdapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                State selectedState = (State)parent.getItemAtPosition(position);
                Toast.makeText(getApplicationContext(), "Был выбран пункт " + selectedState.getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        };
        symbolsList.setOnItemClickListener(itemListener);
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

    private void setInitialData(){

        states.add(new State ("Периодичность поиска", "текст", R.drawable.ic_refresh));
        states.add(new State ("Время работы поиска", "текста", R.drawable.ic_schedule));
        states.add(new State ("Параметры поиска", "нажмите для настройки", R.drawable.ic_wrench));
        states.add(new State ("Предварительные результаты", "Нажмите, чтобы посмотреть", R.drawable.ic_search));
        states.add(new State ("Предварительные результаты", "Внутри приложения", R.drawable.ic_search));
    }
}
