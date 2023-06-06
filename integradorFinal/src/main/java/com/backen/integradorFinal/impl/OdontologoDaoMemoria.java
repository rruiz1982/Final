package com.backen.integradorFinal.impl;


import com.backen.integradorFinal.dao.Dao;
import com.backen.integradorFinal.model.Odontologo;


import java.util.List;
import java.util.logging.Logger;


public class OdontologoDaoMemoria implements Dao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(String.valueOf(OdontologoDaoMemoria.class));
    private List<Odontologo> odontologoRepositorio;

    public OdontologoDaoMemoria() {
    }

    public OdontologoDaoMemoria(List<Odontologo> odontologoRepositorio) {
        this.odontologoRepositorio = odontologoRepositorio;
    }

    public Odontologo registrar(Odontologo odontologo) {
        this.odontologoRepositorio.add(odontologo);
        LOGGER.info("Odontologo guardado: " + String.valueOf(odontologo));
        return odontologo;
    }

    public List<Odontologo> listar() {
        LOGGER.info("Listado de todos los odontologos: \n" + String.valueOf(this.odontologoRepositorio));
        return this.odontologoRepositorio;
    }

    @Override
    public void eliminar(int id) {

    }

    @Override
    public Odontologo buscarPorId(int id) {
        return null;
    }

    @Override
    public Odontologo buscarPorCriterio(String criterio) {
        return null;
    }
}
