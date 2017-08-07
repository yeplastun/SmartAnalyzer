package com.db.graduate.webapp;

import com.db.graduate.dao.Counterparty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CounterpartyConstroller {
    private final DbTierService dbService;

    @Autowired
    public CounterpartyConstroller(DbTierService dbService) {
        this.dbService = dbService;
    }

    @RequestMapping("/counterparty/all")
    List<Counterparty> getAllCounterparties() {
        return dbService.getAllCounterparties();
    }
}
