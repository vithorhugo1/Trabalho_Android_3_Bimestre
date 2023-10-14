package com.example.forcavenda.Controller;

import android.content.Context;

import com.example.forcavenda.DAO.ClienteDao;
import com.example.forcavenda.Models.Cliente;

import java.util.ArrayList;

public class ClienteController {

    private ClienteDao clienteDao;

    public ClienteController(Context context) {
        clienteDao = ClienteDao.getInstancia(context);
    }

    public long inserirCliente(Cliente cliente) {
        return clienteDao.insert(cliente);
    }

    public long atualizarCliente(Cliente cliente) {
        return clienteDao.update(cliente);
    }

    public long deletarCliente(Cliente cliente) {
        return clienteDao.delete(cliente);
    }

    public ArrayList<Cliente> listarClientes() {
        return clienteDao.getAll();
    }

    public Cliente buscarClientePorId(int id) {
        return clienteDao.getbyId(id);
    }
}
