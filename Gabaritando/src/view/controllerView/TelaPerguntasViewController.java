/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controllerView;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextArea;
import controller.AlternativaController;
import controller.PerguntaController;
import controller.QuestionarioController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import model.AlternativaModel;
import model.EnumCategoria;
import model.PerguntaModel;
import model.QuestionarioModel;
import utils.BorderPaneRootSingleton;

/**
 * FXML Controller class
 *
 * @author maykh
 */
public class TelaPerguntasViewController implements Initializable {

    @FXML
    private JFXButton btn01;
    @FXML
    private JFXButton btn02;
    @FXML
    private JFXButton btn03;
    @FXML
    private JFXButton btn04;
    @FXML
    private ProgressBar contagemRegressiva;
    @FXML
    private Label lblQtdePerguntas;
    @FXML
    private Label lblPerguntaAtual;
    @FXML
    private Label lblScore;
    @FXML
    private Label lblCategoria;
    @FXML
    private ImageView imageUsarDica;
    @FXML
    private JFXButton btnProximaPergunta;
    @FXML
    private JFXButton btnPerguntaAnterior;
    @FXML
    private JFXTextArea txtAreaPergunta;

    /**
     * Initializes the controller class.
     */
    private QuestionarioController questionarioController;
    private final PerguntaController perguntaController;
    private AlternativaController alternativaController;
    private QuestionarioModel questionarioModel;
    private List<AlternativaModel> listaAlternativa;
    private AlternativaModel altCorreta;
    private final BorderPane bpRoot = BorderPaneRootSingleton.getInstancia();

    private int indexPerguntaAtual = 0;
    private int i;
    private boolean alternativaClicada;

    public TelaPerguntasViewController(QuestionarioController questionarioController, PerguntaController perguntaController) {
        this.alternativaClicada = false;
        this.questionarioController = questionarioController;
        this.perguntaController = perguntaController;
        //this.alternativaController = alternativaController;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            this.altCorreta = perguntaController.preencherNovaPergunta(questionarioController.getNovaPergunta(indexPerguntaAtual),
                    this.questionarioController.getAlternativasNovaPergunta(indexPerguntaAtual), this);
            this.lblCategoria.setText(questionarioController.getNomeCategoria());
            iniciarProgressBar();
        } catch (Exception ex) {
            Logger.getLogger(TelaPerguntasViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(questionarioController);
        System.out.println("RECEBEU QUESTIONARIO CONTROLLER COMO REFERENCIA");
    }

    private void iniciarProgressBar() throws Exception {
        Service servico = new Service() {
            @Override
            protected Task createTask() {
                return new Task() {
                    @Override
                    protected Void call() throws Exception {
                        Thread.sleep(10);
                        updateProgress(2000, 1);
                        btn01.setDisable(false);
                        btn02.setDisable(false);
                        btn03.setDisable(false);
                        btn04.setDisable(false);
                        btnProximaPergunta.setVisible(false);
                        for (i = 2000; i > 0; i--) {
                            updateProgress(i - 1, 2000);
                            Thread.sleep(10);
                            if (alternativaClicada == true) {
                                break;
                            }
                        }
                        if (alternativaClicada == false) {
                            btn01.setDisable(true);
                            btn02.setDisable(true);
                            btn03.setDisable(true);
                            btn04.setDisable(true);
                            btnProximaPergunta.setVisible(true);
                        }
                        return null;
                    }
                };
            }
        };

        contagemRegressiva.progressProperty().bind(servico.progressProperty());
        //precisa inicializar o Service
        servico.restart();
    }

    @FXML
    private void alternativaClicada(MouseEvent event) {
        Button botaoClicado = (Button) event.getSource();

        btn01.setDisable(true);
        btn02.setDisable(true);
        btn03.setDisable(true);
        btn04.setDisable(true);
        botaoClicado.setDisable(false);
        btnProximaPergunta.setVisible(true);

        if (verificarResposta(altCorreta, botaoClicado.getText())) {
            questionarioController.calcularPontuacao(i, indexPerguntaAtual, this);
            botaoClicado.setStyle("-fx-background-color:green");
        } else {
            botaoClicado.setStyle("-fx-background-color:red");
        }
        alternativaClicada = true;
    }

    private void finalizar() throws IOException, SQLException, Exception {
        System.out.println("ENTROU AQUI");
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/FXML/TelaFimQuestionario.fxml"));
        loader.setController(new TelaFimQuestionarioViewController(questionarioController, this));
        Parent fimQuestionario = loader.load();

        bpRoot.setCenter(fimQuestionario);

    }

    @FXML
    private void handleProximaPergunta(MouseEvent event) throws Exception {
        alternativaClicada = false;
        this.indexPerguntaAtual++;
        this.lblPerguntaAtual.setText(String.valueOf(this.indexPerguntaAtual + 1));
        System.out.println("TAMANHO QUESTIONARIO: "+questionarioController.iniciarQuestionario().getQuestionario().size());
        if (indexPerguntaAtual < questionarioController.iniciarQuestionario().getQuestionario().size()) {
            btn01.setDisable(false);
            btn02.setDisable(false);
            btn03.setDisable(false);
            btn04.setDisable(false);
            btn01.setStyle("-fx-background-color:#38003c");
            btn02.setStyle("-fx-background-color:#38003c");
            btn03.setStyle("-fx-background-color:#38003c");
            btn04.setStyle("-fx-background-color:#38003c");

            this.altCorreta = perguntaController.preencherNovaPergunta(questionarioController.getNovaPergunta(indexPerguntaAtual),
                    this.questionarioController.getAlternativasNovaPergunta(indexPerguntaAtual), this);
            iniciarProgressBar();
        } else {
            System.out.println("FINALIZOU");
            finalizar();
        }
    }

    private boolean verificarResposta(AlternativaModel altCorreta, String resposta) {
        return altCorreta.getAlternativa().equals(resposta);
    }

    public Label getLblQtdePerguntas() {
        return lblQtdePerguntas;
    }

    public Label getLblPerguntaAtual() {
        return lblPerguntaAtual;
    }

    public Label getLblScore() {
        return lblScore;
    }

    public Label getLblCategoria() {
        return lblCategoria;
    }

    public JFXButton getBtn01() {
        return btn01;
    }

    public JFXButton getBtn02() {
        return btn02;
    }

    public JFXButton getBtn03() {
        return btn03;
    }

    public JFXButton getBtn04() {
        return btn04;
    }

    public JFXButton getBtnProximaPergunta() {
        return btnProximaPergunta;
    }

    public JFXButton getBtnPerguntaAnterior() {
        return btnPerguntaAnterior;
    }

    public JFXTextArea getTxtAreaPergunta() {
        return txtAreaPergunta;
    }

//    public TelaPerguntasViewController(QuestionarioController questionarioController) throws Exception {
//        this.questionarioController = questionarioController;
//        this.questionarioModel = questionarioController.iniciarQuestionario();
//
//        this.altCorreta = preencherNovaPergunta(questionarioController.getNovaPergunta(indexPerguntaAtual),
//                this.questionarioController.getAlternativasNovaPergunta(indexPerguntaAtual));
//    }
    /*public void setReferencias(QuestionarioController questionarioController) throws Exception{
        this.questionarioController = questionarioController;
        this.questionarioModel = questionarioController.iniciarQuestionario();

        this.altCorreta = preencherNovaPergunta(questionarioController.getNovaPergunta(indexPerguntaAtual),
                this.questionarioController.getAlternativasNovaPergunta(indexPerguntaAtual));
    }*/
}
