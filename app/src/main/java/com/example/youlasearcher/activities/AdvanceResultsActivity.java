package com.example.youlasearcher.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.youlasearcher.R;
import com.example.youlasearcher.models.request.Request;
import com.example.youlasearcher.models.response.Item;
import com.example.youlasearcher.models.response.Product;
import com.example.youlasearcher.models.response.YoulaResponse;
import com.example.youlasearcher.services.DataService;
import com.example.youlasearcher.services.PostService;
import com.example.youlasearcher.services.PostServiceImpl;

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

        Bundle arguments = getIntent().getExtras();
        SharedPreferences settings = getSharedPreferences("settings", MODE_PRIVATE);
        location = settings.getString("location", null);
        url = arguments.get("url").toString();
        products = findViewById(R.id.preview_list);


        String[] array = url.split("\\?");
        String attributes = "";
        String domain = array[0];
        if (array.length > 1){
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
                for(Item item : items){
                    if (item.getProduct() != null){
                        Product product = item.getProduct();
                        String productURL = "https://youla.ru" + product.getURL();
                        states.add(new PreviewState(product.getName(), product.getPrice().getRealPriceText(), product.getImages()[0].getURL(), productURL));

                    }
                }
                System.out.println("ok");
            }
        }).start();
        PreviewStateAdapter adapter = new PreviewStateAdapter(this, R.layout.preview_list_item, states);
        products.setAdapter(adapter);
        products.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PreviewState state = (PreviewState) adapterView.getItemAtPosition(i);
                state.getURL();
            }
        });

    }
}
