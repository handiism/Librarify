package com.handira.librarify.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.handira.librarify.R;
import com.handira.librarify.database.AppDatabase;
import com.handira.librarify.database.entity.Member;

public class ViewMember extends AppCompatActivity {
    private Button btnEdit;
    private TextView tvIdInfo, tvNameInfo, tvDobInfo, tvAddressInfo;
    private AppDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_view_member);

        tvIdInfo = findViewById(R.id.tv_id_info);
        tvNameInfo = findViewById(R.id.tv_name_info);
        tvDobInfo = findViewById(R.id.tv_dob_info);
        tvAddressInfo = findViewById(R.id.tv_address_info);
        btnEdit = findViewById(R.id.btn_edit);

        database = AppDatabase.getInstance(getApplicationContext());

        Intent getIntent = getIntent();

        getIntent.getIntExtra("id", 0);
        Member member = database.userDao().get(getIntent.getIntExtra("id", 0));

        tvIdInfo.setText(String.valueOf(member.id));
        tvNameInfo.setText(member.name);
        tvDobInfo.setText(member.dob);
        tvAddressInfo.setText(member.address);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewMember.this, EditMember.class);
                intent.putExtra("id",member.id);
                startActivity(intent);
            }
        });
    }
}