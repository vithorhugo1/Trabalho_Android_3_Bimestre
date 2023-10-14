package com.example.forcavenda.DAO;

import com.example.forcavenda.Controller.ItemController;
import com.example.forcavenda.Models.Item;
import com.example.forcavenda.Models.ItemPedido;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.forcavenda.Helper.SQLiteDataHelper;
import com.example.forcavenda.Models.ItemPedido;
import com.example.forcavenda.Models.Pedido;

import java.util.ArrayList;

public class ItemPedidoDao implements GenericDao<ItemPedido> {

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase bd;
    private String[] colunas = {"ID", "PEDIDOID", "ITEMID", "QUANTIDADE", "VALORUNITARIO"};
    private String tableName = "ITEMPEDIDO";
    private Context context;
    private static ItemPedidoDao instancia;

    private ItemPedidoDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 2);
        bd = openHelper.getWritableDatabase();
    }

    public static ItemPedidoDao getInstancia(Context context) {
        if (instancia == null)
            return instancia = new ItemPedidoDao(context);
        else
            return instancia;
    }


    @Override
    public long insert(ItemPedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("PEDIDOID", obj.getPedido().getId());
            valores.put("ITEMID", obj.getItem().getCodigoItem()); // Alteração aqui
            valores.put("QUANTIDADE", obj.getQuantidade());
            valores.put("VALORUNITARIO", obj.getValorUnitario());

            return bd.insert(tableName, null, valores);
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemPedidoDAO.insert(): " + ex.getMessage());
        }
        return -1;
    }


    @Override
    public long update(ItemPedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("PEDIDOID", obj.getPedido().getId());
            valores.put("ITEMID", obj.getItem().getCodigoItem()); // Alteração aqui
            valores.put("QUANTIDADE", obj.getQuantidade());
            valores.put("VALORUNITARIO", obj.getValorUnitario());

            String[] identificador = {String.valueOf(obj.getId())};
            return bd.update(tableName, valores, "ID = ?", identificador);
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemPedidoDAO.update(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(ItemPedido obj) {
        try {
            String[] identification = {String.valueOf(obj.getId())};
            return bd.delete(tableName, "ID = ?", identification);
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemPedidoDAO.delete(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<ItemPedido> getAll() {
        ArrayList<ItemPedido> lista = new ArrayList<>();
        ItemController itemController = new ItemController(context); // Adicionado para acessar os métodos do ItemController
        try {
            Cursor cursor = bd.query(tableName, colunas, null, null, null, null, "ID asc");
            if (cursor.moveToFirst()) {
                do {
                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setId(cursor.getInt(0));

                    Pedido pedido = new Pedido(); // Criamos um objeto Pedido apenas com o ID
                    pedido.setId(cursor.getInt(1));
                    itemPedido.setPedido(pedido); // Associamos o Pedido ao ItemPedido

                    Item item = itemController.buscarItemPorId(cursor.getInt(2)); // Buscamos o objeto Item completo
                    itemPedido.setItem(item); // Associamos o Item ao ItemPedido

                    itemPedido.setQuantidade(cursor.getInt(3));
                    itemPedido.setValorUnitario(cursor.getDouble(4));

                    lista.add(itemPedido);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemPedidoDAO.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public ItemPedido getbyId(int id) {
        ItemPedido itemPedido = null;
        ItemController itemController = new ItemController(context); // Adicionado para acessar os métodos do ItemController
        try {
            Cursor cursor = bd.query(tableName, colunas, "ID = ?", new String[]{String.valueOf(id)}, null, null, null);
            if (cursor.moveToFirst()) {
                itemPedido = new ItemPedido();
                itemPedido.setId(cursor.getInt(0));

                Pedido pedido = new Pedido(); // Criamos um objeto Pedido apenas com o ID
                pedido.setId(cursor.getInt(1));
                itemPedido.setPedido(pedido); // Associamos o Pedido ao ItemPedido

                Item item = itemController.buscarItemPorId(cursor.getInt(2)); // Buscamos o objeto Item completo
                itemPedido.setItem(item); // Associamos o Item ao ItemPedido

                itemPedido.setQuantidade(cursor.getInt(3));
                itemPedido.setValorUnitario(cursor.getDouble(4));
            }
        } catch (Exception ex) {
            Log.e("ERRO", "ItemPedidoDAO.getById(): " + ex.getMessage());
        }
        return itemPedido;
    }
    }
