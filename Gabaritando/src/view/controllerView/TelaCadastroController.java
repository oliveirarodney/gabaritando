package view.controllerView;

import controller.UsuarioController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import model.UsuarioSingleton;
import utils.exceptions.EmailException;
import utils.exceptions.SenhaException;

public class TelaCadastroController implements Initializable {

    /**
     * ************************************************************
     * Variáveis Globais
     * ***********************************************************
     */
    private UsuarioSingleton usuario;
    private UsuarioController usuarioController;

    /**
     * ************************************************************
     * Atributos para manipulação de Banco de Dados
     * ***********************************************************
     */
    @FXML
    private TextField txtfNome;
    @FXML
    private TextField txtfUsername;
    @FXML
    private TextField txtfEmail;
    @FXML
    private TextField txtfConfirmaEmail;
    @FXML
    private PasswordField psswSenha;
    @FXML
    private PasswordField psswConfirmaSenha;
    @FXML
    private Button btnCancelar;

    private Parent parent;
    private Stage telaLogin;
    private Stage thisWindow = null;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO  
        usuario = UsuarioSingleton.getInstancia();
        usuarioController = new UsuarioController();
        try {
            parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FXML/TelaLogin.fxml"));

            Scene firstScene = new Scene(parent);

            telaLogin = new Stage();
            telaLogin.setScene(firstScene);
            telaLogin.setTitle("Login");
            telaLogin.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(TelaCadastroController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @FXML
    private void handleCancelarCadastro(MouseEvent event) throws IOException {

        this.getStage();
        telaLogin.show();
        thisWindow.close();
    }

    @FXML
    private void handleFinalizarCadastro(MouseEvent event) throws IOException {
        try {
            usuarioController.cadastrarUsuario(txtfNome.getText(), txtfUsername.getText(),
                    txtfEmail.getText(), txtfConfirmaEmail.getText(), psswSenha.getText(),
                    psswConfirmaSenha.getText());
            this.getStage();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle(null);
            alert.setContentText("Usuário cadastrado com sucesso!");
            alert.showAndWait();
            System.out.println("Cadastro realizado!");
            
            telaLogin.show();
            thisWindow.close();
            
        } catch (EmailException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cadastro não concluído");
            alert.setTitle("Erro");
            alert.setContentText("Emails diferem");
            alert.showAndWait();
        } catch (SenhaException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Cadastro não concluído");
            alert.setTitle("Erro");
            alert.setContentText("Senhas diferem");
            alert.showAndWait();
        } catch (SQLException ex) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("ERRO DE BANCO DE DADOS");
            alert.setTitle("SQL SERVER");
            alert.setContentText(ex.getMessage());
            alert.show();
        }
    }

    public Stage getStage() {
        if (thisWindow == null) {
            thisWindow = (Stage) this.btnCancelar.getScene().getWindow();
        }
        return thisWindow;
    }
}
