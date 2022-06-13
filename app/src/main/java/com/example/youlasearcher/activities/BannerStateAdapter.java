package com.example.youlasearcher.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.TextView;

import androidx.appcompat.widget.SwitchCompat;

import com.example.youlasearcher.R;

import java.util.List;

public class BannerStateAdapter extends ArrayAdapter<BannerState> {
    private List<BannerState> states;
    private int layout;
    private LayoutInflater inflater;

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

        BannerState state = states.get(position);

        title.setText(state.getTitle());
        periodTitle.setText(state.getPeriodSubTitle());
        scheduleTitle.setText(state.getWorkSubTitle());
        switchCompat.setChecked(true);

        switchCompat.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

            }
        });

        return view;
    }

}
