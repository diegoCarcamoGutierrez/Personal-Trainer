package com.example.personal_trainer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class addexercise extends AppCompatActivity {
    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        int userId=0;

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexercise);

        Spinner spinner = (Spinner) findViewById(R.id.exercise_spinner);
        EditText ejercicio = findViewById(R.id.ejercicio);
        EditText calorias = findViewById(R.id.calorias);
        EditText fecha = findViewById(R.id.fecha);
        EditText duracion = findViewById(R.id.duracion);
        Button enviar = findViewById(R.id.enviar);
        RequestQueue requestQueue = Volley.newRequestQueue(this.getApplicationContext());

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.exercises, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        SharedPreferences sharedPreferences = getSharedPreferences("userInfo", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("userId", userId);
        editor.commit();
        userId = sharedPreferences.getInt("userId", 0);
        String url = "https://63c57b6af3a73b3478575467.mockapi.io/user/" + userId + "/exercises";

        enviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombreEjercicioR = ejercicio.getText().toString();
                String caloriasQuemadasR = calorias.getText().toString();
                String fechaR = fecha.getText().toString();
                String duracionR = duracion.getText().toString();
                String tipoDeEjercicioR = spinner.getSelectedItem().toString();

                JSONObject jsonObject = new JSONObject();
                try {
                    jsonObject.put("name", nombreEjercicioR);
                    jsonObject.put("category", tipoDeEjercicioR);
                    jsonObject.put("start-date", fechaR);
                    jsonObject.put("duration", duracionR);
                    jsonObject.put("calories", caloriasQuemadasR);

                } catch (JSONException e) {
                }

                JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                        Request.Method.POST,
                        url,
                        jsonObject,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                Toast.makeText(addexercise.this, "Ejercicio añadido", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(addexercise.this, "Error al añadir ejercicio", Toast.LENGTH_LONG).show();
                    }
                });

                requestQueue.add(jsonObjectRequest);
            }
        });
    }
}