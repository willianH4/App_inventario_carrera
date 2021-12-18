package com.willianhdz.proyecto_final_daute.ui.usuario;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.willianhdz.proyecto_final_daute.R;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_usuarios;
import com.google.android.material.textfield.TextInputLayout;
import com.willianhdz.proyecto_final_daute.ui.user.remote.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.remote.UserService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class usuario extends Fragment {

    private UserService userService;

    private TextInputLayout ti_id, ti_nombre_prod, ti_descripcion, ti_stock,
            ti_precio, ti_unidadmedida;
    private EditText et_id, et_nombre_usu, et_apellido_usu, et_correo_usu, et_usuario, et_clave, et_respuesta;

    private Spinner sp_estadoUsu, sp_tipo, sp_pregunta;
    private TextView tv_fechahora;
    private Button btnSave, btnNew, btnEditar, btnEliminar, btnBuscar;
    ProgressDialog progressDialog;
    ArrayList<String> lista = null;
    //Arreglos para efectuar pruebas de carga de opciones en spinner.
    String elementos[] = {"Uno", "Dos", "Tres", "Cuatro", "Cinco"};
    final String[] elementos1 =new String[]{
            "Seleccione",
            "1",
            "2",
            "3",
            "4",
            "5"
    };
    dto_usuarios datos = new dto_usuarios();
    String Pregunt2 = "";
    String Pregunt = "";
    String Tipo = "";
    int conta = 0;
    int nu = 0;
    String datoStatusUsua = "";
    //Instancia DTO
    dto_usuarios dto = new dto_usuarios();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_usuario, container,
                false);
        ti_id = view.findViewById(R.id.ti_id);
        ti_nombre_prod = view.findViewById(R.id.ti_nombre_prod);
        ti_descripcion = view.findViewById(R.id.ti_descripcion);

        et_id = view.findViewById(R.id.et_id_usu);
        et_nombre_usu = view.findViewById(R.id.et_nombre_usu);
        et_apellido_usu = view.findViewById(R.id.et_apellido_usu);
        et_correo_usu = view.findViewById(R.id.et_correo);
        et_usuario = view.findViewById(R.id.et_usuario);
        et_clave = view.findViewById(R.id.et_clave);
        et_respuesta = view.findViewById(R.id.et_respuesta);
        sp_estadoUsu = view.findViewById(R.id.sp_estadoUsuario);
        sp_tipo = view.findViewById(R.id.sp_tipo);
        sp_pregunta = view.findViewById(R.id.sp_preguntas);


        btnSave = view.findViewById(R.id.btnSave_usu);
        btnNew = view.findViewById(R.id.btnNew);
        btnEditar = view.findViewById(R.id.btnEditarUsu);
        btnBuscar = view.findViewById(R.id.btnbuscarUsu);
        btnEliminar = view.findViewById(R.id.btnEliminarUsu);
        sp_estadoUsu = view.findViewById(R.id.sp_estadoUsuario);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.estadoUsuarios, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_estadoUsu.setOnItemSelectedListener(new
         AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int
                     position, long id) {
                 if(sp_estadoUsu.getSelectedItemPosition()>0)
                     datoStatusUsua = sp_estadoUsu.getSelectedItem().toString();
                 else{
                     datoStatusUsua = "";
                 }
                     }
                     @Override
                     public void onNothingSelected(AdapterView<?> parent) {
                     }
                 });

//Informacion Spinner
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(getContext(), R.array.TipoUsuario, android.R.layout.simple_spinner_item);
        sp_tipo.setAdapter(adapter1);
        sp_tipo.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               // Toast.makeText(MainActivityUsuario.this, "Seleccionaste: " + parent.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                String tipo = String.valueOf(parent.getItemIdAtPosition(position));
                int val;
                switch (tipo){
                    case "3":
                        val = 3;
                       Tipo = String.valueOf(val);
                        break;
                    case "2":
                        val = 2;
                        Tipo = String.valueOf(val);
                        break;
                    case "1":
                        val = 1;
                        Tipo = String.valueOf(val);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.Preguntas, android.R.layout.simple_spinner_item);
        sp_pregunta.setAdapter(adapter2);
        sp_pregunta.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Toast.makeText(MainActivityUsuario.this, "Seleccionaste: " + parent.getItemIdAtPosition(position), Toast.LENGTH_SHORT).show();
                String est = String.valueOf(parent.getItemIdAtPosition(position));

                switch (est){
                    case "1":
                        Pregunt = "1 ¿Nombre de tu mama?";
                        break;
                    case "2":
                        Pregunt ="2 ¿Nombre de tu primera mascota?";
                        break;
                    case "3":
                        Pregunt ="3 ¿Cual fue tu primera escuela?";
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        userService = ApiUtils.getUserService();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = et_id.getText().toString();
                String nombre = et_nombre_usu.getText().toString();
                String apellido = et_apellido_usu.getText().toString();
                String correo = et_correo_usu.getText().toString();
                String usuario = et_usuario.getText().toString();
                String clave = et_clave.getText().toString();
                String respuesta = et_respuesta.getText().toString();
                if(id.length() == 0){
                    et_id.setError("Campo obligatorio.");
                }else if(nombre.length() == 0){
                    et_nombre_usu.setError("Campo obligatorio.");
                }else if(apellido.length() == 0){
                    et_apellido_usu.setError("Campo obligatorio.");
                }else if(correo.length() == 0){
                    et_correo_usu.setError("Campo obligatorio.");
                }else if(usuario.length() == 0){
                    et_usuario.setError("Campo obligatorio.");
                }else if(clave.length() == 0){
                    et_clave.setError("Campo obligatorio.");
                }else if(sp_tipo.getSelectedItemPosition() == 0){
                    Toast.makeText(getContext(), "Debe seleccionar el tipo usuario.", Toast.LENGTH_SHORT).show();
                }else if(sp_estadoUsu.getSelectedItemPosition() == 0){
                    Toast.makeText(getContext(), "Debe seleccionar el estado usuario.", Toast.LENGTH_SHORT).show();
                }else if(sp_pregunta.getSelectedItemPosition() > 0){
                    save_usuarios(nombre, apellido, correo, usuario, clave, Integer.parseInt(Tipo), Integer.parseInt(datoStatusUsua), Pregunt, respuesta);
                }else{
                    Toast.makeText(getContext(), "Debe seleccionar la dto_categorias.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new_usuario();
            }
        });

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_id.getText().toString();
                String nombre = et_nombre_usu.getText().toString();
                String apellido = et_apellido_usu.getText().toString();
                String correo = et_correo_usu.getText().toString();
                String usuario = et_usuario.getText().toString();
                String clave = et_clave.getText().toString();
                String respuesta = et_respuesta.getText().toString();
                if(id.length() == 0){
                    et_id.setError("Campo obligatorio.");
                }else if(nombre.length() == 0){
                    et_nombre_usu.setError("Campo obligatorio.");
                }else if(apellido.length() == 0){
                    et_apellido_usu.setError("Campo obligatorio.");
                }else if(correo.length() == 0){
                    et_correo_usu.setError("Campo obligatorio.");
                }else if(usuario.length() == 0){
                    et_usuario.setError("Campo obligatorio.");
                }else if(clave.length() == 0){
                    et_clave.setError("Campo obligatorio.");
                }else if(sp_tipo.getSelectedItemPosition() == 0){
                    Toast.makeText(getContext(), "Debe seleccionar el tipo usuario.", Toast.LENGTH_SHORT).show();
                }else if(sp_estadoUsu.getSelectedItemPosition() == 0){
                    Toast.makeText(getContext(), "Debe seleccionar el estado usuario.", Toast.LENGTH_SHORT).show();
                }else if(sp_pregunta.getSelectedItemPosition() > 0){
                    String esta = sp_estadoUsu.getSelectedItem().toString();

                    update_usuarios(Integer.parseInt(id), nombre, apellido, correo, usuario, clave, Integer.parseInt(Tipo), Integer.parseInt(datoStatusUsua), Pregunt, respuesta);
                }else{
                    Toast.makeText(getContext(), "Debe seleccionar la dto_categorias.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String codigo_bu = et_id.getText().toString();
                if (codigo_bu.length()==0){
                    et_id.setError("Campo obligatorio");
                }else {
                    buscarusuario(Integer.parseInt(codigo_bu));
                }
            }
        });

        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_id.getText().toString();
                if (id.length()==0){
                    et_id.setError("Campo obligatorio");
                }else{
                    delete_usuarios(Integer.parseInt(id));
                    //Toast.makeText(getContext(), "Eliminación exitosa ", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }
    private String timedate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String fecha = sdf.format(cal.getTime());
        return fecha;
    }

// metodo para guardar usuario
    private void save_usuarios(String nombre, String apellido, String correo, String usuario,
                               String clave, int tipo, int estado, String pregunta, String respuesta){
        userService.addUser(nombre, apellido, correo, usuario, clave, tipo, estado, pregunta, respuesta).enqueue(new Callback<dto_usuarios>(){

            //TODO: Funcional guarda correctamente
            @Override
            public void onResponse(Call<dto_usuarios> call, retrofit2.Response<dto_usuarios> response) {
                Log.d("Respuesta", response.body().toString());
                Log.d("estado", String.valueOf(response.body().getEstado()));
                int estado = response.body().getEstado();
                if (estado == 1){
                    Toast.makeText(getActivity(), "Registro guardado exitosamente con Retrofit!", Toast.LENGTH_SHORT).show();
                    new_usuario();
                }else{
                    Toast.makeText(getActivity(), "Registro no guardado! Retrofit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<dto_usuarios> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
                Toast.makeText(getActivity(), "Algo fallo!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    // metodo para actualizar usuario
    private void update_usuarios(int id ,String nombre, String apellido, String correo, String usuario,
                                 String clave, int tipo, int estado, String pregunta, String respuesta){

        userService.updateUser(id, nombre, apellido, correo, usuario, clave, tipo, estado, pregunta, respuesta).enqueue(new Callback<dto_usuarios>() {
            @Override
            public void onResponse(Call<dto_usuarios> call, retrofit2.Response<dto_usuarios> response) {
                Log.d("Respuesta", response.body().toString());
                Log.d("estado", String.valueOf(response.body().getEstado()));
                int estado = response.body().getEstado();
                if (estado == 1){
                    Toast.makeText(getActivity(), "Registro actualizado exitosamente! Retrofit", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Registro no actualizado! Retrofit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<dto_usuarios> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
                Toast.makeText(getActivity(), "Algo fallo!", Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void buscarusuario(int id){
        userService.buscarUserForId(id).enqueue(new Callback<dto_usuarios>() {
            @Override
            public void onResponse(Call<dto_usuarios> call, retrofit2.Response<dto_usuarios> response) {
                Log.d("Respuesta", response.body().toString());
            }

            @Override
            public void onFailure(Call<dto_usuarios> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
            }
        });
    }

    private void delete_usuarios(int id){
        userService.deleteUser(id).enqueue(new Callback<dto_usuarios>() {
            @Override
            public void onResponse(Call<dto_usuarios> call, Response<dto_usuarios> response) {
                Log.d("Respuesta", response.body().toString());
                Log.d("estado", String.valueOf(response.body().getEstado()));
                int estado = response.body().getEstado();
                if (estado == 1){
                    Toast.makeText(getActivity(), "Registro borrado exitosamente! Retrofit", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getActivity(), "Registro no borrado! Retrofit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<dto_usuarios> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
                Toast.makeText(getActivity(), "Algo fallo!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void new_usuario() {
        et_id.setText(null);
        et_nombre_usu.setText(null);
        et_correo_usu.setText(null);
        et_clave.setText(null);
        et_usuario.setText(null);
        et_respuesta.setText(null);

        sp_pregunta.setSelection(0);
        sp_tipo.setSelection(0);
        sp_estadoUsu.setSelection(0);

    }

}