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
import modelos.Genero;
import modelos.Produto;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11151506200
 */
public class GeneroDAO {
    
    //select para mostrar todos os generos (na hora de cadastrar um livro)
    private static final String SELECT_ALL_GENEROS = "select * from genero order by desc_genero";
    
    //select para ver os generos que aquele usuario mais gosta
    private static final String SELECTALL_QUAL_LIVRO_RECOMENDADOS = "select g.id_genero, "
            + "avg (a.nota) as Media from genero g, produto p, avaliacao a, usuario u "
            + "where p.id_genero = g.id_genero and p.id_livro = a.id_livro "
            + "and u.id_usuario = a.id_usuario and u.id_usuario = ? "
            + "and p.status = 'ativo' "
            + "group by g.id_genero order by Media desc LIMIT 2";
    
            
     private static final String SELECT_GENERO_DO_LIVRO = "select g.desc_genero, g.id_genero  from genero as g , produto as p where g.id_genero = p.id_genero and p.id_livro = ?";
    
    
        //Listar para mostrar na hora de cadastrar um produto (encher o combo box)
     public ArrayList<Genero> listarGeneros() {

        Connection conexao = null;
        ArrayList<Genero> listaGenero = new ArrayList<Genero>();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL_GENEROS);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Genero g = new Genero();
                g.setId_genero(rs.getInt("id_genero"));
                g.setDesc_genero(rs.getString("desc_genero"));
                listaGenero.add(g);
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
        return listaGenero;
    }
     
     
     
     //Generos que o usuario melhor avaliou (mostra somente os generos de determinado usuario)
      public ArrayList<Genero> qualLivroDeveSerRecomendado(Usuario usuario) {

        Connection conexao = null;
        ArrayList<Genero> listaDeGenero = new ArrayList<Genero>();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECTALL_QUAL_LIVRO_RECOMENDADOS);
            pstmt.setInt(1, usuario.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Genero genero = new Genero();
                genero.setId_genero(rs.getInt("id_genero"));                
                listaDeGenero.add(genero);
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
        return listaDeGenero;
    }
      
      
      
      
      public Genero RecuperaGeneroDoLivro(Produto produto) {

        Connection conexao = null;
        Genero genero = new Genero();
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_GENERO_DO_LIVRO);
            pstmt.setInt(1, produto.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {                
               genero.setId_genero(rs.getInt("id_genero")); 
               genero.setDesc_genero(rs.getString("desc_genero"));
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
        return genero;
    }
      
      
      
    
}
