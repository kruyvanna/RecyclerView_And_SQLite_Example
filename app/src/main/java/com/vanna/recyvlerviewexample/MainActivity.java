package com.vanna.recyvlerviewexample;

import android.content.Intent;
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
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        myDatabase = new MyDatabase(getApplicationContext());
        wordAdapter = new WordAdapter();
        recyclerView.setAdapter(wordAdapter);

//        writeToDatabaseExample();
        updateRecordExample();
        readExample();

        wordAdapter.setAdapterListener(new AdapterListener() {
            @Override
            public void onItemClick(int position) {
                Log.d("MainActivity", "onItemClick " + position);
                cursor.moveToPosition(position);
                String word = cursor.getString(0);
                String definition = cursor.getString(1);
                Log.d("MainActivity", "word: " + word + ", definition: " + definition);

                Intent intent = new Intent(getApplicationContext(), DetailActivity.class);
                intent.putExtra("word", word);
                intent.putExtra("definition", definition);
                startActivity(intent);
            }
        });

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
        cursor = myDatabase.getReadableDatabase().rawQuery("SELECT * FROM Dictionary WHERE word LIKE ? LIMIT 100", selectionArgs);
        wordAdapter.setCursor(cursor);
    }

    private void readExample() {
        String[] args = {};
        MyDatabase database = new MyDatabase(getApplicationContext());
        Cursor cursor = database.getReadableDatabase().rawQuery("SELECT * FROM Dictionary", args);

        // loop all row
        while(!cursor.isLast()) {
            cursor.moveToNext();
            String word = cursor.getString(0); // column 0 is word
            String definition = cursor.getString(1); //column 1 is definition
            Log.d("Database", "word: " + word + ", definition: " + definition);
        }
    }

    private void writeToDatabaseExample() {
        MyDatabase database = new MyDatabase(getApplicationContext());
        String[] data = {"Cat", "A Pet"};
        database.getWritableDatabase().execSQL("INSERT INTO Dictionary VALUES ( ?, ? )", data);
        Log.d("Database", "added cat record");
    }

    private void updateRecordExample() {
        MyDatabase database = new MyDatabase(getApplicationContext());
        String[] data = {"A PET UPDATED", "Cat"};
        database.getWritableDatabase().execSQL("UPDATE Dictionary SET definition = ? WHERE word = ?", data);
        Log.d("Database", "updated cat definition to A PET UPDATED");
    }
}
