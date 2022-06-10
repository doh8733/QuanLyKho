package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity2 extends AppCompatActivity {
    private TextView tvView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        tvView = (TextView) findViewById(R.id.textView3);
        SharedPreferences sdf = getSharedPreferences("FILE_MODE",MODE_PRIVATE);
        String a =  sdf.getString("ROLE","");
        String b =sdf.getString("NAME","");
        tvView.setText(a+"  |"+b);
        tvView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity2.this,ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
}