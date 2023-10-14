package com.example.forcavenda.DAO;

import android.database.sqlite.SQLiteOpenHelper;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.forcavenda.Helper.SQLiteDataHelper;
import com.example.forcavenda.Models.Pedido;

import java.util.Date;

public class PedidoDao implements GenericDao<Pedido>{

    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase bd;
    private String[] colunas = {"ID", "CLIENTEID", "DATAPEDIDO"};
    private String tableName = "PEDIDO";
    private Context context;
    private static PedidoDao instancia;

    private PedidoDao(Context context) {
        this.context = context;
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 2);
        bd = openHelper.getWritableDatabase();
    }

    public static PedidoDao getInstancia(Context context) {
        if (instancia == null)
            return instancia = new PedidoDao(context);
        else
            return instancia;
    }

    @Override
    public long insert(Pedido obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put("ID", obj.getId());
            valores.put("CLIENTEID", obj.getClienteId());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String dataString = formato.format(obj.getDataPedido());
            valores.put("DATAPEDIDO", dataString);

            return bd.insert(tableName, null, valores);
        } catch (SQLException ex) {
            Log.e("ERRO", "PedidoDAO.insert(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long update(Pedido obj) {
        try {
            ContentValues valores = new ContentValues();
            valores.put("CLIENTEID", obj.getClienteId());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            String dataString = formato.format(obj.getDataPedido());
            valores.put("DATAPEDIDO", dataString);

            String[] identificador = {String.valueOf(obj.getId())};
            return bd.update(tableName, valores, "ID = ?", identificador);
        } catch (SQLException ex) {
            Log.e("ERRO", "PedidoDAO.update(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public long delete(Pedido obj) {
        try {
            String[] identification = {String.valueOf(obj.getId())};
            return bd.delete(tableName, "ID = ?", identification);
        } catch (SQLException ex) {
            Log.e("ERRO", "PedidoDAO.delete(): " + ex.getMessage());
        }
        return -1;
    }

    @Override
    public ArrayList<Pedido> getAll() {
        ArrayList<Pedido> lista = new ArrayList<>();
        try {
            Cursor cursor = bd.query(tableName, colunas, null, null, null, null, "ID asc");
            if (cursor.moveToFirst()) {
                do {
                    Pedido pedido = new Pedido();
                    pedido.setId(cursor.getInt(0));
                    pedido.setClienteId(cursor.getInt(1));

                    // Convertendo a data
                    String dataString = cursor.getString(2);
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd"); // Ajuste o formato conforme o que você está usando no banco de dados
                    Date data = null;
                    try {
                        data = sdf.parse(dataString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    pedido.setDataPedido(data);

                    // Adicionando o pedido à lista
                    lista.add(pedido);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "PedidoDAO.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Pedido getbyId(int id) {
        Pedido pedido = null;
        try {
            Cursor cursor = bd.query(tableName, colunas, "ID = ?", new String[]{String.valueOf(id)}, null, null, "ID asc");
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            if (cursor.moveToFirst()) {
                pedido = new Pedido();
                pedido.setId(cursor.getInt(0));
                pedido.setClienteId(cursor.getInt(1));

                String dataString = cursor.getString(2);
                Date data = formato.parse(dataString);
                pedido.setDataPedido(data);
            }
        } catch (Exception ex) {
            Log.e("ERRO", "PedidoDAO.getById(): " + ex.getMessage());
        }
        return pedido;
    }
    }

