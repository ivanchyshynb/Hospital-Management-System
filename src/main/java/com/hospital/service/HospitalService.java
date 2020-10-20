package com.hospital.service;

import com.hospital.dao.DoctorDao;
import com.hospital.dao.HospitalDao;
import com.hospital.model.Doctor;
import com.hospital.model.Hospital;

import java.util.List;

public class HospitalService {
    HospitalDao hospitalDao;

    public HospitalService(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    public List<Hospital> getAll() {
        return hospitalDao.getAll();
    }

    public boolean update(Hospital hospital) {
        return hospitalDao.update(hospital);
    }

    public boolean delete(Hospital hospital) {
        return hospitalDao.delete(hospital);
    }

    public boolean deleteById(int id) {
        return hospitalDao.deleteById(id);
    }

    public Hospital create(Hospital hospital){
        return hospitalDao.create(hospital);
    }

    public Hospital getById(int id){
        return hospitalDao.getById(id);
    }
}
