package com.vanna.recyvlerviewexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class PopupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup);

        String definition = getIntent().getStringExtra("definition");

        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(definition);
    }
}
