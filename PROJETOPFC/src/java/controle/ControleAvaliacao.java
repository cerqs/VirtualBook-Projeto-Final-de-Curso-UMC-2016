/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.AvaliacaoDAO;
import modelo.dao.EstanteVirtualDAO;
import modelos.Avaliacao;
import modelos.EstanteVirtual;
import modelos.Produto;
import modelos.Usuario;

/**
 *
 * @author 11131103404
 */
public class ControleAvaliacao extends HttpServlet {

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
        try  {
             String acao = request.getParameter("acao");
             
             if (acao.equals("Avaliacao")) {
                 
                  //somente para recupar o ID do livro que o usuario vai comentar, após recuperar é direcionado pra pagina de comentario
                //.. com o id do produto 
                int idProduto = Integer.parseInt(request.getParameter("idProduto"));

                // VALIDAR AQUI SE O USUARIO QUE ESTA COMENTANDO TEM O LIVRO (ID)
                HttpSession sessaoUsuario = request.getSession();
                Usuario usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

                if (usuario != null) {
                    //caso exista um usuario logado 
                    Produto p = new Produto();
                    p.setId(idProduto);

                    EstanteVirtual ev = new EstanteVirtual();
                    ev.setProduto(p);
                    ev.setUsuario(usuario);

                    //verifica no banco de dados se o usuario tem o livro 
                    EstanteVirtualDAO estanteVirtualDAO = new EstanteVirtualDAO();
                    
                    boolean existe = estanteVirtualDAO.verificaSePossuiLivro(ev);

                    if (existe == true) {
                        //verifica se o usuario que esta logado tem o livro para comentar
                        request.setAttribute("idProduto", idProduto);
                        RequestDispatcher rd = request.getRequestDispatcher("comentario.jsp");
                        rd.forward(request, response);
                    } else {
                        //caso não tenha o livro informa ao usuario
                        String msg = "Desculpa, você ainda não tem este livro!";
                        request.setAttribute("msg", msg);
                        RequestDispatcher rd = request.getRequestDispatcher("PaginaDeLivros.jsp");
                        rd.forward(request, response);

                    }
                } else {
                    //caso não tenha nenhum usuario logado e tente comentar
                    response.sendRedirect("FacaLogin.jsp");
                }

                 
             }else if (acao.equals("Avaliar")) {

                //após recuperar o id do produto é inserido um comentario assim adicionando os 2 ( id e comentario) no banco
                Integer id_produto = Integer.parseInt(request.getParameter("id_produto"));
                String comentario = request.getParameter("txt_comentario");
                Integer nota = Integer.parseInt(request.getParameter("fb"));

                // recupera o ussuario que esta na sessao 
                HttpSession sessaoUsuario = request.getSession();
                Usuario usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");

                //pega a data de "hoje"
                GregorianCalendar data_hoje = new GregorianCalendar();
                java.sql.Date data = new java.sql.Date(data_hoje.getTime().getTime());

                Produto p = new Produto();
                p.setId(id_produto);

                Avaliacao a = new Avaliacao();
                a.setData_Comentario(data);
                a.setComentario(comentario);
                a.setNota(nota);
                a.setProduto(p);
                a.setUsuario(usuario);

                AvaliacaoDAO avaliacaodao = new AvaliacaoDAO();
                avaliacaodao.avaliar(a);

                response.sendRedirect("EstanteVirtual.jsp");

            } else if (acao.equals("VerAvaliacao")) {

                int idProduto = Integer.parseInt(request.getParameter("idProduto"));

                Produto produto = new Produto();
                produto.setId(idProduto);

                AvaliacaoDAO avaliacaoDao = new AvaliacaoDAO();

                ArrayList<Avaliacao> RecuperaAvaliacao = avaliacaoDao.verComentarioENotas(produto);

                Avaliacao a = new Avaliacao();
                Avaliacao resultadoFinalDaMedia = a.calcularMedia(RecuperaAvaliacao);                
                
               
                RequestDispatcher rd = request.getRequestDispatcher("/PaginaDeLivros.jsp");
                request.setAttribute("avaliacao", RecuperaAvaliacao);
                request.setAttribute("media", resultadoFinalDaMedia);
                rd.forward(request, response);

            } 
          
        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("/erro.jsp").forward(request, response);
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
