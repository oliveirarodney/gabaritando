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
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.EnumCategoria;
import model.QuestionarioModel;
import model.UsuarioSingleton;
import utils.BorderPaneRootSingleton;

/**
 * FXML Controller class
 *
 * @author maykh
 */
public class CategoriasJogoViewController implements Initializable {

    @FXML
    private JFXButton btnCategoriaCiencias;
    
    private BorderPane bpRoot;
    private int codigoCategoria;
    private QuestionarioController questionarioController;
    private QuestionarioModel questionarioModel;
    private UsuarioSingleton usuario = UsuarioSingleton.getInstancia();

    private PerguntaController perguntaController;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        this.bpRoot = BorderPaneRootSingleton.getInstancia();
        perguntaController = new PerguntaController();
        questionarioController = new QuestionarioController(perguntaController);
               
        System.out.println("INSTANCIOU QUESTIONARIO CONTROLLER");
    }    

    @FXML
    private void handleIniciarQuiz(MouseEvent event) throws IOException, Exception {
        Button botaoClicado = (Button) event.getSource();
        codigoCategoria = EnumCategoria.valueOf(botaoClicado.getText()).ordinal();
        System.out.println(codigoCategoria);
        System.out.println(questionarioController.toString());
        questionarioController.carregarQuestionario(codigoCategoria);
        System.out.println("CARREGOU QUESTIONARIO");
        FXMLLoader loader = new FXMLLoader();
        loader.setController(new TelaPerguntasViewController(questionarioController, perguntaController));
        loader.setLocation(getClass().getClassLoader().getResource("view/FXML/TelaPerguntas.fxml"));
        Parent telaPerguntasJogo = loader.load();
        
        System.out.println(usuario);
        System.out.println(bpRoot);
        bpRoot.setCenter(telaPerguntasJogo);
        bpRoot.setRight(null);

        System.out.println("Nome Categoria: " + botaoClicado.getText());
        System.out.println("Enum: " + codigoCategoria);
        System.out.println("Jogador: " + UsuarioSingleton.getInstancia().getNome());
    }
    
    public QuestionarioModel getQuestionarioCarregado(){
        return this.questionarioModel;
    }
  
}
