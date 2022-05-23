package com.example.youlasearcher.models.dialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.example.youlasearcher.R;

public class GuideDialogFragment extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        LayoutInflater inflater = getLayoutInflater();
        View dialogView = inflater.inflate(R.layout.guide_dialog, null);


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("Руководство")
                .setView(dialogView)
                .setPositiveButton("OK", null)
                .create();
    }
}
