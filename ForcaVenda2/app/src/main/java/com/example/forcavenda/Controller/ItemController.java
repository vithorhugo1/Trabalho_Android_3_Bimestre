package com.example.forcavenda.Controller;

import android.content.Context;

import com.example.forcavenda.DAO.ItemDao;
import com.example.forcavenda.Models.Item;

import java.util.ArrayList;

public class ItemController {

    private ItemDao itemDao;

    public ItemController(Context context) {
        itemDao = ItemDao.getInstancia(context);
    }

    public long inserirItem(Item item) {
        return itemDao.insert(item);
    }

    public long atualizarItem(Item item) {
        return itemDao.update(item);
    }

    public long deletarItem(Item item) {
        return itemDao.delete(item);
    }

    public ArrayList<Item> listarItens() {
        return itemDao.getAll();
    }

    public Item buscarItemPorId(int id) {
        return itemDao.getbyId(id);
    }

    public String getDescricaoItemPorId(int id) {
        Item item = buscarItemPorId(id);
        if (item != null) {
            return item.getDescricao();
        }
        return ""; // ou algum valor padrão ou mensagem de erro
    }

}
