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
import modelo.dao.GeneroDAO;
import modelo.dao.ProdutoDAO;
import modelos.CarrinhoDeCompra;
import modelos.Genero;
import modelos.ItemDeCompra;
import modelos.Produto;
import modelos.Usuario;
import util.Email;

/**
 *
 * @author 11131103404
 */
public class ControleCarrinho extends HttpServlet {

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
            String escolha = request.getParameter("escolha");

            if (acao.equals("addProduto")) {
                //recupera o id do produto que deve ser add no carrinho
                int idProduto = Integer.parseInt(request.getParameter("idProduto"));

                Produto produtoRecuperado = new Produto();
                produtoRecuperado.setId(idProduto);

                //recupera a sessão pertencente ao request
                HttpSession sessao = request.getSession();

                //recupera um carrinho de produtos da sessão
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");

                //se não exite um carrinho na sessão o valor será igual a null
                if (carrinho == null) {
                    //cria um carrinho 
                    carrinho = new CarrinhoDeCompra();
                    sessao.setAttribute("carrinho", carrinho);

                }

            
        boolean existe = carrinho.verificaSeProdutoJaExisteNoCarrinho(carrinho,produtoRecuperado);
    

                //se não existe o item ou produto, cria um novo 
                if (existe == false) {
                    //encontra o produto no banco
                    ProdutoDAO pdao = new ProdutoDAO();
                    Produto produto = pdao.consultarPorId(produtoRecuperado);
                    
                    if(produto.getStatus() == null || produto.getStatus().equals("inativo")){                        
                        String LivroInativo = "Desculpe, mas esse livro que você esta tentando acessar não existe";
                        request.setAttribute("inativo", LivroInativo);
                       request.getRequestDispatcher("/CarrinhoDeCompras.jsp").forward(request, response); 
                        
                    }else{
                    
                    //cria um novo item
                    ItemDeCompra novoItem = new ItemDeCompra();
                    novoItem.setProduto(produto);
                    //adiciona novo item
                    carrinho.addNovoItem(novoItem);
                    }

                }else{
                    
                    
                    RequestDispatcher rd = request.getRequestDispatcher("/PaginaDeLivros.jsp");
                    String mensagem = "Este livro já existe em seu carrinho";
                    request.setAttribute("msgexiste", mensagem);
                    rd.forward(request, response);
                    
                }
                
                //redireciona para o carrinho
                request.getRequestDispatcher("/CarrinhoDeCompras.jsp").forward(request, response);

            }//fim addProduto
            else if (acao.equals("cancelaCompra")) {
                //recupera a sessão pertencente ao request
                HttpSession sessao = request.getSession();

                //remove o carrinho da sessão
                sessao.removeAttribute("carrinho");

                //redireciona para pagina principal
                response.sendRedirect("PaginaDeLivros.jsp");

                
                
            } else if (acao.equals("removeProduto")) {
                //recupera a sessão pertencente ao request
                HttpSession sessao = request.getSession();
                //recupera um carrinho de produtos da sessão
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");
                //recupera o id do produto
                int idProduto = Integer.parseInt(request.getParameter("idProduto"));

                Produto prodRemove = new Produto();
                prodRemove.setId(idProduto);

                ItemDeCompra itemRemove = new ItemDeCompra();
                itemRemove.setProduto(prodRemove);

                carrinho.removerItem(itemRemove);

                //Não muda de pagina e continua no carrinho
                response.sendRedirect("CarrinhoDeCompras.jsp");

            } else if (acao.equals("listar")) {
                String opcao = escolha;
                HttpSession sessaoUsuario = request.getSession();
                Usuario usuario = (Usuario) sessaoUsuario.getAttribute("usuarioAutenticado");
                ArrayList<Produto> resultadoDosLivrosRecomendados = new ArrayList<Produto>();

                if (opcao == null) {

                    if (usuario != null) {

                       
                        //CASO TENHA ALGUEM NA SESSAO VERIFICA QUAIS OS 2 GENEROS QUE O USUARIO MELHOR AVALIOU                        
                        // RECUPERA OS 2 MELHORES GENEROS QUE ELE AVALIOU
                        GeneroDAO generoDao = new GeneroDAO();
                        ArrayList<Genero> resultadoDosLivrosQueDevemSerRecomendados = generoDao.qualLivroDeveSerRecomendado(usuario);
                        
                        //TRAS UM SELECT COM O LIVROS CADASTRADOS BASEADO COM O GENERO DO GOSTO DO USUARIO
                        ProdutoDAO produtoDao = new ProdutoDAO();
                        resultadoDosLivrosRecomendados = produtoDao.LivrosRecomendado(resultadoDosLivrosQueDevemSerRecomendados,usuario);
                    }

                    // Tras o resultado do melhores livros avaliados por todos os usuarios
                    ArrayList<Produto> resultadoMelhoresAvaliados = new ProdutoDAO().listarMelhoresAvaliados();

                    // caso não não tenha nenhum livro avaliado ou o total dos livro for menor que 5 (menor que 5 pra na hora de mostrar não ficar "feio" com pouco livro"
                    if (resultadoMelhoresAvaliados.isEmpty() || resultadoMelhoresAvaliados.size() <= 5) {
                        //Lista todos os livros na ordem de ultimos cadastrado ("LANÇAMENTOS")
                        ArrayList<Produto> produtos = new ProdutoDAO().listarLivros();
                        request.setAttribute("produtos", produtos);
                        request.setAttribute("recomendados", resultadoDosLivrosRecomendados);
                        request.getRequestDispatcher("/PaginaDeLivros.jsp").forward(request, response);
                    } else {
                        //se não mostra os melhores e a lista dos recomendados
                        request.setAttribute("produtos", resultadoMelhoresAvaliados);
                        request.setAttribute("recomendados", resultadoDosLivrosRecomendados);
                        request.getRequestDispatcher("/PaginaDeLivros.jsp").forward(request, response);
                    }

                    // caso usuario selecione listar pelo titulo,preço + ou preco -
                } else if (opcao.equals("titulo")) {
                    ArrayList<Produto> produtos = new ProdutoDAO().listarLivrosTitulo();
                    request.setAttribute("produtos", produtos);
                    request.getRequestDispatcher("/PaginaDeLivros.jsp").forward(request, response);
                } else if (opcao.equals("precoMaior")) {
                    ArrayList<Produto> produtos = new ProdutoDAO().listarLivrosPrecoMaior();
                    request.setAttribute("produtos", produtos);
                    request.getRequestDispatcher("/PaginaDeLivros.jsp").forward(request, response);
                } else if (opcao.equals("precoMenor")) {
                    ArrayList<Produto> produtos = new ProdutoDAO().listarLivrosPrecoMenor();
                    request.setAttribute("produtos", produtos);
                    request.getRequestDispatcher("/PaginaDeLivros.jsp").forward(request, response);
                }

            } else if (acao.equals("vercarrinho")) {

                //recupera a sessão pertencente ao request
                HttpSession sessao = request.getSession();

                //recupera um carrinho de produtos da sessão
                CarrinhoDeCompra carrinho = (CarrinhoDeCompra) sessao.getAttribute("carrinho");

                //se não exite um carrinho na sessão o valor será igual a null
                if (carrinho == null) {
                    //Não tem nada no carrinho
                    RequestDispatcher rd = request.getRequestDispatcher("/PaginaDeLivros.jsp");
                    String mensagemSemCarrinho = "Não há nada em seu carrinho!!";
                    request.setAttribute("msgg", mensagemSemCarrinho);
                    rd.forward(request, response);

                }

                request.getRequestDispatcher("/CarrinhoDeCompras.jsp").forward(request, response);

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
