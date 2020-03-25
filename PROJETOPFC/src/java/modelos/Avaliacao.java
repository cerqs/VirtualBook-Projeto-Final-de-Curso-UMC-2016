/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author 11131103404
 */
public class Avaliacao {

    private Integer id;
    private Usuario usuario;
    private Produto produto;
    private String comentario;
    private Integer nota;
    private Date data_Comentario;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public Date getData_Comentario() {
        return data_Comentario;
    }

    public void setData_Comentario(Date data_Comentario) {
        this.data_Comentario = data_Comentario;
    }

    public Integer getNota() {
        return nota;
    }

    public void setNota(Integer nota) {
        this.nota = nota;
    }

    public Avaliacao calcularMedia(ArrayList<Avaliacao> avaliacao) {

        double resultado = 0;
        double auxNotas = 0;
        double auxTotalDeAvaliacoes = 0;

        for (Avaliacao media : avaliacao) {

            auxNotas = auxNotas + media.getNota();
        }

        auxTotalDeAvaliacoes = avaliacao.size();

        if (auxNotas == 0 || auxTotalDeAvaliacoes == 0) {
            resultado = 0;
        } else {
            resultado = auxNotas / auxTotalDeAvaliacoes;
        }

        //arrendodar a nota para mais
        double d = resultado;
        BigDecimal resultadoConvertido = new BigDecimal(d).setScale(0, RoundingMode.HALF_EVEN);

        //Converter bigdecimal para int para fazer o if abaixo
        int resultadoFinal = ((BigDecimal) resultadoConvertido).intValue();
        
        Avaliacao avaliacaoNota = new Avaliacao();
        avaliacaoNota.setNota(resultadoFinal);
        
        return avaliacaoNota;
    }

}
