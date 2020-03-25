/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controle;

import com.google.gson.Gson;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import modelo.dao.GeneroDAO;
import modelo.dao.EstanteVirtualDAO;
import modelo.dao.ProdutoDAO;
import modelos.Genero;
import modelos.Produto;
import modelos.Usuario;
import modelos.EstanteVirtual;

/**
 *
 * @author 11131103404
 */
public class ControleProduto extends HttpServlet {

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
            String cars = request.getParameter("cars");

            //ADM - PRIMEIRA PARTE FUNÇÃO DO CADASTRO.
            if (acao.equals("Cadastrar")) {

                HttpSession sessaoProduto = request.getSession();
                Produto produto = (Produto) sessaoProduto.getAttribute("cadastrarProduto");

                if (produto != null) {
                    sessaoProduto.removeAttribute("cadastrarProduto");
                    produto = null;
                }

                if (produto == null) {
                    //cria um produto na sessao
                    produto = new Produto();
                    sessaoProduto.setAttribute("cadastrarProduto", produto);
                }

                String titulo = request.getParameter("txtTitulo");
                String autor = request.getParameter("txtAutor");
                Double preco = Double.parseDouble(request.getParameter("txtPreco").replace(",", "."));
                Integer id_genero = Integer.parseInt(request.getParameter("txtGenero"));
                String lancamento = request.getParameter("txtLancamento");
                String isbn = request.getParameter("txtIsbn");
                String sinopse = request.getParameter("txtSinopse");

                Genero g = new Genero();
                g.setId_genero(id_genero);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

                Date parsedDe = format.parse(lancamento);
                java.sql.Date data_de_lancamento = new java.sql.Date(parsedDe.getTime());

                produto.setTitulo(titulo);
                produto.setAutor(autor);
                produto.setPreco(preco);
                produto.setLancamento(data_de_lancamento);
                produto.setIsbn(isbn);
                produto.setSinopse(sinopse);
                produto.setGenero(g);
                produto.setStatus("ativo");

                ProdutoDAO pdao = new ProdutoDAO();
                boolean verificaIsbn = pdao.verificaSeExisteIsbn(produto);

                if (verificaIsbn == true) {
                    sessaoProduto.removeAttribute("cadastrarProduto");
                    String mensagem = "Esse Isbn já foi cadastrado anteriormente !!!";
                    request.setAttribute("msgJaExisteIsbn", mensagem);
                    RequestDispatcher rd = request.getRequestDispatcher("AdmTelaPrincipal.jsp");
                    rd.forward(request, response);
                } else {
                    response.sendRedirect("AdmInserirPdf.jsp");
                }
                //ADM- CONFIRMAR CADASTRO APÓS INSERIR TODOS OS DADOS.
            } else if (acao.equals("Confirmar cadastro")) {

                HttpSession sessaoProduto = request.getSession();
                Produto produto = (Produto) sessaoProduto.getAttribute("cadastrarProduto");

                ProdutoDAO pdao = new ProdutoDAO();
                pdao.cadastrar(produto);

                sessaoProduto.removeAttribute("cadastrarProduto");

                response.sendRedirect("AdmGerenciarLivros.jsp");

                // ADM - CANCELAR CADASTRO 
            } else if (acao.equals("Cancelar")) {

                HttpSession sessaoProduto = request.getSession();
                sessaoProduto.removeAttribute("cadastrarProduto");
                response.sendRedirect("AdmGerenciarLivros.jsp");

                //ADM- LISTAR LIVORS NA TELA DE GERENCIAR.
            } else if (acao.equals("Listar")) {

                ProdutoDAO pDAO = new ProdutoDAO();
                String opcao = cars;

                if (opcao == null) {
                    ArrayList<Produto> produtos = pDAO.listar();
                    request.setAttribute("listaProduto", produtos);
                    RequestDispatcher rd = request.getRequestDispatcher("AdmGerenciarLivros.jsp");
                    rd.forward(request, response);

                } else if (opcao.equals("titulo")) {
                    ArrayList<Produto> produtos = pDAO.listarTitulo();
                    request.setAttribute("listaProduto", produtos);
                    RequestDispatcher rd = request.getRequestDispatcher("AdmGerenciarLivros.jsp");
                    rd.forward(request, response);
                } else if (opcao.equals("data")) {
                    ArrayList<Produto> produtos = pDAO.listarData();
                    request.setAttribute("listaProduto", produtos);
                    RequestDispatcher rd = request.getRequestDispatcher("AdmGerenciarLivros.jsp");
                    rd.forward(request, response);
                } else if (opcao.equals("preco")) {
                    ArrayList<Produto> produtos = pDAO.listarPreco();
                    request.setAttribute("listaProduto", produtos);
                    RequestDispatcher rd = request.getRequestDispatcher("AdmGerenciarLivros.jsp");
                    rd.forward(request, response);
                } else if (opcao.equals("inativos")) {
                    ArrayList<Produto> produtos = pDAO.listarLivrosInativos();
                    request.setAttribute("listaProduto", produtos);
                    RequestDispatcher rd = request.getRequestDispatcher("AdmGerenciarLivros.jsp");
                    rd.forward(request, response);

                }
                //ADM - ATAULIZAR DADOS DO LIVRO.
            } else if (acao.equals("Atualizar")) {

                String titulo = request.getParameter("txtTitulo");
                String autor = request.getParameter("txtAutor");
                Double preco = Double.parseDouble(request.getParameter("txtPreco"));
                String lancamento = request.getParameter("txtLancamento");
                String isbn = request.getParameter("txtIsbn");
                Integer id_genero = Integer.parseInt(request.getParameter("txtGenero"));
                String sinopse = request.getParameter("txtSinopse");

                Genero genero = new Genero();
                genero.setId_genero(id_genero);

                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                Date parsedDe = format.parse(lancamento);
                java.sql.Date data_de_lancamento = new java.sql.Date(parsedDe.getTime());

                Produto produto = new Produto();
                produto.setTitulo(titulo);
                produto.setAutor(autor);
                produto.setPreco(preco);
                produto.setLancamento(data_de_lancamento);
                produto.setIsbn(isbn);
                produto.setGenero(genero);
                produto.setSinopse(sinopse);

                ProdutoDAO pDAO = new ProdutoDAO();

                pDAO.atualizar(produto);

                response.sendRedirect("AdmGerenciarLivros.jsp");
                // ADM - INATIVAR LIVRO .
            } else if (acao.equals("Inativar")) {

                String isbn = request.getParameter("txtIsbn");

                Produto produto = new Produto();
                produto.setIsbn(isbn);

                ProdutoDAO pdao = new ProdutoDAO();
                pdao.inativar(produto);

                String mensagemm = "Inativado com sucesso!!";
                request.setAttribute("msgExcluido", mensagemm);
                RequestDispatcher rd = request.getRequestDispatcher("AdmGerenciarLivros.jsp");
                rd.forward(request, response);

                //ADM - ATIVAR UM LIVRO CASO ESTEJA INATIVADO.
            } else if (acao.equals("Ativar")) {

                String isbn = request.getParameter("txtIsbn");

                Produto produto = new Produto();
                produto.setIsbn(isbn);

                ProdutoDAO pdao = new ProdutoDAO();
                pdao.ativar(produto);

                String mensagemm = "Livro ativo com sucesso!!";
                request.setAttribute("msgExcluido", mensagemm);
                RequestDispatcher rd = request.getRequestDispatcher("AdmGerenciarLivros.jsp");
                rd.forward(request, response);

                //ADM - AÇÃO DO PESQUISA "TRAZENDO DADOS DO LIVRO"
            } else if (acao.equals("RecuperaLivroParaAtualizar")) {

                String isbn = request.getParameter("isbn");
                Produto produto = new Produto();
                produto.setIsbn(isbn);
                ProdutoDAO pdao = new ProdutoDAO();
                ArrayList<Produto> produtoSelecionado = pdao.SelecionaTodosDadosComIsbn(produto);

                if (produtoSelecionado.size() == 0) {
                    String msgLivroNaoExiste = "Desculpe, o livro pesquisado não existe!";
                    request.setAttribute("msgNaoExisteIsbn", msgLivroNaoExiste);
                    RequestDispatcher rd = request.getRequestDispatcher("AdmGerenciarLivros.jsp");
                    rd.forward(request, response);

                }

                if (produtoSelecionado.size() > 1) {

                    request.setAttribute("listaProduto", produtoSelecionado);
                    RequestDispatcher rd = request.getRequestDispatcher("AdmGerenciarLivros.jsp");
                    rd.forward(request, response);

                } else {

                    GeneroDAO generoDao = new GeneroDAO();
                    ArrayList<Genero> resultado = generoDao.listarGeneros();

                    request.setAttribute("dadosAtualizar", produtoSelecionado);
                    request.setAttribute("generos", resultado);
                    RequestDispatcher rd = request.getRequestDispatcher("AdmAtualizarLivro.jsp");
                    rd.forward(request, response);

                }

                //ADM - LISTAR GERENEROS PARA APARECER NA TELA DE CADASTRO DE LIVRO.
            } else if (acao.equals("listarGenero")) {

                GeneroDAO generoDao = new GeneroDAO();
                ArrayList<Genero> resultado = generoDao.listarGeneros();

                RequestDispatcher rd = request.getRequestDispatcher("/AdmCadastrarLivro.jsp");
                request.setAttribute("generos", resultado);
                rd.forward(request, response);

                
                // ADM E USUARIO - LISTA DE LIVRO PROCURADO.
            } else if (acao.equals("ajaxConsulta")) {
                response.setContentType("application/json");

                try {
                    String tituloProcurado = request.getParameter("term");

                    Produto produto = new Produto();
                    produto.setTitulo(tituloProcurado);

                    ProdutoDAO produtoDao = new ProdutoDAO();
                    ArrayList<Produto> listaDeProdutosProcurados = produtoDao.ajaxRecuperandoProdutosProcurado(produto);
                    
                    String searchList = new Gson().toJson(listaDeProdutosProcurados);
                    response.getWriter().write(searchList);

                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
                
                
                //USUARIO - LISTA DE LIVRO DO USUARIO.
            } else if (acao.equals("VerMeusLirvos")) {

                HttpSession sessao = request.getSession();
                Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");

                EstanteVirtualDAO estanteVirtualDAO = new EstanteVirtualDAO();

                ArrayList<EstanteVirtual> MeusLivros = estanteVirtualDAO.listarMeusLivros(usuario);

                request.setAttribute("listaProduto", MeusLivros);
                RequestDispatcher rd = request.getRequestDispatcher("EstanteVirtual.jsp");
                rd.forward(request, response);
                
                // USUARIO - LER SINOPSE DO LIVRO.
            } else if (acao.equals("verSinopse")) {

                int idProduto = Integer.parseInt(request.getParameter("idProduto"));

                Produto produto = new Produto();

                produto.setId(idProduto);

                ProdutoDAO pdao = new ProdutoDAO();

                Produto sinopse = pdao.verSinopse(produto);

                    RequestDispatcher rd = request.getRequestDispatcher("/PaginaDeLivros.jsp");
                request.setAttribute("sinopse", sinopse);
                rd.forward(request, response);
                
                //USUARIO - BARRA DE PESQUISA DE LIVRO.
            } else if (acao.equals("pesquisar")) {
                String pesquisa = request.getParameter("txt_pesquisa_livro");

                Produto produto = new Produto();
                produto.setTitulo(pesquisa);

                ProdutoDAO pdao = new ProdutoDAO();

                ArrayList<Produto> livroPesquisado = pdao.listarLivroPesquisado(produto);

                RequestDispatcher rd = request.getRequestDispatcher("/PaginaDeLivrosConsulta.jsp");
                request.setAttribute("livroPesquisado", livroPesquisado);
                rd.forward(request, response);
                
                //USUARIO - LER LIVRO.
            } else if (acao.equals("LerLivro")) {

                int id_do_produto = Integer.parseInt(request.getParameter("idProduto"));

                HttpSession sessao = request.getSession();
                Usuario usuario = (Usuario) sessao.getAttribute("usuarioAutenticado");

                if (usuario != null) {

                    Produto produto = new Produto();
                    produto.setId(id_do_produto);

                    EstanteVirtual estantevirtual = new EstanteVirtual();
                    estantevirtual.setProduto(produto);
                    estantevirtual.setUsuario(usuario);

                    EstanteVirtualDAO estantevirtualDAO = new EstanteVirtualDAO();
                    boolean PossuiOLivro = estantevirtualDAO.verificaSePossuiLivro(estantevirtual);

                    if (PossuiOLivro == true) {

                        ProdutoDAO produtodao = new ProdutoDAO();
                        Produto livro = produtodao.consultarPorId(produto);

                        RequestDispatcher rd = request.getRequestDispatcher("/LeituraDoLivro.jsp");
                        request.setAttribute("livro", livro);
                        rd.forward(request, response);

                    } else {

                        response.sendRedirect("EstanteVirtual.jsp");

                    }

                } else {

                    response.sendRedirect("naoAutenticado.jsp");
                }

            }

        } catch (Exception erro) {
            request.setAttribute("erro", erro);
            request.getRequestDispatcher("erro.jsp").forward(request, response);
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
