package com.example.youlasearcher.activities;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.example.youlasearcher.NotificationService;
import com.example.youlasearcher.R;
import com.example.youlasearcher.models.Modes;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class BannerStateAdapter extends ArrayAdapter<BannerState> {
    private List<BannerState> states;
    private int layout;
    private LayoutInflater inflater;
    private final static String FILE_NAME = "content.txt";

    public BannerStateAdapter(Context context, int resource, List<BannerState> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(this.layout, parent, false);

        TextView title = view.findViewById(R.id.item_title);
        TextView periodTitle = view.findViewById(R.id.period_title);
        TextView scheduleTitle = view.findViewById(R.id.schedule_title);
        SwitchCompat switchCompat = view.findViewById(R.id.item_switch_compat);
        LinearLayout layoutCd = view.findViewById(R.id.layout_cd);

        BannerState state = states.get(position);

        title.setText(state.getTitle());
        periodTitle.setText(state.getPeriodSubTitle());
        scheduleTitle.setText(state.getWorkSubTitle());
        switchCompat.setChecked(state.isActive());

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                Intent serviceIntent = new Intent(getContext(), NotificationService.class);
                state.setActive(b);
                setActive(state);
                if (b) {
                    serviceIntent.putExtra("mode", Modes.CREATE);
                } else {
                    serviceIntent.putExtra("mode", Modes.DELETE);
                }
                serviceIntent.putExtra("name", state.getTitle());
                serviceIntent.putExtra("url", state.getUrl());
                serviceIntent.putExtra("workTime", state.getWorkSubTitle());
                serviceIntent.putExtra("time", state.getPeriodSubTitle());
                serviceIntent.putExtra("id", state.getId());
                serviceIntent.putExtra("active", state.isActive() ? "true" : "false");
                getContext().startService(serviceIntent);
            }
        });

        layoutCd.setOnClickListener(v -> {
            Intent intent = new Intent(getContext(), SearchingTaskActivity.class);
            intent.putExtra("name", state.getTitle());
            intent.putExtra("period", state.getPeriodSubTitle());
            intent.putExtra("schedule", state.getWorkSubTitle());
            intent.putExtra("url", state.getUrl());
            intent.putExtra("id", state.getId());
            boolean active = state.isActive();
            String activeString = new String();
            if (active){
                activeString = "true";
            }else{
                activeString = "false";
            }
            intent.putExtra("active", activeString);
            view.getContext().startActivity(intent);
        });

        return view;
    }

    public void writeData(String data) {
        FileOutputStream fos = null;
        try {
            fos = getContext().openFileOutput(FILE_NAME, MODE_PRIVATE);
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
            fin = getContext().openFileInput(FILE_NAME);
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

    public void setActive(BannerState state){
        String oldData = readData() == null ? "" : readData();
        String data = "";
        String activeString = "true";
        if (!state.isActive()){
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

}
