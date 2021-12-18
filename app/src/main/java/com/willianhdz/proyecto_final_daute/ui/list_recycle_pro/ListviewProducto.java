package com.willianhdz.proyecto_final_daute.ui.list_recycle_pro;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.willianhdz.proyecto_final_daute.R;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_categorias;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_productos;
import com.willianhdz.proyecto_final_daute.ui.user.services.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.services.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListviewProducto extends Fragment {

    private ProductService productService;

    dto_categorias cat = new dto_categorias();
    private ListView lst;
    private Button listar;
    ArrayList<String> lista = null;
    ArrayList<dto_productos> listaProducto;
    private LinearLayoutCompat resultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_listview_producto, container, false);
        ConstraintLayout frameLayout1 = root.findViewById(R.id.constraintLayout2);
        final LinearLayoutCompat frameLayout2 = root.findViewById(R.id.fm3);

        lst = root.findViewById(R.id.lstProducto);
        resultado = root.findViewById(R.id.fm3);

        productService = ApiUtils.getProductService();

        recibirAllPro();
        return root;
    }

    private void recibirAllPro(){
        listaProducto = new ArrayList<dto_productos>();
        lista = new ArrayList<String>();

        productService.getProducts().enqueue(new Callback<List<dto_productos>>() {
            @Override
            public void onResponse(Call<List<dto_productos>> call, Response<List<dto_productos>> response) {
                Log.d("respuesta", String.valueOf(response.body()));
                listaProducto.addAll(response.body());

                for (int i = 0; i < listaProducto.size(); i++) {
                    lista.add("\n**       **\n"+ "Id Producto: "+listaProducto.get(i).getId_producto() + "\nNombre: " + listaProducto.get(i).getNom_producto() + "\nDescripcion "
                                + listaProducto.get(i).getStock() + "\nPrecio: " + listaProducto.get(i).getPrecio() + "\nUnidad de medida: "
                                + listaProducto.get(i).getUnidad_de_medida() + "\nEstado Producto: " + listaProducto.get(i).getEstado_producto() + "\nCategoria: "
                                + listaProducto.get(i).getNom_categoria() + "\nFecha entrada " + listaProducto.get(i).getFecha_entrada());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, lista);
                    lst.setAdapter(adapter);

                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String codigo = "" + listaProducto.get(i).getId_producto();
                            String nombre = "" + listaProducto.get(i).getNom_producto();
                            String des = "" + listaProducto.get(i).getDes_producto();
                            String stock = "" + listaProducto.get(i).getStock();
                            String precio = "" + listaProducto.get(i).getPrecio();
                            String unidad = "" + listaProducto.get(i).getUnidad_de_medida();
                            String estado = "" + listaProducto.get(i).getEstado_producto();
                            String cate = "" + listaProducto.get(i).getCategoria();
                            String fecha = "" + listaProducto.get(i).getFecha_entrada();

                            Intent intent = new Intent(getActivity(), DetalleProducto.class);
                            intent.putExtra("codigo", codigo);
                            intent.putExtra("nombre", nombre);
                            intent.putExtra("des", des);
                            intent.putExtra("stock", stock);
                            intent.putExtra("precio", precio);
                            intent.putExtra("unidad", unidad);
                            intent.putExtra("estado", estado);
                            intent.putExtra("cate", cate);
                            intent.putExtra("fecha", fecha);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<dto_productos>> call, Throwable t) {
                Log.d("Error", t.toString());
                Toast.makeText(getActivity(), "Error en la peticion", Toast.LENGTH_SHORT).show();
            }
        });
    }
}