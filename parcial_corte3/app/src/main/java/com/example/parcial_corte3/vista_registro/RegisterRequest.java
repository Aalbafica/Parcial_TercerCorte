package com.example.parcial_corte3.vista_registro;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_URL = "http://192.168.1.3/apiPHP/Registro.php";
    private Map<String,String> params;
    public RegisterRequest(String nombre, String correo, String contrasena, String fecha, Response.Listener<String> listener, Response.ErrorListener errorListener){
        super(Method.POST, REGISTER_URL, listener, errorListener);
        params = new HashMap<>();
        params.put("nombre",nombre);
        params.put("correo",correo);
        params.put("contrasena",contrasena);
        params.put("fecha",fecha);
    }

    @Nullable
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
