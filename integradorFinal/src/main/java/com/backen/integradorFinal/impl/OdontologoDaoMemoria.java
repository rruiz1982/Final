package com.backen.integradorFinal.impl;


import com.backen.integradorFinal.model.Odontologo;


import java.util.List;
import java.util.logging.Logger;


public class OdontologoDaoMemoria implements Dao<Odontologo> {
    private static final Logger LOGGER = Logger.getLogger(OdontologoDaoMemoria.class);
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
}
