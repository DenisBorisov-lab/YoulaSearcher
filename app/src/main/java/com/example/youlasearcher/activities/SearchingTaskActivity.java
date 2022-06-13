package com.example.youlasearcher.activities;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.example.youlasearcher.MainActivity;
import com.example.youlasearcher.NotificationService;
import com.example.youlasearcher.R;
import com.example.youlasearcher.interfaces.Changeable;
import com.example.youlasearcher.interfaces.ChangeableTime;
import com.example.youlasearcher.models.Modes;
import com.example.youlasearcher.models.dialogFragments.PeriodDialogFragment;
import com.example.youlasearcher.models.dialogFragments.TimePickerDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchingTaskActivity extends AppCompatActivity implements Changeable, ChangeableTime {

    static final String URL = "URL";
    private List<State> states = new ArrayList<>();
    private ListView symbolsList;
    private EditText name;
    private FloatingActionButton saveButton;
    private final static String FILE_NAME = "content.txt";
    private State period = new State("Периодичность поиска", "Каждые 5 минут", R.drawable.ic_refresh);
    private State timeModule = new State("Время работы поиска", "Круглосуточно", R.drawable.ic_schedule);
    private State options = new State("Параметры поиска", "Нажмите для настройки", R.drawable.ic_wrench);
    private State searchWeb = new State("Предварительные результаты", "Нажмите, чтобы посмотреть", R.drawable.ic_search);
    private State searchApp = new State("Предварительные результаты", "Внутри приложения", R.drawable.ic_search);
    private StateAdapter stateAdapter;
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        String url = intent.getStringExtra(URL);
                        options.setSubTitle(url);
                        stateAdapter.notifyDataSetChanged();
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_searching_task);
        name = findViewById(R.id.name);
        saveButton = findViewById(R.id.add_searching_btn);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String time = period.getSubTitle();
                String workTime = timeModule.getSubTitle();
                String url = options.getSubTitle();
                if (url.equals("Нажмите для настройки")) {
                    Toast toast = Toast.makeText(view.getContext(), "Укажите параметры поиска!", Toast.LENGTH_LONG);
                    toast.show();
                } else if (name.getText().toString().length() == 0) {
                    Toast toast = Toast.makeText(view.getContext(), "Введите название поиска!", Toast.LENGTH_LONG);
                    toast.show();
                } else {
                    Intent intent = new Intent(SearchingTaskActivity.this, NotificationService.class);
                    intent.putExtra("mode", Modes.CREATE);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("url", url);
                    intent.putExtra("workTime", workTime);
                    intent.putExtra("time", time);

                    startService(intent);
                    String oldData = readData() == null ? "" : readData();

                    String data = oldData + name.getText().toString() + "@" + workTime + "@" + time + "@" + url + "\n";
                    writeData(data);
                }
            }
        });

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
                        Bundle args = new Bundle();
                        args.putString("subtitle", timeModule.getSubTitle());
                        TimePickerDialogFragment timePickerDialogFragment = new TimePickerDialogFragment();
                        timePickerDialogFragment.setArguments(args);
                        timePickerDialogFragment.show(getSupportFragmentManager(), "timePicker");
                        break;
                    case "Параметры поиска":
                        Intent settingsActivity = new Intent(SearchingTaskActivity.this, SearchingSettingsActivity.class);
                        mStartForResult.launch(settingsActivity);
                        break;
                    default:
                        if (selectedState.getSubTitle().equals("Нажмите, чтобы посмотреть")) {
                            if (options.getSubTitle().equals("Нажмите для настройки")) {
                                Toast toast = Toast.makeText(SearchingTaskActivity.this, "Укажите параметры поиска!", Toast.LENGTH_LONG);
                                toast.show();
                            } else {
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(options.getSubTitle()));
                                startActivity(browserIntent);
                            }
                        } else {
                            if (options.getSubTitle().equals("Нажмите для настройки")) {
                                Toast toast = Toast.makeText(SearchingTaskActivity.this, "Укажите параметры поиска!", Toast.LENGTH_LONG);
                                toast.show();
                            } else {
                                Intent advanceResultsIntent = new Intent(SearchingTaskActivity.this, AdvanceResultsActivity.class);
                                advanceResultsIntent.putExtra("url", options.getSubTitle());
                                startActivity(advanceResultsIntent);
                            }
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
            if (name.getText().toString().length() != 0 && !options.getSubTitle().equals("Нажмите для настройки")) {
                Intent intent = new Intent(SearchingTaskActivity.this, NotificationService.class);
                intent.putExtra("mode", Modes.DELETE);
                intent.putExtra("name", name.getText().toString());
                intent.putExtra("url", "");
                intent.putExtra("workTime", "");
                intent.putExtra("time", "");

                startService(intent);

                String content = readData();
                String[] rows = content.split("\n");
                String newContent = "";
                for (String row : rows) {
                    String[] elements = row.split("@");
                    String i = elements[0];
                    if (!i.equals(name.getText().toString()) && elements[1].equals(timeModule.getSubTitle()) && elements[2].equals(period.getSubTitle()) && elements[3].equals(options.getSubTitle())) {
                        newContent += row + "\n";
                    }
                }
                deleteFile(FILE_NAME);
                writeData(newContent);
            }
            Intent mainActivity = new Intent(SearchingTaskActivity.this, MainActivity.class);
            startActivity(mainActivity);
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

    @Override
    public void changeTime(String[] time) {
        if (time[0].equals("Круглосуточно")) {
            timeModule.setSubTitle(time[0]);
        } else {
            timeModule.setSubTitle("С " + time[0] + " до " + time[1]);
        }
        stateAdapter.notifyDataSetChanged();
    }

    public void writeData(String data) {
        FileOutputStream fos = null;
        try {
            fos = openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(data.getBytes());
            Toast.makeText(this, "Файл сохранен", Toast.LENGTH_SHORT).show();
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


}
