<%-- 
    Document   : alterarSenha
    Created on : 03/05/2016, 16:35:51
    Author     : 11131103404
--%>


<%@page import="modelos.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Virtual book</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />       
        <link rel="stylesheet" href="assets/css/main_1.css" />
        <link href="css/BarraNavegar.css" rel="stylesheet" type="text/css"/>
        <link href="scripts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>

    </head>
    <body class="homepage">


        <!-- Navbar -->
        <ul class="w3-navbar w3-black w3-card-2 w3-top w3-left-align w3-large">

            <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">    
            </li>


            <li><a href="PaginaDeLivros.jsp" class="w3-padding-large w3-white">HOME</a></li> 
            <li class="w3-hide-small"><a href="atualizarCliente.jsp" class="w3-padding-large w3-hover-white">Gerenciar conta</a></li>            
            <li class="w3-hide-small"><a href="EstanteVirtual.jsp" class="w3-padding-large w3-hover-white">Estante virtual</a></li>  
            <li class="w3-hide-small"><a href="MeusPedidos.jsp" class="w3-padding-large w3-hover-white">Meus Pedidos</a></li>



            <li><a href="ControleCarrinho?acao=vercarrinho" class="w3-padding-large w3-yellow">Ver carrinho<i class="fa fa-shopping-cart"></i></a></li> 





            <li class="w3-hide-small"><a href="ControleAcesso?acao=Sair" class="w3-padding-large w3-hover-white">Sair</a></li>



        </ul>




        <div id="page-wrapper">

            <!-- Header -->
            <div id="header-wrapper">
                <header id="header" class="container">

                </header>
            </div>

            <!-- Banner -->
            <div id="banner-wrapper">
                <div id="banner" class="box container">
                    <div class="row">
                        <div class="7u 12u(medium)">

                            <%
                                //PUXAR DA SESSAO O USUARIO 
                                Usuario usuario = (Usuario) request.getAttribute("dadosCliente");
                                if (usuario == null) {
                                    request.getRequestDispatcher("/ControleAcesso?acao=DadosClienteAlterarSenha").forward(request, response);
                                }
                            %>

                            <%
                                String msg = (String) request.getAttribute("invalido");
                                if (msg != null) {
                            %>

                            <font color="red"> <%=msg%></font>
                            <%}%>


                            <form id="form" action="ControleAcesso" method="post">

                                <table>


                                    <tr>
                                        <td><label for="email" ></label>seu email</td>
                                        <td><input  type="email" id="email" value="<%= usuario.getLogin().getLogin()%>" disabled></td>
                                        <td><input  type="hidden" id="email" name="txtLogin" value=<%= usuario.getLogin().getLogin()%> ></td>
                                        <td></td>

                                    </tr>



                                    <tr>
                                        <td><label for="senha" ></label>Nova senha</td>
                                        <td><input type="password" id="senha" name="txtSenha" ></td>
                                        <td></td>

                                    </tr>

                                    <tr>
                                        <td><label for="senha" ></label>Digite novamente a nova senha</td>
                                        <td><input type="password" id="senha" name="txtSenha2" ></td>
                                        <td></td>

                                    </tr>

                                    <tr>
                                        <td></td> 

                                        <td></td>  
                                </table>
                                <input type="submit" name="acao" value="Alterar">
                                </tr>
                            </form>

                        </div>
                        <div class="5u 12u(medium)">
                            -
                        </div>
                    </div>

                </div>



                </body>
                </html>
