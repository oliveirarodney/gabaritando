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
import model.UsuarioModel;
import model.UsuarioSingleton;

/**
 *
 * @author maykh
 */
public class UsuarioDAO {

    private static UsuarioSingleton usuario = UsuarioSingleton.getInstancia();

    private String insertSql;
    private String selectSql;
    private String updateSql;
    private String deleteSql;

    private static final Database database = DatabaseFactory.openDatabaseSQLServer();
    private static final Connection connection = database.conectar();

    //CREATE
    public void save(UsuarioModel usuario) throws SQLException {
        insertSql = "INSERT INTO Usuario VALUES(?, ?, ?, ?)";

        PreparedStatement stmt = connection.prepareStatement(insertSql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getUsername());
        stmt.setString(3, usuario.getEmail());
        stmt.setString(4, usuario.getSenha());
        stmt.executeUpdate();
    }

    public void update(UsuarioModel usuario) throws SQLException {
        updateSql = "UPDATE Usuario SET "
                + "nome = ?, "
                + "userName = ?, "
                + "email = ? "
                + "WHERE Usuario.codUsuario = ?";

        PreparedStatement stmt = connection.prepareStatement(updateSql);
        stmt.setString(1, usuario.getNome());
        stmt.setString(2, usuario.getUsername());
        stmt.setString(3, usuario.getEmail());
        stmt.setInt(4, UsuarioSingleton.getInstancia().getCodUsuario());

        stmt.executeUpdate();

        UsuarioDAO.refreshUsuarioSingleton();
    }

    private static void refreshUsuarioSingleton() {
        String refreshSql;
        try {
            refreshSql = "SELECT codUsuario,nome,userName,email,senha FROM Usuario"
                    + " WHERE Usuario.codUsuario =" + UsuarioSingleton.getInstancia().getCodUsuario();
            PreparedStatement stmt = connection.prepareStatement(refreshSql);
            ResultSet rs = stmt.executeQuery();
            rs.next();
            usuario.setCodUsuario(rs.getInt(1));
            usuario.setNome(rs.getString(2));
            usuario.setUsername(rs.getString(3));
            usuario.setEmail(rs.getString(4));
            usuario.setSenha(rs.getString(5));

        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("ERRO DE BANCO DE DADOS");
            alert.setTitle("SQL SERVER");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    public ResultSet carregarQuizesJogados() {
        try {
            selectSql = "SELECT Quiz.codQuiz, Categoria.codCategoria, SUM(PerguntasRespondidas.pontuacaoPergunta)"
                    + " FROM Pergunta INNER JOIN Categoria ON Pergunta.codCategoria = Categoria.codCategoria"
                    + " INNER JOIN PerguntasRespondidas ON Pergunta.codPergunta = PerguntasRespondidas.codPergunta"
                    + " INNER JOIN Quiz ON Quiz.codQuiz = PerguntasRespondidas.codQuiz"
                    + " INNER JOIN Usuario ON PerguntasRespondidas.codUsuario = Usuario.codUsuario"
                    + " GROUP BY Quiz.codQuiz, Categoria.codCategoria, Usuario.codUsuario"
                    + " HAVING Usuario.codUsuario =" + UsuarioSingleton.getInstancia().getCodUsuario();

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

    public void excluirUsuario() {

        try {
            deleteSql = "DELETE FROM PerguntasRespondidas WHERE codUsuario = ?"                    
                    + " DELETE FROM Usuario WHERE codUsuario = ?";
            PreparedStatement smt = connection.prepareStatement(deleteSql);
            smt.setInt(1, usuario.getCodUsuario());
            smt.setInt(2, usuario.getCodUsuario());
            smt.executeUpdate();
        }catch(SQLException ex){
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("ERRO DE BANCO DE DADOS");
            alert.setTitle("SQL SERVER");
            alert.setContentText(ex.getMessage());
            alert.show();
        }

    }
}
