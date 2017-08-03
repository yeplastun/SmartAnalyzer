<%@ page import="com.db.graduate.mysql.*" %>
<html>
<body>
<h2>Database Connection Status : 
<% 
DbFacade db = new DbFacade();
out.println(db.connect());
%>
</h2>
</body>
</html>
