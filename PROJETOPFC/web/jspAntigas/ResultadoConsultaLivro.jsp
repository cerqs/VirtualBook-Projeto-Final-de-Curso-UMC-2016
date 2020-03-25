
<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Virtual book</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />      
        <link rel="stylesheet" href="assets/css/main_1.css" />

    </head>
    <body class="homepage">
        <div id="page-wrapper">

            <!-- Header -->
            <div id="header-wrapper">
                <header id="header" class="container">

                    <!-- Logo -->
                    <div id="logo">
                        <h1><a href="InserirUpload.jsp">Virtual Book</a></h1>

                    </div>



                </header>
            </div>

            <!-- Banner -->
            <div id="banner-wrapper">
                <div id="banner" class="box container">
                    <div class="row">
                        <div class="7u 12u(medium)">

                            <h1>Consulta</h1>
                            <%
                                Produto p = (Produto) request.getAttribute("Consulta");
                            %> 
                            <table border="1">
                                <thead>
                                    <tr>
                                        <th>ID</th>
                                        <th>Titulo</th>
                                        <th>autor</th>
                                        <th>preco</th>
                                        <th>lancamento</th>
                                        <th>isbn</th>
                                        <th>sinopse</th>

                                    </tr>
                                </thead>
                                <tbody>
                                    <tr>
                                        <td><%= p.getId()%> </td>
                                        <td><%= p.getTitulo()%> </td>
                                        <td><%= p.getAutor()%> </td>
                                        <td><%= p.getPreco()%> </td> 
                                        <td><%= p.getLancamento()%> </td>
                                        <td><%= p.getIsbn()%> </td>
                                        <td><%= p.getSinopse()%> </td>
                                    </tr>
                                </tbody>
                            </table>

                        </div>
                        <div class="5u 12u(medium)">
                            -
                        </div>
                    </div>

                </div>


            </div>

            <!-- Scripts -->

            <script src="assets/js/jquery.min.js"></script>
            <script src="assets/js/jquery.dropotron.min.js"></script>
            <script src="assets/js/skel.min.js"></script>
            <script src="assets/js/util.js"></script>
            <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
            <script src="assets/js/main.js"></script>

    </body>
</html>
