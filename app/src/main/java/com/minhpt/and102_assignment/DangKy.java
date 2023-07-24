package com.minhpt.and102_assignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class DangKy extends AppCompatActivity {
    EditText edt_username, edt_password;
    Button btn_sign_up;
    UserHelper userHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky);
        edt_username = findViewById(R.id.edt_username);
        edt_password = findViewById(R.id.edt_password);
        btn_sign_up = findViewById(R.id.btn_sign_up);

        userHelper = new UserHelper(this);

        btn_sign_up.setOnClickListener(v -> {
            String username = edt_username.getText().toString();
            String password = edt_password.getText().toString();

            if (!userHelper.isUsernameExists(username) && !password.isEmpty()) {
                userHelper.addUser(new User(username, password));
                Toast.makeText(this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(DangKy.this, DangNhap.class));
            } else if (username.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Vui lòng nhập thông tin!", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Tài khoản đã tồn tại!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}