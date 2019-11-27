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

import java.util.Calendar;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Records extends AppCompatActivity {
private int score = 0;
private String name ="";
private String result="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_records);
        Intent intent = getIntent();
        name = intent.getStringExtra("user");
        result = intent.getStringExtra("result");
        SharedPreferences prefs = getSharedPreferences("scores5", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        final Button button = findViewById(R.id.RecordsToMainBtn);
        TextView scoreView = (TextView)findViewById(R.id.scroeView);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent myIntent = new Intent(Records.this, MainActivity.class);
                myIntent.putExtra("user", name);
                Records.this.startActivity(myIntent);

            }
        });
        if (result.equals("game")) {
            score = intent.getIntExtra("score", 0);
            scoreView.setText("your score: "+score);
            String scoreString = prefs.getString(name, null);
            if (scoreString != null) {//player allready exsits
                scoreString = scoreString + score + ",";
                editor.putString(name, scoreString);
                editor.commit();
            } else {//new player
                scoreString = score + ",";
                editor.putString(name, scoreString);
                editor.commit();
            }
        }

        TextView table = (TextView) findViewById(R.id.table);
        table.setMovementMethod(new ScrollingMovementMethod());
        Map<String, ?> keys = prefs.getAll();

        for (Map.Entry<String, ?> entry : keys.entrySet()) {

            String str = entry.getValue().toString();

            int sLen = str.length();
            for (int i = 0; i < sLen; i++) {

                    if (str.charAt(i) != ',') {
                        if(entry.getKey()!=null) {
                            table.append("name: " + entry.getKey() + "         score:     " + str.charAt(i) + "\n");
                        }
                    }

            }
        }


    }
}



