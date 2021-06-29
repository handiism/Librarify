package com.handira.librarify.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.handira.librarify.R;
import com.handira.librarify.tool.RandomIdGenerator;
import com.handira.librarify.database.AppDatabase;
import com.handira.librarify.database.entity.Member;

public class AddMember extends AppCompatActivity {
    private Button btnGenerate;
    private TextView tvIdInfo;
    private EditText etName, etDob, etAddress;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Membuat layar penuh
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(
                WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_member);

        // Inisialisai xml
        btnGenerate = findViewById(R.id.btn_generate);
        tvIdInfo = findViewById(R.id.tv_id_info);
        etName = findViewById(R.id.et_name);
        etDob = findViewById(R.id.et_dob);
        etAddress = findViewById(R.id.et_address);
        database = AppDatabase.getInstance(getApplicationContext());

        btnGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!etName.getText().toString().isEmpty()
                        && !etDob.getText().toString().isEmpty()
                        && !etAddress.getText().toString().isEmpty()) {
                        int numberId = RandomIdGenerator.getRandom();
                        tvIdInfo.setText(String.valueOf(numberId));
                        btnGenerate.setHint("Save Member");
                        btnGenerate.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Member member = new Member();
                                member.id = numberId;
                                member.name = etName.getText().toString();
                                member.dob = etDob.getText().toString();
                                member.address = etAddress.getText().toString();
                                database.memberDao().insertAll(member);
                                Intent intent = new Intent(AddMember.this, Main.class);
                                startActivity(intent);
                            }
                        });
                }
            }
        });
    }
}