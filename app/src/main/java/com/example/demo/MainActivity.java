package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class MainActivity extends AppCompatActivity {
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



    }

    /*public void userNameCall() {
        sendUserName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DisplayMessageActivity.class);
                String userName = getUserName.getText().toString();
                intent.putExtra(EXTRA_MESSAGE, userName);
                startActivity(intent);
            }
        });
    }*/

    /** Called when the user taps the Send button */
    public void sendMessage(View view) { //sendMessage是否可以改為其他名稱?
        Intent intent = new Intent(this, DisplayMessageActivity.class);
        EditText editText = (EditText) findViewById(R.id.getUserName); //建立EditText物件 why 前面需要 (EditText)
        String message = editText.getText().toString(); // 將editText中輸入的資料轉為字串存入變數message中
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }
}
