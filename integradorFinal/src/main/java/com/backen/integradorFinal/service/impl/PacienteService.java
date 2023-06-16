package com.backen.integradorFinal.service.impl;

import com.backen.integradorFinal.entity.Paciente;
import com.backen.integradorFinal.repository.dao.Dao;
import com.backen.integradorFinal.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class PacienteService implements IPacienteService {
    private Dao<Paciente> pacienteDao;

    @Autowired
    public PacienteService(Dao<Paciente> pacienteIDao) {
        this.pacienteDao = pacienteIDao;
    }

    public Paciente guardarPaciente(Paciente paciente) {
        return (Paciente)this.pacienteDao.registrar(paciente);
    }

    public Paciente actualizarPaciente(Paciente paciente) {
        return (Paciente)this.pacienteDao.actualizar(paciente);
    }

    public Paciente buscarPacientePorId(int id) {
        return (Paciente)this.pacienteDao.buscarPorId(id);
    }

    public List<Paciente> listarPacientes() {
        return this.pacienteDao.listar();
    }

    public Paciente buscarPacientePorDni(String dni) {
        return (Paciente)this.pacienteDao.buscarPorCriterio(dni);
    }

    public void eliminarPaciente(int id) {
        this.pacienteDao.eliminar(id);
    }
}


