package com.denisbrisov.youlasearcher.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.denisbrisov.youlasearcher.R;

import java.util.List;

public class StateAdapter extends ArrayAdapter<State> {
    private LayoutInflater inflater;
    private int layout;
    private List<State> states;

    public StateAdapter(Context context, int resource, List<State> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView flagView = view.findViewById(R.id.symbol);
        TextView nameView = view.findViewById(R.id.title);
        TextView capitalView = view.findViewById(R.id.subtitle);

        State state = states.get(position);

        flagView.setImageResource(state.getSymbolResource());
        nameView.setText(state.getTitle());
        capitalView.setText(state.getSubTitle());

        return view;
    }

}
