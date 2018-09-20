/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.QuestionarioDAO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import model.AlternativaModel;
import model.EnumCategoria;
import model.PerguntaModel;
import model.QuestionarioModel;
import view.controllerView.TelaPerguntasViewController;

/**
 *
 * @author User
 */
public class QuestionarioController {  
    private QuestionarioDAO questionarioDAO;
    private QuestionarioModel questionarioModel;
    private PerguntaController perguntaController;
    private ResultSet rs;
    int pontoAtual = 0;
    
    private static final int numeroPerguntas = 10;
    
    public QuestionarioController(PerguntaController perguntaController){
        questionarioDAO = new QuestionarioDAO();
        this.perguntaController = perguntaController;
    }
    
    public QuestionarioController(){};
    
    public void carregarQuestionario(int codigoCategoria) throws Exception {
        rs = questionarioDAO.carregarQuestionario(criarCodAleatorio());
        while(rs.next()){
            questionarioModel = new QuestionarioModel();
            questionarioModel.setCodQuestionario(rs.getString(1));
            questionarioModel.setCodCategoria(codigoCategoria);
        }
        questionarioModel.adicionarPerguntas(perguntaController.carregarPerguntas(questionarioModel, numeroPerguntas));
    }

    public QuestionarioModel iniciarQuestionario() throws Exception {        
        return this.questionarioModel;
    }
    
    public String salvarQuestionario() throws SQLException {
        System.out.println(questionarioModel);
        questionarioDAO.salvarQuestionario(questionarioModel);        
        return Integer.toString(pontoAtual);
    }
    
    public void calcularPontuacao(int i, int indice, TelaPerguntasViewController telaPerguntasViewController) {
        int pontuacao;
        if (i > 1800) {
            pontuacao = (int) Math.ceil(50);
        } else if (i < 300) {
            pontuacao = (int) Math.ceil(10);
        } else {
            pontuacao = (int) Math.ceil(((i + 200) / 100) * 2.5);
        }
        pontoAtual += pontuacao;
        telaPerguntasViewController.getLblScore().setText(String.valueOf(pontoAtual));
        questionarioModel.recuperarPergunta(indice).setPontuacaoObtida(pontuacao);
    }
    
    public String getNomeCategoria(){
        return EnumCategoria.values()[this.questionarioModel.getCodCategoria()].name();       
    }
    
    public int getCodCategoria(){
        return this.questionarioModel.getCodCategoria(); 
    }
    
    private String criarCodAleatorio() {
        String alphaNumerics = "QWERTYUIOPASDFGHJKLZXCVBNM1234567890";
        String codigo = "";
        for (int i = 0; i < 8; i++) {
            codigo += alphaNumerics.charAt((int) (Math.random() * alphaNumerics.length()));
        }
        return codigo;
    }
    
    public PerguntaModel getNovaPergunta(int index){
        System.out.println(this.questionarioModel);
        return this.questionarioModel.recuperarPergunta(index);
    }
    
    public List<AlternativaModel> getAlternativasNovaPergunta(int index){
        return this.getNovaPergunta(index).getAlternativas();
    }
    
    public QuestionarioModel preencherNovoQuestionario(String codQuestionario, int codCategoria, int pontuacao){
        QuestionarioModel novoQuestionario = new QuestionarioModel();
        novoQuestionario.setCodQuestionario(codQuestionario);
        novoQuestionario.setCodCategoria(codCategoria);
        novoQuestionario.setPontuacao(pontuacao);
        
        return novoQuestionario;
    }
}
