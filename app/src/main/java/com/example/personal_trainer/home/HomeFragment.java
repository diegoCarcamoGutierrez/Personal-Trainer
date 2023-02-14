package com.example.personal_trainer.home;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.personal_trainer.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Date;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Context context=getContext();
        SharedPreferences sharedPreferences = context.getSharedPreferences("SESSIONS_APP_PREFS", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId",2);
        String url = "https://63c57b6af3a73b3478575467.mockapi.io/user/" + userId+"/exercises";
        View view=inflater.inflate(R.layout.fragment_home,container,false);

        TextView totalTime=view.findViewById(R.id.totalTime);

        TextView burnedCalories=view.findViewById(R.id.burnedCalories);
        TextView completedExercises=view.findViewById(R.id.completedExercises);
        RequestQueue queue = Volley.newRequestQueue(getContext());

        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int tTime = 0;
                        int bCalories=0;
                        int cExercises=0;
                        for (int i=0;i<response.length();i++){
                            try {
                                tTime= tTime +Integer.parseInt(response.getJSONObject(i).getString("duration"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                        for (int i=0; i<response.length();i++){
                            try {
                                bCalories=bCalories+Integer.parseInt(response.getJSONObject(i).getString("calories"));
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                        for (int i=0; i<=response.length(); i++){
                            cExercises=cExercises+1;
                        }
                        totalTime.setText(Integer.toString(tTime));
                        burnedCalories.setText((Integer.toString(bCalories)));
                        completedExercises.setText((Integer.toString(cExercises)));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                }
        );

        queue.add(request);
        return view;

    }

}