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
import model.QuestionarioModel;

/**
 *
 * @author User
 */
public class PerguntaDAO {
    private String selectSql;
    private static final Database database = DatabaseFactory.openDatabaseSQLServer();
    private static final Connection connection = database.conectar();

    
    public ResultSet carregarPerguntas(int codCategoria, int qtdePerguntas) throws Exception {
        try {
            selectSql = "SELECT TOP "+ qtdePerguntas +" Pergunta.codPergunta, Pergunta.descricao, Pergunta.codCategoria "
                    + "FROM Pergunta "
                    + "WHERE Pergunta.codCategoria = " + codCategoria
                    + "ORDER BY NEWID()";

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
