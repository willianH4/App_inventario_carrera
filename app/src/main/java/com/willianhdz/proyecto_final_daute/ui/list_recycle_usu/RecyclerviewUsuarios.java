package com.willianhdz.proyecto_final_daute.ui.list_recycle_usu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.willianhdz.proyecto_final_daute.R;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_usuarios;
import com.willianhdz.proyecto_final_daute.ui.list_recycle_cate.DashboardViewModel;
import com.willianhdz.proyecto_final_daute.ui.user.remote.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.remote.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RecyclerviewUsuarios extends Fragment {

    private UserService userService;

    ArrayList<String> lista = null;
    ArrayList<dto_usuarios> listaUsers;

    private RecyclerView recyclerView;
    private UsuariosAdapter usuariosAdapter;

    private DashboardViewModel dashboardViewModel;

    public RecyclerviewUsuarios() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        dashboardViewModel = ViewModelProviders.of(this).get(DashboardViewModel.class);

        View root = inflater.inflate(R.layout.fragment_recyclerview_usuarios, container, false);

        recyclerView = root.findViewById(R.id.rvUsuarios);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        userService = ApiUtils.getUserService();

        recibirAllUsers();
        return root;
    }

    private void recibirAllUsers(){
        listaUsers = new ArrayList<dto_usuarios>();
        lista = new ArrayList<String>();

        userService.getUsers().enqueue(new Callback<List<dto_usuarios>>() {
            @Override
            public void onResponse(Call<List<dto_usuarios>> call, Response<List<dto_usuarios>> response) {
                listaUsers.addAll(response.body());

                usuariosAdapter = new UsuariosAdapter(getContext(), listaUsers);

                recyclerView.setAdapter(usuariosAdapter);
            }

            @Override
            public void onFailure(Call<List<dto_usuarios>> call, Throwable t) {
                Log.d("Error: ", t.toString());
            }
        });
    }

}