/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.PerguntaDAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.AlternativaModel;
import model.PerguntaModel;
import model.QuestionarioModel;
import view.controllerView.TelaPerguntasViewController;

/**
 *
 * @author User
 */
public class PerguntaController {

    private final PerguntaDAO perguntaDAO;
    private PerguntaModel perguntaModel;
    private final AlternativaController alternativaController;
    private ResultSet rs;

    public PerguntaController(){
        perguntaDAO = new PerguntaDAO();
        System.out.println("INSTANCIOU PERGUNTA DAO");
        alternativaController = new AlternativaController();
        System.out.println("INSTANCIOU ALTERNATIVA CONTROLLER");
    }

    public List<PerguntaModel> carregarPerguntas(QuestionarioModel questionarioModel, int qtdePerguntas) throws Exception {
        List<PerguntaModel> listaPerguntas = new ArrayList<>();
        rs = perguntaDAO.carregarPerguntas(questionarioModel.getCodCategoria(), qtdePerguntas);

        while (rs.next()) {
            perguntaModel = new PerguntaModel();
            perguntaModel.setCodigoPergunta(rs.getInt(1));
            perguntaModel.setDescricao(rs.getString(2));
            perguntaModel.setCodigoCategoria(rs.getInt(3));
            System.out.println(perguntaModel.getCodigoCategoria() + " " + perguntaModel.getDescricao());
            perguntaModel.adicionarAlternativas(alternativaController.carregarAlternativas(perguntaModel));
            listaPerguntas.add(perguntaModel);
        }
        return listaPerguntas;
    }
    
    public AlternativaModel preencherNovaPergunta(PerguntaModel pergunta, List<AlternativaModel> alternativas, TelaPerguntasViewController telaPerguntasViewController) {
        telaPerguntasViewController.getTxtAreaPergunta().setText(pergunta.getDescricao());
        Collections.shuffle(alternativas);
        telaPerguntasViewController.getBtn01().setText(alternativas.get(0).getAlternativa());
        telaPerguntasViewController.getBtn02().setText(alternativas.get(1).getAlternativa());
        telaPerguntasViewController.getBtn03().setText(alternativas.get(2).getAlternativa());
        telaPerguntasViewController.getBtn04().setText(alternativas.get(3).getAlternativa());

        for (AlternativaModel alternativa : alternativas) {
            
            if (alternativa.getSituacao() == 1) {
                
                return alternativa;
            }
        }
        return pergunta.getAlternativas().get(0);
    }

    public boolean verificarResposta(AlternativaModel altCorreta, String resposta) {
        return altCorreta.getAlternativa().equals(resposta);
    }
}
