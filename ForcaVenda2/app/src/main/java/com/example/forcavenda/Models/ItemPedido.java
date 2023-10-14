package com.example.forcavenda.Models;

public class ItemPedido {

    private int id;
    private Pedido pedido;
    private Item item; // Incluído o objeto Item completo
    private int quantidade;
    private double valorUnitario;

    public ItemPedido(){}

    public ItemPedido(int id, Pedido pedido, Item item, int quantidade, double valorUnitario) {
        this.id = id;
        this.pedido = pedido;
        this.item = item; // Ajustado para receber o objeto Item
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
    }

    public Pedido getPedido() {
        return pedido;
    }

    public void setPedido(Pedido pedido) {
        this.pedido = pedido;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter para o objeto Item
    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(double valorUnitario) {
        this.valorUnitario = valorUnitario;
    }
}
