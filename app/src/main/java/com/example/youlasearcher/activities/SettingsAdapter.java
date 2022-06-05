package com.example.youlasearcher.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.example.youlasearcher.R;

import java.util.List;

public class SettingsAdapter extends ArrayAdapter<SettingsState> {

    private List<SettingsState> states;
    private int layout;
    private LayoutInflater inflater;

    public SettingsAdapter(Context context, int resource, List<SettingsState> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = inflater.inflate(this.layout, parent, false);

        TextView title = view.findViewById(R.id.settings_title);
        TextView subtitle = view.findViewById(R.id.settings_sub_title);

        SwitchCompat switchCompat = view.findViewById(R.id.switch_compat);
        LinearLayout layoutCc = view.findViewById(R.id.layout_cc);

        SettingsState state = states.get(position);

        SharedPreferences settings = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        if (!settings.contains("vibration")){
            SharedPreferences.Editor editor = settings.edit();
            editor.putBoolean("vibration", true);
            editor.putBoolean("wifi", false);
            editor.apply();
        }

        title.setText(state.getTitle());
        switchCompat.setChecked(state.isChecked());
        subtitle.setText(state.getSubTitle());
        if (state.getTitle().equals("Вибрация")) {
            switchCompat.setTag("vibration_button");
            layoutCc.setTag("vibration_layout");
        } else if (state.getTitle().equals("Искать только по Wi-Fi")) {
            switchCompat.setTag("wifi_searching");
            layoutCc.setTag("wifi_layout");
        }

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchCompat.getTag().equals("vibration_button")) {
                    switchCompat.setChecked(b);
                    setWifiSearching(b);
                } else if (switchCompat.getTag().equals("wifi_searching")) {
                    switchCompat.setChecked(b);
                    setVibration(b);
                } else {
                    Log.w("SettingsAdapter", "Неизвестный тег");
                }
            }
        });

        layoutCc.setOnClickListener(v -> {
            if (layoutCc.getTag() == null) {
                return;
            }

            if (layoutCc.getTag().equals("wifi_layout")) {
                switchCompat.setChecked(!switchCompat.isChecked());
                setWifiSearching(switchCompat.isChecked());
            } else if (layoutCc.getTag().equals("vibration_layout")) {
                switchCompat.setChecked(!switchCompat.isChecked());
                setVibration(switchCompat.isChecked());
            } else {
                Log.w("SettingsAdapter", "Неизвестный тег 1");
            }
        });

        if (state.getTitle().equals("Рингтон") || state.getTitle().equals("Способ открытия уведомлений")) {
            switchCompat.setVisibility(View.GONE);
        }
        return view;
    }

    private void setVibration(boolean val) {
        SharedPreferences settings = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putBoolean("vibration", val);
        editor.apply();
        System.out.println(val);
    }

    private void setWifiSearching(boolean val) {
        SharedPreferences settings = getContext().getSharedPreferences("settings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();

        editor.putBoolean("wifi_searching", val);
        editor.apply();
        System.out.println(val);
    }
}
