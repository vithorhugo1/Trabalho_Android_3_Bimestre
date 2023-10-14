package com.example.forcavenda.Controller;

import android.content.Context;

import com.example.forcavenda.DAO.EnderecoDao;
import com.example.forcavenda.Models.Endereco;

import java.util.ArrayList;

public class EnderecoController {

    private EnderecoDao enderecoDao;

    public EnderecoController(Context context) {
        enderecoDao = EnderecoDao.getInstance(context);
    }

    public long inserirEndereco(Endereco endereco) {
        return enderecoDao.insert(endereco);
    }

    public long atualizarEndereco(Endereco endereco) {
        return enderecoDao.update(endereco);
    }

    public long deletarEndereco(Endereco endereco) {
        return enderecoDao.delete(endereco);
    }

    public ArrayList<Endereco> listarEnderecos() {
        return enderecoDao.getAll();
    }

    public Endereco buscarEnderecoPorId(int id) {
        return enderecoDao.getbyId(id);
    }
}
