package services;

import daos.MonitoringDao;
import daos.UserDao;
import models.Monitoring;
import models.User;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.inject.Named;
import java.util.List;

/**
 * Created by saeed on 12/24/15.
 */

@Named
@Transactional
public class MonitoringServiceImpl implements MonitoringService {

    @Inject
    private MonitoringDao monitoringDao;


    @Override
    public List<Monitoring> findAllMonitoring() {
        return monitoringDao.getAll();
    }

    @Override
    public Monitoring create(Monitoring monitoring) {
        try {
            long millis = 3000l;
            if (monitoring.getRequestId() == null){
                millis = 4000l;
            }
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("log requestId - "+ monitoring.getRequestId());

        return monitoringDao.persist(monitoring);
    }



}
