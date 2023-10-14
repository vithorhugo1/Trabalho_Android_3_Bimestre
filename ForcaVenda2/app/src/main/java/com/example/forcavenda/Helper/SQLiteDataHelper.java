package com.example.forcavenda.Helper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SQLiteDataHelper extends SQLiteOpenHelper {

    public SQLiteDataHelper(@Nullable Context context,
                            @Nullable String name,
                            @Nullable SQLiteDatabase.CursorFactory factory,
                            int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE CLIENTE(CODCLIENTE INTEGER, NOME VARCHAR(150), CPF VARCHAR, DTNASC VARCHAR, CODENDERECO INTEGER)");
        sqLiteDatabase.execSQL("CREATE TABLE ENDERECO(CODIGOEND INTEGER, LOGRADOURO VARCHAR, NUMERO VARCHAR, BAIRRO VARCHAR, CIDADE VARCHAR, UF VARCHAR(2))");
        sqLiteDatabase.execSQL("CREATE TABLE ITEM(CODIGOITEM INTEGER, DESCRICAO VARCHAR, VLRUNITARO DOUBLE, UNMEDIDA VARCHAR)");
        sqLiteDatabase.execSQL("CREATE TABLE PEDIDO(ID INTEGER PRIMARY KEY AUTOINCREMENT, CLIENTEID INTEGER, DATAPEDIDO DATE)");
        sqLiteDatabase.execSQL("CREATE TABLE ITEMPEDIDO(ID INTEGER PRIMARY KEY AUTOINCREMENT, PEDIDOID INTEGER, ITEMID INTEGER, QUANTIDADE INTEGER, VALORUNITARIO DOUBLE)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldversion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS CLIENTE");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ENDERECO");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ITEM");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS PEDIDO");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS ITEMPEDIDO");
        onCreate(sqLiteDatabase);
    }
}
