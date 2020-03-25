<%-- 
    Document   : MeusPedidos
    Created on : 20/05/2016, 16:06:30
    Author     : 11131103404
--%>

<%@page import="modelos.ItemDeCompra"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Pedido"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="css/MeusPedidos.css" rel="stylesheet" type="text/css"/>
        <link href="css/BarraNavegar.css" rel="stylesheet" type="text/css"/>
        <link href="scripts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
        <style type="text/css">

            #background-layout{background:url(images/bg.png);
            }

        </style>
        <title>JSP Page</title>
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
        
        z
        


        <%

            ArrayList<Pedido> pedido = (ArrayList<Pedido>) request.getAttribute("meusPedidos");
            
            if (pedido == null) {
                   //envia requisição para a servlet 
                   request.getRequestDispatcher("/ControlePedido?acao=MeusPedidos").forward(request, response);
               }

        %>
        <table border="1" width="1010px" align="center" class="borda" >

            <thead> 


                <tr>
                    <th width="150px" align="center">Número do Pedido</th>
                    <th width="150px" align="center">Item / Itens </th>
                    <th width="150px" align="center">Status do Pedido</th>
                    <th width="20px" align="center"></th> 
                </tr>
            </thead>



            <%                int numero = 0;
                for (int i = 0; i < pedido.size(); i++) {


            %>
            <tr> 

                <td> </td>

                <%                    if (i == 0) {
                %>   

                <td> </td>

                <%} else {%>
                <td align="center" height="50px"> </td>

                <%}%>

                <%

                    for (int ii = 0; ii < pedido.get(i).getCarrinho().getItens().size(); ii++) {


                %>


            <tr>

                <%                    if (pedido.get(i).getId() != numero) {


                %>



                <td align="center" > <%= pedido.get(i).getId()%> </td>



                <%} else {%>

                <td align="center" > </td>

                <%}%>


                <td align="center"> <%=pedido.get(i).getCarrinho().getItens().get(ii).getProduto().getTitulo()%> </td>




                <%

                    if (pedido.get(i).getStatus().equals("pendente")) {
                %>    

                <td align="center">Aguardando pagamento <i class="fa fa-credit-card" aria-hidden="true"></i></td>

                <%} else if (pedido.get(i).getStatus().equals("pago")) {%>

                <td align="center">Pagamento confirmado / Aguardando liberação <i class="fa fa-spinner fa-spin"></i></td>

                <%} else if (pedido.get(i).getStatus().equals("liberado")) { %>

                <td align="center">Livro Liberado <i class="fa fa-check" aria-hidden="true"></i></td>  

                <%} else if (pedido.get(i).getStatus().equals("cancelado")) { %>

                <td align="center">Pedido Cancelado <i class="fa fa-ban" aria-hidden="true"></i></i></td>

                <% }%>


                <%

                    if (pedido.get(i).getStatus().equals("pendente")) {

                        if (pedido.get(i).getId() != numero) {

                %>


                <td><a href="ControleRelatorios?acao=GerarBoleto&idPedido=<%= pedido.get(i).getId()%>" target="_blank">
                        <button type="button" id="opcoes4">Gerar Boleto <i class="fa fa-commenting-o" aria-hidden="true" style="font-size:12px"></i></i></button></td>


                <%} else {%>

                <td> </td>


                <%}%>




                <%}%>

                <% numero = pedido.get(i).getId(); %>

            </tr>
            <%}%>








        </tr>


        <%
            }
        %>

    </table>
</body>
</html>
