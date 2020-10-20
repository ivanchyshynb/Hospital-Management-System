package com.hospital.dao;

import com.hospital.model.Category;
import com.hospital.model.Specialization;
import com.hospital.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SpecializationDao implements DAO<Specialization> {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean update(Specialization specialization) {
        String query = "UPDATE specialization SET Name = ? WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, specialization.getName());
            preparedStatement.setInt(2, specialization.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Specialization can't be updated");
            }
            logger.info("Specialization with id(" + specialization.getId() + ") was updated");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Specialization specialization) {
        String query = "DELETE FROM specialization WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, specialization.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Specialization can't be deleted");
            }
            logger.info("Specialization with id(" + specialization.getId() + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM specialization WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Specialization can't be deleted");
            }
            logger.info("Specialization with id(" + id + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Specialization> getAll() {
        List<Specialization> specializationList = new ArrayList<>();
        String query = "SELECT * FROM specialization";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Specialization specialization = new Specialization();
                specialization.setId(resultSet.getInt("Id"));
                specialization.setName(resultSet.getString("Name"));
                specializationList.add(specialization);
            }
            logger.info("Find all specializations successfully done");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
            return null;
        }
        return specializationList;
    }

    @Override
    public Specialization create(Specialization specialization) {
        String InsertSql = "INSERT INTO specialization (Name) VALUES (?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)) {
            preparedStatement.setString(1, specialization.getName());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Can't create specialization");
                return null;
            }
            logger.info("Specialization " + specialization.getId() + " was created");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return specialization;
    }

    @Override
    public Specialization getById(int id) {
        String query = "SELECT * FROM specialization WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Specialization specialization = new Specialization();
                specialization.setId(id);
                specialization.setName(resultSet.getString("Name"));
                return specialization;
            }
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return null;
    }
}
