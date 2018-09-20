/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package database;

import java.sql.Connection;

/**
 *
 * @author maykh
 */
public interface Database {
    
    /**
     *
     * @return
     */
    public abstract Connection conectar();
    public abstract void desconectar(Connection conn);
}
