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

import com.handira.librarify.R;
import com.handira.librarify.adapter.MemberAdapter;
import com.handira.librarify.database.AppDatabase;
import com.handira.librarify.database.entity.Member;

import java.util.ArrayList;
import java.util.List;

public class Main extends AppCompatActivity {
    private Button btnAdd;
    private RecyclerView recyclerView;
    private AppDatabase database;
    private MemberAdapter memberAdapter;
    private List<Member> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recycler_view);
        btnAdd = findViewById(R.id.btn_add);

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.userDao().getAll());
        memberAdapter = new MemberAdapter(getApplicationContext(), list);
        memberAdapter.setDialog(new MemberAdapter.Dialog() {
            @Override
            public void onLongClick(int position) {
                final CharSequence[] dialogItem = {"Delete"};
                dialog = new AlertDialog.Builder(Main.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 0:
                                Member member = list.get(position);
                                database.userDao().delete(member);
                                onStart();
                                break;
                            default:
                                break;
                        }
                    }
                });
                dialog.show();
            }
        });

        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(
                getApplicationContext(), RecyclerView.VERTICAL, false);

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(memberAdapter);

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Main.this, AddMember.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.userDao().getAll());
        memberAdapter.notifyDataSetChanged();
    }
}