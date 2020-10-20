package com.hospital.service;

import com.hospital.dao.RegistryDao;
import com.hospital.model.Registry;

import java.util.List;

public class RegistryService {
    RegistryDao registryDao;

    public RegistryService(RegistryDao registryDao) {
        this.registryDao = registryDao;
    }

    public List<Registry> getAll() {
        return registryDao.getAll();
    }

    public boolean update(Registry registry) {
        return registryDao.update(registry);
    }

    public boolean delete(Registry registry) {
        return registryDao.delete(registry);
    }

    public boolean deleteById(int id) {
        return registryDao.deleteById(id);
    }

    public Registry create(Registry registry){
        return registryDao.create(registry);
    }

    public Registry getById(int id){
        return registryDao.getById(id);
    }
}
