package com.example.personal_trainer.ui.register;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
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
//import com.example.personaltrainer.R; //es Personal-Trainer ahora

import org.json.JSONException;
import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {

    private EditText editTextUsername;
    private EditText editTextPassword;
    private EditText editTextEmail;
    private Button buttonRegister;
    private Context context = this;
    private RequestQueue queue;


    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        // Los inicializamos a partir de su contraparte en el XML
        editTextUsername = findViewById(R.id.username);
        editTextEmail = findViewById(R.id.mail);
        editTextPassword = findViewById(R.id.password1);
        buttonRegister = findViewById(R.id.registerButton);
        queue=Volley.newRequestQueue(this);

        //Se activa cuando el usuario pulsa el boton de registro
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendPostRegister();
            }
        });

    }

    //método que hace una petición post
    private void sendPostRegister() {
        //Toast.makeText(context,"Por aquí",Toast.LENGTH_LONG).show();
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("name", editTextUsername.getText().toString());
            requestBody.put("mail", editTextEmail.getText().toString());
            requestBody.put("password", editTextPassword.getText().toString());
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                "https://63c57b6af3a73b3478575467.mockapi.io/user",
                requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        Toast.makeText(context,"Usuario creado",Toast.LENGTH_LONG).show();
                        Intent I = new Intent(context, MainActivity.class);
                        context.startActivity(I);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, "Ha ocurrido un error", Toast.LENGTH_SHORT).show();
                    }
                }
        );
        this.queue.add(request);
    }

}