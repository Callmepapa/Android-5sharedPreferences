package com.example.test5sharedpreferences;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText mUsernameTxt;
    EditText mPasswordTxt;
    Button mLoginBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mUsernameTxt = (EditText)findViewById(R.id.txtUsername);//获取账号编辑框
        mPasswordTxt = (EditText)findViewById(R.id.txtPassword);//获取账号编辑框
        mLoginBtn = (Button)findViewById(R.id.btnLogin);//获取登陆按钮

        //自动登陆
        //获取SharedPreferences对象
        SharedPreferences pref = getSharedPreferences(
                "user_credentials", MODE_PRIVATE);
        if (pref.contains("username") && pref.contains("password")) {
            boolean loginOk = logIn(pref.getString("username", ""),
                    //获取账号密码信息
                    pref.getString("password", ""));

            if (loginOk) {
                startProtectedAcvitity();
            }
        }

        //点击login手动登陆并储存账号密码
        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = mUsernameTxt.getText().toString();//获取输入账号
                String password = mPasswordTxt.getText().toString();//获取输入密码

                boolean loginOk = logIn(username, password);
                if (loginOk) {
                    //获取Editor对象
                    SharedPreferences pref = getSharedPreferences("user_credentials", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putString("username",username);//保存账号
                    editor.putString("password",password);//保存密码
                    editor.commit();//提交信息

                    Toast.makeText(getApplicationContext(),
                            "Login successful!",Toast.LENGTH_SHORT).show();
                    startProtectedAcvitity();//通过intent跳转界面
                }
                else
                {
                    Toast.makeText(getApplicationContext(),
                            "Credentials are not valid",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean logIn(String username, String password) {
        if (username.equals("DipSA") && password.equals("DipSA")) {
            return true;
        }
        return false;
    }

    private void startProtectedAcvitity() {
        Intent intent = new Intent(this, ProtectedActivity.class);
        startActivity(intent);
    }

}
