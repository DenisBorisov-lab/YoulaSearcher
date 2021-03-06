package com.denisbrisov.youlasearcher.activities;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.webkit.CookieManager;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.denisbrisov.youlasearcher.R;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class SearchingSettingsActivity extends AppCompatActivity {
    private WebView webView;
    private ProgressBar progressBar;
    private Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searching_settings);
        webView = findViewById(R.id.web_view);

        Bundle extras = getIntent().getExtras();
        String link = extras.getString("url");

        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowContentAccess(true);
        settings.setDomStorageEnabled(true);
        webView.setLayerType(View.LAYER_TYPE_SOFTWARE, null);
        if (link.equals("Нажмите для настройки")){
            link = "https://youla.ru/";
        }
        webView.loadUrl(link);


        webView.setWebViewClient(new MyWebViewClient());

        progressBar = findViewById(R.id.progressBar);
        saveBtn = findViewById(R.id.save_settings);

        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(webView, url);
                String cookies = CookieManager.getInstance().getCookie(url);
                String[] attributes = cookies.split("; ");
                String location = "";
                for (String attribute : attributes) {
                    if (attribute.split("=")[0].equals("location")) {
                        location = attribute.split("=")[1];
                    }
                }
                try {
                    String res = java.net.URLDecoder.decode(location, StandardCharsets.UTF_8.name());
                    SharedPreferences sharedPreferences = getSharedPreferences("settings", MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString("location", res);
                    editor.apply();
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
            }
        });

        saveBtn.setOnClickListener(view -> {
            String url = webView.getUrl();
            Intent intent = new Intent(this, SearchingTaskActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            intent.putExtra(SearchingTaskActivity.URL, url);
            setResult(RESULT_OK, intent);
            finish();
        });


    }

    @Override
    public void onBackPressed() {
        if (webView.canGoBack()) {
            webView.goBack();
        } else {
            super.onBackPressed();
        }
    }

    private class MyWebViewClient extends WebViewClient {
        @TargetApi(Build.VERSION_CODES.N)
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
            view.loadUrl(request.getUrl().toString());
            return true;
        }

        // Для старых устройств
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
            progressBar.setVisibility(View.GONE);
        }

    }
}
