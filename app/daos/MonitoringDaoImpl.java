package daos;

import core.dao.GenericDaoImpl;
import models.Monitoring;
import models.User;

import javax.inject.Named;

/**
 * Created by saeed on 12/24/15.
 */

@Named
public class MonitoringDaoImpl extends GenericDaoImpl<Monitoring, Long> implements MonitoringDao{

    @Override
    protected Class<Monitoring> getEntityClass() {
        return Monitoring.class;
    }


}
