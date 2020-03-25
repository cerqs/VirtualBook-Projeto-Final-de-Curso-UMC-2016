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
import modelos.Produto;
import modelos.RelatorioDeVendas;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class RelatorioDeVendasDAO {

    private static final String RELATORIO = "select p.id_livro, p.titulo,p.isbn, i.valor_unitario, "
            + "COUNT(i.id_livro) as quantidade_vendido, SUM(i.valor_unitario) as total_vendido "
            + "from produto as p , pedido as pe, itemdecompra as i "
            + "where  p.id_livro = i.id_livro and i.id_pedido = pe.id_pedido "
            + "and pe.status = 'liberado' and pe.de between ? and ? group by p.id_livro, p.titulo, i.valor_unitario order by total_vendido desc";

    public ArrayList<RelatorioDeVendas> listarRelatorio(RelatorioDeVendas relatorioComDatas) {
        Connection conexao = null;
        ArrayList<RelatorioDeVendas> listaProduto = new ArrayList<RelatorioDeVendas>();

        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(RELATORIO);
            pstmt.setDate(1, (Date) relatorioComDatas.getDe());
            pstmt.setDate(2, (Date) relatorioComDatas.getAte());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Produto produto = new Produto();
                produto.setTitulo(rs.getString("titulo"));
                produto.setId(rs.getInt("id_livro"));
                produto.setIsbn(rs.getString("isbn"));

                RelatorioDeVendas relatorio = new RelatorioDeVendas();
                relatorio.setTotal_vendido(rs.getDouble("total_vendido"));
                relatorio.setQuantidade_vendido(rs.getInt("quantidade_vendido"));
                relatorio.setProduto(produto);

                listaProduto.add(relatorio);

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

}
