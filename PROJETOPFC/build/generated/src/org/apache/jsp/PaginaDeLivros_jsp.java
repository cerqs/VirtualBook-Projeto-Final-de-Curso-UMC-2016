package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.DecimalFormat;
import modelos.CarrinhoDeCompra;
import modelos.Avaliacao;
import java.text.SimpleDateFormat;
import modelos.Usuario;
import java.util.ArrayList;
import modelos.Produto;

public final class PaginaDeLivros_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=ISO-8859-1");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>       \n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=ISO-8859-1\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/tamanhoimg.css\" media=\"all\">        \n");
      out.write("        <link rel=\"stylesheet\" href=\"css/modal.css\" media=\"all\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/modalSinopse.css\" media=\"all\">\n");
      out.write("        <link href=\"css/css.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script language=\"javascript\" src=\"js/jquery-1.7.2.js\"></script>        \n");
      out.write("        <link href=\"js/jquery-ui.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <script src=\"js/jquery-ui.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"js/completion.js\" type=\"text/javascript\"></script>\n");
      out.write("        <link href=\"css/BarraNavegar.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/PaginaDeLivros.css\" media=\"all\">\n");
      out.write("        <link href=\"scripts/css/avaliacao.css\" rel=\"stylesheet\" type=\"text/css\"/>       \n");
      out.write("        <link href=\"scripts/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\"/>      \n");
      out.write("\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("\n");
      out.write("\n");
      out.write("            #background-layout{background:url(images/bg.png);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            #formCarrinhoDeCompras2{                                        \n");
      out.write("                background-size:100%;\n");
      out.write("                background:#000;\n");
      out.write("                opacity:0,9;\n");
      out.write("                border-radius:30px 30px 30px 30px;\n");
      out.write("            }\n");
      out.write("\n");
      out.write("            #formCarrinhoDeCompras3{                                        \n");
      out.write("                background-size:100%;\n");
      out.write("                background:#000;\n");
      out.write("                opacity:0,9;\n");
      out.write("                border-radius:30px 30px 30px 30px;\n");
      out.write("            }  \n");
      out.write("\n");
      out.write("        </style>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <title>Livraria Virtual Book</title>\n");
      out.write("    </head>\n");
      out.write("    <body id=\"background-layout\"> \n");
      out.write("\n");
      out.write("        ");
 Usuario usuario = (Usuario) session.getAttribute("usuarioAutenticado"); 
      out.write("\n");
      out.write("        \n");
      out.write("        \n");
      out.write("\n");
      out.write("        <!-- Navbar -->\n");
      out.write("        <ul class=\"w3-navbar w3-black w3-card-2 w3-top w3-left-align w3-large\">\n");
      out.write("\n");
      out.write("            <li class=\"w3-hide-medium w3-hide-large w3-opennav w3-right\">    \n");
      out.write("            </li>\n");
      out.write("\n");
      out.write("            ");


                if (usuario == null) {

            
      out.write("\n");
      out.write("            <li><a href=\"index.jsp\" class=\"w3-padding-large w3-white\">Entre / cadastre-se</a></li>\n");
      out.write("\n");
      out.write("            ");
} else {
      out.write("\n");
      out.write("            <li><a href=\"PaginaDeLivros.jsp\" class=\"w3-padding-large w3-white\">HOME</a></li> \n");
      out.write("            <li class=\"w3-hide-small\"><a href=\"atualizarCliente.jsp\" class=\"w3-padding-large w3-hover-white\">Gerenciar conta</a></li>            \n");
      out.write("            <li class=\"w3-hide-small\"><a href=\"EstanteVirtual.jsp\" class=\"w3-padding-large w3-hover-white\">Estante virtual</a></li>  \n");
      out.write("            <li class=\"w3-hide-small\"><a href=\"MeusPedidos.jsp\" class=\"w3-padding-large w3-hover-white\">Meus Pedidos</a></li>\n");
      out.write("\n");
      out.write("            ");
 if (usuario.getLogin().getPerfil().getDescricao().equals("adm")) {
      out.write("\n");
      out.write("\n");
      out.write("           <li class=\"w3-hide-small\"><a href=\"AdmTelaPrincipal.jsp\" class=\"w3-padding-large w3-hover-white\">Gerenciar livros</a></li>\n");
      out.write("\n");
      out.write("            ");
 } 
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            ");
}
      out.write("\n");
      out.write("\n");
      out.write("            ");

                // recupera o carrinho na sessao para mostrar na barra de navegação quantos itens tem no carrinho
                HttpSession sessao = request.getSession();
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");
                Integer qtdeDeItens = 0;
                if (carrinho == null || carrinho.getTotal() == 0) {
                    carrinho = null;
                } else {
                    qtdeDeItens = carrinho.getItens().size();
                }
            
      out.write("\n");
      out.write("\n");
      out.write("            <li><a href=\"ControleCarrinho?acao=vercarrinho\" class=\"w3-padding-large w3-yellow\">Ver carrinho<i class=\"fa fa-shopping-cart\"></i> ");
      out.print(qtdeDeItens);
      out.write("</a></li> \n");
      out.write("\n");
      out.write("            ");
 if (usuario != null) {
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            <li class=\"w3-hide-small\"><a href=\"ControleAcesso?acao=Sair\" class=\"w3-padding-large w3-hover-white\">Sair</a></li>\n");
      out.write("\n");
      out.write("            ");
 }
      out.write("\n");
      out.write("\n");
      out.write("        </ul>\n");
      out.write("\n");
      out.write("            \n");
      out.write("        <form action=\"ControleProduto\" method=\"post\">\n");
      out.write("            <div id=\"retornoAjax\" class=\"ui-widget\">\n");
      out.write("                ");
 if (usuario != null) {
      out.write("\n");
      out.write("                <input type=\"text\" class=\"inputLogado\" autocomplete=\"on\" id=\"search\" placeholder=\"");
      out.print(usuario.getNome().toUpperCase());
      out.write(", Qual livro você procura?\" required=\"required\" name=\"txt_pesquisa_livro\">\n");
      out.write("                ");
} else {
      out.write("\n");
      out.write("                <input type=\"text\"  autocomplete=\"on\" id=\"search\" class=\"input\" placeholder=\"Qual livro você procura?\" required=\"required\" name=\"txt_pesquisa_livro\">\n");
      out.write("                ");
}
      out.write("\n");
      out.write("                <button type=\"submit\" class=\"submitPesquisarCarrinho\" title=\"pesquisar\" name=\"acao\" value=\"pesquisar\"><i class=\"fa fa-search\" aria-hidden=\"true\" style=\"font-size:25px\"></i></button>\n");
      out.write("            </div>\n");
      out.write("        </form>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        <form id=\"formCarrinhoDeCompras2\">\n");
      out.write("        </form>\n");
      out.write("           \n");
      out.write("           \n");
      out.write("           ");

               String boletoNaoExiste = (String) request.getAttribute("boletoNaoExiste");
               if (boletoNaoExiste != null) {
           
      out.write("\n");
      out.write("           <br>\n");
      out.write("           <font color=\"red\"> ");
      out.print(boletoNaoExiste);
      out.write("</font>\n");
      out.write("\n");
      out.write("           ");
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("           ");

               String msg = (String) request.getAttribute("msgexiste");
               if (msg != null) {
           
      out.write("\n");
      out.write("\n");
      out.write("           <font color=\"red\"> ");
      out.print(msg);
      out.write(" <label>Caso queira visualizar, clique</label><a href=\"ControleCarrinho?acao=vercarrinho\"> AQUI</a></font>\n");
      out.write("           ");
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("           ");

               String msgsemcarrinho = (String) request.getAttribute("msgg");
               if (msgsemcarrinho != null) {
           
      out.write("\n");
      out.write("\n");
      out.write("           <font id=\"verCarrinhoTexto2\" color=\"black\"> ");
      out.print(msgsemcarrinho);
      out.write("</font>\n");
      out.write("           ");
}
      out.write("\n");
      out.write("\n");
      out.write("           ");

               String msgNaoExisteLivroParaComentar = (String) request.getAttribute("msg");
               if (msgNaoExisteLivroParaComentar != null) {
           
      out.write("\n");
      out.write("\n");
      out.write("           <font color=\"red\"> ");
      out.print(msgNaoExisteLivroParaComentar);
      out.write(" </font>\n");
      out.write("           ");
}
      out.write("\n");
      out.write("        \n");
      out.write("\n");
      out.write("           ");

               // RECUPERA OS PRODUTOS E OS RECOMENDADOS CASO TENHA.
               ArrayList<Produto> Recomendados = (ArrayList<Produto>) request.getAttribute("recomendados");
               ArrayList<Produto> produtos = (ArrayList<Produto>) request.getAttribute("produtos");
               if (produtos == null) {
                   //envia requisição para a servlet 
                   request.getRequestDispatcher("/ControleCarrinho?acao=listar").forward(request, response);
               }
           
      out.write("\n");
      out.write("\n");
      out.write("           <form action=\"ControleCarrinho\" method=\"post\">\n");
      out.write("               <label >Listar pelo:</label>\n");
      out.write("               <select id=\"opcoes3\"  name=\"escolha\">\n");
      out.write("                   <option value=\"titulo\">Título</option>             \n");
      out.write("                   <option value=\"precoMaior\">Menor preço</option>\n");
      out.write("                   <option value=\"precoMenor\">Maior preço</option>\n");
      out.write("               </select>\n");
      out.write("               <input id=\"opcoes3\" type=\"submit\" value=\"listar\"  name=\"acao\">\n");
      out.write("           </form>\n");
      out.write("\n");
      out.write("\n");
      out.write("           <table border=\"0\" cellpadding=\"5\" align=\"center\">\n");
      out.write("               ");

                   DecimalFormat df = new DecimalFormat("###,##0.00");
                   int contadorColuna = 1;
                   for (Produto produto : produtos) {
                       //se é o primeiro produto, cria o inicio da linha
                       if (contadorColuna == 1) {
      out.write("\n");
      out.write("                           <tr>\n");
      out.write("                       ");
}
               
      out.write("\n");
      out.write("\n");
      out.write("               <td align=\"center\"> \n");
      out.write("                   <div class=\"container\">\n");
      out.write("                       <img src=\"imagens/");
      out.print(produto.getImagem());
      out.write("\" alt=\"Imagem do produto");
      out.print(produto.getImagem());
      out.write("\"/> <br/>\n");
      out.write("                   </div>\n");
      out.write("                   <label id=\"nomeProduto\"> ");
      out.print(produto.getTitulo());
      out.write("</label><br/>                \n");
      out.write("                   <label id=\"nomeProduto\"><label>R$</label>");
      out.print(df.format(produto.getPreco()));
      out.write("</label><br/>\n");
      out.write("                   <a href=\"ControleCarrinho?acao=addProduto&idProduto=");
      out.print(produto.getId());
      out.write("\"> <button type=\"button\" id=\"opcaoComprar\" title=\"Adicionar no carrinho!\"><i class=\"fa fa-cart-plus\" style=\"font-size:25px\"></i></button></a>            \n");
      out.write("                   <a href=\"ControleProduto?acao=verSinopse&idProduto=");
      out.print(produto.getId());
      out.write("\"> <button type=\"button\" id=\"opcaoComprar2\" title=\"Sinopse\"><i class=\"fa fa-book\" style=\"font-size:25px\"></i></button></a>                \n");
      out.write("                   <a href=\"ControleAvaliacao?acao=VerAvaliacao&idProduto=");
      out.print(produto.getId());
      out.write("\"> <button type=\"button\" id=\"opcaoComprar2\" title=\"Avaliações\"><i class=\"fa fa-star-o\" style=\"font-size:25px\"></i></button></a>\n");
      out.write("\n");
      out.write("               </td>            \n");
      out.write("               ");

                       //se completou 6 colunas, fecha a primeira linha
                       if (contadorColuna == 6) {
      out.write("\n");
      out.write("                           </tr>\n");
      out.write("                           ");
contadorColuna = 0;
                       }
                       //atualiza o contador de colunas
                       contadorColuna++;

                   }//fim do for
               
      out.write("  \n");
      out.write("           </table>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("           ");
 if (Recomendados == null || Recomendados.isEmpty()) { 
      out.write("            \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("           ");
} else {
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("           <form id=\"formCarrinhoDeCompras3\">\n");
      out.write("           </form>\n");
      out.write("\n");
      out.write("           <h1>Recomendados para você</h1>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("           <table border=\"0\" cellpadding=\"5\" align=\"center\">\n");
      out.write("               ");

                   int contadorColuna2 = 1;
                   for (Produto produtoRecomendado : Recomendados) {
                       //se é o primeiro produto, cria o inicio da linha
                       if (contadorColuna2 == 1) {
      out.write("\n");
      out.write("                           <tr>\n");
      out.write("                       ");
}
      out.write("\n");
      out.write("                       \n");
      out.write("               <td align=\"center\"> \n");
      out.write("                   <div class=\"container\">\n");
      out.write("                       <img src=\"imagens/");
      out.print(produtoRecomendado.getImagem());
      out.write("\" alt=\"Imagem do produto");
      out.print(produtoRecomendado.getImagem());
      out.write("\"/> <br/>\n");
      out.write("                   </div>\n");
      out.write("                   <label id=\"nomeProduto\"> ");
      out.print(produtoRecomendado.getTitulo());
      out.write("</label><br/>                \n");
      out.write("                   <label id=\"nomeProduto\"><label>R$</label>");
      out.print(df.format(produtoRecomendado.getPreco()));
      out.write("</label><br/>\n");
      out.write("                   <a href=\"ControleCarrinho?acao=addProduto&idProduto=");
      out.print(produtoRecomendado.getId());
      out.write("\"> <button type=\"button\" id=\"opcaoComprar\" title=\"Adicionar no carrinho!\"><i class=\"fa fa-cart-plus\" style=\"font-size:25px\"></i></button></a>            \n");
      out.write("                   <a href=\"ControleCarrinho?acao=verSinopse&idProduto=");
      out.print(produtoRecomendado.getId());
      out.write("\"> <button type=\"button\" id=\"opcaoComprar2\" title=\"Sinopse\"><i class=\"fa fa-book\" style=\"font-size:25px\"></i></button></a>                \n");
      out.write("                   <a href=\"ControleAvaliacao?acao=VerAvaliacao&idProduto=");
      out.print(produtoRecomendado.getId());
      out.write("\"> <button type=\"button\" id=\"opcaoComprar2\" title=\"Avaliações\"><i class=\"fa fa-star-o\" style=\"font-size:25px\"></i></button></a>\n");
      out.write("\n");
      out.write("               </td>            \n");
      out.write("               ");

                       //se completou 3 colunas, fecha a primeira linha
                       if (contadorColuna2 == 7) { 
      out.write("\n");
      out.write("                           </tr>\n");
      out.write("                           ");
 contadorColuna2 = 0;
                       }
                       //atualiza o contador de colunas
                       contadorColuna2++;

                   }//fim do for
               
      out.write("  \n");
      out.write("           </table>\n");
      out.write("           ");
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("           ");

               Produto sinopse = (Produto) request.getAttribute("sinopse");
               ArrayList<Avaliacao> avalicao = (ArrayList<Avaliacao>) request.getAttribute("avaliacao");
               Integer resultadoFinal = (Integer) request.getAttribute("media");
               // IF QUE FAZ CASO TENHA UM COMENTARIO OU UMA SINOPSE ABRIR O MODAL SE NÃO TIVER ESSE IF O MODAL ABRE AUTOMATICAMENTE
               if (sinopse != null || avalicao != null) {
           
      out.write("   \n");
      out.write("\n");
      out.write("           <p id=\"myBtn\"></p>\n");
      out.write("\n");
      out.write("           <div id=\"myModal\" class=\"modalSinopse\">\n");
      out.write("\n");
      out.write("               <div class=\"modal-contentSinopse\">\n");
      out.write("                   <div class=\"modal-headerSinopse\">\n");
      out.write("                       <span class=\"close\">x</span>\n");
      out.write("\n");
      out.write("\n");
      out.write("                       ");
 if (sinopse != null) {  
      out.write("\n");
      out.write("                       <h2>Leia a sinopse </h2>\n");
      out.write("                       ");
}
      out.write("\n");
      out.write("\n");
      out.write("                       ");
 if (avalicao != null) {  
      out.write("                    \n");
      out.write("\n");
      out.write("                       <h2>Leia os comentarios - Media de avaliações: \n");
      out.write("\n");
      out.write("                           ");
 for (int i = 1; i <= resultadoFinal; i++) {  
      out.write("\n");
      out.write("\n");
      out.write("                           <i class=\"fa fa-star\" style=\"color:yellow\"></i>\n");
      out.write("\n");
      out.write("\n");
      out.write("                           ");
} 
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                           ");
 for (int i = 5; i != resultadoFinal; i--) {  
      out.write("\n");
      out.write("\n");
      out.write("                           <i class=\"fa fa-star\" style=\"color:white\"></i>\n");
      out.write("\n");
      out.write("                           ");
}
      out.write("\n");
      out.write("\n");
      out.write("                       </h2>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                       ");
}
      out.write("\n");
      out.write("\n");
      out.write("                   </div>\n");
      out.write("                   <div class=\"modal-bodySinopse\">\n");
      out.write("\n");
      out.write("                       ");
 if (sinopse != null) {
      out.write("\n");
      out.write("                       <P id=\"titulo\">Titulo: <label id=\"titulo2\">  ");
      out.print(sinopse.getTitulo());
      out.write("</label> <P>   \n");
      out.write("                           <textarea id=\"sinopse\" disabled=\"true\">");
      out.print( sinopse.getSinopse());
      out.write("</textarea>\n");
      out.write("\n");
      out.write("                           ");
}
      out.write(" \n");
      out.write("\n");
      out.write("\n");
      out.write("                           ");
 if (avalicao != null) {  
      out.write("\n");
      out.write("                        \n");
      out.write("                        <textarea id=\"comentario\" disabled=\"true\">                     \n");
      out.write("                            ");

                                for (Avaliacao LerComentarios : avalicao) {
                            
      out.write("\n");
      out.write("                            ");
SimpleDateFormat formatoDesejado = new SimpleDateFormat("dd/MM/yyyy");
                                String dataFormatada = null;
                                dataFormatada = formatoDesejado.format(LerComentarios.getData_Comentario());
      out.write('\n');
      out.print(dataFormatada);
      out.write(" - ");
      out.print(LerComentarios.getUsuario().getNome().toUpperCase());
      out.write(':');
      out.write(' ');
      out.print(LerComentarios.getComentario());
      out.write("                    \n");
      out.write(" ");
 } 
      out.write(" \n");
      out.write("\n");
 if (avalicao.isEmpty()) { 
      out.write("\n");
      out.write("                                      Não existem comentários para este livro!");
}
}
      out.write("</textarea> \n");
      out.write("                </div>\n");
      out.write("\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("                <script src=\"js/Modal.js\" type=\"text/javascript\"></script>\n");
      out.write("                \n");
      out.write("        \n");
      out.write("\n");
      out.write("\n");
      out.write("        ");
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("</html>\n");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
