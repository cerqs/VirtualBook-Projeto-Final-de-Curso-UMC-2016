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
import modelos.Pedido;
import modelos.Produto;
import util.ConectaBanco;

/**
 *
 * @author guilherme
 */
public class ItemDeCompraDAO {
    
    private static final String INSERT_ITENS = "INSERT INTO itemdecompra (id_livro,id_pedido,valor_unitario) "
            + "VALUES (?,?,?)";
    private static final String SELECT_MEUS_ITENSDECOMPRA = "select * from itemdecompra where id_pedido = (?)";

    
    //Controle carrinho - inclui os itens em seu determinado pedido.
    public void CriarItensCompra(Pedido pedido) {
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT_ITENS);

            for (ItemDeCompra item : pedido.getCarrinho().getItens()) {
                pstmt.setInt(1, item.getProduto().getId());
                pstmt.setInt(2, pedido.getId());
                pstmt.setDouble(3, item.getTotal());
                pstmt.execute();
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

    }
    
    //NESSE METODO É POSSIVEL VERIFICAR OS ITENS DE DDETERMINADO PEDIDO
    //EXEMPLO: PEDIDO 1 TEM OS ITENS 1,2,3 / PEDIDO 2 TEM O ITEM 6
    // APÓS RECUPERAR OS PEDIDO NO METODO ANTERIOR RECUPERA OS ITENS DAQUELE DETERMINADO PEDIDO
    public ArrayList<ItemDeCompra> SelecionaItensDoMeuPedido(Pedido pedido) {
        
        ItemDeCompra item = null;
        Connection conexao = null;
        ArrayList<ItemDeCompra> ListaDeItens = new ArrayList<>();
        try {
            
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmtt = conexao.prepareStatement(SELECT_MEUS_ITENSDECOMPRA);
            pstmtt.setInt(1, pedido.getId());
            ResultSet rss = pstmtt.executeQuery();

            while (rss.next()) {
                
                item = new ItemDeCompra();                
                item.setId(rss.getInt("id_itens"));
                
                ProdutoDAO produtodao = new ProdutoDAO();
                Produto produto = produtodao.RecuperaOsProdutosParaItemDeCompra(item);
                item.setProduto(produto);
                
                ListaDeItens.add(item);
                
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

        return ListaDeItens;
    }

}
