package com.willianhdz.proyecto_final_daute.ui.categoria;

import android.os.Bundle;
import androidx.fragment.app.Fragment;

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

import com.google.android.material.textfield.TextInputLayout;
import com.willianhdz.proyecto_final_daute.R;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_categorias;
import com.willianhdz.proyecto_final_daute.ui.user.services.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.services.CategoryService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Categoria extends Fragment implements View.OnClickListener{

    private CategoryService categoryService;

    private TextInputLayout ti_idcategoria, ti_namecategoria;
    private EditText et_idcategoria, et_namecategoria;
    public TextView et_categpria;
    private Spinner sp_estado;
    private Button btnSave, btnNew, btnEditar, btnDelete, btnbuscar;
    String datoSelect = "";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_categoria, container, false);

        ti_idcategoria = root.findViewById(R.id.ti_idcategoria);
        ti_namecategoria = root.findViewById(R.id.ti_namecategoria);
        et_idcategoria = root.findViewById(R.id.et_idcategoria);
        et_namecategoria = root.findViewById(R.id.et_namecategoria);
        sp_estado = root.findViewById(R.id.sp_estado);


    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
            R.array.estadoCategorias, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    sp_estado.setAdapter(adapter);
    sp_estado.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            if(sp_estado.getSelectedItemPosition()>0) {
                datoSelect = (String) sp_estado.getSelectedItem();
            }else{
                datoSelect = "";
            }
            //Toast.makeText(getContext(), ""+datoSelect, Toast.LENGTH_SHORT).show();
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    });

        btnSave = root.findViewById(R.id.btnSave);
        btnNew = root.findViewById(R.id.btnNew);
        btnEditar = root.findViewById(R.id.btnEditar);
        btnDelete = root.findViewById(R.id.btnEliminar);
        btnbuscar = root.findViewById(R.id.btnbuscar);


        btnSave.setOnClickListener(this);
        btnNew.setOnClickListener(this);
        btnEditar.setOnClickListener(this);
        btnDelete.setOnClickListener(this);
        btnbuscar.setOnClickListener(this);

        categoryService = ApiUtils.getCategoryService();

        return root;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSave:
                String id = et_idcategoria.getText().toString();
                String nombre = et_namecategoria.getText().toString();
                if (id.length() == 0){
                    et_idcategoria.setError("Campo obligatorio");
                }else if(nombre.length() == 0){
                    et_namecategoria.setError("Campo obligatorio");
                }else if(sp_estado.getSelectedItemPosition() > 0){
                    //TODO Acciones para guardar registro en la base de datos.
                    Log.d("Nombre", nombre);
                    Log.d("estado", datoSelect);
                    save_server(nombre, Integer.parseInt(datoSelect));
                }else{
                    Toast.makeText(getContext(), "Debe seleccionar un estado para la dto_categorias",
                            Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.btnNew:
                new_categories();
                break;
            case R.id.btnbuscar:
                String codigo_bu = et_idcategoria.getText().toString();
                if (codigo_bu.length()==0){
                    et_idcategoria.setError("Campo obligatorio");
                }else {
                    String idbuscar = et_idcategoria.getText().toString();
                    buscarcategoria(Integer.parseInt(codigo_bu));
                }
                break;
            case R.id.btnEditar:
                String codigo = et_idcategoria.getText().toString();
                String nombre_cat = et_namecategoria.getText().toString();

                if (codigo.length()==0){
                    et_idcategoria.setError("Campo obligatorio");
                }else if (nombre_cat.length()==0){
                    et_namecategoria.setError("Campo obligatorio");
                }else if (sp_estado.getSelectedItemPosition()>0 ){
                    String est = (String) sp_estado.getSelectedItem();
                    uddate_server(Integer.parseInt(codigo), nombre_cat, Integer.parseInt(est));
                    Toast.makeText(getContext(), "Registro Actualizado", Toast.LENGTH_SHORT).show();
                }else {
                    Toast.makeText(getContext(), "seleccione un estado", Toast.LENGTH_SHORT).show();

                }
                break;
            case R.id.btnEliminar:
                String codigo_de = et_idcategoria.getText().toString();
                if (codigo_de.length()==0){
                    et_idcategoria.setError("Campo obligatorio");
                }else{
                    delete_server(Integer.parseInt(codigo_de));
                }

                break;
            default:
        }
    }

    private void save_server(String nom_categoria, int estado_categoria) {

        categoryService.addCategory(nom_categoria, estado_categoria).enqueue(new Callback<dto_categorias>() {
            @Override
            public void onResponse(Call<dto_categorias> call, retrofit2.Response<dto_categorias> response) {
                Log.d("Respuesta", response.body().toString());
                Log.d("estado", String.valueOf(response.body().getEstado()));
                int estado = response.body().getEstado();
                if (estado == 1){
                    Toast.makeText(getActivity(), "Registro guardado exitosamente con Retrofit!", Toast.LENGTH_SHORT).show();
                    new_categories();
                }else{
                    Toast.makeText(getActivity(), "Registro no guardado! Retrofit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<dto_categorias> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
                Toast.makeText(getActivity(), "Algo fallo!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void uddate_server(int id_categoria, String nom_categoria, int estado_categoria) {
        categoryService.updateCategory(id_categoria, nom_categoria, estado_categoria).enqueue(new Callback<dto_categorias>() {
            @Override
            public void onResponse(Call<dto_categorias> call, retrofit2.Response<dto_categorias> response) {
                Log.d("Respuesta", response.body().toString());
                Log.d("estado", String.valueOf(response.body().getEstado()));
                int estado = response.body().getEstado();
                if (estado == 1){
                    Toast.makeText(getActivity(), "Registro guardado exitosamente con Retrofit!", Toast.LENGTH_SHORT).show();
                    new_categories();
                }else{
                    Toast.makeText(getActivity(), "Registro no guardado! Retrofit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<dto_categorias> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
                Toast.makeText(getActivity(), "Algo fallo!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void delete_server(int id_categoria) {
        categoryService.deleteCategory(id_categoria).enqueue(new Callback<dto_categorias>() {
            @Override
            public void onResponse(Call<dto_categorias> call, Response<dto_categorias> response) {
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
            public void onFailure(Call<dto_categorias> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
                Toast.makeText(getActivity(), "Algo fallo!", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void buscarcategoria(int id_categoria){
        categoryService.buscarCategoryForId(id_categoria).enqueue(new Callback<dto_categorias>() {
            @Override
            public void onResponse(Call<dto_categorias> call, retrofit2.Response<dto_categorias> response) {
                Log.d("Respuesta", response.body().toString());
            }

            @Override
            public void onFailure(Call<dto_categorias> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
            }
        });
    }

    private void new_categories() {
        et_idcategoria.setText(null);
        et_namecategoria.setText(null);
        sp_estado.setSelection(0);

    }

}
