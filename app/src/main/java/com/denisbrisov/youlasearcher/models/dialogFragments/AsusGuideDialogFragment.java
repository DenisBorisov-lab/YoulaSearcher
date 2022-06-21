package com.denisbrisov.youlasearcher.models.dialogFragments;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import com.denisbrisov.youlasearcher.R;

public class AsusGuideDialogFragment extends DialogFragment {

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        return builder.setTitle("Инструкция")
                .setIcon(R.drawable.ic_info)
                .setMessage("У Asus есть встроенный набор программ, в числе которых присутствует \"Мобильный диспетчер\". В нём есть пункт \"Менеджер автозапуска\", в котором по умолчанию все приложения стоят в запрещённых. Для работы приложения нужно выключить для него запрет.")
                .setPositiveButton("OK", null)
                .create();
    }

}
