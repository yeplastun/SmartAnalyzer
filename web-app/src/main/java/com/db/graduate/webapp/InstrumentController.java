package com.db.graduate.webapp;

import com.db.graduate.dao.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.util.List;

@RestController
public class InstrumentController {
    final DbTierService dbService;

    @Autowired
    public InstrumentController(DbTierService dbService) {
        this.dbService = dbService;
    }

    @RequestMapping("/hello/{name}")
    String hello(@PathVariable String name) {
        return "Hello, " + name + "!";
    }

    @RequestMapping("instrument/all")
    List<Instrument> getAllInstruments() {
        return dbService.getAllInstruments();
    }
}
