package com.example.personal_trainer.stats;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.personal_trainer.R;
import com.example.personal_trainer.addExercise.AddExerciseFragment;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.ValueDependentColor;
import com.jjoe64.graphview.series.BarGraphSeries;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link StatsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StatsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Stats.
     */
    // TODO: Rename and change types and number of parameters
    public static StatsFragment newInstance(String param1, String param2) {
        StatsFragment fragment = new StatsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_stats, container, false);
    }
/**************************************************************************************************/
/*COMENTARIOS SOBRE EL CODIGO:
   EL gráfico muestra los minutos totales del tiempo dedicado(las 7 barras indican lo mismo).
   Al final, hay un codigo comentado con un for, es un intento para separar por dias con un array, pero
   el primer intento se bugeaba y mostraba las barras raras.

*/
/**************************************************************************************************/
    private GraphView funcion;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        funcion = view.findViewById(R.id.graph);

        SharedPreferences sharedPreferences = getContext().getSharedPreferences("SESSIONS_APP_PREFS", Context.MODE_PRIVATE);
        int userId = sharedPreferences.getInt("userId", 0);
        ;
        String url = "https://63c57b6af3a73b3478575467.mockapi.io/user/" + userId + "/exercises";
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int duration=0;
                        for(int i=0;i<response.length();i++){
                            try {
                                duration=duration+Integer.parseInt(response.getJSONObject(i).getString("duration"));
                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }

                        BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{
                                new DataPoint(0,0),
                                new DataPoint(1, duration),
                                new DataPoint(2, duration),
                                new DataPoint(3, duration),
                                new DataPoint(4, duration),
                                new DataPoint(5, duration),
                                new DataPoint(6, duration),
                                new DataPoint(7, duration)
                        });


                        //AÑADIRLOS A LA FUNIÓN
                        funcion.addSeries(series);

                        //DAR COLORES A LA BARRAS
                        series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                            @Override
                            public int get(DataPoint data) {
                                return Color.rgb((int) data.getX()*255/4, (int) data.getY()*255/6, 100);
                            }
                        });

                        //ESPACIAR BARRAS
                        series.setSpacing(10);

                        //dibujar los puntos en la gráfica
                        series.setDrawValuesOnTop(true);
                        series.setValuesOnTopColor(Color.BLUE);


                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //error
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
    }
}

//CODIGO PARA PONERLO POR DIFERENTES DIAS EN LOS ULTIMOS 7, ESTA MAL

             /*           for(int i=0;i<response.length();i++){
                            try {
                                duration=duration+Integer.parseInt(response.getJSONObject(i).getString("duration"));
                                data_started=data_started+(response.getJSONObject(i).getString("data-started"));

                                    if (data_started != data_started2 ){
                                        data_started2 = data_started;

                                    }else{
                                       for (int x=0;x<7;x++){

                                            Array[x] = duration;

                                            BarGraphSeries<DataPoint> series = new BarGraphSeries<>(new DataPoint[]{

                                                    new DataPoint(0,0),
                                                    new DataPoint(1, Array[6]),
                                                    new DataPoint(2, Array[5]),
                                                    new DataPoint(3, Array[4]),
                                                    new DataPoint(4, Array[3]),
                                                    new DataPoint(5, Array[2]),
                                                    new DataPoint(6, Array[1]),
                                                    new DataPoint(7, Array[0])
                                            });

                                            //AÑADIRLOS A LA FUNIÓN
                                            funcion.addSeries(series);
                                            //DAR COLORES A LA BARRAS
                                            series.setValueDependentColor(new ValueDependentColor<DataPoint>() {
                                                @Override
                                                public int get(DataPoint data) {
                                                    return Color.rgb((int) data.getX()*255/4, (int) data.getY()*255/6, 100);
                                                }
                                            });

                                            //ESPACIAR BARRAS
                                            series.setSpacing(10);

                                            //dibujar los puntos en la gráfica
                                            series.setDrawValuesOnTop(true);
                                            series.setValuesOnTopColor(Color.BLUE);
                                    }
                                }

                            }catch (JSONException e){
                                e.printStackTrace();
                            }
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //error
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());
        requestQueue.add(request);
        */