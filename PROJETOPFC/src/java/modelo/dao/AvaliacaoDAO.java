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
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class AvaliacaoDAO {

    private static final String SELECTAVALIACAO = "select * from  avaliacao as av where av.id_livro = (?) order by av.data_comentario desc";

    private static final String INSERT_AVALIACAO = "insert into avaliacao (id_livro,comentario, id_usuario,data_comentario,nota) values (?,?,?,?,?)";

    
    //Recuperar dados para mostrar no modal
    public ArrayList<Avaliacao> verComentarioENotas(Produto produto) {

        Connection conexao = null;
        ArrayList<Avaliacao> listaComentarios = new ArrayList<Avaliacao>();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECTAVALIACAO);
            pstmt.setInt(1, produto.getId());
            ResultSet rs = pstmt.executeQuery();
                
            while (rs.next()) {

                
                Avaliacao avaliacao = new Avaliacao();
                avaliacao.setId(rs.getInt("id_avaliacao"));
                avaliacao.setComentario(rs.getString("comentario"));
                avaliacao.setData_Comentario(rs.getDate("data_comentario"));
                avaliacao.setNota(rs.getInt("nota"));
                
                UsuarioDAO usuariodao = new UsuarioDAO();
                avaliacao.setUsuario(usuariodao.DevolveUsuarioAvaliacao(avaliacao));
                
                listaComentarios.add(avaliacao);

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

        return listaComentarios;
    }

    // AVALIAR UM LIVRO COM UM COMENTARIO E UMA NOTA
    public void avaliar(Avaliacao avaliacao) {

        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT_AVALIACAO);
            pstmt.setInt(1, avaliacao.getProduto().getId());
            pstmt.setString(2, avaliacao.getComentario());
            pstmt.setInt(3, avaliacao.getUsuario().getId());
            pstmt.setDate(4, (Date) avaliacao.getData_Comentario());
            pstmt.setInt(5, avaliacao.getNota());
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

}
