package com.willianhdz.proyecto_final_daute.ui.list_recycle_cate;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.willianhdz.proyecto_final_daute.R;

import com.willianhdz.proyecto_final_daute.ui.dts.dto_categorias;
import com.willianhdz.proyecto_final_daute.ui.user.remote.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.remote.CategoryService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardFragment extends Fragment {

    private CategoryService categoryService;

    ArrayList<String> lista = null;
    ArrayList<dto_categorias> listaCategoria;

    private RecyclerView recyclerView;
    private AdaptadorCategoria adaptadorCategoria;

    private DashboardViewModel dashboardViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        dashboardViewModel =
                ViewModelProviders.of(this).get(DashboardViewModel.class);
        View root = inflater.inflate(R.layout.fragment_dashboard, container, false);

        recyclerView = root.findViewById(R.id.rvProd);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

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

                adaptadorCategoria = new AdaptadorCategoria(getContext(), listaCategoria);

                recyclerView.setAdapter(adaptadorCategoria);
            }

            @Override
            public void onFailure(Call<List<dto_categorias>> call, Throwable t) {
                Log.d("Error: ", t.toString());
            }
        });
    }
}