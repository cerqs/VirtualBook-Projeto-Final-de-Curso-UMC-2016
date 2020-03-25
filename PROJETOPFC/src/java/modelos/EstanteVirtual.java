/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.ArrayList;

/**
 *
 * @author 11131103404
 */
public class EstanteVirtual {    
    
    private int id_estanteVirtual;
    private Usuario usuario;
    private Produto produto;
    private String status;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getId_estanteVirtual() {
        return id_estanteVirtual;
    }

    public void setId_estanteVirtual(int id_estanteVirtual) {
        this.id_estanteVirtual = id_estanteVirtual;
    }

  


    public ArrayList<Produto> VerificaSeExisteOLivroNasMinhasCompras(CarrinhoDeCompra carrinho, ArrayList<EstanteVirtual> listaDeTodosMeusLivros) {

        ArrayList<Produto> listaDeLivros = new ArrayList<Produto>();
        String nomeDoLivros = null;

        for (ItemDeCompra itens : carrinho.getItens()) {

            for (EstanteVirtual minhasCompras : listaDeTodosMeusLivros) {

                if (itens.getProduto().getTitulo().equals(minhasCompras.getProduto().getTitulo())) {
                   
                    Produto produto = new Produto();                     
                    nomeDoLivros = itens.getProduto().getTitulo();
                    produto.setTitulo(nomeDoLivros);
                    listaDeLivros.add(produto);

                }

            }

        }

        return listaDeLivros;
    }

}
