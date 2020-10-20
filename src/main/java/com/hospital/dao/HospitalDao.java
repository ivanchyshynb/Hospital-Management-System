package com.hospital.dao;

import com.hospital.model.Hospital;
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

public class HospitalDao implements DAO<Hospital> {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean update(Hospital hospital) {
        String query = "UPDATE hospital SET Name = ?, Address = ? WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, hospital.getName());
            preparedStatement.setString(2, hospital.getAddress());
            preparedStatement.setInt(3, hospital.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Hospital can't be updated");
            }
            logger.info("Hospital with id(" + hospital.getId() + ") was updated");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Hospital hospital) {
        String query = "DELETE FROM hospital WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, hospital.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Hospital can't be deleted");
            }
            logger.info("Hospital with id(" + hospital.getId() + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM hospital WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Hospital can't be deleted");
            }
            logger.info("Hospital with id(" + id + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Hospital> getAll() {
        List<Hospital> hospitalList = new ArrayList<>();
        String query = "SELECT * FROM hospital";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Hospital hospital = new Hospital();
                hospital.setId(resultSet.getInt("Id"));
                hospital.setName(resultSet.getString("Name"));
                hospital.setAddress(resultSet.getString("Address"));
                hospitalList.add(hospital);
            }
            logger.info("Find all hospitals successfully done");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
            return null;
        }
        return hospitalList;
    }

    @Override
    public Hospital create(Hospital hospital) {
        String InsertSql = "INSERT INTO hospital (Name, Address) VALUES (?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)) {
            preparedStatement.setString(1, hospital.getName());
            preparedStatement.setString(2, hospital.getAddress());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Can't create hospital");
                return null;
            }
            logger.info("Hospital " + hospital.getId() + " was created");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return hospital;
    }

    @Override
    public Hospital getById(int id) {
        String query = "SELECT * FROM hospital WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Hospital hospital = new Hospital();
                hospital.setId(id);
                hospital.setName(resultSet.getString("Name"));
                hospital.setAddress(resultSet.getString("Address"));
                return hospital;
            }
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return null;
    }
}
