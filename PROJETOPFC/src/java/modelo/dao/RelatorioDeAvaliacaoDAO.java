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
import modelos.Avaliacao;
import modelos.Produto;
import modelos.RelatorioDeAvaliacao;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class RelatorioDeAvaliacaoDAO {
    
    private static final String MEDIA_POR_LIVRO = "SELECT AVG(a.nota) as media,COUNT(a.id_avaliacao),p.titulo,p.id_livro,"
            + "p.isbn,p.autor FROM avaliacao as a, produto as p where a.id_livro = p.id_livro "
            + "and a.data_comentario between (?) and (?) group by p.id_livro order by media desc";
    
        // tras o relatorio CONFORME DATA SELECIONADA PELO ADM e lista todos os livros daquele periodo, com nome, notas etc
        public ArrayList<RelatorioDeAvaliacao> CriarRelatorioDeAvaliacao(RelatorioDeAvaliacao relatorioAvaliacao) {
        Connection conexao = null;
        ArrayList<RelatorioDeAvaliacao> listaProduto = new ArrayList<RelatorioDeAvaliacao>();

        try {
            conexao = ConectaBanco.getConexao();
            
            double valorTotalDeVendas = 0;
            int qtdDeAvaliacaoPorLivro =0;                    

            PreparedStatement pstmt = conexao.prepareStatement(MEDIA_POR_LIVRO);
            pstmt.setDate(1, (Date) relatorioAvaliacao.getDe());
            pstmt.setDate(2, (Date) relatorioAvaliacao.getAte());
            ResultSet rs = pstmt.executeQuery();
            
         
            while (rs.next()) {                
                
                valorTotalDeVendas = (rs.getDouble("media"));
                qtdDeAvaliacaoPorLivro = (rs.getInt("count"));
                
                Produto produto = new Produto();
                produto.setTitulo(rs.getString("titulo"));
                produto.setIsbn(rs.getString("isbn"));
                produto.setAutor(rs.getString("autor"));
                
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setProduto(produto);               
                
                RelatorioDeAvaliacao relatorio = new RelatorioDeAvaliacao(); 
                relatorio.setAvaliacao(avaliacao);
                relatorio.setQuantidade(qtdDeAvaliacaoPorLivro);                
                relatorio.setResultadoMedia(valorTotalDeVendas);
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
