package com.hospital.service;

import com.hospital.dao.SpecializationDao;
import com.hospital.model.Specialization;

import java.util.List;

public class SpecializationService {
    SpecializationDao specializationDao;

    public SpecializationService(SpecializationDao specializationDao) {
        this.specializationDao = specializationDao;
    }

    public List<Specialization> getAll() {
        return specializationDao.getAll();
    }

    public boolean update(Specialization specialization) {
        return specializationDao.update(specialization);
    }

    public boolean delete(Specialization specialization) {
        return specializationDao.delete(specialization);
    }

    public boolean deleteById(int id) {
        return specializationDao.deleteById(id);
    }

    public Specialization create(Specialization specialization){
        return specializationDao.create(specialization);
    }

    public Specialization getById(int id){
        return specializationDao.getById(id);
    }

}
