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
import modelos.ImportacaoBoleto;
import modelos.Pedido;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class ImportacaoBoletoDAO {

    private static final String INSERT = "INSERT INTO importacaoBoleto (codigo,status) VALUES (?,?)";
    private static final String LOCALIZA = "select * from importacaoBoleto where codigo = (?)";
    private static final String TROCA_DE_STATUS = "update importacaoBoleto set status = 'S' where codigo = ?";

    
    
    
    
    public void TrocaDeStatusDaImportacao(ArrayList<Pedido> boletos) {
        
             Connection conexao = null;

        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(TROCA_DE_STATUS);

            for (Pedido p : boletos) {

                pstmt.setString(1, p.getCodigo_boleto());
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
    
    
    
    
    
    public ArrayList<ImportacaoBoleto> VerificarSeONumeroDeBoletoJaExiste(ArrayList<ImportacaoBoleto> boleto) {

        ArrayList<ImportacaoBoleto> novaListaBoletosQueNaoExistem = new ArrayList<ImportacaoBoleto>();
        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(LOCALIZA);

            for (ImportacaoBoleto importacaoBoleto : boleto) {

                pstmt.setString(1, importacaoBoleto.getCodigo());
                ResultSet rs = pstmt.executeQuery();
                
                boolean achou = rs.next();

                if(achou == false){

                    ImportacaoBoleto novoBoleto = new ImportacaoBoleto();
                    novoBoleto.setCodigo(importacaoBoleto.getCodigo());
                    novoBoleto.setStatus("N");
                    novaListaBoletosQueNaoExistem.add(novoBoleto);
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
        return novaListaBoletosQueNaoExistem;
    }

    
    public void ImportarBoletosNoBanco(ArrayList<ImportacaoBoleto> boleto) {

        Connection conexao = null;
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT);

            for (ImportacaoBoleto importacaoBoleto : boleto) {

                pstmt.setString(1, importacaoBoleto.getCodigo());
                pstmt.setString(2, importacaoBoleto.getStatus());
                pstmt.execute();
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

    

}
