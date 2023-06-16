package com.backen.integradorFinal.controller;


import com.backen.integradorFinal.entity.Odontologo;
import com.backen.integradorFinal.service.impl.OdontologoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    @Autowired
    public OdontologoController(OdontologoService odontologoService) {
        this.odontologoService = odontologoService;
    }

    @GetMapping()
    public List<Odontologo> listarOdontologos() {
        return odontologoService.listarOdontologos();
    }

    @GetMapping("/{id}")
    public Odontologo buscarOdontologoPorId(@PathVariable int id) {
        return odontologoService.buscarOdontologoPorId(id);
    }

    //POST
    @PostMapping("/registrar")
    public Odontologo registrarOdontologo(@RequestBody Odontologo odontologo) {
        return odontologoService.registrarOdontologo(odontologo);
    }

    //PUT
    @PutMapping("/actualizar")
    public Odontologo actualizarOdontologo(@RequestBody Odontologo odontologo) {
        return odontologoService.actualizarOdontologo(odontologo);

    }

    //DELETE
    @DeleteMapping("/eliminar/{id}")
    public void eliminarOdontologo(@PathVariable int id) {
        odontologoService.eliminarOdontologo(id);
    }

}
