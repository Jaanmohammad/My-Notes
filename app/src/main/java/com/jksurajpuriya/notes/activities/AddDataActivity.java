package com.jksurajpuriya.notes.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.jksurajpuriya.notes.MyDatabase;
import com.jksurajpuriya.notes.MyEntity;
import com.jksurajpuriya.notes.MyRepository;
import com.jksurajpuriya.notes.R;
import com.jksurajpuriya.notes.databinding.ActivityAddDataBinding;

public class AddDataActivity extends AppCompatActivity {
    ActivityAddDataBinding binding;
    MyRepository repository;
    MyEntity entity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivityAddDataBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        repository = new MyRepository(MyDatabase.getInstance(getApplicationContext()).myDao());
        SetUpUpdateData();






    }


    private void UpdateDataToDatabase() {
        String titleA = binding.title.getText().toString();
        String descriptionA = binding.description.getText().toString();

        entity.setTitle(titleA);
        entity.setDescription(descriptionA);

        repository.updateData(entity);

        startActivity(new Intent(AddDataActivity.this,MainActivity.class));
        finish();

        binding.title.setText("");
        binding.description.setText("");
    }

    private void SetUpUpdateData(){
        Intent intent = getIntent();
        entity=intent.getParcelableExtra("jk");
        if(entity!=null){
            binding.title.setText(entity.getTitle());
            binding.description.setText(entity.getDescription());


        }

    }

    private void AddDataToRoomDatabase() {

        if (binding.title.length() <=0){
            binding.title.setError("Please Enter Title");
            return;
        }
        if (binding.description.length() <=0){
            binding.description.setError("Please Enter Description");
            return;
        }


        String titleA = binding.title.getText().toString();
        String descriptionA = binding.description.getText().toString();


        MyEntity entity = new MyEntity(titleA,descriptionA);

        repository.addData(entity);


        binding.title.setText("");
        binding.description.setText("");

        startActivity(new Intent(AddDataActivity.this, MainActivity.class));
        finish();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.save:

                if (entity==null){
                    AddDataToRoomDatabase();
                }else {

                    UpdateDataToDatabase();
                }

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}