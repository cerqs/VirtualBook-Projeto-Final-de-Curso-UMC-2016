/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.Genero;
import modelos.ItemDeCompra;
import modelos.EstanteVirtual;
import modelos.Produto;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class ProdutoDAO {

    //SELECT
    private static final String SELECT_ALL = "SELECT * FROM produto order by id_livro";
    private static final String SELECT_ALL_ORDERDATA = "SELECT * FROM produto order by lancamento";
    
    private static final String SELECT_ALL_ORDERPRECO = "SELECT * FROM produto order by preco";
    
    private static final String SELECT_ALL_INATIVOS = "SELECT * FROM produto where status = 'inativo' ";
    
    private static final String SELECT = "select * from produto where isbn =(?) or titulo ilike (?)";

    private static final String SELECTALL = "select * from produto where status = 'ativo' order by id_livro desc";

    private static final String SELECTALL_MELHORES_AVALIADOS = "select p.titulo,p.preco,p.id_livro,p.imagem,avg(a.nota) as avg1 from produto as p , avaliacao as a where a.id_livro = p.id_livro and p.status = 'ativo' group by p.titulo,p.preco,p.id_livro,p.imagem order by avg1 desc";

    private static final String SELECTALL_TITULO = "select * from produto where status = 'ativo' order by titulo";
    private static final String SELECTALL_PRECOMAIOR = "select * from produto where status = 'ativo' order by preco ASC";
    private static final String SELECTALL_PRECOMENOR = "select * from produto where status = 'ativo'  order by preco DESC";
    private static final String SELECTID = "select * from produto as p, genero as g where p.id_genero = g.id_genero and p.id_livro = (?)";

    private static final String SELECTSINOPSE = "select * from produto where id_livro = (?)";
    private static final String SELECTLIVROPESQUISADO = "select * from produto where status = 'ativo' and titulo ilike (?) ";
    
private static final String SELECTLIVROPESQUISADOAJAX = "select titulo from produto where status = 'ativo' and titulo ilike (?) ";
    
// select para ver os livros daquele determinado genero
    private static final String SELECTALL_LIVRO_RECOMENDADOS = "select * from produto p1, genero g "
            + "where p1.id_livro not in (select p.id_livro from produto p, estante_virtual e, usuario u "
            + "where p.id_livro = e.id_livro and e.id_usuario = u.id_usuario and u.id_usuario = (?) )  "
            + "and p1.id_genero = g.id_genero  and g.id_genero = (?) and p1.status = 'ativo'";

    private static final String SELECT_PRODUTO_DO_ITEMDECOMPRA = "select p.id_livro, p.titulo from produto p, itemdecompra i where p.id_livro = i.id_livro and i.id_itens = (?)";
    
    private static final String  SELECT_PRODUTO_DO_MINHASCOMPRAS = "select p.* from estante_virtual as e , produto as p where e.id_livro = p.id_livro and e.id_estante = (?)";
   
    private static final String SELECT_DADOS_ISBN = "select * from produto where isbn =(?)";
    
    
    
    //INSET
    private static final String INSERT = "INSERT INTO produto (titulo, autor, preco,lancamento, isbn, sinopse,imagem,pdf,id_genero,status) VALUES (?,?,?,?,?,?,?,?,?,?)";

    //UPDATE
    private static final String INATIVAR = "update produto set status = 'inativo' where isbn= (?)";
    private static final String ATIVAR = "update produto set status = 'ativo' where isbn= (?)";
    private static final String UPDATE = "update produto set titulo=(?), autor=(?) , preco=(?) , lancamento=(?) , sinopse=(?), id_genero = (?) where isbn=(?)";

    
    

    // TELA DE CARRINHO (PESQUISA) - TRAS A LISTA DE TODOS OS LIVROS QUE O USUARIO PESQUISOU
    public ArrayList<Produto> listarLivroPesquisado(Produto produto) {

        ArrayList<Produto> listaProduto = new ArrayList<>();
        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECTLIVROPESQUISADO);
            pstmt.setString(1, "%" + produto.getTitulo() + "%");
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Produto novoProduto = new Produto();
                novoProduto.setId(rs.getInt("id_livro"));
                novoProduto.setTitulo(rs.getString("titulo"));
                novoProduto.setAutor(rs.getString("autor"));
                novoProduto.setPreco(rs.getDouble("preco"));
                novoProduto.setLancamento(rs.getDate("lancamento"));
                novoProduto.setIsbn(rs.getString("isbn"));
                novoProduto.setSinopse(rs.getString("sinopse"));
                novoProduto.setImagem(rs.getString("imagem"));

                listaProduto.add(novoProduto);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
        return listaProduto;
    }

    //CADASTRO DE LIVRO (ADM) - CADASTRA O LIVRO QUE O ADMINISTRADOR FEZ
    public void cadastrar(Produto produto) {
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setString(1, produto.getTitulo());
            pstmt.setString(2, produto.getAutor());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setDate(4, (Date) produto.getLancamento());
            pstmt.setString(5, produto.getIsbn());
            pstmt.setString(6, produto.getSinopse());
            pstmt.setString(7, produto.getImagem());
            pstmt.setString(8, produto.getPdf());
            pstmt.setInt(9, produto.getGenero().getId_genero());
            pstmt.setString(10, produto.getStatus());
            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
    }

    //CADASTRO DE LIVRO (ADM) - LISTA TODOS OS LIVROS CADASTRADO PELO ADM
    public ArrayList<Produto> listar() {

        ArrayList<Produto> listaProduto = new ArrayList<>();
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Produto novoProduto = new Produto();
                novoProduto.setId(rs.getInt("id_livro"));
                novoProduto.setTitulo(rs.getString("titulo"));
                novoProduto.setAutor(rs.getString("autor"));
                novoProduto.setPreco(rs.getDouble("preco"));
                novoProduto.setLancamento(rs.getDate("lancamento"));
                novoProduto.setIsbn(rs.getString("isbn"));
                novoProduto.setSinopse(rs.getString("sinopse"));
                novoProduto.setPdf(rs.getString("pdf"));
                novoProduto.setStatus(rs.getString("status"));

                listaProduto.add(novoProduto);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
        return listaProduto;
    }

    //CADASTRO DE LIVRO (ADM) - ATUALIZA OS DETERMINADOS PRODUTO PELO ADM
    public void atualizar(Produto produto) {
        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);
            pstmt.setString(1, produto.getTitulo());
            pstmt.setString(2, produto.getAutor());
            pstmt.setDouble(3, produto.getPreco());
            pstmt.setDate(4, (Date) produto.getLancamento());
            pstmt.setString(5, produto.getSinopse());
            pstmt.setInt(6, produto.getGenero().getId_genero());
            pstmt.setString(7, produto.getIsbn());
            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
    }

    //CADASTRO DE LIVRO (ADM) - EXCLUI DETERMINADOS PRODUTOS PELO ADM
    public void inativar(Produto produto) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INATIVAR);
            pstmt.setString(1, produto.getIsbn());
            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
    }

    //CADASTRO DE LIVRO (ADM) - ATIVAR DETERMINADOS PRODUTOS PELO ADM
    public void ativar(Produto produto) {

        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(ATIVAR);
            pstmt.setString(1, produto.getIsbn());
            pstmt.execute();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
    }


    //CADASTRO DE LIVRO (ADM)- tras determinado livro por determinado isbn
    public ArrayList<Produto> SelecionaTodosDadosComIsbn(Produto produto) {
        Connection conexao = null;
        ArrayList<Produto> listaDeProduto =  new ArrayList<>();
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT);
            pstmt.setString(1, produto.getIsbn());
            pstmt.setString(2, '%' + produto.getIsbn() + '%');
            ResultSet rs = pstmt.executeQuery();

             while (rs.next()) {
               Produto produtoRecuperado = new Produto();
                produtoRecuperado.setId(rs.getInt("id_livro"));
                produtoRecuperado.setTitulo(rs.getString("titulo"));
                produtoRecuperado.setAutor(rs.getString("autor"));
                produtoRecuperado.setPreco(rs.getDouble("preco"));
                produtoRecuperado.setLancamento(rs.getDate("lancamento"));
                produtoRecuperado.setIsbn(rs.getString("isbn"));
                produtoRecuperado.setSinopse(rs.getString("sinopse"));
                produtoRecuperado.setImagem(rs.getString("imagem"));
                produtoRecuperado.setPdf(rs.getString("pdf"));
                produtoRecuperado.setStatus(rs.getString("status"));
                
                
                GeneroDAO genero = new GeneroDAO();
                Genero generoRecuperado = genero.RecuperaGeneroDoLivro(produtoRecuperado);
                
                produtoRecuperado.setGenero(generoRecuperado);
                
                listaDeProduto.add(produtoRecuperado);
                
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
        return listaDeProduto;
    }

    //CADASTRO DE LIVRO (ADM)- verifica se o livro que esta sendo cadastrado já não existe (pelo isbn)
    public boolean verificaSeExisteIsbn(Produto produto) {

        boolean existe = false;
        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_DADOS_ISBN);
            pstmt.setString(1, produto.getIsbn());
            ResultSet rs = pstmt.executeQuery();

            existe = rs.next();

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }

        return existe;
    }

    //TELA DE CADASTRO (ADM) - LISTAR LIVROS POR TITULO (AINDA FALTA MEXER)
    public ArrayList<Produto> listarTitulo() throws SQLException {

        ArrayList<Produto> listaProduto = new ArrayList<>();
        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECTALL_TITULO);
            
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Produto novoProduto = new Produto();
                novoProduto.setId(rs.getInt("id_livro"));
                novoProduto.setTitulo(rs.getString("titulo"));
                novoProduto.setAutor(rs.getString("autor"));
                novoProduto.setPreco(rs.getDouble("preco"));
                novoProduto.setLancamento(rs.getDate("lancamento"));
                novoProduto.setIsbn(rs.getString("isbn"));
                novoProduto.setSinopse(rs.getString("sinopse"));
                novoProduto.setImagem(rs.getString("imagem"));
                novoProduto.setPdf(rs.getString("pdf"));
                novoProduto.setStatus(rs.getString("status"));

                listaProduto.add(novoProduto);

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }

        return listaProduto;
    }

    //TELA DE CADASTRO (ADM) - LISTAR LIVROS POR DATA (AINDA FALTA MEXER)
    public ArrayList<Produto> listarData() {

        ArrayList<Produto> listaProduto = new ArrayList<>();
        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL_ORDERDATA);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Produto novoProduto = new Produto();
                novoProduto.setId(rs.getInt("id_livro"));
                novoProduto.setTitulo(rs.getString("titulo"));
                novoProduto.setAutor(rs.getString("autor"));
                novoProduto.setPreco(rs.getDouble("preco"));
                novoProduto.setLancamento(rs.getDate("lancamento"));
                novoProduto.setIsbn(rs.getString("isbn"));
                novoProduto.setSinopse(rs.getString("sinopse"));
                novoProduto.setImagem(rs.getString("imagem"));
                novoProduto.setPdf(rs.getString("pdf"));
                novoProduto.setStatus(rs.getString("status"));

                listaProduto.add(novoProduto);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }

        return listaProduto;
    }

    //TELA DE CADASTRO (ADM) - LISTAR LIVROS POR PREÇO (AINDA FALTA MEXER)
    public ArrayList<Produto> listarPreco() {

        ArrayList<Produto> listaProduto = new ArrayList<>();
        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL_ORDERPRECO);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Produto novoProduto = new Produto();
                novoProduto.setId(rs.getInt("id_livro"));
                novoProduto.setTitulo(rs.getString("titulo"));
                novoProduto.setAutor(rs.getString("autor"));
                novoProduto.setPreco(rs.getDouble("preco"));
                novoProduto.setLancamento(rs.getDate("lancamento"));
                novoProduto.setIsbn(rs.getString("isbn"));
                novoProduto.setSinopse(rs.getString("sinopse"));
                novoProduto.setImagem(rs.getString("imagem"));
                novoProduto.setPdf(rs.getString("pdf"));
                novoProduto.setStatus(rs.getString("status"));

                listaProduto.add(novoProduto);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }

        return listaProduto;
    }

    //TELA DE CADASTRO (ADM) - LISTAR LIVROS POR PREÇO (AINDA FALTA MEXER)
    public ArrayList<Produto> listarLivrosInativos() {

        ArrayList<Produto> listaProduto = new ArrayList<>();
        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL_INATIVOS);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Produto novoProduto = new Produto();
                novoProduto.setId(rs.getInt("id_livro"));
                novoProduto.setTitulo(rs.getString("titulo"));
                novoProduto.setAutor(rs.getString("autor"));
                novoProduto.setPreco(rs.getDouble("preco"));
                novoProduto.setLancamento(rs.getDate("lancamento"));
                novoProduto.setIsbn(rs.getString("isbn"));
                novoProduto.setSinopse(rs.getString("sinopse"));
                novoProduto.setImagem(rs.getString("imagem"));
                novoProduto.setPdf(rs.getString("pdf"));
                novoProduto.setStatus(rs.getString("status"));

                listaProduto.add(novoProduto);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }

        return listaProduto;
    }


    //TELA DE CARRINHO DE COMPRAS - LISTAR LIVROS POR ORDEM DE CADASTRO (AINDA FALTA MEXER)
    public ArrayList<Produto> listarLivros() {

        Connection conexao = null;
        ArrayList<Produto> listaProduto = new ArrayList<>();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECTALL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setId(rs.getInt("id_livro"));
                prod.setTitulo(rs.getString("titulo"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setImagem(rs.getString("imagem"));
                listaProduto.add(prod);
            }
        } catch (SQLException ex1) {
            throw new RuntimeException(ex1);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex1) {
                throw new RuntimeException(ex1);
            }

        }

        //retorna a lista
        return listaProduto;
    }

    //TELA DE CARRINHO DE COMPRAS - LISTAR LIVROS QUE FORAM MELHORES AVALIADOS PELOS USUARIOS
    public ArrayList<Produto> listarMelhoresAvaliados() {

        ArrayList<Produto> listaProduto = new ArrayList<>();
        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECTALL_MELHORES_AVALIADOS);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Produto novoProduto = new Produto();
                novoProduto.setId(rs.getInt("id_livro"));
                novoProduto.setTitulo(rs.getString("titulo"));
                novoProduto.setPreco(rs.getDouble("preco"));
                novoProduto.setImagem(rs.getString("imagem"));

                listaProduto.add(novoProduto);

            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
        return listaProduto;
    }

    //TELA DE PAGINA DE LIVROS - LISTAR LIVROS POR ORDEM DE TITULO (AINDA FALTA MEXER)
    public ArrayList<Produto> listarLivrosTitulo() {

        Connection conexao = null;
        ArrayList<Produto> listaProduto = new ArrayList<>();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECTALL_TITULO);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setId(rs.getInt("id_livro"));
                prod.setTitulo(rs.getString("titulo"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setImagem(rs.getString("imagem"));

                listaProduto.add(prod);
            }
        } catch (SQLException ex1) {
            throw new RuntimeException(ex1);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex1) {
                throw new RuntimeException(ex1);
            }

        }

        //retorna a lista
        return listaProduto;
    }

    //TELA DE CARRINHO DE COMPRAS - LISTAR LIVROS POR ORDEM DE PREÇO + (AINDA FALTA MEXER)
    public ArrayList<Produto> listarLivrosPrecoMaior() {

        Connection conexao = null;
        ArrayList<Produto> listaProduto = new ArrayList<>();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECTALL_PRECOMAIOR);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setId(rs.getInt("id_livro"));
                prod.setTitulo(rs.getString("titulo"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setImagem(rs.getString("imagem"));

                listaProduto.add(prod);
            }
        } catch (SQLException ex1) {
            throw new RuntimeException(ex1);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex1) {
                throw new RuntimeException(ex1);
            }

        }

        //retorna a lista
        return listaProduto;
    }

    //TELA DE CARRINHO DE COMPRAS - LISTAR LIVROS POR ORDEM DE PREÇO - (AINDA FALTA MEXER)
    public ArrayList<Produto> listarLivrosPrecoMenor() {

        Connection conexao = null;
        ArrayList<Produto> listaProduto = new ArrayList<>();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECTALL_PRECOMENOR);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Produto prod = new Produto();
                prod.setId(rs.getInt("id_livro"));
                prod.setTitulo(rs.getString("titulo"));
                prod.setPreco(rs.getDouble("preco"));
                prod.setImagem(rs.getString("imagem"));

                listaProduto.add(prod);
            }
        } catch (SQLException ex1) {
            throw new RuntimeException(ex1);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex1) {
                throw new RuntimeException(ex1);
            }

        }

        //retorna a lista
        return listaProduto;
    }

    //TELA DE CARRINHO DE COMPRAS - QUANDO CLIENTE CLICA EM UM PRODUTO É RECUPERADO O ID DO PRODUTO QUE ELE SELECIONOU
    public Produto consultarPorId(Produto p) {
        Connection conexao = null;
        Produto produto = new Produto();
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECTID);
            pstmt.setInt(1, p.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                produto.setId(rs.getInt("id_livro"));
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setPreco(rs.getDouble("preco"));
                produto.setLancamento(rs.getDate("lancamento"));
                produto.setIsbn(rs.getString("isbn"));
                produto.setImagem(rs.getString("imagem"));
                produto.setStatus(rs.getString("status"));
                produto.setPdf(rs.getString("pdf"));

                GeneroDAO generodao = new GeneroDAO();
                produto.setGenero(generodao.RecuperaGeneroDoLivro(produto));

            }

        } catch (SQLException ex1) {
            throw new RuntimeException(ex1);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }

        }

        return produto;
    }

    //TELA DE CARRINHO DE COMPRAS - RECUPERA A SINOPSE QUE O USUARIO QUER LER
    public Produto verSinopse(Produto produto) {
        Connection conexao = null;

        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECTSINOPSE);
            pstmt.setInt(1, produto.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {

                produto.setSinopse(rs.getString("sinopse"));
                produto.setTitulo(rs.getString("titulo"));

            }

        } catch (SQLException ex1) {
            throw new RuntimeException(ex1);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex2) {
                throw new RuntimeException(ex2);
            }

        }

        return produto;
    }

    //TELA DE CARRINHO DE COMPRAS - APÓS VERIFICAR QUAIS GENEROS O USUARIO MAIS GOSTOU É SELECIONADO OS LIVROS CONFORME GENERO
    public ArrayList<Produto> LivrosRecomendado(ArrayList<Genero> generos, Usuario usuario) {

        Connection conexao = null;
        ArrayList<Produto> listaDeProdutosRecomendados = new ArrayList<>();
        try {
            conexao = ConectaBanco.getConexao();

            for (Genero genero : generos) {
                //FOI FEITO UM FOR POIS VAI LISTAR TODOS OS LIVROS DE 2 GENEROS..
                PreparedStatement pstmt = conexao.prepareStatement(SELECTALL_LIVRO_RECOMENDADOS);
                pstmt.setInt(1, usuario.getId());
                pstmt.setInt(2, genero.getId_genero());
                ResultSet rs = pstmt.executeQuery();

                while (rs.next()) {

                    Produto prod = new Produto();
                    prod.setId(rs.getInt("id_livro"));
                    prod.setTitulo(rs.getString("titulo"));
                    prod.setPreco(rs.getDouble("preco"));
                    prod.setImagem(rs.getString("imagem"));
                    listaDeProdutosRecomendados.add(prod);
                }

            }

        } catch (SQLException ex1) {
            throw new RuntimeException(ex1);
        } finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex1) {
                throw new RuntimeException(ex1);
            }

        }

        //retorna a lista
        return listaDeProdutosRecomendados;
    }

    //APÓS VERIFICAR OS ITENS DO PEDIDO É RECUPERADO QUAL PRODUTO É REFERENTE DERTERMIDO ITEM QUE É DETERMINADO PEDIDO
    public Produto RecuperaOsProdutosParaItemDeCompra(ItemDeCompra item) {
        Connection conexao = null;
        Produto produto = new Produto();
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_PRODUTO_DO_ITEMDECOMPRA);
            pstmt.setInt(1, item.getId());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                produto.setId(rs.getInt("id_livro"));
                produto.setTitulo(rs.getString("titulo"));

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }

        return produto;
    }

   
    public Produto RecuperaOsProdutosParaMinhasCompras(EstanteVirtual estante) {
        Connection conexao = null;
        Produto produto = new Produto();
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_PRODUTO_DO_MINHASCOMPRAS);
            pstmt.setInt(1, estante.getId_estanteVirtual());
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {               
                produto.setTitulo(rs.getString("titulo"));
                produto.setAutor(rs.getString("autor"));
                produto.setIsbn(rs.getString("isbn"));
                produto.setPdf(rs.getString("pdf"));
                produto.setImagem(rs.getString("imagem"));
                produto.setId(rs.getInt("id_livro"));

            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }

        return produto;
    }
    
   
    public ArrayList<Produto> ajaxRecuperandoProdutosProcurado(Produto produto) throws SQLException {

        String retorno;
        ArrayList<Produto> list = new ArrayList<>();
        

        Connection conexao = ConectaBanco.getConexao();	 //conecta ao banco

        PreparedStatement pstmt = conexao.prepareStatement(SELECTLIVROPESQUISADOAJAX);	  //cria o comando do select de clientes no banco
        pstmt.setString(1,'%' + produto.getTitulo() + '%');
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            
            Produto produtoNome = new Produto();
            produtoNome.setTitulo(rs.getString("titulo"));
            list.add(produtoNome);
        }
        return list; 	//retorna array de clientes para a controle
    }
    
}
