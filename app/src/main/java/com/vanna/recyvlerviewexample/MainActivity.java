package com.vanna.recyvlerviewexample;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private MyDatabase myDatabase;
    private WordAdapter wordAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        List<DictionaryItem> dictionaryItemList = new ArrayList<>();
        dictionaryItemList.add(new DictionaryItem("Apple", "a fruit"));
        dictionaryItemList.add(new DictionaryItem("Banana", "another favorite fruit of monkey"));

        myDatabase = new MyDatabase(getApplicationContext());
        wordAdapter = new WordAdapter();
        recyclerView.setAdapter(wordAdapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        EditText editText = (EditText) findViewById(R.id.searchText);
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.d("Search", "onTextChanged " + s.toString());
                search(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        search("");
    }

    private void search(String word) {
        String[] selectionArgs = { word + "%"};
        Cursor cursor = myDatabase.getReadableDatabase().rawQuery("SELECT * FROM Dictionary WHERE word LIKE ? LIMIT 100", selectionArgs);
        wordAdapter.setCursor(cursor);
    }
}
