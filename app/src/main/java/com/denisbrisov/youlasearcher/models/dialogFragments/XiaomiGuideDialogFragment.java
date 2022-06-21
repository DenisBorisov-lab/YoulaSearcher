package com.denisbrisov.youlasearcher.models.dialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.denisbrisov.youlasearcher.R;

public class XiaomiGuideDialogFragment extends DialogFragment {
    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("Инструкция")
                .setIcon(R.drawable.ic_info)
                .setMessage("Вам необходимо зайти в \"Настройки\" -> \"Батарея и производительность\" -> \"Расход заряда батареи приложениями\" -> Выбрать приложение -> найти \"Юла автопоиск\" -> в \"Настройках фоновой автивности\" установить \"Нет ограничений\". Также возможно необходимо будет включить уведомления в пунктах \"Уведомления\" и \"Автозапуск\"")
                .setPositiveButton("OK", null)
                .create();
    }
}
