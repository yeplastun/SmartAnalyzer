package com.db.graduate.webapp.controller;

import com.db.graduate.dao.Deal;
import com.db.graduate.webapp.DbTierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DealController {
    private final DbTierService dbTierService;

    @Autowired
    public DealController(DbTierService dbTierService) {
        this.dbTierService = dbTierService;
    }

    @RequestMapping("/deal/all")
    public List<Deal> getAllDeals() {
        return dbTierService.getAllDeals();
    }
}
