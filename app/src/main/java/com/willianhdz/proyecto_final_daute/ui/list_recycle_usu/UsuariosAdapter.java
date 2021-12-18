package com.willianhdz.proyecto_final_daute.ui.list_recycle_usu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.willianhdz.proyecto_final_daute.R;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_usuarios;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

class UsuariosAdapter extends RecyclerView.Adapter<com.willianhdz.proyecto_final_daute.ui.list_recycle_usu.UsuariosAdapter.UsuarioHolder> {

    private Context mCtx;
    List<dto_usuarios> listaUsers;

    public UsuariosAdapter(Context mCtx, ArrayList<dto_usuarios> listaUsers) {
        this.mCtx = mCtx;
        this.listaUsers = listaUsers;
    }

    @Override
    public UsuarioHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.usuarios_recyclerview, null);
        return new UsuarioHolder(view);
    }

    @Override
    public void onBindViewHolder(UsuarioHolder holder, int position) {
        dto_usuarios dto = listaUsers.get(position);
        holder.tvId.setText(dto.getId() + "  :  " + dto.getNombre());
        holder.tvNombre.setText(dto.getNombre());
        holder.tvApellidos.setText(dto.getApellido());
        holder.tvCorreo.setText(dto.getCorreo());
        holder.tvUsuario.setText(dto.getUsuario());
        holder.tvClave.setText(dto.getClave());
        holder.tvTipo.setText(String.valueOf(dto.getTipo()));
        holder.tvEstado.setText(String.valueOf(dto.getEstado()));
        holder.tvPregunta.setText(dto.getPregunta());
        holder.tvRespuesta.setText(dto.getRespuesta());
        holder.tvFecha.setText(dto.getFecha_registro());
    }

    @Override
    public int getItemCount() {
        return listaUsers.size();
    }

    public static class UsuarioHolder extends RecyclerView.ViewHolder{

        TextView tvId, tvNombre, tvApellidos, tvCorreo, tvUsuario, tvClave, tvTipo, tvEstado, tvPregunta, tvRespuesta, tvFecha;

        public UsuarioHolder(View itemView) {
            super(itemView);
            tvId = itemView.findViewById(R.id.tvId);
            tvNombre = itemView.findViewById(R.id.tvNombre);
            tvApellidos = itemView.findViewById(R.id.tvApellidos);
            tvCorreo = itemView.findViewById(R.id.tvCorreo);
            tvUsuario = itemView.findViewById(R.id.tvUsuario);
            tvClave = itemView.findViewById(R.id.tvClave);
            tvTipo = itemView.findViewById(R.id.tvTipo);
            tvEstado = itemView.findViewById(R.id.tvEstado);
            tvPregunta = itemView.findViewById(R.id.tvPregunta);
            tvRespuesta = itemView.findViewById(R.id.tvRespuesta);
            tvFecha = itemView.findViewById(R.id.tvFecha);
        }
    }

    @Override
    public void onAttachedToRecyclerView(@NotNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }
}
