<%-- 
    Document   : index
    Created on : 07/03/2016, 16:15:37
    Author     : 11131103404
--%>

<%@page import="java.text.DecimalFormat"%>
<%@page import="modelos.CarrinhoDeCompra"%>
<%@page import="modelos.Avaliacao"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="modelos.Usuario"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelos.Produto"%>
<%@page contentType="text/html" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
    <head>       
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
        <link rel="stylesheet" href="css/tamanhoimg.css" media="all">        
        <link rel="stylesheet" href="css/modal.css" media="all">
        <link rel="stylesheet" href="css/modalSinopse.css" media="all">
        <link href="css/css.css" rel="stylesheet" type="text/css"/>
        <script language="javascript" src="js/jquery-1.7.2.js"></script>        
        <link href="js/jquery-ui.css" rel="stylesheet" type="text/css"/>
        <script src="js/jquery-ui.js" type="text/javascript"></script>
        <script src="js/completion.js" type="text/javascript"></script>
        <link href="css/BarraNavegar.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="css/PaginaDeLivros.css" media="all">
        <link href="scripts/css/avaliacao.css" rel="stylesheet" type="text/css"/>       
        <link href="scripts/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>      

        <style type="text/css">


            #background-layout{background:url(images/bg.png);
            }

            #formCarrinhoDeCompras2{                                        
                background-size:100%;
                background:#000;
                opacity:0,9;
                border-radius:30px 30px 30px 30px;
            }

            #formCarrinhoDeCompras3{                                        
                background-size:100%;
                background:#000;
                opacity:0,9;
                border-radius:30px 30px 30px 30px;
            }  

        </style>


        <title>Livraria Virtual Book</title>
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

            <%} else {%>
            <li><a href="PaginaDeLivros.jsp" class="w3-padding-large w3-white">HOME</a></li> 
            <li class="w3-hide-small"><a href="atualizarCliente.jsp" class="w3-padding-large w3-hover-white">Gerenciar conta</a></li>            
            <li class="w3-hide-small"><a href="EstanteVirtual.jsp" class="w3-padding-large w3-hover-white">Estante virtual</a></li>  
            <li class="w3-hide-small"><a href="MeusPedidos.jsp" class="w3-padding-large w3-hover-white">Meus Pedidos</a></li>

            <% if (usuario.getLogin().getPerfil().getDescricao().equals("adm")) {%>

           <li class="w3-hide-small"><a href="AdmTelaPrincipal.jsp" class="w3-padding-large w3-hover-white">Gerenciar livros</a></li>

            <% } %>




            <%}%>

            <%
                // recupera o carrinho na sessao para mostrar na barra de navegação quantos itens tem no carrinho
                HttpSession sessao = request.getSession();
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");
                Integer qtdeDeItens = 0;
                if (carrinho == null || carrinho.getTotal() == 0) {
                    carrinho = null;
                } else {
                    qtdeDeItens = carrinho.getItens().size();
                }
            %>

            <li><a href="ControleCarrinho?acao=vercarrinho" class="w3-padding-large w3-yellow">Ver carrinho<i class="fa fa-shopping-cart"></i> <%=qtdeDeItens%></a></li> 

            <% if (usuario != null) {%>


            <li class="w3-hide-small"><a href="ControleAcesso?acao=Sair" class="w3-padding-large w3-hover-white">Sair</a></li>

            <% }%>

        </ul>

            
        <form action="ControleProduto" method="post">
            <div id="retornoAjax" class="ui-widget">
                <% if (usuario != null) {%>
                <input type="text" class="inputLogado" autocomplete="on" id="search" placeholder="<%=usuario.getNome().toUpperCase()%>, Qual livro você procura?" required="required" name="txt_pesquisa_livro">
                <%} else {%>
                <input type="text"  autocomplete="on" id="search" class="input" placeholder="Qual livro você procura?" required="required" name="txt_pesquisa_livro">
                <%}%>
                <button type="submit" class="submitPesquisarCarrinho" title="pesquisar" name="acao" value="pesquisar"><i class="fa fa-search" aria-hidden="true" style="font-size:25px"></i></button>
            </div>
        </form>



        <form id="formCarrinhoDeCompras2">
        </form>
           
           
           <%
               String boletoNaoExiste = (String) request.getAttribute("boletoNaoExiste");
               if (boletoNaoExiste != null) {
           %>
           <br>
           <font color="red"> <%=boletoNaoExiste%></font>

           <%}%>


           <%
               String msg = (String) request.getAttribute("msgexiste");
               if (msg != null) {
           %>

           <font color="red"> <%=msg%> <label>Caso queira visualizar, clique</label><a href="ControleCarrinho?acao=vercarrinho"> AQUI</a></font>
           <%}%>


           <%
               String msgsemcarrinho = (String) request.getAttribute("msgg");
               if (msgsemcarrinho != null) {
           %>

           <font id="verCarrinhoTexto2" color="black"> <%=msgsemcarrinho%></font>
           <%}%>

           <%
               String msgNaoExisteLivroParaComentar = (String) request.getAttribute("msg");
               if (msgNaoExisteLivroParaComentar != null) {
           %>

           <font color="red"> <%=msgNaoExisteLivroParaComentar%> </font>
           <%}%>
        

           <%
               // RECUPERA OS PRODUTOS E OS RECOMENDADOS CASO TENHA.
               ArrayList<Produto> Recomendados = (ArrayList<Produto>) request.getAttribute("recomendados");
               ArrayList<Produto> produtos = (ArrayList<Produto>) request.getAttribute("produtos");
               if (produtos == null) {
                   //envia requisição para a servlet 
                   request.getRequestDispatcher("/ControleCarrinho?acao=listar").forward(request, response);
               }
           %>

           <form action="ControleCarrinho" method="post">
               <label >Listar pelo:</label>
               <select id="opcoes3"  name="escolha">
                   <option value="titulo">Título</option>             
                   <option value="precoMaior">Menor preço</option>
                   <option value="precoMenor">Maior preço</option>
               </select>
               <input id="opcoes3" type="submit" value="listar"  name="acao">
           </form>


           <table border="0" cellpadding="5" align="center">
               <%
                   DecimalFormat df = new DecimalFormat("###,##0.00");
                   int contadorColuna = 1;
                   for (Produto produto : produtos) {
                       //se é o primeiro produto, cria o inicio da linha
                       if (contadorColuna == 1) {%>
                           <tr>
                       <%}
               %>

               <td align="center"> 
                   <div class="container">
                       <img src="imagens/<%=produto.getImagem()%>" alt="Imagem do produto<%=produto.getImagem()%>"/> <br/>
                   </div>
                   <label id="nomeProduto"> <%=produto.getTitulo()%></label><br/>                
                   <label id="nomeProduto"><label>R$</label><%=df.format(produto.getPreco())%></label><br/>
                   <a href="ControleCarrinho?acao=addProduto&idProduto=<%=produto.getId()%>"> <button type="button" id="opcaoComprar" title="Adicionar no carrinho!"><i class="fa fa-cart-plus" style="font-size:25px"></i></button></a>            
                   <a href="ControleProduto?acao=verSinopse&idProduto=<%=produto.getId()%>"> <button type="button" id="opcaoComprar2" title="Sinopse"><i class="fa fa-book" style="font-size:25px"></i></button></a>                
                   <a href="ControleAvaliacao?acao=VerAvaliacao&idProduto=<%=produto.getId()%>"> <button type="button" id="opcaoComprar2" title="Avaliações"><i class="fa fa-star-o" style="font-size:25px"></i></button></a>

               </td>            
               <%
                       //se completou 6 colunas, fecha a primeira linha
                       if (contadorColuna == 6) {%>
                           </tr>
                           <%contadorColuna = 0;
                       }
                       //atualiza o contador de colunas
                       contadorColuna++;

                   }//fim do for
               %>  
           </table>



           <% if (Recomendados == null || Recomendados.isEmpty()) { %>            



           <%} else {%>


           <form id="formCarrinhoDeCompras3">
           </form>

           <h1>Recomendados para você</h1>



           <table border="0" cellpadding="5" align="center">
               <%
                   int contadorColuna2 = 1;
                   for (Produto produtoRecomendado : Recomendados) {
                       //se é o primeiro produto, cria o inicio da linha
                       if (contadorColuna2 == 1) {%>
                           <tr>
                       <%}%>
                       
               <td align="center"> 
                   <div class="container">
                       <img src="imagens/<%=produtoRecomendado.getImagem()%>" alt="Imagem do produto<%=produtoRecomendado.getImagem()%>"/> <br/>
                   </div>
                   <label id="nomeProduto"> <%=produtoRecomendado.getTitulo()%></label><br/>                
                   <label id="nomeProduto"><label>R$</label><%=df.format(produtoRecomendado.getPreco())%></label><br/>
                   <a href="ControleCarrinho?acao=addProduto&idProduto=<%=produtoRecomendado.getId()%>"> <button type="button" id="opcaoComprar" title="Adicionar no carrinho!"><i class="fa fa-cart-plus" style="font-size:25px"></i></button></a>            
                   <a href="ControleProduto?acao=verSinopse&idProduto=<%=produtoRecomendado.getId()%>"> <button type="button" id="opcaoComprar2" title="Sinopse"><i class="fa fa-book" style="font-size:25px"></i></button></a>                
                   <a href="ControleAvaliacao?acao=VerAvaliacao&idProduto=<%=produtoRecomendado.getId()%>"> <button type="button" id="opcaoComprar2" title="Avaliações"><i class="fa fa-star-o" style="font-size:25px"></i></button></a>

               </td>            
               <%
                       //se completou 3 colunas, fecha a primeira linha
                       if (contadorColuna2 == 7) { %>
                           </tr>
                           <% contadorColuna2 = 0;
                       }
                       //atualiza o contador de colunas
                       contadorColuna2++;

                   }//fim do for
               %>  
           </table>
           <%}%>


           <%
               Produto sinopse = (Produto) request.getAttribute("sinopse");
               ArrayList<Avaliacao> avalicao = (ArrayList<Avaliacao>) request.getAttribute("avaliacao");
               Avaliacao resultadoFinal = (Avaliacao) request.getAttribute("media");
               // IF QUE FAZ CASO TENHA UM COMENTARIO OU UMA SINOPSE ABRIR O MODAL SE NÃO TIVER ESSE IF O MODAL ABRE AUTOMATICAMENTE
               if (sinopse != null || avalicao != null) {
           %>   

           <p id="myBtn"></p>

           <div id="myModal" class="modalSinopse">

               <div class="modal-contentSinopse">
                   <div class="modal-headerSinopse">
                       <span class="close">x</span>


                       <% if (sinopse != null) {  %>
                       <h2>Leia a sinopse </h2>
                       <%}%>

                       <% if (avalicao != null) {  %>                    

                       <h2>Leia os comentarios - Media de avaliações: 

                           <% for (int i = 1; i <= resultadoFinal.getNota(); i++) {  %>

                           <i class="fa fa-star" style="color:yellow"></i>


                           <%} %>


                           <% for (int i = 5; i != resultadoFinal.getNota(); i--) {  %>

                           <i class="fa fa-star" style="color:white"></i>

                           <%}%>

                       </h2>



                       <%}%>

                   </div>
                   <div class="modal-bodySinopse">

                       <% if (sinopse != null) {%>
                       <P id="titulo">Titulo: <label id="titulo2">  <%=sinopse.getTitulo()%></label> <P>   
                           <textarea id="sinopse" disabled="true"><%= sinopse.getSinopse()%></textarea>

                           <%}%> 


                           <% if (avalicao != null) {  %>
                        
                        <textarea id="comentario" disabled="true">                     
                            <%
                                for (Avaliacao LerComentarios : avalicao) {
                            %>
                            <%SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");
                                String dataFormatada = null;
                                dataFormatada = formatoDesejado.format(LerComentarios.getData_Comentario());%>
<%=dataFormatada%> - <%=LerComentarios.getUsuario().getNome().toUpperCase()%>: <%=LerComentarios.getComentario()%>                    
 <% } %> 

<% if (avalicao.isEmpty()) { %>
                                      Não existem comentários para este livro!<%}%><%}%></textarea> 
                </div>

            </div>



        </div>
                <script src="js/Modal.js" type="text/javascript"></script>
                
        


        <%}%>





    </body>
</html>
