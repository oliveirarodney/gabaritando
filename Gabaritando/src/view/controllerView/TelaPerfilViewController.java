/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controllerView;

import com.jfoenix.controls.JFXButton;
import controller.UsuarioController;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import model.EnumCategoria;
import model.QuestionarioModel;
import model.UsuarioSingleton;
import utils.BorderPaneRootSingleton;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TelaPerfilViewController implements Initializable {

    @FXML
    private Label lblUserName;
    @FXML
    private Label lblQtdeQuizesPublicosJogados;
    @FXML
    private Label lblTotalScore;
    @FXML
    private Label lblQuizOrdenar;
    @FXML
    private Label lblScoreOrdenar;
    @FXML
    private Label lblCategoriaOrdenar;
    @FXML
    private GridPane gpQuizesJogados;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblNome;
    /**
     * Initializes the controller class.
     */
    private UsuarioSingleton usuario = UsuarioSingleton.getInstancia();
    private UsuarioController usuarioController;
    private BorderPane bpRoot;
    private int totalPontuacao = 0;
    @FXML
    private JFXButton btnEditarPerfil;
    @FXML
    private JFXButton btnExcluirConta;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bpRoot = BorderPaneRootSingleton.getInstancia();
        this.usuarioController = new UsuarioController();

        this.lblUserName.setText(usuario.getUsername());
        this.lblEmail.setText(usuario.getEmail());
        this.lblNome.setText(usuario.getNome());
        try {
            carregarQuizesJogados();
        } catch (SQLException ex) {
            Logger.getLogger(TelaPerfilViewController.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.lblQtdeQuizesPublicosJogados.setText(String.valueOf(UsuarioSingleton.getInstancia().getQuestionariosJogados().size()));

        for (QuestionarioModel questionariosJogado : UsuarioSingleton.getInstancia().getQuestionariosJogados()) {
            totalPontuacao += questionariosJogado.getPontuacao();
        }

        this.lblTotalScore.setText(String.valueOf(totalPontuacao));
    }

    @FXML
    private void handleEditarPerfil(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/FXML/TelaEditarPerfil.fxml"));
        Parent telaEditarPerfil = loader.load();

        bpRoot.setCenter(telaEditarPerfil);
    }

    private void carregarQuizesJogados() throws SQLException {

        this.gpQuizesJogados.getChildren().clear();

        this.usuarioController.carregarQuestionariosJogados();

        Label quiz;
        Label categoria;
        Label score;

        int line = 0;
        for (QuestionarioModel q : UsuarioSingleton.getInstancia().getQuestionariosJogados()) {
            quiz = new Label();
            quiz.setAlignment(Pos.CENTER);
            quiz.setPrefHeight(30);
            quiz.setMinWidth(308);
            quiz.setFont(Font.font("Century Gothic", 18));

            categoria = new Label();
            categoria.setAlignment(Pos.CENTER);
            categoria.setPrefHeight(30);
            categoria.setMinWidth(308);
            categoria.setFont(Font.font("Century Gothic", 18));

            score = new Label();
            score.setAlignment(Pos.CENTER);
            score.setPrefHeight(30);
            score.setMinWidth(308);
            score.setFont(Font.font("Century Gothic", 18));

            quiz.setText(q.getCodQuestionario());
            categoria.setText(EnumCategoria.values()[q.getCodCategoria()].name());
            score.setText(String.valueOf(q.getPontuacao()));
            gpQuizesJogados.add(quiz, 0, line);
            gpQuizesJogados.add(categoria, 1, line);
            gpQuizesJogados.add(score, 2, line);
            line++;
        }
    }

    @FXML
    private void handleExcluirConta(MouseEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("Confirmação de exclusão");
        alert.setHeaderText("Sua conta será excluída, assim como o seu progresso.");
        alert.setContentText("Deseja realmente excluir o perfil?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            usuarioController.excluirPerfil();
            alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle(null);
            alert.setContentText("USUARIO EXCLUÍDO COM SUCESSO!!\nPROGRAMA SERÁ ENCERRADO.");
            alert.showAndWait();
            exit(0);
        } 
    }
}
