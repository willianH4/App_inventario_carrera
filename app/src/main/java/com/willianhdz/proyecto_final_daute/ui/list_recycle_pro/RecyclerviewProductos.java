package com.willianhdz.proyecto_final_daute.ui.list_recycle_pro;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.JsonObjectRequest;
import com.willianhdz.proyecto_final_daute.R;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_productos;
import com.willianhdz.proyecto_final_daute.ui.user.remote.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.remote.ProductService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class RecyclerviewProductos extends Fragment{

    private ProductService productService;

    RecyclerView recyclerViewProductos;
    private ProductoAdapter productoAdapter;
    ArrayList<dto_productos> listaProductos;

    ProgressDialog progressDialog;
    JsonObjectRequest jsonObjectRequest;

    public RecyclerviewProductos() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_recyclerview_productos, container, false);

        productService = ApiUtils.getProductService();

        listaProductos = new ArrayList<>();

        recyclerViewProductos = (RecyclerView) vista.findViewById(R.id.rvProductos);
        recyclerViewProductos.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerViewProductos.setHasFixedSize(true);

        //Agregando el metodo
        cargarService();
        return vista;
    }

    private void cargarService(){
        productService.getProducts().enqueue(new Callback<List<dto_productos>>() {
            @Override
            public void onResponse(Call<List<dto_productos>> call, retrofit2.Response<List<dto_productos>> response) {
                listaProductos.addAll(response.body());

                for (int i = 0; i < listaProductos.size(); i++) {
                    productoAdapter = new ProductoAdapter(getContext(), listaProductos);

                    recyclerViewProductos.setAdapter(productoAdapter);
                }
            }

            @Override
            public void onFailure(Call<List<dto_productos>> call, Throwable t) {
                Log.d("Error: ", t.toString());
            }
        });
    }
}