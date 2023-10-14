package com.example.forcavenda.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.forcavenda.Helper.SQLiteDataHelper;
import com.example.forcavenda.Models.Item;

import java.util.ArrayList;

public class ItemDao implements GenericDao<Item>{

    //Abrir a conexão com a BD
    private SQLiteOpenHelper openHelper;

    //Base de Dados
    private SQLiteDatabase bd;

    //nome das colunas da tabela
    private String[] colunas = {"CODIGOITEM", "DESCRICAO", "VLRUNITARO", "UNMEDIDA"};

    //nome da tabela
    private String tableName = "ITEM";

    private Context context;

    private static ItemDao instancia;

    private ItemDao(Context context) {
        this.context = context;
        //Carregando base de dados
        openHelper = new SQLiteDataHelper(this.context, "UNIPAR", null, 2);

        //Atribuindo a base de dados a variavel e dando permissão para escrever nas tabelas
        bd = openHelper.getWritableDatabase();
    }

    public static ItemDao getInstancia(Context context) {
        if (instancia == null)
            return instancia = new ItemDao(context);
        else
            return instancia;
    }

    @Override
    public long insert(Item obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put("CODIGOITEM", obj.getCodigoItem());
            valores.put("DESCRICAO", obj.getDescricao());
            valores.put("VLRUNITARO", obj.getVlrUnitaro());
            valores.put("UNMEDIDA", obj.getUnMedida());

            return bd.insert(tableName, null, valores);
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemDao.insert(): " + ex.getMessage());
        }
        return -1;

    }

    @Override
    public long update(Item obj) {

        try {
            ContentValues valores = new ContentValues();
            valores.put("CODIGOITEM", obj.getCodigoItem());
            valores.put("DESCRICAO", obj.getDescricao());
            valores.put("VLRUNITARO", obj.getVlrUnitaro());
            valores.put("UNMEDIDA", obj.getUnMedida());

            String[] identificador = {String.valueOf(obj.getCodigoItem())};
            return bd.update(tableName, valores, "CODIGOITEM = ?", identificador);
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemDao.update(): " + ex.getMessage());
        }
        return -1;

    }

    @Override
    public long delete(Item obj) {

        try {
            String[] identification = {String.valueOf(obj.getCodigoItem())};
            return bd.delete(tableName, "CODIGOITEM = ?", identification);
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemDao.delete(): " + ex.getMessage());
        }
        return -1;

    }

    @Override
    public ArrayList<Item> getAll() {
        ArrayList<Item> lista = new ArrayList<>();
        try {
            Cursor cursor = bd.query(tableName, colunas, null, null, null, null, "CODIGOITEM asc");
            if (cursor.moveToFirst()) {
                do {
                    Item item = new Item();
                    item.setCodigoItem(cursor.getInt(0));
                    item.setDescricao(cursor.getString(1));
                    item.setVlrUnitaro(cursor.getDouble(2));
                    item.setUnMedida(cursor.getString(3));

                    lista.add(item);
                } while (cursor.moveToNext());
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemDao.getAll(): " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public Item getbyId(int id) {
        try {
            Cursor cursor = bd.query(tableName, colunas, "CODIGOITEM = ?", null, null, null, "CODIGOITEM asc");
            if (cursor.moveToFirst()) {
                Item item = new Item();
                item.setCodigoItem(cursor.getInt(0));
                item.setDescricao(cursor.getString(1));
                item.setVlrUnitaro(cursor.getDouble(2));
                item.setUnMedida(cursor.getString(3));

                return item;
            }
        } catch (SQLException ex) {
            Log.e("ERRO", "ItemDao.getById(): " + ex.getMessage());
        }
        return null;
    }
}
