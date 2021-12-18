package com.willianhdz.proyecto_final_daute.ui.list_recycle_cate;

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
import com.willianhdz.proyecto_final_daute.ui.categoria.Edit_Categoria;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_categorias;
import com.willianhdz.proyecto_final_daute.ui.user.services.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.services.CategoryService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleCategoria extends AppCompatActivity implements View.OnClickListener {

    private CategoryService categoryService;

    private TextView tvCodigo, tvNombre, tvEstado;
    private Button btnEditarCat, btnBorrarCat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_categoria);
        tvCodigo = findViewById(R.id.tvCodigoCatDetalle);
        tvNombre = findViewById(R.id.tvNombreCatDetalle);
        tvEstado = findViewById(R.id.tvEstadoCatDetalle);
        btnEditarCat = findViewById(R.id.btnEditarCat);
        btnBorrarCat = findViewById(R.id.btnBorrarCat);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("codigo");
        String noma = bundle.getString("nombre");
        String esta = bundle.getString("estado");

        tvCodigo.setText(id);
        tvNombre.setText(noma);
        tvEstado.setText(esta);

        btnEditarCat.setOnClickListener(this);
        btnBorrarCat.setOnClickListener(this);

        categoryService = ApiUtils.getCategoryService();
    }

    @Override
    public void onClick(View view) {
        String id = tvCodigo.getText().toString();
        if (view.getId() == R.id.btnBorrarCat){
            delete_server(Integer.parseInt(id));
            //Toast.makeText(this, "Hole Borrado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if(view.getId() == R.id.btnEditarCat){
            //Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            String code = tvCodigo.getText().toString();
            String name = tvNombre.getText().toString();
            String estado = tvEstado.getText().toString();

            Intent intent = new Intent(getApplicationContext(), Edit_Categoria.class);
            intent.putExtra("senal", "1");
            intent.putExtra("codigo", code);
            intent.putExtra("nombre", name);
            intent.putExtra("estado", estado);
            startActivity(intent);
        }

    }

    private void delete_server(int id_categoria) {
        categoryService.deleteCategory(id_categoria).enqueue(new Callback<dto_categorias>() {
            @Override
            public void onResponse(Call<dto_categorias> call, Response<dto_categorias> response) {
                Log.d("Respuesta", response.body().toString());
                Log.d("estado", String.valueOf(response.body().getEstado()));
                int estado = response.body().getEstado();
                if (estado == 1){
                    Toast.makeText(getApplicationContext(), "Registro borrado exitosamente! Retrofit", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Registro no borrado! Retrofit", Toast.LENGTH_SHORT).show();
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