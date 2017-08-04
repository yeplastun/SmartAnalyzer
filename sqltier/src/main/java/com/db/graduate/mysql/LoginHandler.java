package com.db.graduate.mysql;

import java.sql.*;
import java.util.Objects;

class LoginHandler {
    DbResponseCode login(Connection connection, String userId, String userPwd) throws SQLException {
        boolean pwdCorrect = false;

        PreparedStatement statement = connection.prepareStatement(getPasswordSql());
        statement.setString(1, userId);
        if (statement.execute()) {
            ResultSet rs = statement.getResultSet();
            pwdCorrect = checkPassword(userPwd, rs);
        }
        statement.close();

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

    String getPasswordSql() {
        return "SELECT user_pwd FROM users WHERE user_id=?";
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
