<%-- 
    Document   : pagamentoAutomatico
    Created on : 06/05/2016, 15:28:51
    Author     : 11131103404
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        ArrayList<Produto> listaProduto = (ArrayList<Produto>) request.getAttribute("listaProduto");
        if (listaProduto == null) {

            request.getRequestDispatcher("/ControleProduto?acao=ControlarPagamento").forward(request, response);
        }
    %>
    </body>
</html>
