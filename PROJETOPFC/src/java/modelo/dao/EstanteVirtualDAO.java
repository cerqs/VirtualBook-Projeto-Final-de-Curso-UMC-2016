/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelos.ItemDeCompra;
import modelos.EstanteVirtual;
import modelos.Pedido;
import modelos.Produto;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class EstanteVirtualDAO {

    private static final String INSERT = "insert into estante_virtual(id_livro,id_usuario) values (?,?)";
    
    private static final String SELECT_MEUS_LIVROS = "select id_estante from estante_virtual as e where e.id_usuario = (?)";
    
    private static final String SELECTVERIFICARLIVRO = "select * from estante_virtual ev where ev.id_usuario = (?) and ev.id_livro = (?) ";

    
    //TAREFA - APÓS A TAREFA VERIFICAR QUAIS LIVRO FORAM PAGOS PARA PODER INSERIR NA TABELA "ESTANTE VIRTUAL"
    public void AdicinarLivroNaEstanteVirtual(ArrayList<Pedido> pedido) {
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            for (Pedido p : pedido) {

                for (ItemDeCompra item : p.getCarrinho().getItens()) {
                    pstmt.setInt(1, item.getProduto().getId());
                    pstmt.setInt(2, p.getUsuario().getId());
                    //pstmt.setInt(3, p.getId());
                    pstmt.execute();
                }

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
    }

    
    //TELA DE MEUS LIVROS - MOSTRAR AO CLIENTE TODOS OS LIVRO QUE FORAM PAGOS E LIBERADOS.
    public ArrayList<EstanteVirtual> listarMeusLivros(Usuario usuario) {
        Connection conexao = null;
        ArrayList<EstanteVirtual> listaProduto = new ArrayList<EstanteVirtual>();
       
        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_MEUS_LIVROS);
            pstmt.setInt(1, usuario.getId());

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                
                EstanteVirtual compras = new EstanteVirtual();
                compras.setId_estanteVirtual(rs.getInt("id_estante"));
                
                ProdutoDAO produtoDao = new ProdutoDAO();
                Produto produto = produtoDao.RecuperaOsProdutosParaMinhasCompras(compras);
                
                compras.setProduto(produto);

                listaProduto.add(compras);
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
        
        return listaProduto;
    }

 //COMEMTARIO - verifica na hora de comentar se o usuario tem determinado livro ( não deixa colocar id de outro livro na url)
// Leitura de Livro - verifica se o usuário possui o livro na estante virtual
     public boolean verificaSePossuiLivro(EstanteVirtual estante) {
        Connection conexao = ConectaBanco.getConexao();
        boolean existe;
        try {

            PreparedStatement pstmt = conexao.prepareStatement(SELECTVERIFICARLIVRO);
            pstmt.setInt(1, estante.getUsuario().getId());
            pstmt.setInt(2, estante.getProduto().getId());
            ResultSet rs = pstmt.executeQuery();

            existe = rs.next();

            pstmt.close();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            try {
                if (conexao != null) {
                    conexao.close();
                }
            } catch (SQLException ex1) {
                throw new RuntimeException(ex1);
            }

        }
        
        return existe;
    }

}
