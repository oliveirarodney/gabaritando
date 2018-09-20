/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.exceptions;

import javafx.scene.control.Alert;

/**
 *
 * @author maykh
 */
public class SenhaException extends Exception {
    // Parameterless Constructor

    public SenhaException() {             
    }

    // Constructor that accepts a message
    public SenhaException(String message) {
        super(message);
          
    }
}
