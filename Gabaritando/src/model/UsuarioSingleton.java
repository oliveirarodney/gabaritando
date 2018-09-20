/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author maykh
 */
public class UsuarioSingleton {
    
    private static UsuarioSingleton instancia;
    private int codUsuario;
    private String nome;
    private String email;
    private String username;
    private String senha;
    private List<QuestionarioModel> questionariosJogados = new ArrayList<>();
 
    private UsuarioSingleton() {
    }
    
    public static UsuarioSingleton getInstancia() {
        if (instancia == null)
            instancia = new UsuarioSingleton();
        return instancia;
    }

    public int getCodUsuario() {
        return codUsuario;
    }

    public void setCodUsuario(int codUsuario) {
        this.codUsuario = codUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public List<QuestionarioModel> getQuestionariosJogados() {
        return questionariosJogados;
    }

    public void setQuestionariosJogados(List<QuestionarioModel> questionariosJogados) {
        this.questionariosJogados = questionariosJogados;
    }
}
