package com.example.test5sharedpreferences;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProtectedActivity extends AppCompatActivity {

    TextView mInfoTxt;
    Button mLogoutBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protected);

        mInfoTxt = (TextView) findViewById(R.id.txtInfo);//获取Logout文本编辑框
        mLogoutBtn = (Button) findViewById(R.id.btnLogout);//获取logout按钮

        //获取SharedPreferences对象
        final SharedPreferences pref = getSharedPreferences("user_credentials", MODE_PRIVATE);
        //获取账号信息
        String username = pref.getString("username", "");
        mInfoTxt.setText("Hi, " + username +  "! All the best in the next exam!");

        //clear
        mLogoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = pref.edit();
                editor.clear();
                editor.commit();

                finish();
            }
        });
    }

}
