package de.lars0395.github.controller;

import com.datastax.driver.core.ResultSet;
import de.lars0395.github.service.CassandraService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CassandraController {

    @Autowired
    private CassandraService cassandraService;

    @RequestMapping(value = "/getData/{correlationID}", method = RequestMethod.GET)
    public ResultSet getCassandraDataForCorrelationId(@PathVariable String correlationID) {
        return cassandraService.getCassandraDataForCorrelationId(correlationID,null);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToHomePage() {
        return "index";
    }
}
