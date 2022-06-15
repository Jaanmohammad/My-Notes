package com.jksurajpuriya.notes.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.jksurajpuriya.notes.MyAdapter;
import com.jksurajpuriya.notes.MyDatabase;
import com.jksurajpuriya.notes.MyEntity;
import com.jksurajpuriya.notes.MyRepository;
import com.jksurajpuriya.notes.MyViewModel;
import com.jksurajpuriya.notes.R;
import com.jksurajpuriya.notes.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MyAdapter.OnDataItemClickListener {
     ActivityMainBinding binding;
     private MyRepository repository;
     private MyAdapter adapter;
     private ArrayList<MyEntity> arrayList;
     private MyViewModel myViewModel;
     
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();


        init();
        ShowData();



    }

    private void ShowData() {
        myViewModel=new ViewModelProvider(this, ViewModelProvider
                .AndroidViewModelFactory
                .getInstance(getApplication()))
                .get(MyViewModel.class);

        repository = new MyRepository(MyDatabase
                .getInstance(getApplicationContext()).myDao());

        myViewModel.allData.observe(this, new Observer<List<MyEntity>>() {
            @Override
            public void onChanged(List<MyEntity> myEntities) {
                arrayList.clear();
                adapter.notifyDataSetChanged();

                arrayList.addAll(myEntities);

                if (arrayList.isEmpty()){
                    binding.noData.setVisibility(View.VISIBLE);

                }

                adapter.notifyDataSetChanged();



            }
        });
    }

    private void init(){
        arrayList=new ArrayList<>();
        adapter=new MyAdapter(arrayList,this,this);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        binding.recyclerView.addItemDecoration(new DividerItemDecoration(
                this,DividerItemDecoration.VERTICAL));
        binding.recyclerView.setAdapter(adapter);

        binding.floatingActionButton.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, AddDataActivity.class));

        });
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Intent.ACTION_MAIN);
        intent.addCategory(Intent.CATEGORY_HOME);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void onDeleteClickListener(int position) {

        final AlertDialog.Builder alert= new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.dialog_box,null);

        TextView delete = view.findViewById(R.id.delete);
        TextView no = view.findViewById(R.id.no);

        alert.setView(view);

        final AlertDialog alertDialog = alert.create();
        alertDialog.setCanceledOnTouchOutside(true);


        delete.setOnClickListener(v -> {
            MyEntity entity = arrayList.get(position);
            repository.deleteData(entity);
            Toast.makeText(this, "Delete", Toast.LENGTH_SHORT).show();
            alertDialog.dismiss();

        });

        no.setOnClickListener(v -> {
            alertDialog.dismiss();
        });
        alertDialog.show();

    }

    @Override
    public void onEditClickListener(int position) {

        MyEntity entity = arrayList.get(position);
            Intent intent = new Intent(MainActivity.this,AddDataActivity.class);
            intent.putExtra("jk",entity);
            startActivity(intent);
        Toast.makeText(this, "Editing", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onViewClickListener(int position) {
        Toast.makeText(this, "view", Toast.LENGTH_SHORT).show();

    }

}