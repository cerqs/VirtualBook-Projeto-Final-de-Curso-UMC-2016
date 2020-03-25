/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.util.Random;

/**
 *
 * @author 11131103404
 */
public class Login {

    private int id;
    private String login;
    private String senha;
    private PerfilAcesso perfil;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public PerfilAcesso getPerfil() {
        return perfil;
    }

    public void setPerfil(PerfilAcesso perfil) {
        this.perfil = perfil;
    }

    public Login ResetDeSenha(Login login) {

        Random gerador = new Random();
        int gerandoSenha = gerador.nextInt(9999999);
        String senha = String.valueOf(gerandoSenha);
        login.setSenha(senha);
        
        return login;

    }

}
