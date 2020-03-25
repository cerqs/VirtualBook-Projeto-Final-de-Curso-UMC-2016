<%-- 
    Document   : LeituraDoLivro
    Created on : 07/11/2016, 20:51:54
    Author     : 11131103404
--%>

<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
       <%Produto produtos = (Produto) request.getAttribute("livro"); %>
       
        
        <iframe src="/PROJETOPFC/pdf/<%=produtos.getPdf()%>" width="100%" height="900" style="border: none;"></iframe>
    </body>
</html>
