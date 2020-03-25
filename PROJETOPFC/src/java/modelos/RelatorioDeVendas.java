/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Date;

/**
 *
 * @author 11131103404
 */
public class RelatorioDeVendas {
    
    private Produto produto;
    private double total_vendido;
    private int quantidade_vendido;
    private Date de;
    private Date ate;

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getTotal_vendido() {
        return total_vendido;
    }

    public void setTotal_vendido(double total_vendido) {
        this.total_vendido = total_vendido;
    }

    public int getQuantidade_vendido() {
        return quantidade_vendido;
    }

    public void setQuantidade_vendido(int quantidade_vendido) {
        this.quantidade_vendido = quantidade_vendido;
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
   
   
    
    
    
}
