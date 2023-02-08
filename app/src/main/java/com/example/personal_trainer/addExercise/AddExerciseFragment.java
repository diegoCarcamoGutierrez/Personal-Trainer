package com.example.personal_trainer.addExercise;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import com.example.personal_trainer.R;
import androidx.fragment.app.Fragment;
import org.json.JSONException;
import org.json.JSONObject;

public class AddExerciseFragment extends Fragment {
    Context context;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Context context = getContext();
        View view = inflater.inflate(R.layout.fragment_add_exercise,container, false);

        Spinner spinner = view.findViewById(R.id.exercise_spinner);
        EditText ejercicio = view.findViewById(R.id.ejercicio);
        EditText calorias = view.findViewById(R.id.calorias);
        EditText fecha = view.findViewById(R.id.fecha);
        EditText duracion = view.findViewById(R.id.duracion);
        Button enviar = view.findViewById(R.id.enviar);
        RequestQueue requestQueue = Volley.newRequestQueue(getContext());;

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.exercises, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        SharedPreferences sharedPreferences = context.getSharedPreferences("SESSIONS_APP_PREFS", Context.MODE_PRIVATE);

        int userId = sharedPreferences.getInt("userId",0);
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
                                Toast.makeText(context, "Ejercicio añadido", Toast.LENGTH_LONG).show();
                            }
                        }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context.getApplicationContext(), "Error al añadir ejercicio", Toast.LENGTH_LONG).show();
                    }
                });

                requestQueue.add(jsonObjectRequest);
            }
        });
        return view;
    }
}