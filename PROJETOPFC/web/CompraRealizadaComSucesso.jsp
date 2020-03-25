<%-- 
    Document   : CompraRealizadaComSucesso
    Created on : 18/05/2016, 16:20:05
    Author     : 11131103404
--%>

<%@page import="modelos.ItemDeCompra"%>
<%@page import="modelos.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>

    <head>
        <%
            Pedido pedido = (Pedido) request.getAttribute("numeroDoPedido");

        %>

        <META HTTP-EQUIV="Refresh" CONTENT="10 ;URL=ControlePedido?acao=MeusPedidos">        
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/CompraRealizadaOk.css" rel="stylesheet" type="text/css"/>
        <link href="scripts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <style type="text/css">

            #background-layout{background:url(images/bg.png);
            }

        </style>

        <script>
            var myVar = setTimeout(myTimer, 6000);

            function myTimer() {
                window.open('ControleRelatorios?acao=GerarBoleto&idPedido=<%=pedido.getId()%>');
            }
        </script>

        <title>Confirmação de pedido</title>
    </head>
    <body id="background-layout">       

        <p class="gerandoboleto"><%=pedido.getUsuario().getNome().toUpperCase()%> obrigado por escolher o Virtual Book, aqui o nosso comprometimento é com você! </p> <br>
        <br>
        <br>
        <br>     
        <p class="gerandoboleto2"> Aguarde que estamos processando o seu boleto...<i class="fa fa-spinner fa-spin"></i></p>

    </body>
</html>
