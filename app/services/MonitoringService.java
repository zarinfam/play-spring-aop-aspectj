package services;


import models.Monitoring;
import models.User;

import java.util.List;

/**
 * Created by saeed on 12/24/15.
 */
public interface MonitoringService {
    List<Monitoring> findAllMonitoring();

    Monitoring create(Monitoring monitoring);
}
