package de.lars0395.github.controller;

import com.datastax.driver.core.ColumnDefinitions;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import de.lars0395.github.model.CassandraBlobAsStringEntry;
import de.lars0395.github.model.CassandraEntry;
import de.lars0395.github.service.CassandraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class CassandraController {

    @Autowired
    private CassandraService cassandraService;

    @RequestMapping(value = "/CassandraSpringBootAngular/getData/{correlationID}", method = RequestMethod.GET)
    public List<CassandraBlobAsStringEntry> getCassandraDataForCorrelationId(@PathVariable String correlationID) {
        ResultSet resultSet = cassandraService.getCassandraDataForCorrelationId(correlationID,null);
        return createListFromResultSet(resultSet);
    }

    @RequestMapping(value = "/CassandraSpringBootAngular/getData", method = RequestMethod.GET)
    public List<CassandraBlobAsStringEntry> getDataForIdAndIp(@RequestParam("correlationID") String correlationID, @RequestParam("ip") String ip) {
        ResultSet resultSet = cassandraService.getCassandraDataForCorrelationId(correlationID,ip);
        return createListFromResultSet(resultSet);
    }

    @RequestMapping(value = "/CassandraSpringBootAngular/getData", method = RequestMethod.POST)
    public List<CassandraEntry> executeNativeQuery(@RequestParam("ip") String ip, @RequestBody String nativeQuery) {
        ResultSet resultSet = cassandraService.executeNativeQuery(ip, nativeQuery.substring(1, nativeQuery.length()-1));
        List<CassandraEntry> cassandraEntries = new ArrayList<CassandraEntry>();
        for(Row row : resultSet.all()) {
            CassandraEntry temp = new CassandraEntry();
            for (ColumnDefinitions.Definition definition : resultSet.getColumnDefinitions().asList()) {
                switch (definition.getName()) {
                    case "key" : {
                        temp.setKey(row.getString(definition.getName()));
                        break;
                    }
                    case "timestamp" : {
                        temp.setTimestamp(row.getTimestamp(definition.getName()));
                        break;
                    }
                    case "characteristics": {
                        temp.setCharacteristics(row.getList(definition.getName(), String.class));
                        break;
                    }
                    case "local_ip": {
                        temp.setLocalIp(row.getString(definition.getName()));
                        break;
                    }
                    case "local_port": {
                        temp.setLocalPort(row.getInt(definition.getName()));
                        break;
                    }
                    case "metaData": {
                        temp.setMetadata(row.getBytes(definition.getName()));
                        break;
                    }
                    case "payload": {
                        temp.setPayload(row.getBytes(definition.getName()));
                        break;
                    }
                    case "remote_ip": {
                        temp.setRemoteIp(row.getString(definition.getName()));
                        break;
                    }
                    case "remote_port": {
                        temp.setRemotePort(row.getInt(definition.getName()));
                        break;
                    }
                    case "remote_path": {
                        temp.setRemotePath(row.getString(definition.getName()));
                        break;
                    }
                    case "remote_user": {
                        temp.setRemoteUser(row.getString(definition.getName()));
                        break;
                    }
                    default:
                }
            }
            cassandraEntries.add(temp);
        }
        return cassandraEntries;
    }



    @RequestMapping(value = "/CassandraSpringBootAngular/", method = RequestMethod.GET)
    public String goToHomePage() {
        return "index";
    }

    private List<CassandraBlobAsStringEntry> createListFromResultSet(ResultSet resultSet) {
        List<CassandraBlobAsStringEntry> entries = new ArrayList<CassandraBlobAsStringEntry>();
        for(Row r : resultSet.all()) {
            CassandraBlobAsStringEntry entry = new CassandraBlobAsStringEntry();
            entry.setKey(r.getString("key"));
            entry.setTimestamp(r.getTimestamp("timestamp"));
            entry.setMetadata(r.getString("system.blobastext(metadata)"));
            entry.setPayload(r.getString("system.blobastext(payload)"));
            entries.add(entry);
        }
        return entries;
    }
}
