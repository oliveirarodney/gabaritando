/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.controllerView;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import static java.lang.System.exit;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import model.UsuarioSingleton;
import javafx.scene.Scene;
import javafx.stage.Stage;
import utils.BorderPaneRootSingleton;

/**
 * FXML Controller class
 *
 * @author maykh
 */
public class PrincipalJogoViewController implements Initializable {

    @FXML
    private BorderPane bpRoot;
    @FXML
    private JFXButton btnPerfil;
    @FXML
    private JFXButton btnJogar;
    @FXML
    private JFXButton btnSair;
    @FXML
    private StackPane stkPaneCentral;
    @FXML
    private StackPane stkPaneDireita;

    private UsuarioSingleton usuario;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        BorderPaneRootSingleton.setInstancia(bpRoot);
        this.usuario = UsuarioSingleton.getInstancia();
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getClassLoader().getResource("view/FXML/TelaCategoriasJogo.fxml"));
            Parent telaCategorias;
            telaCategorias = loader.load();
            bpRoot.setCenter(null);
            bpRoot.setCenter(telaCategorias);
            bpRoot.setRight(null);
        } catch (IOException ex) {
            Logger.getLogger(PrincipalJogoViewController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handlePerfil(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/FXML/TelaPerfil.fxml"));
        Parent telaCategorias = loader.load();

        bpRoot.setCenter(null);
        bpRoot.setCenter(telaCategorias);
    }

    @FXML
    private void handleJogar(MouseEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getClassLoader().getResource("view/FXML/TelaCategoriasJogo.fxml"));
        Parent telaCategorias = loader.load();

        bpRoot.setCenter(null);
        bpRoot.setCenter(telaCategorias);
    }

    @FXML
    private void handleSair(MouseEvent event) throws IOException {
        exit(0);
    }

}
