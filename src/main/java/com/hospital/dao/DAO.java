package com.hospital.dao;

import java.sql.SQLException;
import java.util.List;

public interface DAO<T> {
    boolean update(T t);

    boolean delete(T t);

    boolean deleteById(int id);

    List<T> getAll() throws SQLException;

    T create(T t);

    T getById(int id);
}
