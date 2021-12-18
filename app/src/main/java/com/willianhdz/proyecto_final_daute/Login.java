package com.willianhdz.proyecto_final_daute;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_usuarios;
import com.willianhdz.proyecto_final_daute.ui.user.remote.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.remote.UserService;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;

public class Login extends AppCompatActivity {

    EditText edtUsuario, edtPassword;
    Button btnLogin;

    private UserService miUserService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsuario = findViewById(R.id.edtUsuario);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        miUserService = ApiUtils.getUserService();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String usua = edtUsuario.getText().toString();
                String passw = edtPassword.getText().toString();

                if (usua.length()==0){
                    edtUsuario.setError("Campo obligatorio");
                }else if (passw.length()==0){
                    edtPassword.setError("Campo obligatorio");
                }else {
                    validar_usuario(usua, passw);
                    vaciarcampos();
                }

                }

        });


    }

    public void validar_usuario(String usuario, String password){
        miUserService.validarUser(usuario, password).enqueue(new Callback<dto_usuarios>() {
            @Override
            public void onResponse(Call<dto_usuarios> call, retrofit2.Response<dto_usuarios> response) {
                if(response.isSuccessful()) {
                    Log.d("respuesta", response.body().toString());
                    Intent intent= new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<dto_usuarios> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
                Toast.makeText(getApplicationContext(), "Algo fallo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void vaciarcampos() {
        edtUsuario.setText(null);
        edtPassword.setText(null);

    }

}