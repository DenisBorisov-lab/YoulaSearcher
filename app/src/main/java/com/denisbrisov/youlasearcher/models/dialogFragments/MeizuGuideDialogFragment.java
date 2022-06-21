package com.denisbrisov.youlasearcher.models.dialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.denisbrisov.youlasearcher.R;

public class MeizuGuideDialogFragment extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("Инструкция")
                .setIcon(R.drawable.ic_info)
                .setMessage("Вам требуется добавить программу в исключения. Для этого зайдите в \"Настройки\" -> \"Безопасность\" -> \"Управление питанием\" -> \"Управление спящим режимом\".")
                .setPositiveButton("OK", null)
                .create();
    }

}
