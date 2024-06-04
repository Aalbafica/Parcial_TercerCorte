package com.example.parcial_corte3.vista_registro;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterRequest extends StringRequest {
    private static final String REGISTER_URL = "http://192.168.1.3/apiPHP/Register.php";
    private Map<String,String> params;
    public RegisterRequest(String nombre, String email, String password, String fechaNacimiento, Response.Listener<String> listener){
        super(Method.POST, REGISTER_URL, listener, null);
        params = new HashMap<>();
        params.put("nombre",nombre);
        params.put("email",email);
        params.put("password",password);
        params.put("fecha_nacimiento",fechaNacimiento);
    }

    @Nullable
    @Override
    public Map<String, String> getParams() {
        return params;
    }
}
