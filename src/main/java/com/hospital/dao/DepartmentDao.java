package com.hospital.dao;

import com.hospital.model.Department;
import com.hospital.model.Hospital;
import com.hospital.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDao implements DAO<Department> {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean update(Department department) {
        String query = "UPDATE department SET Name = ?, Amount = ? WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getAmount());
            preparedStatement.setInt(3, department.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Department can't be updated");
            }
            logger.info("Department with id(" + department.getId() + ") was updated");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Department department) {
        String query = "DELETE FROM department WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, department.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Department can't be deleted");
            }
            logger.info("Department with id(" + department.getId() + ") was deleted");
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
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Department can't be deleted");
            }
            logger.info("Hospital with id(" + id + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Department> getAll() {
        List<Department> departmentList = new ArrayList<>();
        String query = "SELECT * FROM department";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(resultSet.getInt("Id"));
                department.setName(resultSet.getString("Name"));
                department.setAmount(resultSet.getInt("Amount"));
                departmentList.add(department);
            }
            logger.info("Find all departments successfully done");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
            return null;
        }
        return departmentList;
    }

    @Override
    public Department create(Department department) {
        String InsertSql = "INSERT INTO department (Name, Amount) VALUES (?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(InsertSql)) {
            preparedStatement.setString(1, department.getName());
            preparedStatement.setInt(2, department.getAmount());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Can't create department");
                return null;
            }
            logger.info("Department: " + department.getId() + " was created");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return department;
    }

    @Override
    public Department getById(int id) {
        String query = "SELECT * FROM department WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Department department = new Department();
                department.setId(id);
                department.setName(resultSet.getString("Name"));
                department.setAmount(resultSet.getInt("Amount"));
                return department;
            }
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return null;
    }
}
