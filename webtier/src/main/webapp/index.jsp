<%@ page import="com.db.graduate.mysql.*" %>
<% 
String username = request.getParameter("username");
String password = request.getParameter("password");
if(username != null && !username.trim().isEmpty()){
	DbFacade db = new DbFacade();
	out.println(db.connectWithCredentials(username, password));
}
%>
<html>
<body>
<h2>Login</h2> 
<form action="index.jsp" method="post">
Username : <input type="text" name="username"> 
Password : <input type="password" name="password">
<button type="submit">Login</button>
</form>
</body>
</html>
