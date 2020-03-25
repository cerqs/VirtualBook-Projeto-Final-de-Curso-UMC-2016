<%-- 
    Document   : MeusLivros
    Created on : 23/04/2016, 00:33:29
    Author     : guilherme
--%>

<%@page import="modelos.EstanteVirtual"%>
<%@page import="java.util.ArrayList"%> 
<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<!-- HTML5 Boilerplate -->
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->

    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title>Virtual Book</title>

        <meta http-equiv="cleartype" content="on">

        <link rel="shortcut icon" href="/favicon.ico">
        <meta name="MobileOptimized" content="320">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <!-- Stylesheets -->
        <link rel="stylesheet" href="css/MeusLivros.css" media="all">
        <link rel="stylesheet" href="css/html5reset.css" media="all">
        <link rel="stylesheet" href="css/col.css" media="all">
        <link rel="stylesheet" href="css/2cols.css" media="all">
        <link rel="stylesheet" href="css/3cols.css" media="all">
        <link rel="stylesheet" href="css/4cols.css" media="all">
        <link rel="stylesheet" href="css/5cols.css" media="all">
        <link rel="stylesheet" href="css/6cols.css" media="all">
        <link rel="stylesheet" href="css/7cols.css" media="all">
        <link rel="stylesheet" href="css/8cols.css" media="all">
        <link rel="stylesheet" href="css/9cols.css" media="all">
        <link rel="stylesheet" href="css/10cols.css" media="all">
        <link rel="stylesheet" href="css/11cols.css" media="all">
        <link rel="stylesheet" href="css/12cols.css" media="all">
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <link href="css/BarraNavegar.css" rel="stylesheet" type="text/css"/>
        <link href="scripts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <style type="text/css">

            th, td {
                padding: 2px;
            }

            th {
                text-align: left;
            }


            /*            table#t01 tr:nth-child(even) {
                            background-color:#c5b8fc;
            
                        }*/
            table#t01 tr:nth-child(odd) {

            }

            body { font : 100%/1.4 'Helvetica Neue', arial, helvetica, helve, sans-serif; 	
            }
            h1 { font-size:2.2em; padding:0 0 .5em 0; }
            h2 { font-size:1.5em; }
            .header { padding:1em 0; }
            /*
            .col { background: #ccc; padding:1em 0; text-align:center;}
            */
            #background-layout{background:url(images/bg.png);
            }

        </style>





    </head>

    <body id="background-layout">

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



        <%

            ArrayList<EstanteVirtual> listaDePendencia = (ArrayList<EstanteVirtual>) request.getAttribute("listaProduto");
            if (listaDePendencia == null) {

                request.getRequestDispatcher("/ControleProduto?acao=VerMeusLirvos").forward(request, response);

            }

            if (listaDePendencia.size() == 0) {
        %> 

        <form id="cab">
            <text id="cabecalho2">Você ainda não possui nenhum livro. </text><br>
        </form>

        <% return;
                }%>



        <form id="formCadastrarLivro" action="ControleProduto" method="POST">


            <div id="box-inputs">
                <caption> <h1 id="cabecalho">Meus Livros</h1> </caption>
                <table border="1" width="1010px" class="borda" id="t01">

                    <thead> 


                        <tr>
                            <th width="120px"> </th>
                            <th width="200px">Titulo</th>
                            <th width="200px">Autor</th>
                            <th width="180px">isbn</th>
                            <th width="140px">Status</th>
                            <th width="140px"></th>                                    

                        </tr>
                    </thead>


                    <%
                        for (EstanteVirtual p : listaDePendencia) {

                    %>
                    <tr>
                        <td>
                            <div class="container">
                                <img src="imagens/<%=p.getProduto().getImagem()%>" alt="Imagem do produto<%=p.getProduto().getImagem()%>"/>  </div> </td>
                        <td height="50"> <%= p.getProduto().getTitulo()%> </td>
                        <td height="50"> <%= p.getProduto().getAutor()%> </td>                  
                        <td height="50"> <%= p.getProduto().getIsbn()%> </td>                                    
                        <td height="50"> Liberado </td>                                                                 

                        <td width="200px"> <a
                                href="ControleProduto?acao=LerLivro&idProduto=<%= p.getProduto().getId()%>" target="_blank">
                                <button type="button" id="opcoes4">Exibir conteúdo <i class="fa fa-book" style="font-size:16px"></i></button></td>

                        <td><a
                                href="ControleAvaliacao?acao=Avaliacao&idProduto=<%= p.getProduto().getId()%>" >
                                <button type="button" id="opcoes4">Avaliar <i class="fa fa-commenting-o" aria-hidden="true" style="font-size:12px"></i></i></button></td>

                    </tr> 
                    <%
                        }
                    %>


                </table> 



            </div>

        </form>




    </body>
</html>


