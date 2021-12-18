package com.willianhdz.proyecto_final_daute.ui.list_recycle_usu;

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
import com.willianhdz.proyecto_final_daute.ui.dts.dto_usuarios;
import com.willianhdz.proyecto_final_daute.ui.user.services.ApiUtils;
import com.willianhdz.proyecto_final_daute.ui.user.services.UserService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListViewUsuario extends Fragment {

    private UserService userService;

    private ListView lst;
    private Button listar;
    ArrayList<String> lista = null;
    ArrayList<dto_usuarios> listaUsers;
    private LinearLayoutCompat resultado;


    public ListViewUsuario() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_list_view_usuario, container, false);
        ConstraintLayout frameLayout1 = root.findViewById(R.id.constraintLayout);
        final LinearLayoutCompat frameLayout2 = root.findViewById(R.id.fm2);

        lst = root.findViewById(R.id.lstUsuario);
        resultado = root.findViewById(R.id.fm2);

        userService = ApiUtils.getUserService();

        recibirAllUsers();
        return root;
    }

    private void recibirAllUsers(){
        dto_usuarios user = new dto_usuarios();
        listaUsers = new ArrayList<dto_usuarios>();
        lista = new ArrayList<String>();

        userService.getUsers().enqueue(new Callback<List<dto_usuarios>>() {
            @Override
            public void onResponse(Call<List<dto_usuarios>> call, Response<List<dto_usuarios>> response) {
                listaUsers.addAll(response.body());
                for (int i = 0; i < listaUsers.size() ; i++) {
                    lista.add("\n**       **\n"+ "Id usuario: "+listaUsers.get(i).getId() + "\nNombre: " + listaUsers.get(i).getNombre() + "\nApellidos " + listaUsers.get(i).getApellido() + "\nCorreo: "
                        + listaUsers.get(i).getCorreo() + "\nUsuario: " + listaUsers.get(i).getUsuario() + "\nClave: " + listaUsers.get(i).getClave() + "\nTipo: "
                         + listaUsers.get(i).getTipo() + "\nEstado " + listaUsers.get(i).getEstado() + "\nPregunta: " + listaUsers.get(i).getPregunta() + "\nRespuesta: "
                        + listaUsers.get(i).getRespuesta() + "\nFecha de registro: " + listaUsers.get(i).getFecha_registro());

                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, lista);
                    lst.setAdapter(adapter);

                    lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                            @Override
                            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                                String id = "" + listaUsers.get(i).getId();
                                String nombre = "" + listaUsers.get(i).getNombre();
                                String apellidos = "" + listaUsers.get(i).getApellido();
                                String correo = "" + listaUsers.get(i).getCorreo();
                                String usuario = "" + listaUsers.get(i).getUsuario();
                                String clave = "" + listaUsers.get(i).getClave();
                                String tipo = "" + listaUsers.get(i).getTipo();
                                String estado = "" + listaUsers.get(i).getEstado();
                                String pregunta = "" + listaUsers.get(i).getPregunta();
                                String respuesta = "" + listaUsers.get(i).getRespuesta();
                                String fecha = "" + listaUsers.get(i).getFecha_registro();

                                Intent intent = new Intent(getActivity(), DetalleUsuario.class);
                                intent.putExtra("id", id);
                                intent.putExtra("nombre", nombre);
                                intent.putExtra("apellido", apellidos);
                                intent.putExtra("correo", correo);
                                intent.putExtra("usuario", usuario);
                                intent.putExtra("clave", clave);
                                intent.putExtra("tipo", tipo);
                                intent.putExtra("estado", estado);
                                intent.putExtra("pregunta", pregunta);
                                intent.putExtra("respuesta", respuesta);
                                intent.putExtra("fecha", fecha);
                                startActivity(intent);
                            }
                        });

                }
                Log.d("Respuesta", response.body().toString());
            }

            @Override
            public void onFailure(Call<List<dto_usuarios>> call, Throwable t) {
                Log.d("Error: ", t.toString());
            }
        });
    }

}