/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.UsuarioDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.QuestionarioModel;
import model.UsuarioModel;
import model.UsuarioSingleton;
import utils.exceptions.EmailException;
import utils.exceptions.SenhaException;

/**
 *
 * @author User
 */
public class UsuarioController {

    private UsuarioModel usuario;
    private final UsuarioSingleton usuarioSingleton = UsuarioSingleton.getInstancia();
    
    private final UsuarioDAO usuarioDAO = new UsuarioDAO();
    private final QuestionarioController questionarioController = new QuestionarioController( );

    public void cadastrarUsuario(String nome, String username, String email,
            String confirmarEmail, String password, String confirmarPassword)
            throws EmailException, SenhaException, SQLException {

        usuario = new UsuarioModel();
        usuario.setNome(nome);
        usuario.setUsername(username);

        if (email.equals(confirmarEmail)) {
            usuario.setEmail(email);
        } else {
            throw new EmailException();
        }

        if (password.equals(confirmarPassword)) {
            usuario.setSenha(password);
        } else {
            throw new SenhaException();
        }

        try {
            usuarioDAO.save(usuario);
        } catch (SQLException ex) {
            throw ex;
        }
    }

    public void editarPerfil(String nome, String username, String email,
            String confirmarEmail, String password, String confirmarPassword)
            throws EmailException, SenhaException, SQLException {

        usuario = new UsuarioModel();
        usuario.setNome(nome);
        usuario.setUsername(username);

        if (email.equals(confirmarEmail)) {
            usuario.setEmail(email);
        } else {
            throw new EmailException();
        }

        try {
            usuarioDAO.update(usuario);
        } catch (SQLException ex) {
            throw ex;
        }
        System.out.println("Dados alterados!!");

    }
    
    public void carregarQuestionariosJogados() throws SQLException{
        ResultSet rs = usuarioDAO.carregarQuizesJogados();
        this.limparQuestionariosJogados();
        while (rs.next()) {
            this.adicionarQuestionarioJogado(questionarioController.preencherNovoQuestionario(rs.getString(1), rs.getInt(2), rs.getInt(3)));
        }
    }
    
    private void adicionarQuestionarioJogado(QuestionarioModel e){
        usuarioSingleton.getQuestionariosJogados().add(e);
    }
    
    private void limparQuestionariosJogados(){
        usuarioSingleton.getQuestionariosJogados().clear();
    }
    
    public void excluirPerfil(){
        usuarioDAO.excluirUsuario();
        
    }
    
}
