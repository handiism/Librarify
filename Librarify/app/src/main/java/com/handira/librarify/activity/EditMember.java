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
import com.handira.librarify.database.AppDatabase;
import com.handira.librarify.database.entity.Member;

public class EditMember extends AppCompatActivity {
    private Button btnSave;
    private TextView tvIdInfo;
    private EditText etName, etDob, etAddress;
    private Member member;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_edit_member);

        tvIdInfo = findViewById(R.id.tv_id_info);
        etName = findViewById(R.id.et_name);
        etDob = findViewById(R.id.et_dob);
        etAddress = findViewById(R.id.et_address);
        btnSave = findViewById(R.id.btn_save);

        database = AppDatabase.getInstance(getApplicationContext());
        Intent getIntent = getIntent();
        getIntent.getIntExtra("id",0);
        member = database.memberDao().get(getIntent.getIntExtra("id", 0));

        tvIdInfo.setText(String.valueOf(member.id));
        etName.setText(member.name);
        etDob.setText(member.dob);
        etAddress.setText(member.address);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EditMember.this, Main.class);
                if (!etName.getText().toString().isEmpty()
                        && !etDob.getText().toString().isEmpty()
                        && !etAddress.getText().toString().isEmpty()
                ) {
                    database.memberDao().update(member.id
                            , etName.getText().toString()
                            , etDob.getText().toString()
                            , etAddress.getText().toString());
                    startActivity(intent);
                }
            }
        });
    }
}