<%-- 
    Document   : comentario
    Created on : 10/08/2016, 17:07:00
    Author     : 11131103404
--%>

<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="iso-8859-1"%>

<!DOCTYPE html>
<html>
    <html xmlns="http://www.w3.org/1999/xhtml" lang="pt-br" xml:lang="pt-br"></html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">             
            <link href="css/Comentario.css" rel="stylesheet" type="text/css"/>
            <link href="css/BarraNavegar.css" rel="stylesheet" type="text/css"/>
            <link href="scripts/css/avaliacao.css" rel="stylesheet" type="text/css"/>
            <!-- <link href="scripts/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>-->
            <link href="scripts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
            <!-- <script src="scripts/js/jquery-3.1.0.min.js" type="text/javascript"></script>-->
            <!-- <script src="scripts/js/bootstrap.min.js" type="text/javascript"></script>-->
            <script src="js/jquery-1.7.2.js" type="text/javascript"></script>
            <script src="js/jquery.validate.js"type="text/javascript"></script>
            <script src="js/validacaoDeAvaliacao.js" type="text/javascript"></script>
            <title>JSP Page</title>
            <style type="text/css">

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
            Integer idProduto = (Integer) request.getAttribute("idProduto");

        %>
        
        <span id="msg"></span>
        
        <form id="formAvaliacao" action="ControleAvaliacao" method="POST"> 
            <table>
                <tr>
                    <td><input type="hidden" name="id_produto" value="<%=idProduto%>" ></td>
                    <td></td>
                </tr>
                    
                <tr>
                    
                    <td><textarea name="txt_comentario" rows="10" cols="30" class="teste" placeholder="Comente e dê sua nota"></textarea>  </td>   
                    <td></td>
                </tr>
                    
            </table>
                    
            <div class="estrelas">                
                <label for="cm_star-1"><i class="fa fa-2x" style="margin-left:42%;"></i></label>
                <input type="radio" id="cm_star-1" name="fb" value="1"/>
                <label for="cm_star-2"><i class="fa fa-2x "></i></label>
                <input type="radio" id="cm_star-2" name="fb" value="2"/>
                <label for="cm_star-3"><i class="fa fa-2x "></i></label>
                <input type="radio" id="cm_star-3" name="fb" value="3"/>
                <label for="cm_star-4"><i class="fa fa-2x "></i></label>
                <input type="radio" id="cm_star-4" name="fb" value="4" checked/>
                <label for="cm_star-5"><i class="fa fa-2x "></i></label>
                <input type="radio" id="cm_star-5" name="fb" value="5" />
            </div>      

            <input type="submit" value="Avaliar" class="submit" name="acao">

        </form>
                    
          

    </body>
</html>
