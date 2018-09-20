/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controllerView;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import controller.UsuarioController;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import model.UsuarioSingleton;
import utils.BorderPaneRootSingleton;
import utils.exceptions.EmailException;
import utils.exceptions.SenhaException;

/**
 * FXML Controller class
 *
 * @author User
 */
public class TelaEditarPerfilViewController implements Initializable {

    @FXML
    private JFXTextField txtfNome;
    @FXML
    private JFXTextField txtfUsername;
    @FXML
    private JFXTextField txtfEmail;
    @FXML
    private JFXTextField txtfConfirmaEmail;
    @FXML
    private JFXButton btnCancelar;
    @FXML
    private Font x11;
    @FXML
    private JFXButton btnConfirmar;
    @FXML
    private Font x2;
    @FXML
    private JFXPasswordField psswSenha;
    @FXML
    private JFXPasswordField psswConfirmaSenha;

    /**
     * Initializes the controller class.
     */
    private Parent parent;
    private Stage telaPerfil;
    private Stage thisWindow = null;
    private UsuarioSingleton usuario;
    private UsuarioController usuarioController;
    private BorderPane bpRoot = BorderPaneRootSingleton.getInstancia();

    @Override
    public void initialize(URL url, ResourceBundle rb) {      
        usuario = UsuarioSingleton.getInstancia();
        this.txtfNome.setText(usuario.getNome());
        this.txtfUsername.setText(usuario.getUsername());
        this.txtfEmail.setText(usuario.getEmail());
        this.txtfConfirmaEmail.setText(usuario.getEmail());

        usuarioController = new UsuarioController();
    }

    @FXML
    private void handleCancelar(MouseEvent event) throws IOException {
        bpRoot.setCenter(null);
        parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FXML/TelaPerfil.fxml"));
        bpRoot.setCenter(parent); 
    }

    @FXML
    private void handleConfirmar(MouseEvent event) throws IOException {
        try {
            this.usuarioController.editarPerfil(txtfNome.getText(), txtfUsername.getText(),
                    txtfEmail.getText(), txtfConfirmaEmail.getText(), psswSenha.getText(),
                    psswConfirmaSenha.getText());

            bpRoot.setCenter(null);
            parent = FXMLLoader.load(getClass().getClassLoader().getResource("view/FXML/TelaPerfil.fxml"));
            bpRoot.setCenter(parent);
        } catch (EmailException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Alteração não concluída");
            alert.setTitle("Erro");
            alert.setContentText("Emails diferem");
            alert.showAndWait();
        } catch (SenhaException e) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText("Alteração não concluída");
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
