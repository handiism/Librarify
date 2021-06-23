package com.handira.librarify.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.handira.librarify.R;
import com.handira.librarify.activity.ViewMember;
import com.handira.librarify.database.entity.Member;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.ViewAdapter>{
    private List<Member> list;
    private Context context;
    private Dialog dialog;

    public interface Dialog {
        void onLongClick(int position);
    }

    public void setDialog(Dialog dialog) {
        this.dialog = dialog;
    }

    public MemberAdapter(Context context, List<Member> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewAdapter onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_member, parent, false);
        return new ViewAdapter(view);
    }

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
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewAdapter extends RecyclerView.ViewHolder {
        TextView tvName;
        public ViewAdapter(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tv_name);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), ViewMember.class);
                }
            });
            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    if (dialog!=null) {
                        dialog.onLongClick(getLayoutPosition());
                    }
                    return true;
                }
            });
        }
    }
}
