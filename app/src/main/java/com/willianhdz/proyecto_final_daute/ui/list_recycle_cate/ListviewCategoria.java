package com.willianhdz.proyecto_final_daute.ui.list_recycle_cate;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import com.willianhdz.proyecto_final_daute.R;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_categorias;
import com.willianhdz.proyecto_final_daute.ui.user.remote.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.remote.CategoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListviewCategoria extends Fragment {

    private CategoryService categoryService;

    private ListView lst;
    private Button listar;
    ArrayList<String> lista = null;
    ArrayList<dto_categorias> listaCategoria;
    private LinearLayoutCompat resultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_listview_categoria, container, false);
        ConstraintLayout frameLayout1 = root.findViewById(R.id.constraintLayout);
        final LinearLayoutCompat frameLayout2 = root.findViewById(R.id.fm2);

        lst = root.findViewById(R.id.lstCategoria);
        resultado = root.findViewById(R.id.fm2);

        categoryService = ApiUtils.getCategoryService();

        recibirAllCat();
        return root;
    }




    private void recibirAllCat(){

        listaCategoria = new ArrayList<dto_categorias>();
        lista = new ArrayList<String>();

        categoryService.getCategories().enqueue(new Callback<List<dto_categorias>>() {
            @Override
            public void onResponse(Call<List<dto_categorias>> call, Response<List<dto_categorias>> response) {
                listaCategoria.addAll(response.body());
                for (int i = 0; i <listaCategoria.size() ; i++) {
                    lista.add("\n**       **\n"+ "Id Categoria: " + listaCategoria.get(i).getId_categoria() + "\nNombre Categoria: "
                                + listaCategoria.get(i).getNom_categoria() + "\nEstado Categoria " + listaCategoria.get(i).getEstado_categoria());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, lista);
                        lst.setAdapter(adapter);

                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            String codigo = "" + listaCategoria.get(i).getId_categoria();
                            String nombre = "" + listaCategoria.get(i).getNom_categoria();
                            String estado = "" + listaCategoria.get(i).getEstado_categoria();

                            Intent intent = new Intent(getActivity(), DetalleCategoria.class);
                            intent.putExtra("codigo", codigo);
                            intent.putExtra("nombre", nombre);
                            intent.putExtra("estado", estado);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(Call<List<dto_categorias>> call, Throwable t) {
                Log.d("Error: ", t.toString());
            }
        });

    }
}