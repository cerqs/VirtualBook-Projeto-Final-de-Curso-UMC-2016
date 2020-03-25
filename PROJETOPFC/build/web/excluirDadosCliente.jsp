
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


            <li><a href="ControleCarrinho?acao=vercarrinho" class="w3-padding-large w3-yellow">Ver carrinho!<i class="fa fa-shopping-cart"></i></a></li> 




            <li class="w3-hide-small"><a href="ControleAcesso?acao=Sair" class="w3-padding-large w3-hover-white">Sair</a></li>



        </ul>



        <div id="page-wrapper">

            <div id="header-wrapper">
                <header id="header" class="container">

                </header>
            </div>
    </body>


    <%
        //PUXAR DA SESSAO O USUARIO 
        Usuario usuario = (Usuario) request.getAttribute("dadosCliente");
        if (usuario == null) {
            request.getRequestDispatcher("/ControleAcesso?acao=DadosClienteExcluir").forward(request, response);
        }
    %>



    <!-- Banner -->
    <div id="banner-wrapper">
        <div id="banner" class="box container">
            <div class="row">
                <div class="7u 12u(medium)">

                    <form id="form" action="ControleAcesso" method="post">

                        <table>


                            <tr>
                                <td><label for="email" ></label>Seu e-mail</td>
                                <td><input  type="email" id="email" value=<%= usuario.getLogin().getLogin()%> disabled></td>
                                <td><input  type="hidden" id="email" name="txtLogin" value=<%= usuario.getLogin().getLogin()%> ></td>
                                <td></td>

                            </tr> 





                            <tr>
                                <td><label for="id" ></label>Digite sua senha</td>
                                <td><input type="password" id="id" name="txtSenha"/></td>
                                <td></td>
                            </tr> 

                            <tr>
                                <td></td>                        
                        </table>
                        <input type="submit" name="acao" value="Excluir">
                        </tr>

                    </form>
                </div>
                <span id="msg"></span>  
                </body>


            </div>
            <div class="5u 12u(medium)">
                -
            </div>
        </div>

    </div>



</div>



<script src="js/jquery-1.7.2.js" type="text/javascript"></script>
<script src="js/jquery.validate.js"type="text/javascript"></script>
<script src="js/validacao.js"type="text/javascript"></script>
<link href="css/css.css" rel="stylesheet" type="text/css"/>

</html>
