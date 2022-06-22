package com.denisbrisov.youlasearcher.activities;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
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

import com.denisbrisov.youlasearcher.MainActivity;
import com.denisbrisov.youlasearcher.NotificationService;
import com.denisbrisov.youlasearcher.R;
import com.denisbrisov.youlasearcher.interfaces.Changeable;
import com.denisbrisov.youlasearcher.interfaces.ChangeableTime;
import com.denisbrisov.youlasearcher.models.Modes;
import com.denisbrisov.youlasearcher.models.dialogFragments.PeriodDialogFragment;
import com.denisbrisov.youlasearcher.models.dialogFragments.TimePickerDialogFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SearchingTaskActivity extends AppCompatActivity implements Changeable, ChangeableTime {

    static final String URL = "URL";
    private final static String FILE_NAME = "content.txt";
    private List<State> states = new ArrayList<>();
    private ListView symbolsList;
    private EditText name;
    private FloatingActionButton saveButton;
    private State period;
    private State timeModule;
    private State options;
    private State searchWeb;
    private State searchApp;
    private String[] uuid;
    private StateAdapter stateAdapter;
    ActivityResultLauncher<Intent> mStartForResult = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if (result.getResultCode() == Activity.RESULT_OK) {
                        Intent intent = result.getData();
                        String url = null;
                        if (intent != null) {
                            url = intent.getStringExtra(URL);
                            options.setSubTitle(url);
                            stateAdapter.notifyDataSetChanged();
                        }
                    }
                }
            });


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_searching_task);
        name = findViewById(R.id.name);
        Bundle extras = getIntent().getExtras();
        String title = extras.getString("name");
        String url = extras.getString("url");
        String periodTime = extras.getString("period");
        String schedule = extras.getString("schedule");
        String activeString = extras.getString("active");
        uuid = new String[]{extras.getString("id")};

        name.setText(title);
        period = new State("Периодичность поиска", periodTime, R.drawable.ic_refresh);
        timeModule = new State("Время работы поиска", schedule, R.drawable.ic_schedule);
        options = new State("Параметры поиска", url, R.drawable.ic_wrench);
        searchApp = new State("Предварительные результаты", "Внутри приложения", R.drawable.ic_search);
        searchWeb = new State("Предварительные результаты", "Нажмите, чтобы посмотреть", R.drawable.ic_search);

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

                    String oldData = readData() == null ? "" : readData();
                    String data = "";
                    boolean isExist = false;
                    if (oldData.length() != 0) {
                        for (String row : oldData.split("\n")) {
                            String[] elements = row.split("@");
                            if (elements[4].equals(uuid[0])) {
                                isExist = true;
                                String newRow = name.getText().toString() + "@" + workTime + "@" + time + "@" + url + "@" + uuid[0] + "@" + activeString + "\n";
                                data += newRow;
                            } else {
                                data += row + "\n";
                            }
                        }
                    }

                    if (!isExist) {
                        uuid[0] = UUID.randomUUID().toString();
                        data = oldData + name.getText().toString() + "@" + workTime + "@" + time + "@" + url + "@" + uuid[0] + "@" + activeString + "\n";
                    }
                    Intent intent = new Intent(SearchingTaskActivity.this, NotificationService.class);
                    intent.putExtra("mode", Modes.CREATE);
                    intent.putExtra("name", name.getText().toString());
                    intent.putExtra("url", url);
                    intent.putExtra("workTime", workTime);
                    intent.putExtra("time", time);
                    intent.putExtra("id", uuid[0]);
                    intent.putExtra("active", activeString);

                    startService(intent);
                    writeData(data);

                    Intent mainActivity = new Intent(SearchingTaskActivity.this, MainActivity.class);
                    startActivity(mainActivity);
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
                        settingsActivity.putExtra("url", options.getSubTitle());
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
                    String i = elements[4];
                    if (!i.equals(uuid[0])) {
                        newContent += row + "\n";
                    }
                }
                deleteFile(FILE_NAME);
                writeData(newContent);
            }
            Intent mainActivity = new Intent(SearchingTaskActivity.this, MainActivity.class);
            startActivity(mainActivity);
            return true;
        } else if (id == R.id.copy) {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData clipData = ClipData.newPlainText("link", options.getSubTitle());
            clipboardManager.setPrimaryClip(clipData);

            // TODO: 22.06.2022 скопировать в буфер
        } else if (id == R.id.paste) {
            ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
            ClipData data = clipboardManager.getPrimaryClip();
            ClipData.Item elem = data.getItemAt(0);
            String text = elem.getText().toString();
            options.setSubTitle(text);
            stateAdapter.notifyDataSetChanged();
            // TODO: 22.06.2022 вставить из буфера
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
