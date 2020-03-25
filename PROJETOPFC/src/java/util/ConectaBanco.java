/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author 11131103404
 */
public class ConectaBanco {

    public static Connection getConexao() {
        Connection conexao = null;
        try {
            Class.forName("org.postgresql.Driver");
            conexao = DriverManager.getConnection("jdbc:postgresql://localhost:5432/a", "postgres", "postgres");
        } catch (ClassNotFoundException erro1) {
            throw new RuntimeException(erro1);
        } catch (SQLException erro2) {
            throw new RuntimeException(erro2);
        }
        return conexao;

    }
}
