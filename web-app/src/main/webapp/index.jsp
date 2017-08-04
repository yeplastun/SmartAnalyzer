<%@ page import="com.db.graduate.mysql.*" %>
<%@ page import="static com.db.graduate.mysql.DbResponseCode.*" %>
<%
    DbFacade db = new DbFacade();
    DbResponseCode responseCode = null;
    try {
        responseCode = db.connectToDb();
    } catch (ClassNotFoundException e) {
        responseCode = FAILED;
    }
    String username = request.getParameter("username");
    String password = request.getParameter("password");
    if (username != null && !username.trim().isEmpty() && responseCode == SUCCESSFUL) {
        out.println(db.login(username, password));
    }
%>
<html>
<body>
<h2>Database Connection Status : <% out.println(responseCode); %>
</h2>
<h2>Login</h2>
<form action="index.jsp" method="post">
    Username : <input type="text" name="username">
    Password : <input type="password" name="password">
    <button type="submit">Login</button>
</form>
</body>
</html>
