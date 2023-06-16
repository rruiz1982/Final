package com.backen.integradorFinal.service.impl;

import com.backen.integradorFinal.dto.TurnoDto;
import com.backen.integradorFinal.entity.Turno;
import com.backen.integradorFinal.repository.dao.Dao;
import com.backen.integradorFinal.service.ITurnoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TurnoService implements ITurnoService {
    private Dao<Turno> turnoDao;
    private ObjectMapper objectMapper;

    @Autowired
    public TurnoService(Dao<Turno> turnoDao, ObjectMapper objectMapper) {
        this.turnoDao = turnoDao;
        this.objectMapper = objectMapper;
    }

    @Override
    public TurnoDto guardarTurno(Turno turno) {
        return TurnoDto.fromTurno(turnoDao.registrar(turno));
    }

    @Override
    public List<TurnoDto> listarTodos() {

        return turnoDao.listar().stream().map(TurnoDto::fromTurno).toList();
    }

    @Override
    public TurnoDto buscarTurnoPorId(int id) {
        return TurnoDto.fromTurno(turnoDao.buscarPorId(id));
    }

    @Override
    public TurnoDto actualizarTurno(Turno turno) {
        return TurnoDto.fromTurno(turnoDao.actualizar(turno));
    }

    @Override
    public void eliminarTurno(int id) {
        turnoDao.eliminar(id);
    }
}

