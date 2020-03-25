package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import modelos.ItemDeCompra;
import java.util.ArrayList;
import modelos.Pedido;

public final class MeusPedidos_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      response.setContentType("text/html;charset=UTF-8");
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <link href=\"css/MeusPedidos.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"css/BarraNavegar.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <link href=\"scripts/css/font-awesome.min.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("        <style type=\"text/css\">\n");
      out.write("\n");
      out.write("            #background-layout{background:url(images/bg.png);\n");
      out.write("            }\n");
      out.write("\n");
      out.write("        </style>\n");
      out.write("        <title>JSP Page</title>\n");
      out.write("    </head>\n");
      out.write("    <body id=\"background-layout\">\n");
      out.write("\n");
      out.write("        <!-- Navbar -->\n");
      out.write("        <ul class=\"w3-navbar w3-black w3-card-2 w3-top w3-left-align w3-large\">\n");
      out.write("\n");
      out.write("            <li class=\"w3-hide-medium w3-hide-large w3-opennav w3-right\">    \n");
      out.write("            </li>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <li><a href=\"PaginaDeLivros.jsp\" class=\"w3-padding-large w3-white\">HOME</a></li> \n");
      out.write("            <li class=\"w3-hide-small\"><a href=\"atualizarCliente.jsp\" class=\"w3-padding-large w3-hover-white\">Alterar Dados</a></li>\n");
      out.write("            <li class=\"w3-hide-small\"><a href=\"excluirDadosCliente.jsp\" class=\"w3-padding-large w3-hover-white\">Excluir</a></li>\n");
      out.write("            <li class=\"w3-hide-small\"><a href=\"MeusLivros.jsp\" class=\"w3-padding-large w3-hover-white\">Meus livros</a></li>  \n");
      out.write("            <li class=\"w3-hide-small\"><a href=\"ControlePedido?acao=MeusPedidos\" class=\"w3-padding-large w3-hover-white\">Meus Pedidos</a></li>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            <li><a href=\"ControleCarrinho?acao=vercarrinho\" class=\"w3-padding-large w3-yellow\">Ver carrinho<i class=\"fa fa-shopping-cart\"></i></a></li> \n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            <li class=\"w3-hide-small\"><a href=\"ControleAcesso?acao=Sair\" class=\"w3-padding-large w3-hover-white\">Sair</a></li>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        </ul>\n");
      out.write("        \n");
      out.write("        \n");
      out.write("        \n");
      out.write("           ");

            String boletoNaoExiste = (String) request.getAttribute("boletoNaoExiste");
            if (boletoNaoExiste != null) {
        
      out.write("\n");
      out.write("\n");
      out.write("        <font color=\"black\"> ");
      out.print(boletoNaoExiste);
      out.write("</font>\n");
      out.write("        \n");
      out.write("        ");
}
      out.write("\n");
      out.write("        \n");
      out.write("\n");
      out.write("\n");
      out.write("        ");


            ArrayList<Pedido> pedido = (ArrayList<Pedido>) request.getAttribute("meusPedidos");
            

            if(pedido.size() >= 0){                
             
        
      out.write("\n");
      out.write("        <table border=\"1\" width=\"1010px\" align=\"center\" class=\"borda\" >\n");
      out.write("\n");
      out.write("            <thead> \n");
      out.write("\n");
      out.write("\n");
      out.write("                <tr>\n");
      out.write("                    <th width=\"150px\" align=\"center\">Número do Pedido</th>\n");
      out.write("                    <th width=\"150px\" align=\"center\">Item / Itens </th>\n");
      out.write("                    <th width=\"150px\" align=\"center\">Status do Pedido</th>\n");
      out.write("                    <th width=\"20px\" align=\"center\"></th> \n");
      out.write("                </tr>\n");
      out.write("            </thead>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            ");
                int numero = 0;
                for (int i = 0; i < pedido.size(); i++) {


            
      out.write("\n");
      out.write("            <tr> \n");
      out.write("\n");
      out.write("                <td> </td>\n");
      out.write("\n");
      out.write("                ");
                    if (i == 0) {
                
      out.write("   \n");
      out.write("\n");
      out.write("                <td> </td>\n");
      out.write("\n");
      out.write("                ");
} else {
      out.write("\n");
      out.write("                <td align=\"center\" height=\"50px\"> </td>\n");
      out.write("\n");
      out.write("                ");
}
      out.write("\n");
      out.write("\n");
      out.write("                ");


                    for (int ii = 0; ii < pedido.get(i).getCarrinho().getItens().size(); ii++) {


                
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            <tr>\n");
      out.write("\n");
      out.write("                ");
                    if (pedido.get(i).getId() != numero) {


                
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                <td align=\"center\" > ");
      out.print( pedido.get(i).getId());
      out.write(" </td>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                ");
} else {
      out.write("\n");
      out.write("\n");
      out.write("                <td align=\"center\" > </td>\n");
      out.write("\n");
      out.write("                ");
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                <td align=\"center\"> ");
      out.print(pedido.get(i).getCarrinho().getItens().get(ii).getProduto().getTitulo());
      out.write(" </td>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                ");


                    if (pedido.get(i).getStatus().equals("pendente")) {
                
      out.write("    \n");
      out.write("\n");
      out.write("                <td align=\"center\">Aguardando pagamento <i class=\"fa fa-credit-card\" aria-hidden=\"true\"></i></td>\n");
      out.write("\n");
      out.write("                ");
} else if (pedido.get(i).getStatus().equals("pago")) {
      out.write("\n");
      out.write("\n");
      out.write("                <td align=\"center\">Pagamento confirmado / Aguardando liberação <i class=\"fa fa-spinner fa-spin\"></i></td>\n");
      out.write("\n");
      out.write("                ");
} else if (pedido.get(i).getStatus().equals("liberado")) { 
      out.write("\n");
      out.write("\n");
      out.write("                <td align=\"center\">Livro Liberado <i class=\"fa fa-check\" aria-hidden=\"true\"></i></td>  \n");
      out.write("\n");
      out.write("                ");
} else if (pedido.get(i).getStatus().equals("cancelado")) { 
      out.write("\n");
      out.write("\n");
      out.write("                <td align=\"center\">Pedido Cancelado <i class=\"fa fa-ban\" aria-hidden=\"true\"></i></i></td>\n");
      out.write("\n");
      out.write("                ");
 }
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                ");


                    if (pedido.get(i).getStatus().equals("pendente")) {

                        if (pedido.get(i).getId() != numero) {

                
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                <td><a href=\"ControleRelatorios?acao=GerarBoleto&idPedido=");
      out.print( pedido.get(i).getId());
      out.write("\" target=\"_blank\">\n");
      out.write("                        <button type=\"button\" id=\"opcoes4\">Gerar Boleto <i class=\"fa fa-commenting-o\" aria-hidden=\"true\" style=\"font-size:12px\"></i></i></button></td>\n");
      out.write("\n");
      out.write("\n");
      out.write("                ");
} else {
      out.write("\n");
      out.write("\n");
      out.write("                <td> </td>\n");
      out.write("\n");
      out.write("\n");
      out.write("                ");
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("                ");
}
      out.write("\n");
      out.write("\n");
      out.write("                ");
 numero = pedido.get(i).getId(); 
      out.write("\n");
      out.write("\n");
      out.write("            </tr>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("        </tr>\n");
      out.write("\n");
      out.write("\n");
      out.write("        ");

            }
        
      out.write("\n");
      out.write("        \n");
      out.write("         ");

            }
        
      out.write("\n");
      out.write("\n");
      out.write("    </table>\n");
      out.write("</body>\n");
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
