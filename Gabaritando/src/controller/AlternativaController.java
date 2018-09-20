/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import DAO.AlternativaDAO;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import model.AlternativaModel;
import model.PerguntaModel;

/**
 *
 * @author User
 */
public class AlternativaController {

    private AlternativaModel alternativaModel;
    private final AlternativaDAO alternativaDAO;
    private ResultSet rs;
    private List<AlternativaModel> listaAlternativas;

    public AlternativaController() {
        alternativaDAO = new AlternativaDAO();
    }

    public List<AlternativaModel> carregarAlternativas(PerguntaModel perguntaModel) throws Exception {
        listaAlternativas = new ArrayList<>();
        rs = alternativaDAO.carregarAlternativas(perguntaModel);
        while (rs.next()) {
            alternativaModel = new AlternativaModel();
            alternativaModel.setCodAlternativa(rs.getInt(1));
            alternativaModel.setCodPergunta(perguntaModel.getCodigoPergunta());
            alternativaModel.setAlternativa(rs.getString(2));
            alternativaModel.setSituacao(rs.getInt(3));
            System.out.println("    "+alternativaModel.getAlternativa());
            listaAlternativas.add(alternativaModel);
        }
        return listaAlternativas;
    }

}
