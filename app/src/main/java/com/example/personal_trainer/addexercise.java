package com.example.personal_trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class addexercise extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexercise);

        Spinner spinner = (Spinner) findViewById(R.id.exercise_spinner);
        EditText ejercicio = findViewById(R.id.ejercicio);
        EditText calorias = findViewById(R.id.calorias);
        EditText fecha = findViewById(R.id.fecha);
        EditText duracion = findViewById(R.id.duracion);
        Button enviar = findViewById(R.id.enviar);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercises, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreEjercicioR = ejercicio.getText().toString();
                String caloriasQuemadasR = calorias.getText().toString();
                String fechaR = fecha.getText().toString();
                String duracionR = duracion.getText().toString();
                String tipoDeEjercicioR = spinner.getSelectedItem().toString();

                Toast.makeText(addexercise.this, "EJERCICIO AÑADIDO: "+"\n" + nombreEjercicioR + "\nFecha: " + fechaR + "\nDuración: "
                        + duracionR + "\nCalorias quemadas: " + caloriasQuemadasR + "\nTipo: " + tipoDeEjercicioR,
                        Toast.LENGTH_LONG).show();
            }
        });
    }
}