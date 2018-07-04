package de.lars0395.github.controller;

import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
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

    @RequestMapping(value = "/getData/{correlationID}", method = RequestMethod.GET)
    public List<CassandraEntry> getCassandraDataForCorrelationId(@PathVariable String correlationID) {
        ResultSet resultSet = cassandraService.getCassandraDataForCorrelationId(correlationID,null);
        return createListFromResultSet(resultSet);
    }

    @RequestMapping(value = "/getData", method = RequestMethod.GET)
    public List<CassandraEntry> getDataForIdAndIp(@RequestParam("correlationID") String correlationID, @RequestParam("ip") String ip) {
        ResultSet resultSet = cassandraService.getCassandraDataForCorrelationId(correlationID,ip);
        return createListFromResultSet(resultSet);
    }

    @RequestMapping(value = "/getData", method = RequestMethod.POST)
    public List<CassandraEntry> executeNativeQuery(@RequestParam("ip") String ip, @RequestBody String nativeQuery) {
        ResultSet resultSet = cassandraService.executeNativeQuery(ip, nativeQuery.substring(1, nativeQuery.length()-1));
        return createListFromResultSet(resultSet);
    }



    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToHomePage() {
        return "index";
    }

    private List<CassandraEntry> createListFromResultSet(ResultSet resultSet) {
        List<CassandraEntry> entries = new ArrayList<CassandraEntry>();
        for(Row r : resultSet.all()) {
            CassandraEntry entry = new CassandraEntry();
            entry.setKey(r.getString("key"));
            entry.setTimestamp(r.getTimestamp("timestamp"));
            entry.setMetadata(r.getString("system.blobastext(metadata)"));
            entry.setPayload(r.getString("system.blobastext(payload)"));
            entries.add(entry);
        }
        return entries;
    }
}
