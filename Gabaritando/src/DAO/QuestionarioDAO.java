/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import controller.AutenticacaoController;
import database.Database;
import database.DatabaseFactory;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.scene.control.Alert;
import model.PerguntaModel;
import model.QuestionarioModel;
import model.UsuarioSingleton;

/**
 *
 * @author maykh
 */
public class QuestionarioDAO {
    private String insertSql, selectSql;
    private final UsuarioSingleton usuario = UsuarioSingleton.getInstancia();
    private static final Database database = DatabaseFactory.openDatabaseSQLServer();
    private static final Connection connection = database.conectar();

    
    int indice = 0;
    int score = 0;
    double pontuacaoPergunta = 0;
    
    public ResultSet carregarQuestionario(String codigo) throws Exception {
        try {
            insertSql = "INSERT INTO Quiz (codQuiz) VALUES (?)";
            PreparedStatement stmt = connection.prepareStatement(insertSql);
            stmt.setString(1, codigo);
            stmt.executeUpdate();
            
            selectSql = "SELECT * FROM Quiz WHERE codQuiz = '" + codigo + "'";
            stmt = connection.prepareStatement(selectSql);
            ResultSet rs = stmt.executeQuery();
            return rs;
            
        }catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("ERRO DE BANCO DE DADOS");
            alert.setTitle("SQL SERVER");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
        return null;
    }
    
    public void salvarQuestionario(QuestionarioModel questionario) throws SQLException {
        try {

            for (PerguntaModel object : questionario.getQuestionario()) {
                pontuacaoPergunta = object.getPontuacaoObtida();
            
                insertSql = "INSERT INTO perguntasrespondidas (codUsuario, codQuiz, codPergunta, respostaUsuario, pontuacaoPergunta) VALUES (?, ?, ?, ?, ?)";
                PreparedStatement stmt = connection.prepareStatement(insertSql);

                System.out.println(usuario.getCodUsuario());
                stmt.setInt(1, usuario.getCodUsuario());
                stmt.setString(2, questionario.getCodQuestionario());
                stmt.setInt(3, object.getCodigoPergunta());
                stmt.setInt(4, object.getAlternativaResp());
                stmt.setDouble(5, pontuacaoPergunta);
                stmt.execute();
            }
            
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("ERRO DE BANCO DE DADOS");
            alert.setTitle("SQL SERVER");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }
}
