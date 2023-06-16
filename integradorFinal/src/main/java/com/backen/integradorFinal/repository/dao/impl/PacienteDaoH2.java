package com.backen.integradorFinal.repository.dao.impl;

import com.backen.integradorFinal.entity.Domicilio;
import com.backen.integradorFinal.entity.Paciente;
import com.backen.integradorFinal.repository.dao.H2Connection;
import com.backen.integradorFinal.repository.dao.Dao;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class PacienteDaoH2 implements Dao<Paciente> {

    private static final Logger LOGGER = LoggerFactory.getLogger(PacienteDaoH2.class);


    @Override
    public Paciente registrar(Paciente paciente) {
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            DomicilioDaoH2 domicilioDaoH2 = new DomicilioDaoH2();
            domicilioDaoH2.registrar(paciente.getDomicilio());

            PreparedStatement ps = connection.prepareStatement("INSERT INTO PACIENTES (NOMBRE, APELLIDO, DNI, FECHA, DOMICILIO_ID) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDni());
            ps.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            ps.setInt(5, paciente.getDomicilio().getId());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                paciente.setId(rs.getInt(1));
            }

            connection.commit();
            LOGGER.info("Se ha registrado el paciente: " + paciente);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception e) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + e.getMessage());
                e.printStackTrace();
            }
        }

        return paciente;
    }

    @Override
    public List<Paciente> listar() {
        Connection connection = null;
        List<Paciente> pacientes = new ArrayList<>();

        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Paciente paciente = crearObjetoPaciente(rs);
                pacientes.add(paciente);
            }

            LOGGER.info("Listado de todos los pacientes: " + pacientes);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();

        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return pacientes;
    }

    @Override
    public void eliminar(int id) {
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("DELETE FROM PACIENTES WHERE ID = ?");
            ps.setInt(1, id);
            ps.execute();
            connection.commit();
            LOGGER.info("Se ha eliminado el paciente con id: " + id);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }

    }

    @Override
    public Paciente buscarPorId(int id) {
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES WHERE ID = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                paciente = crearObjetoPaciente(rs);
            }
            if (paciente != null) LOGGER.info("Se ha encontrado el paciente con id " + id + ": " + paciente);


        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return paciente;
    }


    public Paciente buscarPorCriterio(String criterio) {
        Connection connection = null;
        Paciente paciente = null;
        try {
            connection = H2Connection.getConnection();
            PreparedStatement ps = connection.prepareStatement("SELECT * FROM PACIENTES WHERE DNI = ?");
            ps.setString(1, criterio);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                paciente = crearObjetoPaciente(rs);
            }
            LOGGER.info("Se ha encontrado el paciente con dni " + criterio + ": " + paciente);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return paciente;
    }

    @Override
    public Paciente actualizar(Paciente paciente) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("UPDATE PACIENTES SET NOMBRE = ?, APELLIDO = ?, DNI = ?, FECHA = ?, DOMICILIO_ID = ? WHERE ID = ?");
            ps.setString(1, paciente.getNombre());
            ps.setString(2, paciente.getApellido());
            ps.setString(3, paciente.getDni());
            ps.setDate(4, Date.valueOf(paciente.getFechaIngreso()));
            ps.setInt(5, paciente.getDomicilio().getId());

            ps.setInt(6, paciente.getId());
            ps.execute();
            connection.commit();
            LOGGER.warn("El paciente con id " + paciente.getId() + " ha sido actualizado. " + paciente);

        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    e.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception ex) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + ex.getMessage());
                ex.printStackTrace();
            }
        }
        return paciente;
    }


    private Paciente crearObjetoPaciente(ResultSet resultSet) throws SQLException {
        int idPaciente = resultSet.getInt("id");
        String nombrePaciente = resultSet.getString("nombre");
        String apellidoPaciente = resultSet.getString("apellido");
        String dniPaciente = resultSet.getString("dni");
        LocalDate fechaIngreso = resultSet.getDate("fecha").toLocalDate();
        Domicilio domicilioPaciente = new DomicilioDaoH2().buscarPorId(resultSet.getInt("domicilio_id"));
        return new Paciente(idPaciente, nombrePaciente, apellidoPaciente, dniPaciente, fechaIngreso, domicilioPaciente);
    }


}

