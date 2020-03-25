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
import modelos.Login;
import modelos.RelatorioDeUsuario;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class RelatorioDeUsuarioDAO {

    private static final String SELECT_ALL = "select * from usuario as u , login as l"
            + " where u.id_usuario = l.id_login";
    
    

    public ArrayList<RelatorioDeUsuario> listar() {
        Connection conexao = null;
        ArrayList<RelatorioDeUsuario> listaProduto = new ArrayList<RelatorioDeUsuario>();

        try {
            conexao = ConectaBanco.getConexao();
            
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_ALL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Login l = new Login();
                l.setLogin(rs.getString("login"));

                Usuario u = new Usuario();
                u.setId(rs.getInt("id_usuario"));
                u.setNome(rs.getString("nome"));
                u.setSobrenome(rs.getString("sobrenome"));
                u.setTelefone(rs.getString("telefone"));
                u.setCpf(rs.getString("cpf"));
                u.setLogin(l);

                RelatorioDeUsuario relatorio = new RelatorioDeUsuario();
                relatorio.setUsuario(u);

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
