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
public class PerguntaModel {

    protected int codPergunta;
    protected int codCategoria;
    private String descricao;
    private int alternativaResp;
    private List<AlternativaModel> listaAlternativas = new ArrayList<>();//IMPLEMENTAR GET/SET 
    protected int pontuacaoObtida;
    
    public int getCodigoCategoria() {
        return codCategoria;
    }
    
    public void setCodigoCategoria(int codigoCategoria) {
        this.codCategoria = codigoCategoria;
    }    
    
    public int getCodigoPergunta() {
        return codPergunta;
    }

    public void setCodigoPergunta(int codigoPergunta) {
        this.codPergunta = codigoPergunta;
    } 
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getPontuacaoObtida() {
        return pontuacaoObtida;
    }

    public void setPontuacaoObtida(int pontuacaoObtida) {
        this.pontuacaoObtida = pontuacaoObtida;
    }
    
    public void adicionarAlternativas(List<AlternativaModel> alternativa) {
        for (AlternativaModel alternativaModel : alternativa) {
            listaAlternativas.add(alternativaModel);
        }    
    }
        
    public List<AlternativaModel> getAlternativas(){
        return this.listaAlternativas;
    }
    
    public int getAlternativaResp() {
        return alternativaResp;
    }

    public void setAlternativaResp(int alternativaResp) {
        this.alternativaResp = alternativaResp;
    }
    
}
