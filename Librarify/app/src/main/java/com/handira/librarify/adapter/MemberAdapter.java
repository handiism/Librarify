package com.handira.librarify.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.handira.librarify.R;
import com.handira.librarify.activity.ViewMember;
import com.handira.librarify.database.AppDatabase;
import com.handira.librarify.database.entity.Member;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewAdapter> {
    private List<Member> list;
    private Context context;
    private AppDatabase appDatabase;

    public MemberAdapter(Context context, AppDatabase appDatabase, List<Member> list) {
        this.context = context;
        this.list = list;
        this.appDatabase = appDatabase;
    }

    // Menginisialisasi view
    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_member, parent, false);
        return new ViewAdapter(view);
    }

    // Membuat aksi untuk setiap item di view
    @Override
    public void onBindViewHolder(@NonNull MemberAdapter.ViewAdapter holder, int position) {
        holder.tvName.setText(list.get(position).name);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewMember.class);
                intent.putExtra("id", list.get(position).id);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);
            }
        });
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Delete");
                builder.setMessage("Do you really want to delete this data?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context
                                , "Member deleted, please press refresh button!"
                                , Toast.LENGTH_SHORT).show();
                        Member member = list.get(position);
                        appDatabase.memberDao().delete(member);
                    }
                });
                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(context, "Delete canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                AlertDialog alertDialog = builder.create();
                alertDialog.show();
                return true;
            }
        });
    }

    // Mendapatkan jumlah database (list)
    @Override
    public int getItemCount() {
        return list.size();
    }

    // Inisisalisasi viewadapter untuk onBindViewHolder
    class ViewAdapter extends RecyclerView.ViewHolder {
        TextView tvName;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
        }
    }
}
