package com.willianhdz.proyecto_final_daute.ui.list_recycle_usu;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.willianhdz.proyecto_final_daute.MainActivity;
import com.willianhdz.proyecto_final_daute.R;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_usuarios;
import com.willianhdz.proyecto_final_daute.ui.user.services.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.services.UserService;
import com.willianhdz.proyecto_final_daute.ui.usuario.Edit_Usuario;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DetalleUsuario extends AppCompatActivity implements View.OnClickListener {
    private TextView tvcodigo, tvNombre, tvApe, tvCorreo, tvUsu, tvClave, tvTipo, tvEstado, tvPregun, tvRespu, tvfecha;
    private Button btnEditarPro, btnBorrarPro;

    private UserService userService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_usuario);

        userService = ApiUtils.getUserService();

        tvcodigo = findViewById(R.id.tvId);
        tvNombre = findViewById(R.id.tvNombre);
        tvApe = findViewById(R.id.tvApellido);
        tvCorreo = findViewById(R.id.tvCorreo);
        tvUsu = findViewById(R.id.tvUsuario);
        tvClave = findViewById(R.id.tvClave);
        tvTipo = findViewById(R.id.tvTipo);
        tvEstado = findViewById(R.id.tvEstado);
        tvPregun = findViewById(R.id.tvPregunta);
        tvRespu = findViewById(R.id.tvRespuesta);
        tvfecha = findViewById(R.id.tvFecha);

        btnEditarPro = findViewById(R.id.btnEditarCat);
        btnBorrarPro = findViewById(R.id.btnBorrarCat);

        Bundle bundle = getIntent().getExtras();
       String codigo = bundle.getString("id");
        String nombre = bundle.getString("nombre");
        String ape = bundle.getString("apellido");
        String correo = bundle.getString("correo");
        String usu = bundle.getString("usuario");
        String clave = bundle.getString("clave");
        String tipo = bundle.getString("tipo");
        String est = bundle.getString("estado");
        String pregun = bundle.getString("pregunta");
        String respu = bundle.getString("respuesta");
        String fech = bundle.getString("fecha");

        tvcodigo.setText(codigo);
        tvNombre.setText(nombre);
        tvApe.setText(ape);
        tvCorreo.setText(correo);
        tvUsu.setText(usu);
        tvClave.setText(clave);
        tvTipo.setText(tipo);
        tvEstado.setText(est);
        tvRespu.setText(respu);
        tvPregun.setText(pregun);
        tvfecha.setText(fech);

        btnEditarPro.setOnClickListener(this);
        btnBorrarPro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String id = tvcodigo.getText().toString();
        if (view.getId() == R.id.btnBorrarCat){
            delete_usuarios(Integer.parseInt(id));
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if(view.getId() == R.id.btnEditarCat){
            String code = tvcodigo.getText().toString();
            String name = tvNombre.getText().toString();
            String ape = tvApe.getText().toString();
            String corre = tvCorreo.getText().toString();
            String usua = tvUsu.getText().toString();
            String cla = tvClave.getText().toString();
            String tipo = tvTipo.getText().toString();
            String est = tvEstado.getText().toString();
            String preg = tvPregun.getText().toString();
            String resp = tvRespu.getText().toString();
            String fecha = tvfecha.getText().toString();

            Intent intent = new Intent(getApplicationContext(), Edit_Usuario.class);
            intent.putExtra("senal", "1");
            intent.putExtra("id", code);
            intent.putExtra("nombre", name);
            intent.putExtra("apellido", ape);
            intent.putExtra("correo", corre);
            intent.putExtra("usuario", usua);
            intent.putExtra("clave", cla);
            intent.putExtra("tipo", tipo);
            intent.putExtra("estado", est);
            intent.putExtra("pregunta", preg);
            intent.putExtra("respuesta", resp);
            intent.putExtra("fecha", fecha);
            startActivity(intent);
        }

    }

    private void delete_usuarios(int id){
        userService.deleteUser(id).enqueue(new Callback<dto_usuarios>() {
            @Override
            public void onResponse(Call<dto_usuarios> call, Response<dto_usuarios> response) {
                Log.d("Respuesta", response.body().toString());
                Log.d("estado", String.valueOf(response.body().getEstado()));
                int estado = response.body().getEstado();
                if (estado == 1){
                    Toast.makeText(getApplicationContext(), "Registro borrado exitosamente, usando Retrofit!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Registro no borrado, usando Retrofit!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<dto_usuarios> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
                Toast.makeText(getApplicationContext(), "Algo fallo, usando Retrofit!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}