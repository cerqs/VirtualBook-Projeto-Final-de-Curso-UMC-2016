/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.LoginDAO;
import modelo.dao.UsuarioDAO;
import modelos.CarrinhoDeCompra;
import modelos.Login;
import modelos.PerfilAcesso;
import modelos.Usuario;
import util.Email;

/**
 *
 * @author 11131103404
 */
public class ControleUsuario extends HttpServlet {

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
            if (acao.equals("Logar")) {

                Login log = new Login();
                log.setLogin(request.getParameter("txtLogin"));
                log.setSenha(request.getParameter("txtSenha"));
                
                
                
                LoginDAO logindao = new LoginDAO();
                Login loginAutenticado = logindao.AutenticaLoginDoUsuario(log);
                
                if (loginAutenticado != null ) {
                
                    UsuarioDAO udao = new UsuarioDAO();
                    Usuario DadosUsuario = udao.RecuperaDadosUsuarioPorLogin(loginAutenticado);
                

                    HttpSession sessaoUsuario = request.getSession();
                    sessaoUsuario.setAttribute("usuarioAutenticado", DadosUsuario);

                    HttpSession sessao = request.getSession();
                    CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");

                    if (carrinho == null) {
                        response.sendRedirect("PaginaDeLivros.jsp");
                    } else {
                        response.sendRedirect("CarrinhoDeCompras.jsp");
                    }

                } else {
                    RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
                    String mensagem = "Login ou Senha Incorreto!";
                    request.setAttribute("msg", mensagem);
                    rd.forward(request, response);
                }

            } else if (acao.equals("Sair")) {
                HttpSession sessaoUsuario = request.getSession();
                sessaoUsuario.removeAttribute("usuarioAutenticado");

                HttpSession sessao = request.getSession();
                sessao.removeAttribute("carrinho");

                response.sendRedirect("index.jsp");

            } else if (acao.equals("Cadastrar")) {
                String email = request.getParameter("txtLogin");
                String senha = request.getParameter("txtSenha");
                String nome = request.getParameter("txtNome");
                String sobrenome = request.getParameter("txtSobrenome");
                String cpf = request.getParameter("txtCpf");
                String telefone = request.getParameter("txtTel");

                PerfilAcesso pacesso = new PerfilAcesso();
                pacesso.setId(2);

                Login log = new Login();
                log.setLogin(email);
                log.setSenha(senha);
                log.setPerfil(pacesso);

                Usuario usuario = new Usuario();
                usuario.setNome(nome);
                usuario.setSobrenome(sobrenome);
                usuario.setCpf(cpf);
                usuario.setTelefone(telefone);

                UsuarioDAO udao = new UsuarioDAO();
                boolean verificaSeExisteCpf = udao.verificaSeExisteCpf(usuario);

                if (verificaSeExisteCpf != true) {

                    LoginDAO ldao = new LoginDAO();

                    boolean verificaSeExisteEmail = ldao.verificaSeExisteEmail(log);

                    if (verificaSeExisteEmail != true) {

                        Login id_login = ldao.cadastraLogin(log);
                        usuario.setLogin(id_login);
                        udao.cadastrarUsuario(usuario);

                        Email notificao = new Email();
                        notificao.EnviarEmail(usuario);
                        
                        request.setAttribute("msgCadastrado", usuario);
                        RequestDispatcher rd = request.getRequestDispatcher("cadastroSucessoCliente.jsp");
                        rd.forward(request, response);
                    } else {
                        String mensagem = "Esse EMAIL Já foi cadastrado anteriormente!";
                        request.setAttribute("msgJaExisteEmail", mensagem);
                        RequestDispatcher rd = request.getRequestDispatcher("cadastrarCliente.jsp");
                        rd.forward(request, response);

                    }

                } else {
                    String mensagem = "Esse CPF Já foi cadastrado anteriormente!";
                    request.setAttribute("msgJaExisteCpf", mensagem);
                    RequestDispatcher rd = request.getRequestDispatcher("cadastrarCliente.jsp");
                    rd.forward(request, response);

                }

            } else if (acao.equals("Excluir")) {
                String login = request.getParameter("txtLogin");
                String senha = request.getParameter("txtSenha");
                
                Login log = new Login();
                log.setLogin(login);
                log.setSenha(senha);
                
                UsuarioDAO udao = new UsuarioDAO();
                udao.excluir(log);
                
                response.sendRedirect("index.jsp");

            } else if (acao.equals("Atualizar")) {
                                
                HttpSession sessao = request.getSession();
                Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");

                String nome = request.getParameter("txtNome");
                String sobrenome = request.getParameter("txtSobrenome");
                String telefone = request.getParameter("txtTel");

                Usuario u = new Usuario();
                u.setId(usuario.getId());
                u.setNome(nome);
                u.setSobrenome(sobrenome);
                u.setTelefone(telefone);

                UsuarioDAO uDAO = new UsuarioDAO();
                uDAO.atualizar(u);

                // recupera o login e senha que esta na sessao para poder atualizar a sessao após usario atualizar dados.
                Login log = new Login();
                log.setLogin(usuario.getLogin().getLogin());
                log.setSenha(usuario.getLogin().getSenha());
                log.setId(usuario.getLogin().getId());
                log.setPerfil(usuario.getLogin().getPerfil());

                UsuarioDAO udao = new UsuarioDAO();
                Usuario usuarioAutenticado = udao.RecuperaDadosUsuarioPorLogin(log);

                HttpSession sessaoUsuario = request.getSession();
                sessaoUsuario.setAttribute("usuarioAutenticado", usuarioAutenticado);

                response.sendRedirect("PaginaDeLivros.jsp");

            } //  ACESSAR A SESSAO VERIFICAR DADOS DO CLIENTE NA SESSAO E DEVOLVER PRA JSP ..
            else if (acao.equals("DadosClienteAtualizar")) {
                               
                HttpSession sessao = request.getSession();
                Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");
                request.setAttribute("dadosCliente", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("atualizarCliente.jsp");
                rd.forward(request, response);

            } else if (acao.equals("DadosClienteAlterarSenha")) {

                HttpSession sessao = request.getSession();
                Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");
                request.setAttribute("dadosCliente", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("alterarSenha.jsp");
                rd.forward(request, response);

            } else if (acao.equals("DadosClienteExcluir")) {

                HttpSession sessao = request.getSession();
                Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");
                request.setAttribute("dadosCliente", usuario);
                RequestDispatcher rd = request.getRequestDispatcher("excluirDadosCliente.jsp");
                rd.forward(request, response);
                
            } else if (acao.equals("Alterar")) {
                String login = request.getParameter("txtLogin");
                String senha = request.getParameter("txtSenha");
                String senha2 = request.getParameter("txtSenha2");

                if (senha.equals(senha2)) {
                    Login log = new Login();
                    log.setLogin(login);
                    log.setSenha(senha);

                    LoginDAO ldao = new LoginDAO();
                    ldao.alterarSenha(log);

                    response.sendRedirect("PaginaDeLivros.jsp");

                } else {
                    String msg = "A senha que você digitou não é compativel";
                    request.setAttribute("invalido", msg);
                    RequestDispatcher rd = request.getRequestDispatcher("alterarSenha.jsp");
                    rd.forward(request, response);

                }
            } else if (acao.equals("Recuperar")) {

                String email = request.getParameter("txtLogin");
                String cpf = request.getParameter("txtCpf");

                Login login = new Login();
                login.setLogin(email);

                Usuario usuario = new Usuario();
                usuario.setLogin(login);
                usuario.setCpf(cpf);

                UsuarioDAO udao = new UsuarioDAO();
                boolean existe = udao.VerificaSeExisteEmailECpfParaRecuperarSenha(usuario);

                if (existe == true) {

                    login.ResetDeSenha(login);

                    LoginDAO loginDAO = new LoginDAO();
                    loginDAO.RecuperandoSenha(login);
                    
                    Email enviarNotificacao = new Email();
                    enviarNotificacao.EnviarEmailRecuperarSenha(login);
                    
                    response.sendRedirect("index.jsp");
                } else {
                    
                    String msg = "Os dados digitados estão invalidos";
                    request.setAttribute("invalido", msg);
                    RequestDispatcher rd = request.getRequestDispatcher("RecuperarSenha.jsp");
                    rd.forward(request, response);

                }

            }

        } catch (Exception erro) {
            //RequestDispatcher rd = request.getRequestDispatcher("/erro.jsp");
            //request.setAttribute("erro", erro);
            //rd.forward(request, response);
            response.sendRedirect("PaginaDeLivros.jsp");
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
