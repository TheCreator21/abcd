package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.util.Random;

public class GameActivity extends AppCompatActivity {
private long time = 3000;//intailize time to play 3000 miliseconds
private int clickCount = 0;//count for clicks
private boolean gameDone = false;//indicate if game is still running
    private float minX = 30;
    private final float minY = 30;
    private String logs= "";
    private String name="";
    CountDownTimer dd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        logs =" "+ name+" started a new game :  ";
        SharedPreferences prefs = getSharedPreferences("logs", Context.MODE_PRIVATE);
        final SharedPreferences.Editor editor = prefs.edit();
        logs = prefs.getString("logs","")+"\n"+ name+"started anew game :  ";
        Intent intent = getIntent();
        name = intent.getStringExtra("user");
        final TextView CountDownTextField = (TextView) findViewById(R.id.CountDownText);
//        final TextView ddd = (TextView) findViewById(R.id.t);
        final TextView ClickCountTextField = (TextView) findViewById(R.id.ClickCountText);
        ClickCountTextField.setText(Integer.toString(clickCount));
        final Button button = (Button) findViewById(R.id.dot);
         dd = new CountDownTimer(time*(int)(Math.pow(0.95,clickCount)), 100) {

            public void onTick(long millisUntilFinished) {
                CountDownTextField.setText("miliseconds remaining: " + millisUntilFinished);
            }

            public void onFinish() {
                CountDownTextField.setText("done!");
                button.setClickable(false);
                editor.putString("logs", logs);
                editor.commit();
                Intent myIntent = new Intent(GameActivity.this, MainActivity.class);
                myIntent.putExtra("score",clickCount);
                GameActivity.this.startActivity(myIntent);

            }
        }.start();

        final DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                clickCount++;
                ClickCountTextField.setText(Integer.toString(clickCount));
                logs+="Click number: "+clickCount+" x: "+ button.getX()+" y: "+button.getY()+",";
                dd.cancel();

                if(time>100) {
                    time = (long) (time * 0.95);
//                    ddd.append("   "+time);
                }
                Random R1 = new Random();
                float dx = Math.abs(R1.nextFloat() *R1.nextFloat()* (displaymetrics.widthPixels*((float)0.4)));

                float dy = Math.abs(R1.nextFloat() * R1.nextFloat()* displaymetrics.heightPixels*((float)0.4));



                button.animate()
                        .x(dx)
                        .y(dy)
                        .setDuration(0)
                        .start();
                dd = new CountDownTimer(time, 100) {//decrease timer by 5%

                    public void onTick(long millisUntilFinished) {
                        CountDownTextField.setText("miliseconds remaining: " + millisUntilFinished);
                    }

                    public void onFinish() {
                        CountDownTextField.setText("done!");
                        button.setClickable(false);
                        editor.putString("logs", logs);
                        editor.commit();
                        Intent myIntent = new Intent(GameActivity.this, Records.class);
                        myIntent.putExtra("score",clickCount);
                        myIntent.putExtra("user",name);
                        myIntent.putExtra("result","game");
                        GameActivity.this.startActivity(myIntent);

                    }
                }.start();
            }

        });

    }


}
