<%-- 
    Document   : erro
    Created on : 07/03/2016, 16:16:01
    Author     : 11131103404
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ERRO</title>
    </head>
    <%
    Exception erro = (Exception) request.getAttribute("erro");

    %>

    <body>
        <h1>erro! <%=erro%></h1>
    </body>
</html>
