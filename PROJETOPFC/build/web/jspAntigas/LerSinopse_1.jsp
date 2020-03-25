<%-- 
    Document   : teste
    Created on : 20/04/2016, 22:04:08
    Author     : guilherme
--%>

<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/LerSinopse.css" media="all">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Sinopse do livro</h1>


        <%
            Produto sinopse = (Produto) request.getAttribute("sinopse");
            if (sinopse == null) {
        %>

        <font color="red">Não existe sinopse para este Livro!</font>
        <%}%>





        <P id="titulo">Titulo: <label id="titulo2">  <%=sinopse.getTitulo()%> </label> <P>   
            <textarea id="sinopse" disabled="true"><%= sinopse.getSinopse()%> </textarea>

            <a href="PaginaDeLivros.jsp"> <button type="button" id="voltar" >Voltar</button></a>



    </body>
</html>
