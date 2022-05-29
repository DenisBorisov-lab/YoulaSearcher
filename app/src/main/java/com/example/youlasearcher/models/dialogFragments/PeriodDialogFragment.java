package com.example.youlasearcher.models.dialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.youlasearcher.interfaces.Changeable;

public class PeriodDialogFragment extends DialogFragment {
    private Changeable changeable;

    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        changeable = (Changeable) context;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final String[] periods = {"Каждую минуту", "Каждые 3 минуты", "Каждые 5 минут", "Каждые 10 минут", "Каждые 15 минут", "Каждые 30 минут", "Каждый час", "Каждые 2 часа", "Каждые 4 часа", "Каждые 6 часов", "Каждые 12 часов"};

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setItems(periods, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                changeable.change(periods[which]);
            }
        });

        return builder.create();
    }
}
