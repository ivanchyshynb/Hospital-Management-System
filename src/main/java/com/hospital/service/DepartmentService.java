package com.hospital.service;

import com.hospital.dao.DepartmentDao;
import com.hospital.model.Department;

import java.util.List;

public class DepartmentService {
    DepartmentDao departmentDao;

    public DepartmentService(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    public List<Department> getAll() {
        return departmentDao.getAll();
    }

    public boolean update(Department department) {
        return departmentDao.update(department);
    }

    public boolean delete(Department department) {
        return departmentDao.delete(department);
    }

    public boolean deleteById(int id) {
        return departmentDao.deleteById(id);
    }

    public Department create(Department department) {
        return departmentDao.create(department);
    }

    public Department getById(int id) {
        return departmentDao.getById(id);
    }
}
