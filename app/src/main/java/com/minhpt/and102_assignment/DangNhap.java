package com.minhpt.and102_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

public class DangNhap extends AppCompatActivity {
    EditText edt_username, edt_password;
    Button btn_login;
    TextView tv_sign_up;
    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_nhap);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        tv_sign_up = findViewById(R.id.tv_sign_up);
        btn_login = findViewById(R.id.btn_login);

        userHelper = new UserHelper(this);

        btn_login.setOnClickListener(v -> {
            String username = edt_username.getText().toString();
            String password = edt_password.getText().toString();
            User currentUser = userHelper.checkAcc(new User(username, password));
            if (currentUser != null) {
                Toast.makeText(this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DangNhap.this, QuanLySP.class));
            } else {
                Toast.makeText(this, "Tài khoản hoặc mật khẩu sai!", Toast.LENGTH_SHORT).show();
            }
        });
        tv_sign_up.setOnClickListener(v -> {
            startActivity(new Intent(DangNhap.this, DangKy.class));
        });
    }
}