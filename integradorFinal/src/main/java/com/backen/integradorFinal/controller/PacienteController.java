package com.backen.integradorFinal.controller;

import com.backen.integradorFinal.entity.Paciente;
import com.backen.integradorFinal.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pacientes")
public class PacienteController {
    private IPacienteService pacienteService;

    @Autowired
    public PacienteController(IPacienteService pacienteService) {
        this.pacienteService = pacienteService;
    }

    @PostMapping("/registrar")
    public Paciente registrarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.guardarPaciente(paciente);

    }

    //PUT
    @PutMapping("/actualizar")
    public Paciente actualizarPaciente(@RequestBody Paciente paciente) {
        return pacienteService.actualizarPaciente(paciente);
    }

    //GET
    @GetMapping
    public List<Paciente> listarTodos() {
        return pacienteService.listarPacientes();
    }

    @GetMapping("/{id}")
    public Paciente buscarPacientePorId(@PathVariable int id) {
        return pacienteService.buscarPacientePorId(id);
    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public void eliminarPaciente(@PathVariable int id) {
        pacienteService.eliminarPaciente(id);
    }


    @GetMapping
    public Paciente buscarPacientePorDni(@PathVariable String dni) {
        return pacienteService.buscarPacientePorDni(dni);
    }




}

