package com.db.graduate.mysql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

class LoginHandler {
    DbResponseCode login(Connection connection, String userId, String userPwd) throws SQLException {
        boolean pwdCorrect = false;

        Statement statement = connection.createStatement();
        if (statement.execute(getPasswordSql(userId))) {
            ResultSet rs = statement.getResultSet();
            pwdCorrect = checkPassword(userPwd, rs);
        }

        storeLoginInfo();

        if (pwdCorrect) {
            return DbResponseCode.LOGIN_SUCCESSFUL;
        } else {
            return DbResponseCode.LOGIN_FAILED;
        }
    }

    private void storeLoginInfo() {
        // todo: implement in next req sprints
    }

    String getPasswordSql(String userId) {
        return "SELECT user_pwd FROM users WHERE user_id='" + userId + "'";
    }

    private boolean checkPassword(String userPwd, ResultSet resultSet) throws SQLException {
        boolean pwdCorrect = false;

        while (resultSet.next()) {
            String userPwdFromDb = resultSet.getString("user_pwd");
            pwdCorrect = pwdCorrect || Objects.equals(userPwd, userPwdFromDb);
        }

        return pwdCorrect;
    }
}
