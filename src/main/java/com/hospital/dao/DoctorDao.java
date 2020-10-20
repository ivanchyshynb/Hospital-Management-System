package com.hospital.dao;

import com.hospital.model.*;
import com.hospital.util.DBUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DoctorDao implements DAO<Doctor> {

    private static final Logger logger = LogManager.getLogger();

    @Override
    public boolean update(Doctor doctor) {
        String query = "UPDATE doctor SET Name = ?, Surname = ?, Id_specialization = ?, Id_category = ?, " +
                "Hospital_number = ?, Id_department = ?, Username = ?, Password=?, Email = ? WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query);) {
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getLastname());
            preparedStatement.setInt(3, doctor.getSpecialization().getId());
            preparedStatement.setInt(4, doctor.getCategory().getId());
            preparedStatement.setInt(5, doctor.getHospital().getId());
            preparedStatement.setInt(6, doctor.getDepartment().getId());
            preparedStatement.setString(7, doctor.getUsername());
            preparedStatement.setString(8, doctor.getPassword());
            preparedStatement.setString(9, doctor.getEmail());
            preparedStatement.setInt(10, doctor.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Doctor can't be updated");
            }
            logger.info("Doctor with id: " + doctor.getId() + " was updated");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean delete(Doctor doctor) {
        String query = "DELETE FROM doctor WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, doctor.getId());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Doctor can't be deleted");
            }
            logger.info("Doctor with id(" + doctor.getId() + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public boolean deleteById(int id) {
        String query = "DELETE FROM doctor WHERE Id = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Doctor can't be deleted");
            }
            logger.info("Doctor with id(" + id + ") was deleted");
            return true;
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return false;
    }

    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctorList = new ArrayList<>();
        String query = "SELECT doctor.Id, doctor.Name, Surname, doctor.Id_specialization, specialization.Name as Specialization_name, " +
                "doctor.Id_category, category.Name as Category_name, doctor.Hospital_number, " +
                "hospital.Name as Hospital_name, doctor.Id_department, department.Name as Department_name, Email, " +
                "Username, Password " +
                "FROM doctor JOIN specialization ON doctor.Id_specialization = specialization.Id " +
                "JOIN category ON doctor.Id_category = category.Id " +
                "JOIN hospital ON doctor.Hospital_number = hospital.Id " +
                "JOIN department ON doctor.Id_department = department.Id ";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(resultSet.getInt("Id"));
                doctor.setName(resultSet.getString("Name"));
                doctor.setLastname(resultSet.getString("Surname"));
                Specialization specialization = new Specialization();
                specialization.setId(resultSet.getInt("Id_specialization"));
                specialization.setName(resultSet.getString("Specialization_name"));
                doctor.setSpecialization(specialization);
                Category category = new Category();
                category.setId(resultSet.getInt("Id_category"));
                category.setName(resultSet.getString("Category_name"));
                doctor.setCategory(category);
                Hospital hospital = new Hospital();
                hospital.setId(resultSet.getInt("Hospital_number"));
                hospital.setName(resultSet.getString("Hospital_name"));
                doctor.setHospital(hospital);
                Department department = new Department();
                department.setId(resultSet.getInt("Id_department"));
                department.setName(resultSet.getString("Department_name"));
                doctor.setDepartment(department);
                doctor.setEmail(resultSet.getString("Email"));
                doctor.setPassword(resultSet.getString("Password"));
                doctor.setUsername(resultSet.getString("Username"));
                doctorList.add(doctor);
            }
            logger.info("Find all doctors successfully done");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
            return null;
        }
        return doctorList;
    }

    @Override
    public Doctor create(Doctor doctor) {
        String query = "INSERT INTO doctor (Name, Surname, Id_specialization, Id_category, Hospital_number, Id_department, " +
                "Email, Username, Password)" +
                " VALUES (?,?,?,?,?,?,?,?,?)";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, doctor.getName());
            preparedStatement.setString(2, doctor.getLastname());
            preparedStatement.setInt(3, doctor.getSpecialization().getId());
            preparedStatement.setInt(4, doctor.getCategory().getId());
            preparedStatement.setFloat(5, doctor.getHospital().getId());
            preparedStatement.setFloat(6, doctor.getDepartment().getId());
            preparedStatement.setString(7, doctor.getEmail());
            preparedStatement.setString(8, doctor.getUsername());
            preparedStatement.setString(9, doctor.getPassword());
            if (preparedStatement.executeUpdate() == 0) {
                logger.error("Can't create doctor");
            }
            logger.info("Doctor with id(" + doctor.getId() + ") was created");
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Doctor getById(int id) {
        String query = "SELECT doctor.Id, doctor.Name, Surname, doctor.Id_specialization, specialization.Name as Specialization_name, " +
                "doctor.Id_category, category.Name as Category_name, doctor.Hospital_number, " +
                "hospital.Name as Hospital_name, doctor.Id_department, department.Name as Department_name, " +
                "Email, Username, Password " +
                "FROM doctor JOIN specialization ON doctor.Id_specialization = specialization.Id " +
                "JOIN category ON doctor.Id_category = category.Id " +
                "JOIN hospital ON doctor.Hospital_number = hospital.Id " +
                "JOIN department ON doctor.Id_department = department.Id WHERE doctor.Id = ? ";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Doctor doctor = new Doctor();
                doctor.setId(id);
                doctor.setName(resultSet.getString("Name"));
                doctor.setLastname(resultSet.getString("Surname"));
                Specialization specialization = new Specialization();
                specialization.setId(resultSet.getInt("Id_specialization"));
                specialization.setName(resultSet.getString("Specialization_name"));
                doctor.setSpecialization(specialization);
                Category category = new Category();
                category.setId(resultSet.getInt("Id_category"));
                category.setName(resultSet.getString("Category_name"));
                doctor.setCategory(category);
                Hospital hospital = new Hospital();
                hospital.setId(resultSet.getInt("Hospital_number"));
                hospital.setName(resultSet.getString("Hospital_name"));
                doctor.setHospital(hospital);
                Department department = new Department();
                department.setId(resultSet.getInt("Id_department"));
                department.setName(resultSet.getString("Department_name"));
                doctor.setDepartment(department);
                doctor.setEmail(resultSet.getString("Email"));
                doctor.setPassword(resultSet.getString("Password"));
                doctor.setUsername(resultSet.getString("Username"));
                return doctor;
            }
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
            return null;
        }
        return null;
    }

    public Doctor validate(String username, String password) {
        String query = "SELECT doctor.Id, doctor.Name, Surname, doctor.Id_specialization, " +
                "specialization.Name as Specialization_name, doctor.Id_category, category.Name as Category_name, " +
                "doctor.Hospital_number, hospital.Name as Hospital_name, doctor.Id_department, " +
                "department.Name as Department_name, Email, Username, Password, Role FROM doctor " +
                "JOIN specialization ON doctor.Id_specialization = specialization.Id " +
                "JOIN category ON doctor.Id_category = category.Id " +
                "JOIN hospital ON doctor.Hospital_number = hospital.Id " +
                "JOIN department ON doctor.Id_department = department.Id WHERE doctor.Username = ? AND doctor.Password = ?";
        try (Connection connection = DBUtil.getDataSource().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    Doctor doctor = new Doctor();
                    doctor.setId(resultSet.getInt("Id"));
                    doctor.setName(resultSet.getString("Name"));
                    doctor.setLastname(resultSet.getString("Surname"));
                    Specialization specialization = new Specialization();
                    specialization.setId(resultSet.getInt("Id_specialization"));
                    specialization.setName(resultSet.getString("Specialization_name"));
                    doctor.setSpecialization(specialization);
                    Category category = new Category();
                    category.setId(resultSet.getInt("Id_category"));
                    category.setName(resultSet.getString("Category_name"));
                    doctor.setCategory(category);
                    Hospital hospital = new Hospital();
                    hospital.setId(resultSet.getInt("Hospital_number"));
                    hospital.setName(resultSet.getString("Hospital_name"));
                    doctor.setHospital(hospital);
                    Department department = new Department();
                    department.setId(resultSet.getInt("Id_department"));
                    department.setName(resultSet.getString("Department_name"));
                    doctor.setDepartment(department);
                    doctor.setEmail(resultSet.getString("Email"));
                    doctor.setPassword(password);
                    doctor.setUsername(username);
                    doctor.setRole(resultSet.getString("Role"));
                    return doctor;
                }
            }
        } catch (SQLException e) {
            logger.error("Message: " + e.getMessage());
        }
        return null;
    }
}
