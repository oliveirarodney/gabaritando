/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import javafx.fxml.FXML;
import javafx.scene.layout.BorderPane;
import model.UsuarioSingleton;

/**
 *
 * @author maykh
 */
public class BorderPaneRootSingleton {

    @FXML
    private static BorderPane instancia;

    public static BorderPane getInstancia() {
        if (instancia == null) {
            System.out.println("BORDER PANE REFERENCIA NAO DEFINIDO");
        }
        return instancia;
    }

    public static void setInstancia(BorderPane instancia) {
        if (BorderPaneRootSingleton.instancia == null) {
            BorderPaneRootSingleton.instancia = instancia;
        }
    }
}
