/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controllerView;

import com.jfoenix.controls.JFXButton;
import controller.PerguntaController;
import controller.QuestionarioController;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import utils.BorderPaneRootSingleton;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TelaFimQuestionarioViewController implements Initializable {

    @FXML
    private Label lblScore;
    @FXML
    private JFXButton btnJogarNovamente;
    @FXML
    private JFXButton btnSair;

    private QuestionarioController questionarioController;
    private final TelaPerguntasViewController telaPerguntasViewController;
    private PerguntaController perguntaController;
    private final BorderPane bpRoot = BorderPaneRootSingleton.getInstancia();

    /**
     * Initializes the controller class.
     *
     * @param questionarioController
     * @param telaPerguntasViewController
     */
    public TelaFimQuestionarioViewController(QuestionarioController questionarioController, TelaPerguntasViewController telaPerguntasViewController) {
        this.questionarioController = questionarioController;
        this.telaPerguntasViewController = telaPerguntasViewController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.lblScore.setText(questionarioController.salvarQuestionario());
        } catch (SQLException ex) {
            Logger.getLogger(TelaFimQuestionarioViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleJogarNovamente(MouseEvent event) throws IOException, Exception {
        int codCategoria = questionarioController.getCodCategoria();
        perguntaController = new PerguntaController();
        questionarioController = new QuestionarioController(perguntaController);
        questionarioController.carregarQuestionario(codCategoria);
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/FXML/TelaPerguntas.fxml"));
        loader.setController(new TelaPerguntasViewController(questionarioController, perguntaController));
        Parent telaPerguntasJogo = loader.load();

        bpRoot.setCenter(null);
        bpRoot.setCenter(telaPerguntasJogo);
    }

    @FXML
    private void handleSair(MouseEvent event) throws IOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/FXML/TelaCategoriasJogo.fxml"));
        Parent telaCategorias;
        telaCategorias = loader.load();
        bpRoot.setCenter(null);
        bpRoot.setCenter(telaCategorias);
        bpRoot.setRight(null);
    }

}
