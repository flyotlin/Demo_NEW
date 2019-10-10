package com.example.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class DisplayMessageActivity extends AppCompatActivity {

    TextView userName;
    SharedPreferences pref;
    ArrayAdapter arrayAdapter;
    ListView objectView;
    EditText getObjectName;
    Button sendObjectName;
    ArrayList<String> list = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);

        init();
        readData();
        setUserName();
        sendButton();

    }

    public void init() { //初始化
        userName = (TextView) findViewById(R.id.userName); //建立TextView物件
        objectView = (ListView) findViewById(R.id.objectView);
        getObjectName = (EditText) findViewById(R.id.getObjectName);
        sendObjectName = (Button) findViewById(R.id.sendObjectName);

    }

    public void setUserName() { //設定使用者名稱
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        saveUserName(message);

        if(message.equals("") ){
            pref = getSharedPreferences("userName", MODE_PRIVATE);
            String name = pref.getString("user", " ");
            userName.setText(name);
        }
        else
            userName.setText("User:" + message);
    }
    public void saveUserName(String str){ //儲存使用者名稱
        pref = getSharedPreferences("userName", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("user", str);
        editor.apply();

    }

    public void sendButton() { //按按鈕
        sendObjectName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addList();
            }
        });
    }

    public void addList() { //新增物件
        list.add(getObjectName.getText().toString());
        saveData();
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        arrayAdapter.notifyDataSetChanged();
        objectView.setAdapter(arrayAdapter);
        //getObjectName.setTextColor(0xFF32A14B);
        getObjectName.setText("");
    }

    public void saveData() { //儲存資料
        pref = getSharedPreferences("objectName", MODE_PRIVATE);
        SharedPreferences.Editor editor = pref.edit();
        editor.putInt("size", list.size());
        editor.commit();

        for(int i = 0; i < list.size(); i++){
            editor.putString("str"+i, list.get(i));
            editor.commit();
        }
    }

    public void readData() { //讀取資料
        pref = getSharedPreferences("objectName", MODE_PRIVATE);
        int size = pref.getInt("size", 0);

        for(int i = 0; i < size; i++) {
            list.add(pref.getString("str"+i, ""));

        }
        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list);
        arrayAdapter.notifyDataSetChanged();
        objectView.setAdapter(arrayAdapter);
        getObjectName.setText("");
    }
}
