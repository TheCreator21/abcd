package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        final EditText editText1 = (EditText)findViewById(R.id.EditText1);
        editText1.setHint("Username");//display the hint
        final EditText editText2 = (EditText)findViewById(R.id.EditText2);
        editText2.setHint("Password");//display the hint
        final Button button = findViewById(R.id.LoginButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if(isValid(editText1.getText().toString(),editText2.getText().toString())) {
                    Intent myIntent = new Intent(LoginActivity.this, MainActivity.class);
                    myIntent.putExtra("user",editText1.getText().toString());
                    LoginActivity.this.startActivity(myIntent);
                }
            }
        });
    }

    public boolean isValid(String user, String Pass){
       return user.equals(Pass)?true:false;
    }
}
