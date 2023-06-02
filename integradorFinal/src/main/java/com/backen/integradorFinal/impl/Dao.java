package com.backen.integradorFinal.impl;


import java.util.List;

public interface Dao<P> {
    P registrar(P p);

    List<P> listar();
}



