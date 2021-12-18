package com.willianhdz.proyecto_final_daute.ui.categoria;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.willianhdz.proyecto_final_daute.MainActivity;
import com.willianhdz.proyecto_final_daute.R;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_categorias;
import com.willianhdz.proyecto_final_daute.ui.user.remote.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.remote.CategoryService;

import retrofit2.Call;
import retrofit2.Callback;

public class Edit_Categoria extends AppCompatActivity {

private CategoryService categoryService;

private EditText edtCode, edtNombre;
private Spinner spinner;
private Button btnUpdate, btnSalir;
String datoSelect = "";
int posi =0;

@Override
protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_edit__categoria);

    categoryService = ApiUtils.getCategoryService();

    //referencias
    edtCode = findViewById(R.id.edtCategoriaUp);
    edtNombre = findViewById(R.id.edtNombreCategoriaUp);
    spinner = findViewById(R.id.sp_estadoUp);
    btnUpdate = findViewById(R.id.btnUpdate);
    btnSalir = findViewById(R.id.btnSalir);

   btnSalir.setOnClickListener(view -> {
       Intent intent = new Intent(Edit_Categoria.this, MainActivity.class);
       startActivity(intent);
   });

    ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
            R.array.estadoCategorias, android.R.layout.simple_spinner_item);
    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    spinner.setAdapter(adapter);
    spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            if (spinner.getSelectedItemPosition()>0){
                datoSelect = spinner.getSelectedItem().toString();
            } else {
                datoSelect = "";
            }
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    });

    String senal = "";
    String codigo = "";
    String nombre = "";
    String estad = "";
    int suma = 0;

    try {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        if (bundle != null){
            codigo = bundle.getString("codigo");
            senal = bundle.getString("senal");
            nombre = bundle.getString("nombre");
            estad = bundle.getString("estado");

            if (senal.equals("1")){
                edtCode.setText(codigo);
                edtNombre.setText(nombre);
                suma = Integer.parseInt(estad);
                 spinner.setSelection(suma);
            }
        }
    } catch (Exception ex){
        ex.printStackTrace();
    }

    btnUpdate.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String id = edtCode.getText().toString();
            String nombre = edtNombre.getText().toString();

            if (id.length() == 0){
                edtCode.setError("Por favor introduzca el Id");
            } else if (nombre.length() == 0){
                edtNombre.setError("Por favor escriba el nombre de la categoria");
            } else if (spinner.getSelectedItemPosition() > 0){
                //this action save in the BD
                String est = (String) spinner.getSelectedItem();
                update_server(Integer.parseInt(id), nombre, Integer.parseInt(est));
            } else {
                Toast.makeText(getApplicationContext(), "Seleccione un estado para la categoria", Toast.LENGTH_SHORT).show();
            }
        }
    });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(com.willianhdz.proyecto_final_daute.ui.categoria.Edit_Categoria.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void update_server(int id_categoria, String nom_categoria, int estado_categoria) {
        categoryService.updateCategory(id_categoria, nom_categoria, estado_categoria).enqueue(new Callback<dto_categorias>() {
            @Override
            public void onResponse(Call<dto_categorias> call, retrofit2.Response<dto_categorias> response) {
                Log.d("Respuesta", response.body().toString());
                Log.d("estado", String.valueOf(response.body().getEstado()));
                int estado = response.body().getEstado();
                if (estado == 1){
                    Toast.makeText(getApplicationContext(), "Registro actualizado exitosamente con Retrofit!", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Registro no actualizado! Retrofit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<dto_categorias> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
                Toast.makeText(getApplicationContext(), "Algo fallo!", Toast.LENGTH_SHORT).show();
            }
        });
    }

}