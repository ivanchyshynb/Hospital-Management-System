package com.hospital.service;

import com.hospital.dao.PatientDao;
import com.hospital.model.Doctor;
import com.hospital.model.Patient;

import java.util.List;

public class PatientService {
    PatientDao patientDao;

    public PatientService(PatientDao patientDao) {
        this.patientDao = patientDao;
    }

    public List<Patient> getAll() {
        return patientDao.getAll();
    }

    public boolean update(Patient patient) {
        return patientDao.update(patient);
    }

    public boolean delete(Patient patient) {
        return patientDao.delete(patient);
    }

    public boolean deleteById(int id) {
        return patientDao.deleteById(id);
    }

    public Patient create(Patient patient){
        return patientDao.create(patient);
    }

    public Patient getById(int id){
        return patientDao.getById(id);
    }
}
