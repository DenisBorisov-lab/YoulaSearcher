package com.example.youlasearcher.models.dialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TimePicker;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.youlasearcher.R;
import com.example.youlasearcher.interfaces.ChangeableTime;

import java.util.Calendar;

public class TimePickerDialogFragment extends DialogFragment {
    private ChangeableTime changeable;
    private Calendar dateAndTime = Calendar.getInstance();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        changeable = (ChangeableTime) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder
                = new AlertDialog.Builder(getActivity());
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
                        String time = DateUtils.formatDateTime(getActivity(),
                                dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);
                        start.setText(time);
                    }
                };


                TimePickerDialog tp = new TimePickerDialog(getActivity(), t,
                        dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE), true);
                tp.show();

            }
        });

        EditText end = customLayout.findViewById(R.id.end);
        end.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog.OnTimeSetListener t = new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int i, int i1) {
                        dateAndTime.set(Calendar.HOUR_OF_DAY, i);
                        dateAndTime.set(Calendar.MINUTE, i1);
                        String time = DateUtils.formatDateTime(getActivity(),
                                dateAndTime.getTimeInMillis(), DateUtils.FORMAT_SHOW_TIME);

                        end.setText(time);
                    }
                };
                new TimePickerDialog(getActivity(), t,
                        dateAndTime.get(Calendar.HOUR_OF_DAY),
                        dateAndTime.get(Calendar.MINUTE), true)
                        .show();
            }
        });

        CheckBox checkBox = customLayout.findViewById(R.id.round_the_lock);

        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()) {
                    start.setEnabled(false);
                    end.setEnabled(false);
                    start.setHintTextColor(null);
                    end.setHintTextColor(null);
                } else {
                    start.setHintTextColor(Color.parseColor("#FF000000"));
                    end.setHintTextColor(Color.parseColor("#FF000000"));
                    start.setEnabled(true);
                    end.setEnabled(true);
                }
            }
        });

        String subtitle = getArguments().getString("subtitle");
        if (subtitle.equals("Круглосуточно")) {
            checkBox.setChecked(true);
            start.setEnabled(false);
            end.setEnabled(false);
        } else {
            String[] s = subtitle.split(" ");
            String a = s[1];
            String b = s[3];
            start.setText(a);
            end.setText(b);
        }

        return builder.setPositiveButton("Применить", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String[] result = new String[2];
                        if (checkBox.isChecked()) {
                            result[0] = "Круглосуточно";
                        } else {
                            String startText = start.getText().toString();
                            String endText = end.getText().toString();
                            if (startText.length() == 0) {
                                startText = start.getHint().toString();
                            }

                            if (endText.length() == 0) {
                                endText = end.getHint().toString();
                            }
                            result[0] = startText;
                            result[1] = endText;
                        }
                        changeable.changeTime(result);
                    }
                })
                .create();
    }

}
