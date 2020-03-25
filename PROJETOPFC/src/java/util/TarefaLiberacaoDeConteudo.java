/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import modelo.dao.EstanteVirtualDAO;
import modelo.dao.PedidoDAO;
import modelos.Pedido;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 *
 * @author 11131103404
 */
public class TarefaLiberacaoDeConteudo implements Job {

    @Override
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        try {
            VerificaPagamento();
        } catch (SQLException ex) {

        }
    }

    public void VerificaPagamento() throws SQLException {

        PedidoDAO pedidoDao = new PedidoDAO();

        //Recupera no banco TODOS os usuarios que efetuou pagamento
        //na variavel "resultadoDosPagantes" é um array com todos usuarios que pagaram e nele CONTEM login,status e ID
        //No liberarConteudo passa como parametro o resultado como dito acima para que possa mudar o status de pedente para liberado
        ArrayList<Pedido> resultadoDosPagantes = pedidoDao.listarPagos();
        
        

        if (resultadoDosPagantes.isEmpty() || resultadoDosPagantes == null) {

            System.out.println("Não consta nenhum pedido pago!!/ DATA: " + new Date());

        } else {

            //MUDA O STATUS DE PAGO PARA LIBERADO (SOMENTE OS PEDIDOS PAGOS)            
          ArrayList<Pedido> listaDePedidoQueForamLiberados = pedidoDao.TrocaDeStatusPagoParaLiberado(resultadoDosPagantes);

            //Adiciona na tabela COMPRA os cliente que efetuaram pagamento e o livro estiver como liberado
            EstanteVirtualDAO estanteVirtual = new EstanteVirtualDAO();
            estanteVirtual.AdicinarLivroNaEstanteVirtual(listaDePedidoQueForamLiberados);

            // LISTA DE REPETIÇÃO PARA ENVIAR UMA NOTIFICAÇÃO AOS USUARIOS QUE EFETUARAM PAGAMENTO
           for (Pedido pagos : resultadoDosPagantes) {
                Email pedidoPago = new Email();
                pedidoPago.EnviarNotificacaoPedidoPago(pagos);
                }
            System.out.println("Pedido pago com sucesso/ DATA: " + new Date());

        }

        System.out.println("Fim da Tarefa de pagamento/ DATA: " + new Date());

    }

}
