package com.backen.integradorFinal.service.impl;

import com.backen.integradorFinal.entity.Odontologo;
import com.backen.integradorFinal.repository.dao.Dao;
import com.backen.integradorFinal.service.IOdontologoService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class OdontologoService implements IOdontologoService {
    private Dao<Odontologo> odontologoIDao;

    public OdontologoService(Dao<Odontologo> odontologoDao) {
        this.odontologoIDao = odontologoDao;
    }

    public Odontologo buscarOdontologoPorId(int id) {
        return odontologoIDao.buscarPorId(id);
    }

    public List<Odontologo> listarOdontologos() {
        return odontologoIDao.listar();
    }


    public Odontologo registrarOdontologo(Odontologo odontologo) {
        return odontologoIDao.registrar(odontologo);
    }


    public Odontologo actualizarOdontologo(Odontologo odontologo) {
        return odontologoIDao.actualizar(odontologo);
    }

    public void eliminarOdontologo(int id) {
        odontologoIDao.eliminar(id);
    }


}