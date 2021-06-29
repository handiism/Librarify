package com.handira.librarify.activity;

import android.content.DialogInterface;
import android.content.Intent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.handira.librarify.R;
import com.handira.librarify.adapter.MemberAdapter;
import com.handira.librarify.database.AppDatabase;
import com.handira.librarify.database.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    private Button btnAdd, btnRefresh;
    private AppDatabase database;
    private RecyclerView recyclerView;
    private MemberAdapter memberAdapter;
    private List<Member> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Membuat layar penuh
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Inisialisasi layout dan view
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);
        btnAdd = findViewById(R.id.btn_add);
        btnRefresh = findViewById(R.id.btn_refresh);

        // Memndapatkan database
        database = AppDatabase.getInstance(getApplicationContext());

        // Memindahkan database ke list
        list.clear();
        list.addAll(database.memberDao().getAll());

        // Menginisialisasi adapter dengan item berupa isi dari list
        memberAdapter = new MemberAdapter(this, database, list);
        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(
                getApplicationContext(), RecyclerView.VERTICAL, false);

        // mengatur adapter dan layout dari recycle view
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(memberAdapter);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, AddMember.class);
                startActivity(intent);
            }
        });
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (memberAdapter.getItemCount() > 0) {
                    onStart();
                } else {
                    Toast.makeText(Main.this, "Database Null", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    // Untuk merefresh database dan tampilan
    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.memberDao().getAll());
        memberAdapter.notifyDataSetChanged();
    }
}