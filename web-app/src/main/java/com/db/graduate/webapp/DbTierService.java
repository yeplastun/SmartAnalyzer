package com.db.graduate.webapp;

import com.db.graduate.dao.Counterparty;
import com.db.graduate.dao.Instrument;
import com.db.graduate.mysql.DbFacade;
import com.db.graduate.mysql.DbResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

import static com.db.graduate.mysql.DbResponseCode.SUCCESSFUL;

@Service
public class DbTierService {
    private Logger log = LoggerFactory.getLogger(DbTierService.class);

    private DbFacade dbFacade;

    public DbTierService() {
        dbFacade = new DbFacade();
        DbResponseCode responseCode = dbFacade.connectToDb();
        if (responseCode == SUCCESSFUL) {
            log.info("Connection to database successful");
        } else {
            log.warn("Connection failed due to: {}", responseCode);
        }
    }

    public DbResponseCode login(String userId, String userPwd) {
        log.info("Logging as {}", userId);
        return dbFacade.login(userId, userPwd);
    }

    public List<Instrument> getAllInstruments() {
        try {
            return dbFacade.getAllInstruments();
        } catch (SQLException ex) {
            return Collections.emptyList();
        }
    }

    public List<Counterparty> getAllCounterparties() {
        try {
            return dbFacade.getAllCounterparties();
        } catch (SQLException ex) {
            return Collections.emptyList();
        }
    }
}
