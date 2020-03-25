/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.sql.Date;
import java.text.DecimalFormat;
import java.util.GregorianCalendar;
import java.util.Random;

/**
 *
 * @author guilherme
 */
public class Pedido {

    private int id;
    private Usuario usuario;
    private String status;
    private Date de;
    private Date ate;
    private CarrinhoDeCompra carrinho;
    private String codigo_boleto;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getDe() {
        return de;
    }

    public void setDe(Date de) {
        this.de = de;
    }

    public Date getAte() {
        return ate;
    }

    public void setAte(Date ate) {
        this.ate = ate;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public CarrinhoDeCompra getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(CarrinhoDeCompra carrinho) {
        this.carrinho = carrinho;
    }

    public String getCodigo_boleto() {
        return codigo_boleto;
    }

    public void setCodigo_boleto(String codigo_boleto) {
        this.codigo_boleto = codigo_boleto;
    }

    public Pedido GerarBoleto(Pedido pedido) {

        //recupera o valor total e converte para string, decimal format para deixar o valor com 2 casas decimais        
        DecimalFormat df = new DecimalFormat("###,##0.00");
        String valor_total_convertido = df.format(pedido.getCarrinho().getTotal());

        // da um radom de até 9 digitos de 0 a 9 e converte para string
        Random r = new Random();

        int cod = r.nextInt(99999);
        String cod_boleto_parte1 = String.valueOf(cod);

        int cod2 = r.nextInt(99999);
        String cod_boleto_parte2 = String.valueOf(cod2);

        int cod3 = r.nextInt(99999);
        String cod_boleto_parte3 = String.valueOf(cod3);

        int cod4 = r.nextInt(99999);
        String cod_boleto_parte4 = String.valueOf(cod4);

        int cod5 = r.nextInt(99999);
        String cod_boleto_parte5 = String.valueOf(cod5);

        int cod6 = r.nextInt(999999);
        String cod_boleto_parte6 = String.valueOf(cod6);

        int cod7 = r.nextInt(9);
        String cod_boleto_parte7 = String.valueOf(cod7);

        int cod8 = r.nextInt(999);
        String cod_boleto_parte8 = String.valueOf(cod8);

        String cod_boleto_parte9 = "0000";

        
        //montando boleto numeros aleatorios e no final o valor da compra
        pedido.setCodigo_boleto(cod_boleto_parte1 + "." + cod_boleto_parte2 + " " + cod_boleto_parte3 + "."
                + cod_boleto_parte4 + " " + cod_boleto_parte5 + "." + cod_boleto_parte6 + " " + cod_boleto_parte7 + " "
                + cod_boleto_parte8 + cod_boleto_parte9 + valor_total_convertido.replace(",", ""));
        
        

        return pedido;
    }

    public Pedido CalculaDataDoPedido(Pedido pedido) {

        //pega a data de hoje (sistema) joga na variavel "de"
        GregorianCalendar data_hoje = new GregorianCalendar();
        data_hoje.getTime();
        java.sql.Date de = new java.sql.Date(data_hoje.getTime().getTime());

        //pega a data de hoje(sistema) + 5 dias a frente, joga na variavel "ate"
        GregorianCalendar hoje_5 = new GregorianCalendar();
        hoje_5.add(hoje_5.DAY_OF_MONTH, 5);
        java.sql.Date ate = new java.sql.Date(hoje_5.getTime().getTime());
        
        pedido.setDe(de);
        pedido.setAte(ate);
        
        return pedido;

    }

}
