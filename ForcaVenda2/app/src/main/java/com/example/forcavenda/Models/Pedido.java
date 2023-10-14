package com.example.forcavenda.Models;

import java.util.ArrayList;
import java.util.Date;

public class Pedido {

        private int id;
        private int clienteId;
        private Date dataPedido;
        private ArrayList<ItemPedido> itens;

        public Pedido(){}

    public Pedido(int id, int clienteId, Date dataPedido, ArrayList<ItemPedido> itens) {
        this.id = id;
        this.clienteId = clienteId;
        this.dataPedido = dataPedido;
        this.itens = itens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClienteId() {
        return clienteId;
    }

    public void setClienteId(int clienteId) {
        this.clienteId = clienteId;
    }

    public Date getDataPedido() {
        return dataPedido;
    }

    public void setDataPedido(Date dataPedido) {
        this.dataPedido = dataPedido;
    }

    public ArrayList<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(ArrayList<ItemPedido> itens) {
        this.itens = itens;
    }

}



