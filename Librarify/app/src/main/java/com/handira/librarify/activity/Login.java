package com.handira.librarify.activity;

import android.content.Intent;
import android.net.Uri;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.handira.librarify.R;

public class Login extends AppCompatActivity {
    EditText etUsername, etPassword;
    Button btnLogin, btnHelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // to make fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_login);

        etUsername = findViewById(R.id.et_uname);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        btnHelp = findViewById(R.id.btn_help);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (etUsername.getText().toString().equals("admin")
                    && etPassword.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Login Succes",
                            Toast.LENGTH_SHORT).show();
                    Intent move = new Intent(Login.this, Main.class);
                    startActivity(move);
                } else if (!etUsername.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Username Not Registered :(",
                            Toast.LENGTH_SHORT).show();
                } else if (etUsername.getText().toString().equals("admin")
                        && !etPassword.getText().toString().equals("admin")) {
                    Toast.makeText(getApplicationContext(),
                            "Incorrect Password :(",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Intent.ACTION_VIEW,
                        Uri.parse("https://github.com/handirachmawan/Librarify#readme")));
            }
        });
    }
}