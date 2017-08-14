package com.db.graduate.webapp.controller;

import com.db.graduate.webapp.DbTierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class VisualizationController {
    private final DbTierService dbService;

    @Autowired
    public VisualizationController(DbTierService dbService) {
        this.dbService = dbService;
    }

    @RequestMapping("/visualization/avg_amounts_per_instrument_name")
    public Map<String, Double> getAvgAmountsPerInstrumentName() {
        return dbService.getAvgAmountsPerInstrumentName();
    }
}
