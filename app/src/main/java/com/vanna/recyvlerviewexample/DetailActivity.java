package com.vanna.recyvlerviewexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String word = intent.getStringExtra("word");
        String definition = intent.getStringExtra("definition");

        TextView wordTextView = (TextView) findViewById(R.id.wordTextView);
        wordTextView.setText(word);

        TextView textView = (TextView) findViewById(R.id.definitionTextView);
        textView.setText(definition);


    }
}
