package view.controllerView;

import controller.AutenticacaoController;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.UsuarioSingleton;

public class LoginViewController implements Initializable {

    @FXML
    private TextField txtfLogin;
    @FXML
    private Button btnLogar;
    @FXML
    private Button btnCadastrar;
    @FXML
    private PasswordField pssfSenha;

    private UsuarioSingleton usuario;

    private Stage telaLogin;
    private Scene scene;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void handleNovoCadastro(MouseEvent event) throws IOException {

        Parent loadCadastro = FXMLLoader.load(getClass().getClassLoader().getResource("view/FXML/TelaCadastro.fxml"));

        telaLogin = (Stage) btnCadastrar.getScene().getWindow();
        scene = new Scene(loadCadastro);
        Stage telaCadastro = new Stage();
        telaCadastro.setScene(scene);
        telaCadastro.setTitle("Cadastro");
        telaCadastro.setResizable(false);
        telaLogin.close();
        telaCadastro.show();
    }

    @FXML
    private void handleRealizarLogin(MouseEvent event) throws Exception {
        try {
            if (AutenticacaoController.autenticarUsuario(txtfLogin.getText(), pssfSenha.getText())) {
                AutenticacaoController.getInstanciaUsuario();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getClassLoader().getResource("view/FXML/TelaPrincipalJogo.fxml"));
                Parent loadPrincipalJogo = loader.load();

                scene = new Scene(loadPrincipalJogo);
                Stage telaPrincipalJogo = new Stage();
                telaPrincipalJogo.setScene(scene);
                telaPrincipalJogo.setTitle("TelaPrincipalJogo");
                telaPrincipalJogo.setResizable(false);

                telaLogin = (Stage) btnLogar.getScene().getWindow();
                telaLogin.close();

                telaPrincipalJogo.show();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle(null);
                alert.setContentText("Usuário ou Senha incorretos!!");
                alert.showAndWait();
                System.out.println("Falha no Login");
            }

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public Stage getStage() {
        return telaLogin;
    }
}
