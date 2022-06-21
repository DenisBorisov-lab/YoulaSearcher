package com.denisbrisov.youlasearcher.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.denisbrisov.youlasearcher.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class PreviewStateAdapter extends ArrayAdapter<PreviewState> {
    private LayoutInflater inflater;
    private int layout;
    private List<PreviewState> states;

    public PreviewStateAdapter(Context context, int resource, List<PreviewState> states) {
        super(context, resource, states);
        this.states = states;
        this.layout = resource;
        this.inflater = LayoutInflater.from(context);
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View view = inflater.inflate(this.layout, parent, false);

        ImageView imageView = view.findViewById(R.id.preview_image);
        TextView nameView = view.findViewById(R.id.preview_name);
        TextView priceView = view.findViewById(R.id.preview_price);

        PreviewState state = states.get(position);

        Picasso.get().load(state.getImageURL()).into(imageView);
        nameView.setText(state.getName());
        priceView.setText(state.getPrice());

        return view;
    }
}
