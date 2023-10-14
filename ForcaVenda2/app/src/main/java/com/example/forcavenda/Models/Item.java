package com.example.forcavenda.Models;

public class Item {

    private int codigoItem;
    private String descricao;
    private Double vlrUnitaro;
    private String unMedida;

    public Item() {}

    public Item(int codigoItem, String descricao, Double vlrUnitaro, String unMedida) {
        this.codigoItem = codigoItem;
        this.descricao = descricao;
        this.vlrUnitaro = vlrUnitaro;
        this.unMedida = unMedida;
    }

    public int getCodigoItem() {
        return codigoItem;
    }

    public void setCodigoItem(int codigoItem) {
        this.codigoItem = codigoItem;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Double getVlrUnitaro() {
        return vlrUnitaro;
    }
    public void setVlrUnitaro(Double vlrUnitaro) {
        this.vlrUnitaro = vlrUnitaro;
    }

    public String getUnMedida() {
        return unMedida;
    }

    public void setUnMedida(String unMedida) {
        this.unMedida = unMedida;
    }
}
