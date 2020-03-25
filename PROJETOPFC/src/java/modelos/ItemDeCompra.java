/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

/**
 *
 * @author 11131103404
 */
public class ItemDeCompra {

    private Integer id;
    private Produto produto;    
    private double total;
    

    public void setTotal(double total) {
        this.total = total;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public double getTotal() {

        this.total =  this.produto.getPreco();
        return total;

    }
    
    public double getTotalRelatorio() {
        return total;
    }
    
    
    
    

}
