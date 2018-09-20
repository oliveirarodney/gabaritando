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
public class QuestionarioModel {    

    private String codQuestionario;     
    private int codCategoria;  
    private int pontuacao;
    
    private List<PerguntaModel> questionario = new ArrayList<>();      

    public String getCodQuestionario() {
        return codQuestionario;
    }

    public void setCodQuestionario(String codQuestionario) {
        this.codQuestionario = codQuestionario;
    }

    public int getCodCategoria() {
        return codCategoria;
    }

    public void setCodCategoria(int codCategoria) {
        this.codCategoria = codCategoria;
    }  
    
    public List<PerguntaModel> getQuestionario() {
        return questionario;
    }

    public void adicionarPerguntas(List<PerguntaModel> perguntas) {
        for (PerguntaModel pergunta : perguntas) {
            questionario.add(pergunta);
        }
    }
    
    public PerguntaModel recuperarPergunta(int indice){
        return questionario.get(indice);
    }

    public int getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(int pontuacao) {
        this.pontuacao = pontuacao;
    }
}
