package com.example.personal_trainer.ui.login;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.personal_trainer.MainActivity;
import com.example.personal_trainer.R;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private EditText password;
    private Button boton;
    private Context context= this;
    private RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.username);
        password = findViewById( R.id.password);
        boton = findViewById(R.id.login);


        boton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {loginUser();}
        });
        queue = Volley.newRequestQueue(this);
    }

    private void loginUser() {
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("name", username.getText().toString());
            requestBody.put("password", password.getText().toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://63c57b6af3a73b3478575467.mockapi.io/user?name="+username.getText().toString(),
                requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        String receivedToken;
                        try {
                            receivedToken = response.getString("sessionToken");
                        } catch (JSONException e) {
                            // Si el JSON de la respuesta NO contiene "sessionToken", vamos a lanzar
                            // una RuntimeException para que la aplicación rompa.
                            // En preferible que sea NOTORIO el problema del servidor, pues desde
                            // la aplicación no podemos hacer nada. Estamos 'vendidos'.
                            throw new RuntimeException(e);
                        }
                        // Si la respuesta está OK, mostramos un Toast
                        // Esta línea asume que private Context context = this; está definido
                        Toast.makeText(context, "Token: " + receivedToken, Toast.LENGTH_LONG).show();
                        Intent I = new Intent(context, MainActivity.class);
                        context.startActivity(I);
                        // Instanciamos un objeto de tipo SharedPreferences
                        // En el constructor pasamos un String. SIEMPRE será el mismo para nuestra aplicación.
                        SharedPreferences preferences = context.getSharedPreferences("SESSIONS_APP_PREFS", MODE_PRIVATE);
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString("VALID_USERNAME", username.getText().toString());
                        editor.putString("VALID_TOKEN", receivedToken);
                        editor.commit();
                        finish();

                    }

                },
//comentario de prueba
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