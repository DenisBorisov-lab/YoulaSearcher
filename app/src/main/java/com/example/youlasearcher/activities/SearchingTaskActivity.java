package com.example.youlasearcher.activities;

import android.app.AlertDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TimePicker;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youlasearcher.MainActivity;
import com.example.youlasearcher.R;
import com.example.youlasearcher.interfaces.Changeable;
import com.example.youlasearcher.models.dialogFragments.PeriodDialogFragment;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class SearchingTaskActivity extends AppCompatActivity implements Changeable {

    private List<State> states = new ArrayList<>();
    private ListView symbolsList;
    private State period = new State("Периодичность поиска", "Каждые 5 минут", R.drawable.ic_refresh);
    private State timeModule = new State("Время работы поиска", "Круглосуточно", R.drawable.ic_schedule);
    private State options = new State("Параметры поиска", "нажмите для настройки", R.drawable.ic_wrench);
    private State searchWeb = new State("Предварительные результаты", "Нажмите, чтобы посмотреть", R.drawable.ic_search);
    private State searchApp = new State("Предварительные результаты", "Внутри приложения", R.drawable.ic_search);
    private StateAdapter stateAdapter;
    private Calendar dateAndTime = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_searching_task);

        setInitialData();
        symbolsList = findViewById(R.id.optionsList);
        stateAdapter = new StateAdapter(this, R.layout.list_item, states);
        symbolsList.setAdapter(stateAdapter);
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                State selectedState = (State) parent.getItemAtPosition(position);

                String name = selectedState.getTitle();
                switch (name) {
                    case "Периодичность поиска":
                        PeriodDialogFragment fragment = new PeriodDialogFragment();
                        fragment.show(getSupportFragmentManager(), "period");
                        break;
                    case "Время работы поиска":
//                        TimeDialogFragment timeFragment = new TimeDialogFragment();
//                        timeFragment.show(getSupportFragmentManager(), "time picker");
                        showDialog();
                        break;
                    case "Параметры поиска":
                        break;
                    default:
                        if (selectedState.getSubTitle().equals("Нажмите, чтобы посмотреть")) {

                        } else {

                        }
                        break;
                }
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

    private void setInitialData() {
        states.add(period);
        states.add(timeModule);
        states.add(options);
        states.add(searchWeb);
        states.add(searchApp);
    }

    @Override
    public void change(String name) {
        period.setSubTitle(name);
        stateAdapter.notifyDataSetChanged();
    }

    public void showDialog() {
        AlertDialog.Builder builder
                = new AlertDialog.Builder(this);
        builder.setTitle("Установка времени");
        final View customLayout
                = getLayoutInflater()
                .inflate(
                        R.layout.dialog_time_picker,
                        null);
        builder.setView(customLayout);
        EditText start = customLayout.findViewById(R.id.start);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        dateAndTime.set(Calendar.HOUR_OF_DAY, i);
                        dateAndTime.set(Calendar.MINUTE, i1);
                    }
                };


                start.setText(DateUtils.formatDateTime(SearchingTaskActivity.this,
                        dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME));


                new TimePickerDialog(SearchingTaskActivity.this, t,
                        dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE), true)
                        .show();

            }
        });
        builder.create().show();
    }


}
