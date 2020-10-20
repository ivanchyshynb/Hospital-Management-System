package com.hospital.service;

import com.hospital.dao.DoctorDao;
import com.hospital.model.Doctor;

import java.util.List;

public class DoctorService {
    DoctorDao doctorDao;

    public DoctorService(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    public List<Doctor> getAll() {
        return doctorDao.getAll();
    }

    public boolean update(Doctor doctor) {
        return doctorDao.update(doctor);
    }

    public boolean delete(Doctor doctor) {
        return doctorDao.delete(doctor);
    }

    public boolean deleteById(int id) {
        return doctorDao.deleteById(id);
    }

    public Doctor create(Doctor doctor) {
        return doctorDao.create(doctor);
    }

    public Doctor getById(int id) {
        return doctorDao.getById(id);
    }
}
