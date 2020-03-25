/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.ItemDeCompraDAO;
import modelo.dao.EstanteVirtualDAO;
import modelo.dao.PedidoDAO;
import modelos.CarrinhoDeCompra;
import modelos.EstanteVirtual;
import modelos.Pedido;
import modelos.Produto;
import modelos.Usuario;
import util.Email;

/**
 *
 * @author 11131103404
 */
public class ControlePedido extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try {

            String acao = request.getParameter("acao");

            if (acao.equals("MeusPedidos")) {

                //recupera USUARIO da sessao atual.
                HttpSession sessaoUsuario = request.getSession();
                Usuario usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");
                
                if(usuario != null){

                PedidoDAO pedidodao = new PedidoDAO();

                ArrayList<Pedido> resultado = pedidodao.MeusPedidos(usuario);

                RequestDispatcher rd = request.getRequestDispatcher("MeusPedidos.jsp");
                request.setAttribute("meusPedidos", resultado);
                rd.forward(request, response);
                }else{
                    
                    response.sendRedirect("naoAutenticado.jsp");
                }

            } else if (acao.equals("finalizarCompra")) {

                //recupera USUARIO da sessao atual.
                HttpSession sessaoUsuario = request.getSession();
                Usuario usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");
                
                //recupera o carrinho da sessão atual.
                HttpSession sessao = request.getSession();
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");

                //if abaixo para caso O usuario tenta finaliza a compra sem nenhum item no carrinho
                if (carrinho == null || carrinho.getItens().isEmpty()) {
                    response.sendRedirect("PaginaDeLivros.jsp");
                } else if (usuario == null) {
                    //caso nao tenha nenhum usuario na sessão e direcionado para pagina de login.
                    response.sendRedirect("FacaLogin.jsp");
                } else {

                    EstanteVirtualDAO estanteVirtualDAO = new EstanteVirtualDAO();
                    ArrayList<EstanteVirtual> listaDeTodosMeusLivros = estanteVirtualDAO.listarMeusLivros(usuario);

                    EstanteVirtual compra = new EstanteVirtual();
                    ArrayList<Produto> ExisteOLivro = compra.VerificaSeExisteOLivroNasMinhasCompras(carrinho, listaDeTodosMeusLivros);

                    if (ExisteOLivro.isEmpty()) {

                        

                        //joga dentro da classe pedido as datas,usuario da sessao e valor total.
                        Pedido pedido = new Pedido();                        
                        pedido.setUsuario(usuario);
                        pedido.setCarrinho(carrinho);
                        //Calcula a data "de" e "até"
                        pedido.CalculaDataDoPedido(pedido); 
                        //recupera um objeto aonde vai estar o boleto gerado
                         pedido.GerarBoleto(pedido);
                        
                       
                        //Dentro da classe pedido agora tem : Um usuario,data da compra & vencimento,e valor total da compra
                        //dentro da variavel id_pedido tem o resultado do ULTIMO id_pedido criado 
                        PedidoDAO pedidodao = new PedidoDAO();
                        pedidodao.CriarPedido(pedido);
                        
                        
                        //criar ITENS do pedido passa os itens que ta no carrinho & o id do pedido criado acima.
                        ItemDeCompraDAO item = new ItemDeCompraDAO();
                        item.CriarItensCompra(pedido);
                        
                        
                        //remove o carrinho da sessão
                        sessao.removeAttribute("carrinho");

                        //envia uma notificação que foi realizado um pedido com sucesso.
                        Email email = new Email();
                        email.EnviarNotificacaoPedidoRealizado(pedido);
                        
                        
                        

                        //direciona para pagina que a compra foi realizada com sucesso e passa o pedido para que a pagina...
                        //depois de 5 segundos emita o boleto com o numero do pedido
                        RequestDispatcher rd = request.getRequestDispatcher("CompraRealizadaComSucesso.jsp");
                        request.setAttribute("numeroDoPedido", pedido);
                        rd.forward(request, response);
                        
                       

                    } else {
                        RequestDispatcher rd = request.getRequestDispatcher("/CarrinhoDeCompras.jsp");
                        request.setAttribute("msgVoceJaTemEsseLivro", ExisteOLivro);
                        rd.forward(request, response);
                    }
                }

            }

        } catch (Exception e) {
            
            response.sendRedirect("MeusPedidos.jsp");
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
