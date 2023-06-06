package com.backen.integradorFinal.dao;


import java.util.List;

public interface Dao<P> {
    P registrar(P p);

    List<P> listar();

    void eliminar(int id);

    P buscarPorId(int id);

    P buscarPorCriterio(String criterio);
}



