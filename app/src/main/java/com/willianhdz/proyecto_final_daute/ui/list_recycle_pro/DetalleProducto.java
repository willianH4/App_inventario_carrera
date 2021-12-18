package com.willianhdz.proyecto_final_daute.ui.list_recycle_pro;

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
import com.willianhdz.proyecto_final_daute.ui.dts.dto_productos;
import com.willianhdz.proyecto_final_daute.ui.producto.Edit_Producto;
import com.willianhdz.proyecto_final_daute.ui.user.services.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.services.ProductService;

import retrofit2.Call;
import retrofit2.Callback;


public class DetalleProducto extends AppCompatActivity implements View.OnClickListener {

    private ProductService productService;

    private TextView tvcodigo, tvNombre, tvDescrip, tvStock, tvprecio, tvUnidad, tvEstado, tvcategori, tvfecha;
    private Button btnEditarPro, btnBorrarPro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_producto);

        productService = ApiUtils.getProductService();

        tvcodigo = findViewById(R.id.tvCodigoProDetalle);
        tvNombre = findViewById(R.id.tvNombreProDetalle);
        tvDescrip = findViewById(R.id.tvDescProDetalle);
        tvStock = findViewById(R.id.tvStockProDetalle);
        tvprecio = findViewById(R.id.tvPrecioProDetalle);
        tvUnidad = findViewById(R.id.tvUnidadProDetalle);
        tvEstado = findViewById(R.id.tvEstadoProDetalle);
        tvcategori = findViewById(R.id.tvCategoriaProDetalle);
        tvfecha = findViewById(R.id.tvFechaProDetalle);

        btnEditarPro = findViewById(R.id.btnEditarCat);
        btnBorrarPro = findViewById(R.id.btnBorrarCat);

        Bundle bundle = getIntent().getExtras();
        String id = bundle.getString("codigo");
        String noma = bundle.getString("nombre");
        String des = bundle.getString("des");
        String stock = bundle.getString("stock");
        String pre = bundle.getString("precio");
        String uni = bundle.getString("unidad");
        String esta = bundle.getString("estado");
        String cat = bundle.getString("cate");
        String fech = bundle.getString("fecha");

        tvcodigo.setText(id);
        tvNombre.setText(noma);
        tvDescrip.setText(des);
        tvStock.setText(stock);
        tvprecio.setText(pre);
        tvUnidad.setText(uni);
        tvEstado.setText(esta);
        tvcategori.setText(cat);
        tvfecha.setText(fech);

        btnEditarPro.setOnClickListener(this);
        btnBorrarPro.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String id = tvcodigo.getText().toString();
        if (view.getId() == R.id.btnBorrarCat){
            delete_productos(Integer.parseInt(id));
            //Toast.makeText(this, "Hole Borrado", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        } else if(view.getId() == R.id.btnEditarCat){
            //Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            String code = tvcodigo.getText().toString();
            String name = tvNombre.getText().toString();
            String des = tvDescrip.getText().toString();
            String stock = tvStock.getText().toString();
            String pre = tvprecio.getText().toString();
            String uni = tvUnidad.getText().toString();
            String est = tvEstado.getText().toString();
            String cate = tvcategori.getText().toString();
            String fecha = tvfecha.getText().toString();

            Intent intent = new Intent(getApplicationContext(), Edit_Producto.class);
            intent.putExtra("senal", "1");
            intent.putExtra("codigo", code);
            intent.putExtra("nombre", name);
            intent.putExtra("des", des);
            intent.putExtra("stock", stock);
            intent.putExtra("precio", pre);
            intent.putExtra("unidad", uni);
            intent.putExtra("estado", est);
            intent.putExtra("cate", cate);
            intent.putExtra("fecha", fecha);
            startActivity(intent);
        }

    }

    private void delete_productos(int id_producto){
        productService.deleteProduct(id_producto).enqueue(new Callback<dto_productos>() {
            @Override
            public void onResponse(Call<dto_productos> call, retrofit2.Response<dto_productos> response) {
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
            public void onFailure(Call<dto_productos> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
                Toast.makeText(getApplicationContext(), "Algo fallo!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}