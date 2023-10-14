package com.example.forcavenda.Adapters;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.forcavenda.Controller.ItemController;
import com.example.forcavenda.Models.ItemPedido;
import com.example.forcavenda.R;

import java.util.List;

public class ItemPedidoAdapter extends RecyclerView.Adapter<ItemPedidoAdapter.ViewHolder> {
    private List<ItemPedido> itemPedidos;
    private ItemController itemController;


    public ItemPedidoAdapter(List<ItemPedido> itemPedidos, ItemController itemController) {
        this.itemPedidos = itemPedidos;
        this.itemController = itemController;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_pedido, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ItemPedido itemPedido = itemPedidos.get(position);
        holder.tvDescricao.setText("Descrição: " + itemPedido.getItem().getDescricao());
        holder.tvQuantidade.setText("Quantidade: " + itemPedido.getQuantidade());
        if (itemPedido != null && itemPedido.getItem() != null) {
            if (holder.tvDescricao != null) {
                holder.tvDescricao.setText("Descrição: " + itemPedido.getItem().getDescricao());
            }
            if (holder.tvQuantidade != null) {
                holder.tvQuantidade.setText("Quantidade: " + itemPedido.getQuantidade());
            }
            if (holder.tvValorUnitario != null) {
                holder.tvValorUnitario.setText("Valor Unitário: " + itemPedido.getValorUnitario());
            }
        }
    }

    @Override
    public int getItemCount() {
        return itemPedidos.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvDescricao;
        public TextView tvQuantidade;
        public TextView tvValorUnitario;

        public ViewHolder(View itemView) {
            super(itemView);
            tvDescricao = itemView.findViewById(R.id.tvDescricao);
            tvQuantidade = itemView.findViewById(R.id.tvQuantidade);
            tvValorUnitario = itemView.findViewById(R.id.tvValorUnitario);
        }
    }
}

