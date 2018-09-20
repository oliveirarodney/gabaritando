/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.sql.Connection;
import DAO.AutenticacaoDAO;
import model.UsuarioSingleton;
import database.Database;
import database.DatabaseFactory;

/**
 *
 * @author maykh
 */
public class AutenticacaoController {
    
    private static UsuarioSingleton usuario;
    
    private final Database database = DatabaseFactory.openDatabaseSQLServer();
    private final Connection connection = database.conectar();
    
    public static boolean autenticarUsuario(String username, String senha) throws Exception{
        System.out.println("Autenticando...");
        return (AutenticacaoDAO.getUsuarioSingleton(username,senha));
    }
    
    public static UsuarioSingleton getInstanciaUsuario(){
        return AutenticacaoController.usuario;
    }
}
