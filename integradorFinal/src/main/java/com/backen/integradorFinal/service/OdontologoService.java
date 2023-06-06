package com.backen.integradorFinal.service;

import com.backen.integradorFinal.impl.OdontologoDaoH2;
import com.backen.integradorFinal.model.Odontologo;

import java.util.List;

public class OdontologoService {

    private OdontologoDaoH2 odontologoDaoH2;

    public OdontologoService() {
        this.odontologoDaoH2 = new OdontologoDaoH2();
    }

    public Odontologo registrarOdontologo(Odontologo odontologo) {
        // Código para registrar el odontólogo
        return odontologo;
    }

    public List<Odontologo> listarOdontologos() {
        List<Odontologo> listaOdontologos = odontologoDaoH2.listar();

        return listaOdontologos;
    }
}