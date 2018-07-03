package de.lars0395.github.service;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Session;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service("cassandraService")
public class CassandraService {

    @Value("${cassandra.default.ip}")
    private String defaultIP;

    public ResultSet getCassandraDataForCorrelationId(String correlationID, String ip) {
        Session cassandraSession = openSession(ip);
        return cassandraSession.execute("Select key, timestamp, blobastext(metadata), blobastext(payload) " +
                "From heidi3.logspi where correlationId=?",correlationID);
    }

    private Session openSession(String ip) {
        if (ip == null || ip.isEmpty()) {
            ip = defaultIP;
        }
        return Cluster.builder().addContactPoint(ip).build().connect("heidi3");
    }
}
