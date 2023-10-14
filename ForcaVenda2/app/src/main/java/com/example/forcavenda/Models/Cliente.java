package com.example.forcavenda.Models;

import java.util.Date;

public class Cliente {

    private int codigoCliente;
    private String nome;
    private String cpf;
    private String dtNasc;

    private int codEndereco;

    public Cliente() {}

    public Cliente(int codigoCliente, String nome, String cpf, String dtNasc, int codEndereco) {
        this.codigoCliente = codigoCliente;
        this.nome = nome;
        this.cpf = cpf;
        this.dtNasc = dtNasc;
        this.codEndereco = codEndereco;
    }

    public int getCodigo() {
        return codigoCliente;
    }

    public void setCodigo(int codigo) {
        this.codigoCliente = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getDtNasc() {
        return dtNasc;
    }

    public void setDtNasc(String dtNasc) {
        this.dtNasc = dtNasc;
    }

    public int getCodEndereco() {
        return codEndereco;
    }

    public void setCodEndereco(int codEndereco) {
        this.codEndereco = codEndereco;
    }
}
