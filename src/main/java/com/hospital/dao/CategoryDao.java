package com.hospital.dao;

import com.hospital.model.Category;
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

public class CategoryDao implements DAO<Category> {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean update(Category category) {
        String query = "UPDATE category SET Name = ? WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, category.getName());
            preparedStatement.setInt(2, category.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Category can't be updated");
            }
            logger.info("Category with id(" + category.getId() + ") was updated");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Category category) {
        String query = "DELETE FROM category WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, category.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Category can't be deleted");
            }
            logger.info("Category with id(" + category.getId() + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM category WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Category can't be deleted");
            }
            logger.info("Category with id(" + id + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categoryList = new ArrayList<>();
        String query = "SELECT * FROM category";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(resultSet.getInt("Id"));
                category.setName(resultSet.getString("Name"));
                categoryList.add(category);
            }
            logger.info("Find all categories successfully done");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
            return null;
        }
        return categoryList;
    }

    @Override
    public Category create(Category category) {
        String query = "INSERT INTO category (Name) VALUES (?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, category.getName());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Can't create category");
                return null;
            }
            logger.info("Category " + category.getId() + " was created");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return category;
    }

    @Override
    public Category getById(int id) {
        String query = "SELECT * FROM category WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category();
                category.setId(id);
                category.setName(resultSet.getString("Name"));
                return category;
            }
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return null;
    }
}
