package com.vanna.recyvlerviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        List<DictionaryItem> dictionaryItemList = new ArrayList<>();
        dictionaryItemList.add(new DictionaryItem("Apple", "a fruit"));
        dictionaryItemList.add(new DictionaryItem("Banana", "another favorite fruit of monkey"));

        WordAdapter wordAdapter = new WordAdapter(dictionaryItemList);
        recyclerView.setAdapter(wordAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }
}
