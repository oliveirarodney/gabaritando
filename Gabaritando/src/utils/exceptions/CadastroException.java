/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils.exceptions;


/**
 *
 * @author maykh
 */
public class CadastroException extends Exception {
    // Parameterless Constructor

    public CadastroException() {
        
    }

    // Constructor that accepts a message
    public CadastroException(String message) {
        super(message);
    }
}
