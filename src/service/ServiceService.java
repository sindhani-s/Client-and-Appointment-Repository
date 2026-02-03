package service;

import dao.ServiceDao;
import model.Service;

import java.util.List;

public class ServiceService {

    private final ServiceDao serviceDao;

    public ServiceService(ServiceDao serviceDao) {
        this.serviceDao = serviceDao;
    }

    public List<Service> getAllServices() {
        try {
            return serviceDao.getAllServices();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
