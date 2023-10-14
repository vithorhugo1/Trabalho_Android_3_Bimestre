package com.example.forcavenda.Models;

public class Endereco {

    private int codigoEnd;
    private String logradouro;
    private int numero;
    private String bairro;
    private String Cidade;
    private String uf;

    public Endereco() {}

    public Endereco(int codigoEnd, String logradouro, int numero, String bairro, String cidade, String uf) {
        this.codigoEnd = codigoEnd;
        this.logradouro = logradouro;
        this.numero = numero;
        this.bairro = bairro;
        Cidade = cidade;
        this.uf = uf;
    }

    public int getCodigoEnd() {
        return codigoEnd;
    }

    public void setCodigoEnd(int codigoEnd) {
        this.codigoEnd = codigoEnd;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return Cidade;
    }

    public void setCidade(String cidade) {
        Cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    @Override
    public String toString() {
        return getLogradouro() + ", " + getNumero();
    }
}
