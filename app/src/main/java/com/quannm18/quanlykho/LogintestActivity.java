package com.quannm18.quanlykho;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class LogintestActivity extends AppCompatActivity {
    private EditText edtuser;
    private EditText edtpass;
    private Button btnlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logintest);

        edtuser = (EditText) findViewById(R.id.user);
        edtpass = (EditText) findViewById(R.id.pass);
        btnlogin = (Button) findViewById(R.id.login);





        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user = edtuser.getText().toString();
                String pass = edtuser.getText().toString();

                if (user.equalsIgnoreCase("admin")&& pass.equalsIgnoreCase("admin")){
                    SharedPreferences sdf = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                    SharedPreferences.Editor editor;
                    editor = sdf.edit();
                    editor.putString("USER",user);
                    editor.commit();
                    Intent i = new Intent(LogintestActivity.this,AdminActivity.class);
                    startActivity(i);


                }
                if (user.equalsIgnoreCase("123")&& pass.equalsIgnoreCase("123")){
                    SharedPreferences sdf = getSharedPreferences("USER_FILE", MODE_PRIVATE);
                    SharedPreferences.Editor editor;
                    editor = sdf.edit();
                    editor.putString("USER",user);
                    editor.commit();
                    Intent i = new Intent(LogintestActivity.this,NhanVienActivity.class);
                    startActivity(i);


                }
            }
        });
    }

}