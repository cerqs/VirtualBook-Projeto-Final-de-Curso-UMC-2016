
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Virtual book</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />        
        <link rel="stylesheet" href="assets/css/main_2.css" />

    </head>
    <body class="homepage">
        <div id="page-wrapper">

            <!-- Header -->
            <div id="header-wrapper">
                <div id="header">



                    <!-- Logo -->
                    <h1><a href="principal.jsp">Virtual Book</a></h1>

                    <!-- Nav -->
                    <nav id="nav">
                        <ul>
                            <li class="current"><a href="principal.jsp">Home</a></li>
                            <li><a href="atualizarCliente.jsp">Alterar dados</a></li>
                            <li><a href="excluirDadosCliente.jsp">Excluir conta</a></li>                            
                            <li><a href="PaginaDeLivros.jsp">Comprar Livros</a></li>
                            <li><a href="MeusLivros.jsp">Meus Livros</a></li>
                            <li><a href="ControleCarrinho?acao=MeusPedidos">Meus Pedidos</a></li>
                            <li><a href="ControleAcesso?acao=Sair">Logout</a></li>



                            <%
                                Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado");
                                if (usuario.getLogin().getPerfil().getDescricao().equals("adm")) {

                            %>

                            <li><a href="Relatorios.jsp">Gerar relatórios</a></li>
                            <li><a href="testandoUmaTelaPrincipal.jsp">Administração do acervo</a></li>

                            <%}%>



                        </ul>
                    </nav>


                    <!-- Banner -->
                    <section id="banner">
                        <header>
                            <%

                                if (usuario != null) {
                            %>
                            <h2>Bem-vindo, <%= usuario.getNome()%> !</h2> 
                            <%}%>

                        </header>
                    </section>

                </div>
            </div>





            <!-- Scripts -->
            <script src="assets/js/jquery.min.js"></script>
            <script src="assets/js/jquery.dropotron.min.js"></script>
            <script src="assets/js/skel.min.js"></script>
            <script src="assets/js/skel-viewport.min.js"></script>
            <script src="assets/js/util.js"></script>            
            <script src="assets/js/main.js"></script>

    </body>
</html>
