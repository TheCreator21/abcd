package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LogsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logs);
        SharedPreferences prefs = getSharedPreferences("logs", Context.MODE_PRIVATE);
        final TextView logsView= (TextView) findViewById(R.id.logsView);
        logsView.setMovementMethod(new ScrollingMovementMethod());
        logsView.setText(prefs.getString("logs",""));
        final Button button = findViewById(R.id.LogsToMainBtn);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    Intent myIntent = new Intent(LogsActivity.this, MainActivity.class);
                    LogsActivity.this.startActivity(myIntent);

            }
        });
    }
}
