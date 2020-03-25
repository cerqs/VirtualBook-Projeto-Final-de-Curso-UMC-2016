/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author 11131103404
 */
public class CarrinhoDeCompra {

    private Integer id;
    private List<ItemDeCompra> itens;
    private double total;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public Integer getId() {
        return id;
    }

    public void setItens(List<ItemDeCompra> itens) {
        this.itens = itens;
    }

    public List<ItemDeCompra> getItens() {
        return itens;
    }

    public double getTotal() {
        return total;
    }

    public void addNovoItem(ItemDeCompra item) {
        if (this.itens == null) {
            this.itens = new ArrayList<ItemDeCompra>();
        }

        this.itens.add(item);
    }

    public void removerItem(ItemDeCompra itemRemove) {

        for (int i = 0; i < this.itens.size(); i++) {
            if (this.itens.get(i).getProduto().getId() == itemRemove.getProduto().getId()) {
                this.itens.remove(i);
            }

        }

    }

    public double calculaTotal() {
        double vtotal = 0;
        for (ItemDeCompra item : this.itens) {
            vtotal += item.getTotal();
        }
        this.total = vtotal;
        return total;
    }

    public boolean verificaSeProdutoJaExisteNoCarrinho(CarrinhoDeCompra carrinho, Produto produto) {
        
        boolean existe = false;
        
        if (carrinho.getItens() != null) {
            for (ItemDeCompra item : carrinho.getItens()) {
                if (item.getProduto().getId() == produto.getId()) {
                    existe = true;
                }
            }
        }
        
        return existe;
    }

}
