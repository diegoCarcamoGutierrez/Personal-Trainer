package com.example.personal_trainer.home;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.example.personal_trainer.R;

import org.json.JSONArray;

import java.util.Date;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context=getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("SESSIONS_APP_PREFS", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId",0);
        String url = "https://63c57b6af3a73b3478575467.mockapi.io/user/" + userId+"/exercises";
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        TextView totalTime=view.findViewById(R.id.totalTime);
        TextView burnedCalories=view.findViewById(R.id.burnedCalories);
        TextView completedExercises=view.findViewById(R.id.completedExercises);


        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int tTime; //TotalTime o tiempo total de ejercicio
                        int bCal; //BurnedCalories o calorías quemadas
                        int cE; //CompletedExercises o total de ejercicios completados
                        int actualDateMS= (int) System.currentTimeMillis();
                        
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );


        return view;

    }

}