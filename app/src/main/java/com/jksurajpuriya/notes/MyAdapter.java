package com.jksurajpuriya.notes;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.jksurajpuriya.notes.activities.AllDataShowActivity;
import com.jksurajpuriya.notes.databinding.RowItemBinding;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<MyEntity> arrayList;
    Context context;
    OnDataItemClickListener clickListener;


    public MyAdapter(ArrayList<MyEntity> arrayList, Context context, OnDataItemClickListener clickListener) {
        this.arrayList = arrayList;
        this.context = context;
        this.clickListener = clickListener;
    }

    public interface OnDataItemClickListener{
        void onDeleteClickListener(int position);
        void onEditClickListener(int position);
        void onViewClickListener(int position);

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.row_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull  MyViewHolder  holder, int position) {
        MyEntity data = arrayList.get(position);
        if (data==null){
            return;
        }
        

        holder.binding.rowTitle.setText(data.getTitle());
        holder.binding.rowDescription.setText(data.getDescription());

        holder.binding.rel.setOnLongClickListener(v -> {

            clickListener.onViewClickListener(position);
            return false;
        });

        holder.binding.edit.setOnClickListener(v -> {
            clickListener.onEditClickListener(position);
        });

        holder.binding.delete.setOnClickListener(v -> {
            clickListener.onDeleteClickListener(position);
        });

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, AllDataShowActivity.class);
            intent.putExtra("title",arrayList.get(position).getTitle());
            intent.putExtra("description",arrayList.get(position).getDescription());
            context.startActivity(intent);
        });

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        RowItemBinding binding;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            binding=RowItemBinding.bind(itemView);
        }
    }
}
