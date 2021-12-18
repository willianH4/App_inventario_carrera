package com.willianhdz.proyecto_final_daute.ui.list_recycle_pro;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.willianhdz.proyecto_final_daute.R;
import com.willianhdz.proyecto_final_daute.ui.dts.dto_productos;

import java.util.List;

public class ProductoAdapter extends RecyclerView.Adapter<ProductoAdapter.ProductoHolder>{

    private Context mCtx;
    List<dto_productos> listaProductos;

    public ProductoAdapter(Context mCtx, List<dto_productos> listaProductos){
        this.mCtx = mCtx;
        this.listaProductos = listaProductos;
    }

    @Override
    public ProductoHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.productos_recyclerview,parent,false);
        RecyclerView.LayoutParams layoutParams = new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        vista.setLayoutParams(layoutParams);
        return new ProductoHolder(vista);
    }

    @Override
    public void onBindViewHolder(ProductoHolder holder, int position) {
        dto_productos dto = listaProductos.get(position);
        holder.tvId.setText(String.valueOf(dto.getId_producto()));
        holder.tvNombre.setText(dto.getNom_producto());
        holder.tvDescripcion.setText(dto.getDes_producto());
        holder.tvStock.setText(String.valueOf(dto.getStock()));
        holder.tvPrecio.setText(String.valueOf(dto.getPrecio()));
        holder.tvUnidad.setText(dto.getUnidad_de_medida());
        holder.tvEstado.setText(String.valueOf(dto.getEstado_producto()));
        holder.tvCategoria.setText(String.valueOf(dto.getNom_categoria()));
        holder.tvFecha.setText(dto.getFecha_entrada());
    }

    @Override
    public int getItemCount() {
        return listaProductos.size();
    }

    public class ProductoHolder extends RecyclerView.ViewHolder{

        TextView tvId, tvNombre, tvDescripcion, tvStock, tvPrecio, tvUnidad, tvEstado, tvCategoria, tvFecha;

        public ProductoHolder(View itemView) {
            super(itemView);
            tvId = (TextView) itemView.findViewById(R.id.tvId);
            tvNombre = (TextView) itemView.findViewById(R.id.tvNombre);
            tvDescripcion = (TextView) itemView.findViewById(R.id.tvDescripcion);
            tvStock = (TextView) itemView.findViewById(R.id.tvStock);
            tvPrecio = (TextView) itemView.findViewById(R.id.tvPrecio);
            tvUnidad = (TextView) itemView.findViewById(R.id.tvUnidad);
            tvEstado = (TextView) itemView.findViewById(R.id.tvEstado);
            tvCategoria = (TextView) itemView.findViewById(R.id.tvCategoria);
            tvFecha = (TextView) itemView.findViewById(R.id.tvFecha);
        }
    }
}
