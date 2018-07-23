package com.example.barber;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private EditText Nombre,ApellidoP,ApellidoM,Genero,Usuario,Correo,Password,Cpassword;
    private Button btnRegistrar;
    private ProgressBar pbCargando;
    private static String URL_REGIST ="http://192.168.0.102/android_barber/register.php";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

    pbCargando = findViewById(R.id.pbCargando);
    Nombre = findViewById(R.id.txtNombre);
    ApellidoP = findViewById(R.id.txtApellidoP);
    ApellidoM = findViewById(R.id.txtApellidoM);
    Genero = findViewById(R.id.txtGenero);
    Usuario = findViewById(R.id.txtUsuario);
    Correo = findViewById(R.id.txtCorreo);
    Password = findViewById(R.id.txtPassword);
    Cpassword= findViewById(R.id.txtCpassword);
    btnRegistrar= findViewById(R.id.btnRegistrar);

    btnRegistrar.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Regitro();
        }
    });

    }

    private void Regitro(){
        pbCargando.setVisibility(View.VISIBLE);
        btnRegistrar.setVisibility(View.GONE);

        final String Nombre = this.Nombre.getText().toString().trim();
        final String ApellidoP = this.ApellidoP.getText().toString().trim();
        final String ApellidoM = this.ApellidoM.getText().toString().trim();
        final String Genero = this.Genero.getText().toString().trim();
        final String Usuario = this.Usuario.getText().toString().trim();
        final String Correo = this.Correo.getText().toString().trim();
        final String Password = this.Password.getText().toString().trim();
        final String Cpassword = this.Cpassword.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                    try {
                        JSONObject jsonObject = new JSONObject(response);
                        //String succes = jsonObject.getString( "succces");
                        String succes = jsonObject.getString( "succes");

                        if(succes.equals("1")){
                            Toast.makeText(RegisterActivity.this, "Register Success! ", Toast.LENGTH_SHORT).show();
                        }

                    } catch (JSONException e) {
                        e.printStackTrace();
                        Toast.makeText(RegisterActivity.this, "Register Error!" + e.toString(), Toast.LENGTH_SHORT).show();
                        pbCargando.setVisibility(View.GONE);
                        btnRegistrar.setVisibility(View.VISIBLE);

                    }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(RegisterActivity.this, "Register Error!" + error.toString(), Toast.LENGTH_SHORT).show();
                        pbCargando.setVisibility(View.GONE);
                        btnRegistrar.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("Nombre",Nombre);
                params.put("ApellidoP",ApellidoP);
                params.put("ApellidoM",ApellidoM);
                params.put("Genero",Genero);
                params.put("Usuario",Usuario);
                params.put("Correo",Correo);
                params.put("Password",Password);
                params.put("Cpassword",Cpassword);
                return params;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue( this);
        requestQueue.add(stringRequest);

    }
}
