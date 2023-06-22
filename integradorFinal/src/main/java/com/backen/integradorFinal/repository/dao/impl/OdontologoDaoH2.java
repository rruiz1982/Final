package com.backen.integradorFinal.repository.dao.impl;

import com.backen.integradorFinal.repository.dao.Dao;
import com.backen.integradorFinal.repository.dao.H2Connection;
import com.backen.integradorFinal.entity.Odontologo;

import org.slf4j.Logger;

import java.sql.*;

import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class OdontologoDaoH2 implements Dao<Odontologo> {

    private static final Logger LOGGER = getLogger(OdontologoDaoH2.class);

    @Override
    public Odontologo registrar(Odontologo odontologo) {
        Connection connection = null;
        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("INSERT INTO ODONTOLOGOS (MATRICULA, NOMBRE, APELLIDO) VALUES (?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, odontologo.getNumeroMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.execute();
            ResultSet rs = ps.getGeneratedKeys();
            while (rs.next()) {
                odontologo.setId(rs.getInt(1));
            }

            connection.commit();
            LOGGER.info("Se ha registrado el odontologo: " + odontologo);

        } catch (Exception p) {
            LOGGER.error(p.getMessage());
            p.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback();
                    System.out.println("Tuvimos un problema");
                    p.printStackTrace();
                } catch (SQLException exception) {
                    LOGGER.error(exception.getMessage());
                    exception.printStackTrace();
                }
            }
        } finally {
            try {
                connection.close();
            } catch (Exception p) {
                LOGGER.error("Ha ocurrido un error al intentar cerrar la bdd. " + p.getMessage());
                p.printStackTrace();
            }
        }

        return odontologo;
    }

    @Override
    public List<Odontologo> listar() {
        return null;
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

    @Override
    public Odontologo actualizar(Odontologo odontologo) {
        Connection connection = null;

        try {
            connection = H2Connection.getConnection();
            connection.setAutoCommit(false);

            PreparedStatement ps = connection.prepareStatement("UPDATE ODONTOLOGOS SET NUMEROMATRICULA = ?, NOMBRE = ?, APELLIDO = ? WHERE ID = ?");
            ps.setString(1, odontologo.getNumeroMatricula());
            ps.setString(2, odontologo.getNombre());
            ps.setString(3, odontologo.getApellido());
            ps.setInt(4, odontologo.getId());
            ps.execute();
            connection.commit();
            LOGGER.warn("El odontologo con id " + odontologo.getId() + " ha sido actualizado. " + odontologo);

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
        return odontologo;
    }

    private Odontologo crearObjetoOdontologo(ResultSet resultSet) throws SQLException {
        int id = resultSet.getInt("id");
        String nombre = resultSet.getString("nombre");
        String apellido = resultSet.getString("apellido");
        String numeroMatricula = resultSet.getString("numeroMatricula");

        return new Odontologo(id, numeroMatricula, nombre, apellido);
    }

}