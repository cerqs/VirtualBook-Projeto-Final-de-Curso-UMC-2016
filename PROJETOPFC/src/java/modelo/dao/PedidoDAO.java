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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelos.CarrinhoDeCompra;
import modelos.ItemDeCompra;
import modelos.Pedido;
import modelos.Usuario;
import util.ConectaBanco;

/**
 *
 * @author 11131103404
 */
public class PedidoDAO {

    //private static final String SELECT = "SELECT id_pedido FROM pedido";

    private static final String SELECT_PAGOS_PEDIDO = "select id_pedido, status, valor_total from pedido as pe where pe.status = 'pago'";

    private static final String SELECT_CANCELADO = "select id_pedido, status from pedido where status = 'pendente'  and ate <= (?)";

    private static final String SELECT_MEUS_PEDIDOS = "select * from pedido where id_usuario = (?) order by id_pedido ";
    
    //private static final String SELECT_MEUS_PEDIDOS_ITENS = "select * from itemdecompra as i, produto as p where i.id_livro = p.id_livro and i.id_pedido = (?)";

    private static final String LIBERAR_CONTEUDO = "update pedido set status='liberado' where id_pedido = (?) ";

    private static final String CANCELAR_CONTEUDO = "update pedido set status='cancelado' where id_pedido = (?) ";

    private static final String INSERT_PEDIDO = "INSERT INTO pedido (id_usuario,status,de,ate,valor_total,codigo_boleto) VALUES (?,?,?,?,?,?)";

    private static final String SELECT_DADOS_IMPORTCAO_COM_PEDIDO = "select * from pedido as p , importacaoBoleto as i where p.codigo_boleto = i.codigo and i.status = 'N' and p.status = 'pendente'";

    private static final String TROCA_DE_STATUS = "update pedido set status = 'pago' where id_pedido = ?";

    private static final String SELECT_DADOS_PEDIDO_BOLETO = "select p.id_pedido, p.codigo_boleto,p.de,p.ate,p.valor_total from pedido as p where p.id_pedido = (?) and p.id_usuario = (?) and p.status ='pendente'";
    
    // metodo para Recuperar infomações do pedido para montar o boleto
    public ArrayList<Pedido> CriarDadosBoleto(Pedido pedido) {
        Connection conexao = null;
        ArrayList<Pedido> listaDePedido = new ArrayList<Pedido>();

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_DADOS_PEDIDO_BOLETO);
            pstmt.setInt(1, pedido.getId());
            pstmt.setInt(2, pedido.getUsuario().getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                CarrinhoDeCompra carrinho = new CarrinhoDeCompra();
                carrinho.setTotal(rs.getDouble("valor_total"));

                Pedido p = new Pedido();
                p.setId(rs.getInt("id_pedido"));
                p.setCodigo_boleto(rs.getString("codigo_boleto"));
                p.setUsuario(pedido.getUsuario());
                p.setCarrinho(carrinho);
                p.setDe(rs.getDate("de"));
                p.setAte(rs.getDate("ate"));

                listaDePedido.add(p);

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
        return listaDePedido;
    }

    public ArrayList<Pedido> TrocaDeStatusParaPagoPedido(ArrayList<Pedido> pendentes) throws SQLException {
        Connection conexao = null;

        ArrayList<Pedido> listaDeBoletoQueForamPagos = new ArrayList<Pedido>();
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(TROCA_DE_STATUS);

            for (Pedido p : pendentes) {
                pstmt.setInt(1, p.getId());
                pstmt.execute();

                //após trocar o status para pago adiciono o numero do boleto para depois validar o status para S
                //ou seja ex : pedido 1 foi pago guarda o numero do boleto abaixo para dps trocar o status
                Pedido pedidosimportados = new Pedido();
                pedidosimportados.setCodigo_boleto(p.getCodigo_boleto());
                listaDeBoletoQueForamPagos.add(pedidosimportados);
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
        return listaDeBoletoQueForamPagos;
    }
    
    
    
    

    public ArrayList<Pedido> ImportacaoComPedidoPago() {
        Connection conexao = null;
        ArrayList<Pedido> listaDePedido = new ArrayList<Pedido>();

        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_DADOS_IMPORTCAO_COM_PEDIDO);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setCodigo_boleto(rs.getString("codigo_boleto"));

                listaDePedido.add(pedido);

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
        return listaDePedido;
    }

    
    
    
    
    //TAREFA - TRAS UMA LISTA DE TODOS OS PEDIDOS E SEUS ITENS POR DETERMINADO PEDIDO
    //EX: PEDIDO 1 TEM 5 ITENS ENTÃO IRA FICA PEDIDO 1 ITENS1,2,3,4,5 E O PROX VAI PRO PEDIDO 2 ...
    public ArrayList<Pedido> listarPagos() {
        Connection conexao = null;
        ArrayList<Pedido> listaPago = new ArrayList<Pedido>();
        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmtP = conexao.prepareStatement(SELECT_PAGOS_PEDIDO);
            ResultSet rsP = pstmtP.executeQuery();

            while (rsP.next()) {

                Pedido pedidoPago = new Pedido();
                pedidoPago.setId(rsP.getInt("id_pedido"));
                pedidoPago.setStatus(rsP.getString("status"));

                ItemDeCompraDAO itemdao = new ItemDeCompraDAO();
                ArrayList<ItemDeCompra> lista_itensrecuperados = itemdao.SelecionaItensDoMeuPedido(pedidoPago);

                CarrinhoDeCompra carrinho = new CarrinhoDeCompra();
                carrinho.setTotal(rsP.getDouble("valor_total"));
                carrinho.setItens(lista_itensrecuperados);
                pedidoPago.setCarrinho(carrinho);

                UsuarioDAO usuariodao = new UsuarioDAO();
                Usuario dados_recuperados = usuariodao.RecuperaDadosUsuarioPorPedido(pedidoPago);
                pedidoPago.setUsuario(dados_recuperados);

                listaPago.add(pedidoPago);

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
        return listaPago;
    }

    //TAREFA 2º PARTE- RECEBE OS PEDIDOS PAGOS E MUDA O STATUS PARA LIBERADO
    public ArrayList<Pedido> TrocaDeStatusPagoParaLiberado(ArrayList<Pedido> pendentes) throws SQLException {
        Connection conexao = null;
        ArrayList<Pedido> listaPedidosLiberado = new ArrayList<Pedido>();
        try {

            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(LIBERAR_CONTEUDO);

            for (Pedido p : pendentes) {

                pstmt.setInt(1, p.getId());
                pstmt.execute();
                
                listaPedidosLiberado.add(p);
                
                
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
        return listaPedidosLiberado;
    }

    // TAREFA CANCELAR - TRAS UMA LISTA DE TODOS O PEDIDOS QUE NA DATA DE "HOJE" ESTAO PENDENTE
    public ArrayList<Pedido> listarCancelados(Date hoje) {
        Connection conexao = null;
        ArrayList<Pedido> listaCancelados = new ArrayList<Pedido>();
        
        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_CANCELADO);
            pstmt.setDate(1, hoje);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Pedido pedidoCancelado = new Pedido();
                pedidoCancelado.setId(rs.getInt("id_pedido"));
                pedidoCancelado.setStatus(rs.getString("status"));

                UsuarioDAO usuario = new UsuarioDAO();
                Usuario resultado_usuario = usuario.RecuperaDadosUsuarioPorPedido(pedidoCancelado);

                pedidoCancelado.setUsuario(resultado_usuario);

                listaCancelados.add(pedidoCancelado);

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
        
        return listaCancelados;
    }

    //TAREFA CANCELAR 2º PARTE - ALTERAR STATUS DO RESULTADO DA LISTA ACIMA PARA CANCELADA 
    public void CancelarConteudo(ArrayList<Pedido> pedidosCancelar) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(CANCELAR_CONTEUDO);

            for (Pedido p : pedidosCancelar) {

                pstmt.setInt(1, p.getId());
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

    //Controle carrinho - cria o pedido de vendas e retorna o id para passar para itens de compra.
    public Pedido CriarPedido(Pedido pedido) {
        Connection conexao = null;
        try {
            conexao = ConectaBanco.getConexao();
            PreparedStatement pstmt = conexao.prepareStatement(INSERT_PEDIDO, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1, pedido.getUsuario().getId());
            pstmt.setString(2, "pendente");
            pstmt.setDate(3, (Date) pedido.getDe());
            pstmt.setDate(4, (Date) pedido.getAte());
            pstmt.setDouble(5, pedido.getCarrinho().getTotal());
            pstmt.setString(6, pedido.getCodigo_boleto());
            pstmt.execute();

            ResultSet id = pstmt.getGeneratedKeys();
            id.next();
            int id_pedido = id.getInt(1);
            pedido.setId(id_pedido);

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

        return pedido;
    }

    //Tras todos os pedidos do usuario.
    //divido em 2 select um tras o numero do pedido e outro os seus respectivos itens (uma parte esta no motodo do itensdecompraDAO)
    //ex pedido 1 tem 5 itens .. ou seja 1(1,2,3,4,5)
    //INICIO RECUPERA NO BANCO TODOS OS PEDIDOS QUE EXISTEM E VAI ATE A CLASE ITEM DE COMPRA DAO
    public ArrayList<Pedido> MeusPedidos(Usuario usuario) {
        Connection conexao = null;
        CarrinhoDeCompra carrinho = null;
        ArrayList<Pedido> meusPedidos = new ArrayList<Pedido>();
        try {

            conexao = ConectaBanco.getConexao();

            PreparedStatement pstmt = conexao.prepareStatement(SELECT_MEUS_PEDIDOS);
            pstmt.setInt(1, usuario.getId());
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {

                Pedido pedido = new Pedido();
                pedido.setId(rs.getInt("id_pedido"));
                pedido.setStatus(rs.getString("status"));

                ItemDeCompraDAO itens = new ItemDeCompraDAO();
                ArrayList<ItemDeCompra> listaDeItens = itens.SelecionaItensDoMeuPedido(pedido);

                carrinho = new CarrinhoDeCompra();

                carrinho.setItens(listaDeItens);

                pedido.setCarrinho(carrinho);

                meusPedidos.add(pedido);

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

        return meusPedidos;
    }

}
