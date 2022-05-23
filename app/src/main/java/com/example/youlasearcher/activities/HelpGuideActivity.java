package com.example.youlasearcher.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.text.Html;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.youlasearcher.R;
import com.example.youlasearcher.models.dialogFragments.AsusGuideDialogFragment;
import com.example.youlasearcher.models.dialogFragments.GuideDialogFragment;
import com.example.youlasearcher.models.dialogFragments.MeizuGuideDialogFragment;
import com.example.youlasearcher.models.dialogFragments.XiaomiGuideDialogFragment;

import java.util.EventListener;

public class HelpGuideActivity extends AppCompatActivity {


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        TextView refactor_searching = findViewById(R.id.refactor_searching);
        Button recreateSearchingGuideBtn = findViewById(R.id.recreate_searching_guide_btn);
        Button meizuGuideBtn = findViewById(R.id.meizu_guide_btn);
        Button xiaomiGuideBtn = findViewById(R.id.xiaomi_guide_btn);
        Button asusGuideBtn = findViewById(R.id.asus_guide_btn);
        Button openSettingsBtn = findViewById(R.id.open_settings_btn);
        Button openGuideBtn = findViewById(R.id.open_guide_btn);

        recreateSearchingGuideBtn.setOnClickListener(view -> {
            // пересоздать поиск
            // TODO: 23.05.2022
        });

        meizuGuideBtn.setOnClickListener(view -> {
            // диалоговое  окно
            MeizuGuideDialogFragment meizuGuideDialogFragment = new MeizuGuideDialogFragment();
            meizuGuideDialogFragment.show(getSupportFragmentManager(), "meizu guide");
        });

        xiaomiGuideBtn.setOnClickListener(view -> {
            // диалогове окно
            XiaomiGuideDialogFragment xiaomiGuideDialogFragment = new XiaomiGuideDialogFragment();
            xiaomiGuideDialogFragment.show(getSupportFragmentManager(), "xiaomi guide");
        });

        asusGuideBtn.setOnClickListener(view -> {
            // диалогове окно
            AsusGuideDialogFragment asusGuideDialogFragment = new AsusGuideDialogFragment();
            asusGuideDialogFragment.show(getSupportFragmentManager(), "asus guide");
        });

        openSettingsBtn.setOnClickListener(view -> {
            // открыть настройки
            startActivity(new Intent(Settings.ACTION_IGNORE_BATTERY_OPTIMIZATION_SETTINGS));
        });

        openGuideBtn.setOnClickListener(view -> {
            // открыть инструкцию
            startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://dontkillmyapp.com/")));
        });



        refactor_searching.setText(Html.fromHtml("Если <strong>поиска работали</strong>, но через какое-то время перестали приходить оповещения, нажмите кнопку 'пересоздать поиски'."));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

}
