package com.example.forcavenda.DAO;

import java.util.ArrayList;

public interface GenericDao <Object> {

    long insert(Object obj);

    long update(Object obj);

    long delete(Object obj);

    ArrayList<Object> getAll();

    Object getbyId(int id);

}
