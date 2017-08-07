package com.db.graduate.webapp;

import com.db.graduate.mysql.DbFacade;
import com.db.graduate.mysql.DbResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import static com.db.graduate.mysql.DbResponseCode.SUCCESSFUL;

@Service
public class DbTier {
    private Logger log = LoggerFactory.getLogger(DbTier.class);

    private DbFacade dbFacade;

    public DbTier() {
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
}
