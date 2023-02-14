package com.example.personal_trainer.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.personal_trainer.MainActivity;
import com.example.personal_trainer.R;
import com.example.personal_trainer.ui.register.RegisterActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private TextView crearCuenta;
    private Button boton;
    private Context context= this;
    private RequestQueue queue;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences preferences = getSharedPreferences("SESSIONS_APP_PREFS", MODE_PRIVATE);
        String userLogged = preferences.getString("VALID_USERNAME", "");
        int userId = preferences.getInt("userId", -1);


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById( R.id.password);
        boton = findViewById(R.id.login);
        crearCuenta=findViewById(R.id.gotoRegister);
        if (!userLogged.isEmpty() && userId != -1) {
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("VALID_USERNAME", userLogged);
            editor.putInt("userId", userId);
            editor.apply();
            Intent i = new Intent(context,MainActivity.class);
            startActivity(i);
            finish();
        }
        crearCuenta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent I = new Intent(context, RegisterActivity.class);
                context.startActivity(I);
            }
        });



        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {loginUser();}
        });
        queue = Volley.newRequestQueue(this);
    }

    private void loginUser() {
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                "https://63c57b6af3a73b3478575467.mockapi.io/user?name="+username.getText().toString(),
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        int receivedToken;
                        String pw;
                        try {
                            receivedToken = response.getJSONObject(0).getInt("id");
                            pw = response.getJSONObject(0).getString("password");
                            Toast.makeText(context,pw,Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            throw new RuntimeException(e);
                        }
                        if (password.getText().toString().equals(pw)){
                            Toast.makeText(context, "Token: " + receivedToken, Toast.LENGTH_LONG).show();
                            Intent I = new Intent(context, MainActivity.class);
                            context.startActivity(I);
                            SharedPreferences preferences = context.getSharedPreferences("SESSIONS_APP_PREFS", MODE_PRIVATE);
                            SharedPreferences.Editor editor = preferences.edit();
                            editor.putString("VALID_USERNAME", username.getText().toString());
                            editor.putInt("userId", receivedToken);
                            editor.apply();
                            finish();
                        }else {
                            Toast.makeText(context,"Contraseña incorrecta",Toast.LENGTH_SHORT).show();
                        }

                    }

                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context,"Código de respuesta: ", Toast.LENGTH_LONG).show();
                    }
                }
        );
        this.queue.add(request);
    }

}