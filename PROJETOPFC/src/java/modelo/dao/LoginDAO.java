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
import java.sql.Statement;
import modelos.Login;
import modelos.PerfilAcesso;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class LoginDAO {

    private static final String SELECT_EMAIL = "select login from login where login =(?)";
    private static final String UPDATE_SENHA = "update login set senha=(?) where login = (?)";
    private static final String INSERT = "INSERT INTO login (login,senha,perfil_id) VALUES (?,?,?)";   
    
    private static final String RECUPERA_LOGIN_POR_USUARIO = "select l.*, p.* from usuario u, login as l , perfilacesso as p where p.id_perfil = l.perfil_id and u.id_usuario = l.id_login and u.id_usuario = (?)";
            
    
    private static final String AUTENTICA_LOGIN = "select * from login as l , perfilacesso as p where p.id_perfil = l.perfil_id and l.login = (?) and l.senha = (?)"; 
    
    //Metodo realizado na controle
    public Login AutenticaLoginDoUsuario(Login login) {        
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsLogin = null;
         Login loginAutenticado = null;
        
        try {

            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(AUTENTICA_LOGIN);
            pstmt.setString(1, login.getLogin());
            pstmt.setString(2, login.getSenha());

            rsLogin = pstmt.executeQuery();
            
            if (rsLogin.next()) { 
                
                
                PerfilAcesso pacesso = new PerfilAcesso();
                pacesso.setId(rsLogin.getInt("id_perfil"));
                pacesso.setDescricao(rsLogin.getString("descricao"));                
                
                loginAutenticado = new Login();
                loginAutenticado.setLogin(rsLogin.getString("login"));
                //loginAutenticado.setSenha(rsLogin.getString("senha"));
                loginAutenticado.setId(rsLogin.getInt("id_login"));
                loginAutenticado.setPerfil(pacesso);

            }

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                    
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);

                }
            }
        }

        return loginAutenticado;

    }
    
    
    
    
      //Recupera dados login para o usuario para o metodo ListarPagos
    public Login RecuperaLoginPorUsuario(Usuario usuario) {        
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsLogin = null;
        Login loginAutenticado = null;
        
        try {

            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(RECUPERA_LOGIN_POR_USUARIO);
            pstmt.setInt(1, usuario.getId());
          
            rsLogin = pstmt.executeQuery();
            
            if (rsLogin.next()) { 
                
                
                PerfilAcesso pacesso = new PerfilAcesso();
                pacesso.setId(rsLogin.getInt("id_perfil"));
                pacesso.setDescricao(rsLogin.getString("descricao"));                
                
                loginAutenticado = new Login();
                loginAutenticado.setLogin(rsLogin.getString("login"));
                loginAutenticado.setSenha(rsLogin.getString("senha"));
                loginAutenticado.setId(rsLogin.getInt("id_login"));
                loginAutenticado.setPerfil(pacesso);

            }

        } catch (SQLException sqlErro) {
            throw new RuntimeException(sqlErro);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                    
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);

                }
            }
        }

        return loginAutenticado;

    }
    
    

   

    //Verifica se existe um email na hora do usuario criar um cadastro
    public boolean verificaSeExisteEmail(Login log) {
        Connection conexao = ConectaBanco.getConexao();
        boolean existe;
        try {
            
            PreparedStatement pstmt = conexao.prepareStatement(SELECT_EMAIL);
            pstmt.setString(1, log.getLogin());
            ResultSet rs = pstmt.executeQuery();
            //caso exista um email a variavel existe recebe TRUE
            existe = rs.next();
            
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (conexao != null) {
                try {
                    conexao.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);

                }
            }
        }
        return existe;
    }

    // Cadastrar Um login e após o cadastro devolve o id cadastrado para que possa relacionar com o usuario
    public Login cadastraLogin(Login log) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, log.getLogin());
            pstmt.setString(2, log.getSenha());
            pstmt.setInt(3, log.getPerfil().getId());
            pstmt.execute();

            ResultSet id = pstmt.getGeneratedKeys();
            id.next();
            
            int id_login = id.getInt(1);
            log.setId(id_login);
            

        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
        return log;
    }

    // ALTERAR A SENHA DO LOGIN
    public void alterarSenha(Login login) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE_SENHA);

            pstmt.setString(1, login.getSenha());
            pstmt.setString(2, login.getLogin());

            pstmt.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                conexao.close();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            }
        }
    }
    
    
    public void RecuperandoSenha(Login login) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE_SENHA);
            pstmt.setString(1, login.getSenha());
            pstmt.setString(2, login.getLogin());
            
            pstmt.execute();
        } catch (SQLException e) {
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
