package com.db.graduate.webapp;

import com.db.graduate.mysql.DbFacade;
import com.db.graduate.mysql.DbResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Service
public class DbUserDetailsService implements UserDetailsService {

    private Logger log = LoggerFactory.getLogger(DbUserDetailsService.class);

    private Collection<GrantedAuthority> grantedAuthorities =
            Collections.singletonList(new SimpleGrantedAuthority("USER"));

    private DbFacade dbFacade;

    public DbUserDetailsService() {
        dbFacade = new DbFacade();
        dbFacade.connectToDb();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            return new User(username, getPassword(username), grantedAuthorities);
        } catch (SQLException ex) {
            throw new UsernameNotFoundException("Unable to get check password", ex);
        }
    }

    private String getPassword(String userId) throws SQLException {
        if (!dbFacade.isConnected()) {
            dbFacade.connectToDb();
        }
        return dbFacade.getPasswordByUserId(userId);
    }
}
