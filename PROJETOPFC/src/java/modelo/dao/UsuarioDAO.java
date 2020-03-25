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
import modelos.Avaliacao;
import modelos.Login;
import modelos.Pedido;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class UsuarioDAO {
    
    private static final String SELECT_DADOS_USUARIO = "select u.* from usuario as u, login as l where l.id_login = u.id_usuario and l.id_login = (?)";
    
    private static final String SELECT_DADOS_USUARIO_PEDIDO = "select u.* from usuario as u , pedido p where p.id_usuario = u.id_usuario and p.id_pedido = (?)";

    
    private static final String INSERT = "INSERT INTO usuario (id_usuario,nome,sobrenome,cpf,telefone) VALUES (?,?,?,?,?)";
    private static final String SELECT_CPF = "select cpf from usuario"
            + " where cpf =(?)";

    private static final String DELETE = "delete from usuario"
            + " where login=? AND senha= ? ";

    private static final String UPDATE = "update usuario set nome=(?), sobrenome=(?) , telefone=(?)"
            + " where id_usuario = (?)";

    private static final String RECUPERAR_SENHA = "select * from usuario as u, login as l where l.id_login = u.id_usuario and l.login = ? and u.cpf = ? ";
       
    private static final String RECUPERA_USUARIO_AVALIACAO = "select u.nome from usuario as u, avaliacao a where  u.id_usuario = a.id_usuario and a.id_avaliacao =  ? ";
    
    //DEVOLVE USUARIO DA AVALIACAO
    public Usuario DevolveUsuarioAvaliacao(Avaliacao avaliacao){
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsUsuario = null;
         Usuario usuario = new Usuario();
             
        try {
            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(RECUPERA_USUARIO_AVALIACAO);
            pstmt.setInt(1, avaliacao.getId());
            rsUsuario = pstmt.executeQuery();
            
            while (rsUsuario.next()){
               
                usuario.setNome(rsUsuario.getString("nome"));
                                
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
        return usuario;
        
    }
    
    
    
    
    
    
     // autenticar usuario no sistema(sessao) caso exista iniciar uma sessao para usuario
    public Usuario RecuperaDadosUsuarioPorLogin(Login login) {        
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsUsuario = null;
        Usuario usuarioAutenticado = new Usuario();
        try {

            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(SELECT_DADOS_USUARIO);
            pstmt.setInt(1, login.getId()); 
            rsUsuario = pstmt.executeQuery();
            
            if (rsUsuario.next()) {
                
                                
                usuarioAutenticado.setNome(rsUsuario.getString("nome"));
                usuarioAutenticado.setSobrenome(rsUsuario.getString("sobrenome"));
                usuarioAutenticado.setCpf(rsUsuario.getString("cpf"));
                usuarioAutenticado.setTelefone(rsUsuario.getString("telefone"));
                usuarioAutenticado.setId(rsUsuario.getInt("id_usuario"));
                usuarioAutenticado.setLogin(login);
                
               

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

        return usuarioAutenticado;

    }
    
    
    // autenticar usuario no sistema(sessao) caso exista iniciar uma sessao para usuario
    public Usuario RecuperaDadosUsuarioPorPedido(Pedido pedido) {        
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsUsuario = null;
        Usuario usuarioAutenticado = new Usuario();
        try {

            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(SELECT_DADOS_USUARIO_PEDIDO);
            pstmt.setInt(1, pedido.getId()); 
            rsUsuario = pstmt.executeQuery();
            
            if (rsUsuario.next()) {
                
                                
                usuarioAutenticado.setNome(rsUsuario.getString("nome"));
                usuarioAutenticado.setSobrenome(rsUsuario.getString("sobrenome"));
                usuarioAutenticado.setCpf(rsUsuario.getString("cpf"));
                usuarioAutenticado.setTelefone(rsUsuario.getString("telefone"));
                usuarioAutenticado.setId(rsUsuario.getInt("id_usuario"));
                
                LoginDAO logdao = new LoginDAO();
                Login dados_recuperados = logdao.RecuperaLoginPorUsuario(usuarioAutenticado);
                usuarioAutenticado.setLogin(dados_recuperados);
                
               
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

        return usuarioAutenticado;

    }
    
    
    
       
    //ATUALIZA DADOS DO USUARIO
    public void atualizar(Usuario usuario) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(UPDATE);

            pstmt.setString(1, usuario.getNome());
            pstmt.setString(2, usuario.getSobrenome());
            pstmt.setString(3, usuario.getTelefone());
            pstmt.setInt(4, usuario.getId());

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

    // EXCLUIR DADOS DO USUARIO
    public void excluir(Login login) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(DELETE);
            pstmt.setString(1, login.getLogin());
            pstmt.setString(2, login.getSenha());
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

    // CADASTRA O USUARIO 
    public void cadastrarUsuario(Usuario usuario) {
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            pstmt.setInt(1, usuario.getLogin().getId());
            pstmt.setString(2, usuario.getNome());
            pstmt.setString(3, usuario.getSobrenome());
            pstmt.setString(4, usuario.getCpf());
            pstmt.setString(5, usuario.getTelefone());
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

    //VERIFICA SE O CPF QUE ESTA TENTANDO CADASTRA JÁ EXISTE
    public boolean verificaSeExisteCpf(Usuario usuario) {
        boolean existe;
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_CPF);
            pstmt.setString(1, usuario.getCpf());
            ResultSet rs = pstmt.executeQuery();

            existe = rs.next();

            pstmt.close();
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
    
    //verifica se os dados que usuario digitou realmente existe no banco (recuperar senha)
    public boolean VerificaSeExisteEmailECpfParaRecuperarSenha(Usuario usuario) {       
        Connection conexao = null;
        PreparedStatement pstmt = null;
        ResultSet rsUsuario = null;
        boolean existe = false;
        
        try {

            conexao = ConectaBanco.getConexao();
            pstmt = conexao.prepareStatement(RECUPERAR_SENHA);
            pstmt.setString(1, usuario.getLogin().getLogin());
            pstmt.setString(2, usuario.getCpf());
            
            rsUsuario = pstmt.executeQuery();

            existe = rsUsuario.next(); 
               
               

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
        return existe;
    }

}
