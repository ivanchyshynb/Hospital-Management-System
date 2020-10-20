package com.hospital.dao;

import com.hospital.model.*;
import com.hospital.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RegistryDao implements DAO<Registry> {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean update(Registry registry) {
        String query = "UPDATE registry SET Id_patient = ?, Id_doctor = ?, Disease = ?, Date_admission = ?, " +
                "Date_dismission = ?, Room_number = ? WHERE Number = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, registry.getPatient().getId());
            preparedStatement.setInt(2, registry.getDoctor().getId());
            preparedStatement.setString(3, registry.getDisease());
            preparedStatement.setObject(4, registry.getAdmission());
            preparedStatement.setObject(5, registry.getDismission());
            preparedStatement.setInt(6, registry.getRoomNumber());
            preparedStatement.setInt(7, registry.getNumber());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Doctor can't be updated");
            }
            logger.info("Registry with number(" + registry.getNumber() + ") was updated");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Registry registry) {
        String query = "DELETE FROM registry WHERE Number = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, registry.getNumber());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Registry can't be deleted");
            }
            logger.info("Registry with number(" + registry.getNumber() + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM registry WHERE Number = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Registry can't be deleted");
            }
            logger.info("Registry with number(" + id + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Registry> getAll() {
        List<Registry> registryList = new ArrayList<>();
        String query = "SELECT registry.Number, registry.Id_patient, patient.Surname as Patient_surname, " +
                "registry.Id_doctor, doctor.Surname as Doctor_surname, Disease, Date_admission, Date_dismission, " +
                "Room_number FROM registry JOIN patient ON registry.Id_patient = patient.Id " +
                "JOIN doctor ON registry.Id_doctor = doctor.Id ";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Registry registry = new Registry();
                registry.setNumber(resultSet.getInt("Number"));
                Patient patient = new Patient();
                patient.setId(resultSet.getInt("Id_patient"));
                patient.setLastname(resultSet.getString("Patient_surname"));
                registry.setPatient(patient);
                Doctor doctor = new Doctor();
                doctor.setId(resultSet.getInt("Id_doctor"));
                doctor.setLastname(resultSet.getString("Doctor_surname"));
                registry.setDoctor(doctor);
                registry.setDisease(resultSet.getString("Disease"));
                registry.setAdmission(resultSet.getDate("Date_admission"));
                registry.setDismission(resultSet.getDate("Date_dismission"));
                registry.setRoomNumber(resultSet.getInt("Room_number"));
                registryList.add(registry);
            }
            logger.info("Find all registries successfully done");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
            return null;
        }
        return registryList;
    }

    @Override
    public Registry create(Registry registry) {
        String query = "INSERT INTO registry (Id_patient, Id_doctor, Disease, Date_admission, Date_dismission, Room_number)" +
                " VALUES (?,?,?,?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, registry.getPatient().getId());
            preparedStatement.setInt(2, registry.getDoctor().getId());
            preparedStatement.setString(3, registry.getDisease());
            preparedStatement.setObject(4, registry.getAdmission());
            preparedStatement.setObject(5, registry.getDismission());
            preparedStatement.setInt(6, registry.getRoomNumber());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Can't create registry");
            }
            logger.info("Registry with number(" + registry.getNumber() + ") was created");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Registry getById(int id) {
        String query = "SELECT registry.Number, registry.Id_patient, patient.Surname as Patient_surname, " +
                "registry.Id_doctor, doctor.Surname as Doctor_surname, Disease, Date_admission, Date_dismission, " +
                "Room_number FROM registry JOIN patient ON registry.Id_patient = patient.Id " +
                "JOIN doctor ON registry.Id_doctor = doctor.Id WHERE registry.Number = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Registry registry = new Registry();
                registry.setNumber(id);
                Patient patient = new Patient();
                patient.setId(resultSet.getInt("Id_patient"));
                patient.setLastname(resultSet.getString("Patient_surname"));
                registry.setPatient(patient);
                Doctor doctor = new Doctor();
                doctor.setId(resultSet.getInt("Id_doctor"));
                doctor.setLastname(resultSet.getString("Doctor_surname"));
                registry.setDoctor(doctor);
                registry.setDisease(resultSet.getString("Disease"));
                registry.setAdmission(resultSet.getDate("Date_admission"));
                registry.setDismission(resultSet.getDate("Date_dismission"));
                registry.setRoomNumber(resultSet.getInt("Room_number"));
                return registry;
            }
            logger.info("Find all registries successfully done");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
            return null;
        }
        return null;
    }
}
