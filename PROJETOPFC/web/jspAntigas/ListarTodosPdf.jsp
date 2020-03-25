<%-- 
    Document   : ListarTodosPdf
    Created on : 23/04/2016, 03:11:17
    Author     : guilherme
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="css/tamanhoimg.css" media="all">
        <link rel="stylesheet" href="css/PaginaDeLivros.css" media="all">
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <style type="text/css">

            #background-layout{background:url(image/livros.jpg);
                               background-size:300%;
            }


        </style>
        <title>Livraria Virtual Book</title>
    </head>
    <body id="background-layout">
        <%
             
            Produto pp = (Produto) request.getAttribute("listaDePendencia");
       ArrayList<Produto> listaProduto = (ArrayList<Produto>) request.getAttribute("listaProduto");
       if (listaProduto == null) {

           request.getRequestDispatcher("/ControleProduto?acao=ListarTodosPdf").forward(request, response);
       }
        %>



        <table border="1" align="center">

            <thead> 


                <tr>
                    <th>Nome Pdf</th>                                


                </tr>
            </thead>


            <%
                for (Produto p : listaProduto) {

            %>
            <tr>

                <td> <%= p.getPdf()%> </td>
                <td> <a
                        href="ControleProduto?acao=AtualizarPdf&isbn=<%=pp.getIsbn()%>&nome=<%=p.getPdf()%>">
                        <button type="button" id="opcoes" ">Escolher este</button></td>

                <%
                    }
                %>


        </table> 



    </body>
</html>
