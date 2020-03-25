<%-- 
    Document   : carrinho
    Created on : 07/03/2016, 16:15:45
    Author     : 11131103404
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="modelos.Produto"%>
<%@page import="modelos.EstanteVirtual"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Usuario"%>
<%@page import="modelos.ItemDeCompra"%>
<%@page import="modelos.CarrinhoDeCompra"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">

        <title>Virtual Book</title>

        <meta http-equiv="cleartype" content="on">

        <link rel="shortcut icon" href="/favicon.ico">
        <meta name="MobileOptimized" content="320">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
        <script src="js/jquery.validate.js"type="text/javascript"></script>
        <script src="js/validacaoLogin.js"type="text/javascript"></script>

        <!-- Stylesheets -->
        <link rel="stylesheet" href="css/CarrinhoDeCompras.css" media="all">
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


            #background-layout{background:url(images/bg.png);
            }

            th, td {
                padding: 2px;
            }

            th {
                text-align: left;
            }


            /*            table#t01 tr:nth-child(even) {
                            background-color:#65ffba;
            
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
            /*                        #background-layout{background:url(image/livros.jpg);
                                                       background-size:100%;}*/
            #formCarrinhoDeCompras{                                        
                background-size:100%;
                background:#0e0c0c;
                opacity:0,9;
                border-radius:30px 30px 30px 30px;
            }

        </style>


    </head>
    <body id="background-layout">


        <% Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado"); %>

        <!-- Navbar -->
        <ul class="w3-navbar w3-black w3-card-2 w3-top w3-left-align w3-large">

            <li class="w3-hide-medium w3-hide-large w3-opennav w3-right">    
            </li>

            <%

                if (usuario == null) {

            %>
            <li><a href="index.jsp" class="w3-padding-large w3-white">Entre / cadastre-se</a></li>
            <li class="w3-hide-small"><a href="PaginaDeLivros.jsp" class="w3-padding-large w3-hover-white">HOME</a></li>       

            <%} else{%>
            <li><a href="PaginaDeLivros.jsp" class="w3-padding-large w3-white">HOME</a></li> 
            <li class="w3-hide-small"><a href="atualizarCliente.jsp" class="w3-padding-large w3-hover-white">Gerenciar conta</a></li>            
            <li class="w3-hide-small"><a href="EstanteVirtual.jsp" class="w3-padding-large w3-hover-white">Estante virtual</a></li>  
            <li class="w3-hide-small"><a href="MeusPedidos.jsp" class="w3-padding-large w3-hover-white">Meus Pedidos</a></li>
            <%}%>



            <% if (usuario != null) {%>


            <li class="w3-hide-small"><a href="ControleAcesso?acao=Sair" class="w3-padding-large w3-hover-white">Sair</a></li>

            <% }%>

        </ul>


        <%

            ArrayList<Produto> listaDosMeusLivros = (ArrayList<Produto>) request.getAttribute("msgVoceJaTemEsseLivro");

        %>






        <h1 id="cabecalho">Carrinho de Compras</h1>
        <table border="1" cellpadding="2" class="borda" id="t01" >

            <thead> 

                <tr>
                    <th width="150px">Imagem</font></th>
                    <th width="280px">Titulo</font></th>
                    <th width="180px">Autor</font></th>
                    <th width="200px">Genero</font></th> 
                    <th width="180px" >Valor do Livro</th> 
                    <th width="200px" >Isbn</font></th>

                </tr>

            </thead>

            <%                String inativo = (String) request.getAttribute("inativo");
                if (inativo != null) {
            %>
            <br>
            <font color="red"> <%=inativo%></font>

            <%}%>


            <%  if (listaDosMeusLivros != null) {%>


            <font id="verCarrinhoTextoJaPossuiOLivro" color="black"> Desculpe, mas voce já possui os seguinte(s) iten(s) <%
                for (Produto produtos : listaDosMeusLivros) {%>
            <label id="verCarrinhoTextoJaPossuiOLivroTEXTO"><%=produtos.getTitulo()%>,</label>
            <%}%>
            para ver clique  <a href="EstanteVirtual.jsp">Aqui!</a>
            </font>
            <%}%>


            <%
                DecimalFormat df = new DecimalFormat("###,##0.00");
                //recupera os produtos do carrinho da sessao
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) session.getAttribute("carrinho");

                if (carrinho == null || carrinho.getItens() == null) {
                    return;
                }

                for (ItemDeCompra item : carrinho.getItens()) {

            %>
            <tr>
                <td>
                    <div class="container">
                        <img src="imagens/<%=item.getProduto().getImagem()%>" alt="Imagem do produto<%=item.getProduto().getImagem()%>"/>  </div> </td>

                <td height="100"><%=item.getProduto().getTitulo()%></td>                
                <td height="100"><%=item.getProduto().getAutor()%></td> 
                <td height="100"><%=item.getProduto().getGenero().getDesc_genero()%></td>
                <td height="100">R$ <%=df.format(item.getProduto().getPreco())%></td>                
                <td height="100"><%=item.getProduto().getIsbn()%></td>

                <td><a
                        href="ControleCarrinho?acao=removeProduto&idProduto=<%=item.getProduto().getId()%>">
                        <button type="button" id="opcoes4"><i class="fa fa-trash-o"></i></button></td>


            </tr>

            <%
                }
            %>

        </table>


        <a href="PaginaDeLivros.jsp"><button type="button" id="opcoes3" > Continue comprando</button></a>


        <form id="formCarrinhoDeCompras">

            <strong id="valortotal">Valor Total: R$ <%=df.format(carrinho.calculaTotal())%></strong>
            <a href="ControlePedido?acao=finalizarCompra"><button type="button" id="opcoes2" >Finalizar minha compra</button></a>

            <a href="ControleCarrinho?acao=cancelaCompra"><button type="button" id="opcoes5" > Cancelar </button></a> 

        </form> 




    </body>
</html>
