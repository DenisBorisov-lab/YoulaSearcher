package com.denisbrisov.youlasearcher.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.denisbrisov.youlasearcher.R;
import com.denisbrisov.youlasearcher.models.request.Request;
import com.denisbrisov.youlasearcher.models.response.Item;
import com.denisbrisov.youlasearcher.models.response.Product;
import com.denisbrisov.youlasearcher.models.response.YoulaResponse;
import com.denisbrisov.youlasearcher.services.DataService;
import com.denisbrisov.youlasearcher.services.PostService;
import com.denisbrisov.youlasearcher.services.PostServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class AdvanceResultsActivity extends AppCompatActivity {

    private String url;
    private String location;
    private DataService dataService;
    private PostService postService;
    private List<PreviewState> states;
    private ListView products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advance_results);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle arguments = getIntent().getExtras();
        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
        location = settings.getString("location", "");
        url = arguments.getString("url");
        products = findViewById(R.id.preview_list);
        states = new ArrayList<>();


        String[] array = url.split("\\?");
        String attributes = "";
        String domain = array[0];
        if (array.length > 1) {
            attributes = array[1];
        }
        dataService = new DataService(domain, attributes, location);
        Request model = dataService.getModelRequest();

        postService = new PostServiceImpl(model);
        new Thread(new Runnable() {
            @Override
            public void run() {
                String stringObjects = postService.post();
                YoulaResponse objects = dataService.getResultedObject(stringObjects);
                Item[] items = objects.getData().getFeed().getItems();
                if (items.length == 1) {
                    return;
                }
                for (Item item : items) {
                    if (item.getProduct() != null) {
                        Product product = item.getProduct();
                        String productURL = product.getURL();
                        states.add(new PreviewState(product.getName(), product.getPrice().getRealPriceText(), product.getImages()[0].getURL(), productURL));
                        products.post(new Runnable() {
                            @Override
                            public void run() {
                                PreviewStateAdapter stateAdapter = new PreviewStateAdapter(AdvanceResultsActivity.this, R.layout.preview_list_item, states);
                                products.setAdapter(stateAdapter);
                                products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                    @Override
                                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                        PreviewState state = (PreviewState) adapterView.getItemAtPosition(i);
                                        String productURL = "https://youla.ru" + state.getURL();
                                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(productURL));
                                        startActivity(intent);
                                    }
                                });
                            }
                        });


                    }
                }
                System.out.println("ok");
            }
        }).start();

    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, SearchingTaskActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            Intent intent = new Intent(this, SearchingTaskActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
            setResult(RESULT_OK, intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
