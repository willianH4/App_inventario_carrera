package com.willianhdz.proyecto_final_daute.ui.producto;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.willianhdz.proyecto_final_daute.MainActivity;
import com.willianhdz.proyecto_final_daute.R;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_categorias;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_productos;
import com.google.android.material.textfield.TextInputLayout;
import com.willianhdz.proyecto_final_daute.ui.user.services.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.services.CategoryService;
import com.willianhdz.proyecto_final_daute.ui.user.services.ProductService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Edit_Producto extends AppCompatActivity {

    private ProductService productService;
    private CategoryService categoryService;

    private TextInputLayout ti_id, ti_nombre_prod, ti_descripcion, ti_stock,
            ti_precio, ti_unidadmedida;
    private EditText et_id, et_nombre_prod, et_descripcion, et_stock,
            et_precio, et_unidadmedida;
    private Spinner sp_estadoProductos, sp_fk_categoria;
    private TextView tv_fechahora;
    private Button  btnEditar, btnSalir;
    ProgressDialog progressDialog;
    ArrayList<String> lista = null;
    ArrayList<dto_categorias> listaCategorias; //Va a representar la información que se va a mostrar en el combo
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
    String idcategoria = "";
    String nombrecategoria = "";
    int conta = 0;
    String datoStatusProduct = "";
    //Instancia DTO
    dto_productos dto = new dto_productos();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_producto);

        productService = ApiUtils.getProductService();

        categoryService = ApiUtils.getCategoryService();

        ti_id = findViewById(R.id.ti_id);
        ti_nombre_prod = findViewById(R.id.ti_nombre_prod);
        ti_descripcion = findViewById(R.id.ti_descripcion);
        et_id = findViewById(R.id.et_id);
        et_nombre_prod = findViewById(R.id.et_nombre_prod);
        et_descripcion = findViewById(R.id.et_descripcion);
        et_stock = findViewById(R.id.et_stock);
        et_precio = findViewById(R.id.et_precio);
        et_unidadmedida = findViewById(R.id.et_unidadmedida);
        sp_fk_categoria = findViewById(R.id.sp_fk_categoria);
        tv_fechahora = findViewById(R.id.tv_fechahora);
        tv_fechahora.setText(timedate());

        btnEditar = findViewById(R.id.btnEditarPro);
        btnSalir = findViewById(R.id.btnSalir);
        sp_estadoProductos = findViewById(R.id.sp_estadoProductos);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getApplicationContext(),
                R.array.estadoProductos, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sp_estadoProductos.setOnItemSelectedListener(new
         AdapterView.OnItemSelectedListener() {
             @Override
             public void onItemSelected(AdapterView<?> parent, View view, int
                     position, long id) {
                 if(sp_estadoProductos.getSelectedItemPosition()>0)
                     datoStatusProduct = sp_estadoProductos.getSelectedItem().toString();
                 else{
                     datoStatusProduct = "";
                 }
             }
             @Override
             public void onNothingSelected(AdapterView<?> parent) {
             }
         });
    fk_categorias(getApplicationContext());
    sp_fk_categoria.setOnItemSelectedListener(new
      AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int
                  position, long id) {
              if(conta>=1 && sp_fk_categoria.getSelectedItemPosition()>0){
                  String item_spinner= sp_fk_categoria.getSelectedItem().toString();
                  //Hago una busqueda en la cadena seleccionada en elspinner para separar el idcategoria y el nombre de la dto_categorias
                  //Esto es necesario, debido a que lo que debe enviarse aguardar a la base de datos es únicamente el idcategoria.
                  String s[] = item_spinner.split("~");
                  //Dato ID CATEGORIA
                  idcategoria = s[0].trim();
                  //Con trim eliminoespacios al inicio y final de la cadena para enviar limpio el ID CATEGORIA.
                  //Dato NOMBRE DE LA CATEGORIA
                  nombrecategoria = s[1];
                  Toast toast = Toast.makeText(getApplicationContext(), "Id cat: " + idcategoria + "\n" + "Nombre Categoria: "+nombrecategoria,Toast.LENGTH_SHORT);
                  toast.setGravity(Gravity.CENTER, 0, 0);toast.show();
              }else{
                  idcategoria = "";
                  nombrecategoria = "";
              }
              conta++;
          }
          @Override
          public void onNothingSelected(AdapterView<?> parent) {
          }
      });

        String senal = "";
        String codigo = "";
        String nombre = "";

        String descrip = "";
        String stock = "";
        String precio = "";
        String unidad = "";
        String cate = "";
        String fecha = "";
        String estad = "";

        try {
            Intent intent = getIntent();
            Bundle bundle = intent.getExtras();
            if (bundle != null){
                codigo = bundle.getString("codigo");
                senal = bundle.getString("senal");
                nombre = bundle.getString("nombre");
                descrip = bundle.getString("des");
                stock = bundle.getString("stock");
                precio = bundle.getString("precio");
                unidad = bundle.getString("unidad");
                cate = bundle.getString("cate");
                estad = bundle.getString("estado");

                if (senal.equals("1")){
                    et_id.setText(codigo);
                    et_nombre_prod.setText(nombre);
                    et_descripcion.setText(descrip);
                    et_stock.setText(stock);
                    et_precio.setText(precio);
                    et_unidadmedida.setText(unidad);
                    sp_estadoProductos.setSelection(1+Integer.parseInt(estad));
                    sp_fk_categoria.setSelection(Integer.parseInt(cate));
                }
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

         btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et_id.getText().toString();
                String nombre = et_nombre_prod.getText().toString();
                String descripcion = et_descripcion.getText().toString();
                String stock = et_stock.getText().toString();
                String precio = et_precio.getText().toString();
                String unidad = et_unidadmedida.getText().toString();
                if(id.length() == 0){
                    et_id.setError("Campo obligatorio.");
                }else if(nombre.length() == 0){
                    et_nombre_prod.setError("Campo obligatorio.");
                }else if(descripcion.length() == 0){
                    et_descripcion.setError("Campo obligatorio.");
                }else if(stock.length() == 0){
                    et_stock.setError("Campo obligatorio.");
                }else if(precio.length() == 0){
                    et_precio.setError("Campo obligatorio.");
                }else if(unidad.length() == 0){
                    et_unidadmedida.setError("Campo obligatorio.");
                }else if(sp_estadoProductos.getSelectedItemPosition() == 0){
                    Toast.makeText(getApplicationContext(), "Debe seleccionar el estado del dto_productos.", Toast.LENGTH_SHORT).show();
                }else if(sp_fk_categoria.getSelectedItemPosition() > 0){
                    String esta = sp_estadoProductos.getSelectedItem().toString();
                    update_productos(Integer.parseInt(id), nombre, descripcion, Double.parseDouble(stock),
                            Double.parseDouble(precio), unidad, Integer.parseInt(esta), Integer.parseInt(idcategoria));
                    Intent intent = new Intent(Edit_Producto.this, MainActivity.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "Debe seleccionar la dto_categorias.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Edit_Producto.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }


    private void update_productos(int id_producto, String nom_producto, String des_producto, double stock,
                                  double precio, String unidad_de_medida, int estado_producto, int categoria){
        productService.updateProduct(id_producto, nom_producto, des_producto, stock, precio, unidad_de_medida, estado_producto,
                categoria).enqueue(new Callback<dto_productos>() {
            @Override
            public void onResponse(Call<dto_productos> call, retrofit2.Response<dto_productos> response) {
                Log.d("Respuesta", response.body().toString());
                Log.d("estado", String.valueOf(response.body().getEstado()));
                int estado = response.body().getEstado();
                if (estado == 1){
                    Toast.makeText(getApplicationContext(), "Registro actualizado exitosamente! Retrofit", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Registro no actualizado! Retrofit", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<dto_productos> call, Throwable t) {
                Log.d("Fallo", call.toString() + t.toString());
                Toast.makeText(getApplicationContext(), "Algo fallo!", Toast.LENGTH_SHORT).show();
            }
        });
    }


    private String timedate(){
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss a");
        String fecha = sdf.format(cal.getTime());
        return fecha;
    }

    public void fk_categorias(final Context context){
        listaCategorias = new ArrayList<dto_categorias>();
        lista = new ArrayList<String>();
        lista.add("Seleccione Categoria");

        categoryService.getCategories().enqueue(new Callback<List<dto_categorias>>() {
            @Override
            public void onResponse(Call<List<dto_categorias>> call, Response<List<dto_categorias>> response) {
                Log.d("Response", String.valueOf(response.body()));
                listaCategorias.addAll(response.body());

                for (int i = 0; i < listaCategorias.size(); i++) {
                    lista.add(listaCategorias.get(i).getId_categoria()+" ~ "+listaCategorias.get(i).getNom_categoria());

                    ArrayAdapter<String> adaptador =new ArrayAdapter<String> (getApplicationContext(),android.R.layout.simple_spinner_item, lista);
                    sp_fk_categoria.setAdapter(adaptador);
                }
            }

            @Override
            public void onFailure(Call<List<dto_categorias>> call, Throwable t) {
                Log.d("Error", t.toString());
            }
        });
    }

}