package com.hospital.dao;

import com.hospital.model.Patient;
import com.hospital.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PatientDao implements DAO<Patient> {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean update(Patient patient) {
        String query = "UPDATE patient SET patient.Name = ?, Surname = ?, Age = ?, Sex = ?, Phone = ?, Address = ?" +
                "WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getLastname());
            preparedStatement.setInt(3, patient.getAge());
            preparedStatement.setString(4, patient.getSex());
            preparedStatement.setString(5, patient.getPhone());
            preparedStatement.setString(6, patient.getAddress());
            preparedStatement.setInt(7, patient.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Patient can't be updated");
            }
            logger.info("Member with id(" + patient.getId() + ") was updated");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Patient patient) {
        String query = "DELETE FROM patient WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, patient.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Patient can't be deleted");
            }
            logger.info("Member with id(" + patient.getId() + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM patient WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Member can't be deleted");
            }
            logger.info("Member with id(" + id + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patientList = new ArrayList<>();
        String query = "SELECT * FROM patient";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setId(resultSet.getInt("Id"));
                patient.setName(resultSet.getString("Name"));
                patient.setLastname(resultSet.getString("Surname"));
                patient.setAge(resultSet.getInt("Age"));
                patient.setSex(resultSet.getString("Sex"));
                patient.setAddress(resultSet.getString("Address"));
                patient.setPhone(resultSet.getString("Phone"));
                patientList.add(patient);
            }
            logger.info("Find all patients successfully done");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
            return null;
        }
        return patientList;
    }

    @Override
    public Patient create(Patient patient) {
        String InsertSql = "INSERT INTO Patient (Name, Surname, Age, Sex, Phone, Address) " +
                "VALUES (?,?,?,?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)) {
            preparedStatement.setString(1, patient.getName());
            preparedStatement.setString(2, patient.getLastname());
            preparedStatement.setInt(3, patient.getAge());
            preparedStatement.setString(4, patient.getSex());
            preparedStatement.setString(5, patient.getPhone());
            preparedStatement.setString(6, patient.getAddress());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Can't create patient");
                return null;
            }
            logger.info("Member " + patient.getId() + " was created");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return patient;
    }

    @Override
    public Patient getById(int id) {
        String query = "SELECT * FROM patient WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Patient patient = new Patient();
                patient.setId(id);
                patient.setName(resultSet.getString("Name"));
                patient.setLastname(resultSet.getString("Surname"));
                patient.setAge(resultSet.getInt("Age"));
                patient.setSex(resultSet.getString("Sex"));
                patient.setPhone(resultSet.getString("Phone"));
                patient.setAddress(resultSet.getString("Address"));
                return patient;
            }
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return null;
    }
}
