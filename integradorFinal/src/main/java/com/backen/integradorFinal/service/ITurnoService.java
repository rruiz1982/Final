package com.backen.integradorFinal.service;

import com.backen.integradorFinal.dto.TurnoDto;
import com.backen.integradorFinal.entity.Turno;

import java.util.List;

public interface ITurnoService {
    TurnoDto guardarTurno(Turno turno);

    List<TurnoDto> listarTodos();

    TurnoDto buscarTurnoPorId(int id);

    TurnoDto actualizarTurno(Turno turno);

    void eliminarTurno(int id);


}
