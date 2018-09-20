/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import database.Database;
import database.DatabaseFactory;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import model.PerguntaModel;

/**
 *
 * @author User
 */
public class AlternativaDAO {
    private String selectSql;
    private static final Database database = DatabaseFactory.openDatabaseSQLServer();
    private static final Connection connection = database.conectar();
    
    public ResultSet carregarAlternativas(PerguntaModel pergunta) throws Exception {
        try {
            selectSql = "SELECT Alternativa.codAlternativa,  Alternativa.alternativa, Alternativa.situacao "
                    + "FROM Pergunta INNER JOIN Alternativa ON "
                    + "Alternativa.codPergunta = Pergunta.codPergunta WHERE Pergunta.codPergunta = " + pergunta.getCodigoPergunta();
            
            PreparedStatement stmt = connection.prepareStatement(selectSql);
            ResultSet rs = stmt.executeQuery();
            
            return rs;
            
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("ERRO DE BANCO DE DADOS");
            alert.setTitle("SQL SERVER");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
        return null;
    }
}
