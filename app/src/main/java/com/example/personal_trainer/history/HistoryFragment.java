package com.example.personal_trainer.history;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.personal_trainer.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class HistoryFragment extends Fragment {

    RecyclerView recyclerExercises;
    Exercise_Adapter exercise_adapter;
    List<Exercise> exerciseList;

    public HistoryFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_exercise_history, container, false);
        initialize(view);
        loadExercises();
        return view;
    }

    private void initialize(View view) {
        recyclerExercises = view.findViewById(R.id.recyclerview);
        recyclerExercises.setLayoutManager(new LinearLayoutManager(getActivity()));

        exerciseList = new ArrayList<>();
        exercise_adapter = new Exercise_Adapter(exerciseList, getActivity());
        recyclerExercises.setAdapter(exercise_adapter);
    }

    private void loadExercises() {
        Context context = getContext();

        SharedPreferences sharedPreferences = context.getSharedPreferences("SESSIONS_APP_PREFS", Context.MODE_PRIVATE);

        int userId = sharedPreferences.getInt("userId",0);
        String url = "https://63c57b6af3a73b3478575467.mockapi.io/user/" + userId + "/exercises";

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        Toast.makeText(context, "Manten pulsado para editar un ejercicio", Toast.LENGTH_LONG).show();

                        List<Exercise> exerciseList = new ArrayList<>();

                        for (int i = 0; i < response.length(); i++) {
                            try {
                                JSONObject exerciseJson = response.getJSONObject(i);
                                int id = exerciseJson.getInt("id");
                                String name = exerciseJson.getString("name");
                                String duration = exerciseJson.getString("duration");
                                String calories = exerciseJson.getString("calories");

                                Exercise exercise = new Exercise(id, name, duration, calories);
                                exerciseList.add(exercise);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }

                        exercise_adapter.setExerciseList(exerciseList);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Context context = getContext();

                        Toast.makeText(context, "Se ha producido un error", Toast.LENGTH_LONG).show();
                    }
                }
        );

        requestQueue.add(request);
    }

}
