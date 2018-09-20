/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author maykh
 */
public class AlternativaModel {
    
    private int codAlternativa;
    private int codPergunta;
    private String descricao;
    private int situacao = 0;

    public int getCodAlternativa() {
        return codAlternativa;
    }

    public void setCodAlternativa(int codAlternativa) {
        this.codAlternativa = codAlternativa;
    }

    public String getAlternativa() {
        return descricao;
    }

    public void setAlternativa(String alternativa) {
        this.descricao = alternativa;
    }

    public int getCodPergunta() {
        return codPergunta;
    }

    public void setCodPergunta(int codPergunta) {
        this.codPergunta = codPergunta;
    }

    public int getSituacao() {
        return situacao;
    }

    public void setSituacao(int situacao) {
        this.situacao = situacao;
    }
    
}
