/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import modelo.dao.ImportacaoBoletoDAO;
import modelo.dao.PedidoDAO;
import modelos.ImportacaoBoleto;
import modelos.Pedido;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author 11131103404
 */
public class TarefaImportacaoBoleto implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        //CRIA UMA LISTA DE BOLETOS QUE FORAM RECUPERADOS DO ARQUIVO DE TEXTO
        ArrayList<ImportacaoBoleto> listaRecuperadoDoArquivoTXT = ListaDeImportacao();

        //VERIFICO NO BANCO DE DADOS SE ESSES ARQUIVOS RECUPERADOS JÁ EXISTEM E ME DEVOLVE UMA LISTA DOS BOLETOS QUE AINDA NÃO EXISTEM NA TABELA IMPORTAÇÃO
        ArrayList<ImportacaoBoleto> listaAposVerificacao = VerificaSeOsBoletosRecuperadoJaNaoExiste(listaRecuperadoDoArquivoTXT);

        //APÓS RECUPERAR A LISTA DOS BOLETOS QUE NÃO EXISTE É INSERIDO NA TABELA IMPORTAÇÃO OS NOVOS BOLETOS PAGOS
        InserirNoBancoImportacao(listaAposVerificacao);

        //TROCA O STATUS DE PENDENTE PARA PAGO E RETORNA UMA LISTA DOS BOLETOS QUE FORAM VALIDADOS (PAGOS) PARA ABAIXO TROCA O STATUS        
        ArrayList<Pedido> listaDeBoletoQueForamPagos = TrocaDeStatusPago();

        //TROCAR O STATUS DO IMPORTAÇÃO BOLETO DE N PARA S POIS TODOS FORAM VERIFICADOS
        TrocaDeStatusDeNparaS(listaDeBoletoQueForamPagos);

        System.out.println("Verificação feita/ DATA: " + new Date());
    }

    public ArrayList<ImportacaoBoleto> ListaDeImportacao() {

        String nomeArquivo = "C:\\importacao\\BoletosPago.txt";

        File arq = new File(nomeArquivo);
        ArrayList<ImportacaoBoleto> ListaDeBoletosPagos = new ArrayList<ImportacaoBoleto>();

        if (arq.exists()) {

            try {

                FileReader leitorDeArquivo = new FileReader(nomeArquivo);
                BufferedReader bufferDeArquivo = new BufferedReader(leitorDeArquivo);

                while (true) {

                    ImportacaoBoleto importacao = new ImportacaoBoleto();

                    importacao.setCodigo(bufferDeArquivo.readLine());
                    importacao.setStatus("N");

                    if (importacao.getCodigo() == null) {
                        break;
                    }
                    
                    
                    if(importacao.getCodigo().equals("") || importacao.getCodigo() == null || importacao.getCodigo().equals("0")){
                   
                    }else{
                        
                         ListaDeBoletosPagos.add(importacao);
                    }
                    
                }

            } catch (Exception e) {

            }
        }
        return ListaDeBoletosPagos;
    }

    public ArrayList<ImportacaoBoleto> VerificaSeOsBoletosRecuperadoJaNaoExiste(ArrayList<ImportacaoBoleto> ListaDeBoletosPagos) {

        ImportacaoBoletoDAO ibDAO = new ImportacaoBoletoDAO();

        //Verifica se os novos boletos já não estao no banco de dados para nao inserir boleto igual
        //assim recupera uma lista dos boletos que serão importados ou seja que não existe na tabela importação
        ArrayList<ImportacaoBoleto> lista = ibDAO.VerificarSeONumeroDeBoletoJaExiste(ListaDeBoletosPagos);

        return lista;

    }

    public void InserirNoBancoImportacao(ArrayList<ImportacaoBoleto> lista) {

        ImportacaoBoletoDAO ibDAO = new ImportacaoBoletoDAO();

        if (lista.isEmpty() || lista == null) {

            System.out.println("Não possui nenhum boleto para inserir");

        } else {

            // inserir na tabela importação 
            ibDAO.ImportarBoletosNoBanco(lista);

        }

    }

    
    
    public ArrayList<Pedido> TrocaDeStatusPago() {

        //tras uma lista aonde o status do importação boleto esta como N junto com seus respetivos pedido
        //ou seja boleto 1234 é referente ao pedido 1...
        PedidoDAO pdao = new PedidoDAO();
        ArrayList<Pedido> lista = pdao.ImportacaoComPedidoPago();

        ArrayList<Pedido> listaDeBoletosPagos = new ArrayList<>();

        try {
            //vai até a tabela PEDIDO e troca os status do pedido que foi recuperado para 'pago'
            listaDeBoletosPagos = pdao.TrocaDeStatusParaPagoPedido(lista);

        } catch (SQLException ex) {

        }
        
        return listaDeBoletosPagos;
    }

    
    
    public void TrocaDeStatusDeNparaS(ArrayList<Pedido> lista) {

        ImportacaoBoletoDAO idao = new ImportacaoBoletoDAO();

        //vai até a tabela importação boleto e troca o status de N para S dos boletos que foram verificados
        idao.TrocaDeStatusDaImportacao(lista);

    }

}

//Linhas a baixo para inserir dados em um arquivo txt
//BufferedWriter buffWrite = new BufferedWriter(new FileWriter(nomeArquivo));
//String linhas = "Linha doque vc quer inserir dentro do txt";
//for (int i = 0; i < 10; i++) {
//buffWrite.append(linhas + "\n");
//}
//buffWrite.close();
//Essas 2 linhas abaixo são para limpas todas as informações do arquivo txt 
//Writer clean = new BufferedWriter(new FileWriter(nomeArquivo));  
                //clean.close(); 
