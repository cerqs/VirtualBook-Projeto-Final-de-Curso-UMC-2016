/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;


import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import modelo.dao.PedidoDAO;
import modelos.Pedido;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author 11131103404
 */
public class TarefaCancelarPedido implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        
        VerificaPedidosCancelados();
        
    }

    public void VerificaPedidosCancelados() {
        
        //Recupera a data de HOJE
        GregorianCalendar gc = new GregorianCalendar();
        java.sql.Date hoje = new java.sql.Date(gc.getTime().getTime());

        PedidoDAO pedidoDao = new PedidoDAO();

        //Recupera uma lista de cliente que no qual nao efetuaram pagamento em 5 dias
        //Na variavel "resultadoCancelados" contem a lista.
        //é cancelado todos o usuarios no quais nao pagaram em 5 dias e o status ainda estiver pendente
        ArrayList<Pedido> resultadoCancelados = pedidoDao.listarCancelados(hoje);
        
        if(resultadoCancelados.isEmpty() || resultadoCancelados == null){
            System.out.println("Não consta nenhum pedido vencido / DATA: " + new Date());
        }else{
        pedidoDao.CancelarConteudo(resultadoCancelados);

        //LISTA DE REPETIÇÃO PARA ENVIAR UMA NOTIFICAÇÃO AOS USUARIOS QUE EFETUARAM A COMPRA FOI CANCELADA
        for (Pedido cancelados : resultadoCancelados) {
        Email pedidoCancelado = new Email();
       pedidoCancelado.EnviarNotificacaoPedidoCancelado(cancelados);
        }
        System.out.println("Pedido cancelado com sucesso/ DATA: " + new Date());
        }
        
         System.out.println("Fim da tarefa de cancelar pedido/ DATA: " + new Date());
    }

}
