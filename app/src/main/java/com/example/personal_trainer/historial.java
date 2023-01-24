package com.example.personal_trainer;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.personal_trainer.Adapter.Exercise;
import com.example.personal_trainer.Adapter.Exercise_Adapter;

import java.util.ArrayList;
import java.util.List;

public class historial extends AppCompatActivity {

    RecyclerView recyclerExercises;
    Exercise_Adapter exercise_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historial);
        initialize();
    }
    private void initialize(){
        recyclerExercises = findViewById(R.id.recyclerview);
        recyclerExercises.setLayoutManager(new LinearLayoutManager(this));

        List<Exercise> exerciseList= new ArrayList<>();
        for(int i = 0; i<20; i++){
            exerciseList.add(new Exercise(i,"Ejercicio","15:45","134"));
        }

        exercise_adapter = new Exercise_Adapter(exerciseList,this);

        recyclerExercises.setAdapter(exercise_adapter);
    }
}
