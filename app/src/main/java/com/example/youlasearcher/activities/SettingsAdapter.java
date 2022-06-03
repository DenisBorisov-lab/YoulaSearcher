package com.example.youlasearcher.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.example.youlasearcher.R;

import java.util.List;

public class SettingsAdapter extends ArrayAdapter<SettingsState> {

    private List<SettingsState> states;
    private int layout;
    private LayoutInflater inflater;

    public SettingsAdapter(Context context, int resource, List<SettingsState> states){
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent){
        View view = inflater.inflate(this.layout, parent, false);

        TextView title = view.findViewById(R.id.settings_title);
        TextView subtitle = view.findViewById(R.id.settings_sub_title);
        SwitchCompat switchCompat = view.findViewById(R.id.switch_compat);

        SettingsState state = states.get(position);

        SharedPreferences settings = getContext().getSharedPreferences("NotificationSettings", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = settings.edit();
        editor.putBoolean("vibration", true);
        editor.putBoolean("wifi", false);
        editor.apply();

        title.setText(state.getTitle());
        switchCompat.setChecked(state.isChecked());
        subtitle.setText(state.getSubTitle());
        if(state.getTitle().equals("Вибрация")){
            switchCompat.setTag("vibration_button");
        }else if(state.getTitle().equals("Искать только по Wi-Fi")){
            switchCompat.setTag("wifi_searching");
        }

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (switchCompat.getTag().equals("vibration_button")){
//                    boolean result = !settings.getBoolean("vibration", true);
                    switchCompat.setChecked(b);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("vibration", b);
                    editor.apply();
                    System.out.println("Что-то поменялось");
                }else{
                    boolean result = !switchCompat.isChecked();
                    switchCompat.setChecked(result);
                    SharedPreferences.Editor editor = settings.edit();
                    editor.putBoolean("wifi_searching", result);
                    editor.apply();
                    System.out.println("Что-то поменялось");
                    notifyDataSetChanged();
                }
            }
        });

        if(state.getTitle().equals("Рингтон") || state.getTitle().equals("Способ открытия уведомлений")){
            switchCompat.setVisibility(View.GONE);
        }
        return view;
    }
}
