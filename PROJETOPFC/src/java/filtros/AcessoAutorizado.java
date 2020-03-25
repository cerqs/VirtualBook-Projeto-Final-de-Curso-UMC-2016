/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filtros;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelos.Usuario;

/**
 *
 * @author 11131103404
 */
public class AcessoAutorizado implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        //casting dos objetos request e response para HttpServletRequest e HttpServletResponse
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //recupera a sessao do usuario
        HttpSession sessao = req.getSession();

        //recupera o usuario da sessão
        Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");

        if (usuario != null) {

            if (usuario.getLogin().getPerfil().getId() == 1) {

                //permite o encaminhamento da requisicao
                chain.doFilter(req, resp);
            } else {
                //redireciona para a pagina não autenticado   
                //resp.sendRedirect("./acessoNegado.jsp");
                resp.sendRedirect("./PaginaDeLivros.jsp");
            }

        } else {
            //resp.sendRedirect("./acessoNegado.jsp");
            resp.sendRedirect("./PaginaDeLivros.jsp");
        }

    }

    @Override
    public void destroy() {
        //metodo executado sempre que o objeto da classe é desalocado(pelo servidor de aplicacao)
    }

}
