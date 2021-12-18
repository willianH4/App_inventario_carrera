package com.willianhdz.proyecto_final_daute.ui.list_recycle_cate;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.willianhdz.proyecto_final_daute.ui.dts.dto_categorias;
import com.willianhdz.proyecto_final_daute.R;

import java.util.List;


public class AdaptadorCategoria extends RecyclerView.Adapter<AdaptadorCategoria.ProductosViewHolder> {

    private Context mCtx;
    private List<dto_categorias> productoList;

    public AdaptadorCategoria(Context mCtx, List<dto_categorias> productoListList) {
        this.mCtx = mCtx;
        this.productoList = productoListList;
    }

    @NonNull
    @Override
    public ProductosViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_recyclerview, null);
        return new ProductosViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductosViewHolder holder, int position) {
        dto_categorias producto = productoList.get(position);
        holder.tvCodigoProducto.setText(String.valueOf(producto.getId_categoria()));
        holder.tvNombreProducto.setText(producto.getNom_categoria());
        holder.tvPrecioProducto.setText(String.valueOf(producto.getEstado_categoria()));
    }

    @Override
    public int getItemCount() {
        return productoList.size();
    }

    public class ProductosViewHolder extends RecyclerView.ViewHolder {

        TextView tvCodigoProducto, tvNombreProducto, tvPrecioProducto;
        public ProductosViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCodigoProducto = itemView.findViewById(R.id.textViewCodigo1);
            tvNombreProducto = itemView.findViewById(R.id.textViewDescripcion1);
            tvPrecioProducto = itemView.findViewById(R.id.textViewPrecio1);

        }
    }

}
