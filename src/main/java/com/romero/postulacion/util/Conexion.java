/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romero.postulacion.util;

import java.io.Closeable;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henry
 */
public class Conexion implements Closeable{    
    
    private Connection conexion;
    private CallableStatement callableStatement;
    
    public Conexion() throws IOException, ClassNotFoundException, SQLException {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("conexion");
        
        String clase = resourceBundle.getString("clase");
        String url = resourceBundle.getString("url");
        String usuario = resourceBundle.getString("usuario");
        String clave = resourceBundle.getString("clave");        
             
        Class.forName(clase);
        this.conexion = DriverManager.getConnection(url, usuario, clave);
        this.conexion.setAutoCommit(false);
    }
    
    
    
    private static Object[] getParameters(Object[] parameters) {
        if ( (parameters[0] instanceof Object[]) && (1 == parameters.length) ) {
            return (Object[]) parameters[0];
        } else {
            return parameters;
        }
    }
      
    
    public ResultSet select(String query, Object... parameters) throws SQLException {
        this.callableStatement = this.conexion.prepareCall(query);
        int index = 0;
        if ( (parameters != null) && (parameters.length > 0) ) {
            parameters = getParameters(parameters);
            for (Object parameter : parameters) {                
                index++;
                if (parameter == null) {
                    this.callableStatement.setObject(index, parameter);
                } else if (parameter instanceof String) {
                    this.callableStatement.setString(index, (String) parameter);
                } else if (parameter instanceof Integer) {
                    this.callableStatement.setInt(index, (Integer) parameter);
                } else if (parameter instanceof Long) {
                    this.callableStatement.setLong(index, (Long) parameter);
                } else if (parameter instanceof BigDecimal) {
                    this.callableStatement.setBigDecimal(index, (BigDecimal) parameter);
                } else if (parameter instanceof Double) {
                    this.callableStatement.setDouble(index, (Double) parameter);
                } else if (parameter instanceof Boolean) {
                    this.callableStatement.setBoolean(index, (Boolean) parameter);
                }
            }            
        }
        
        this.callableStatement.execute();
        return this.callableStatement.getResultSet(); 
    }    

    private boolean isOpen() {
        try {
            return !this.conexion.isClosed();
        } catch (SQLException ex) {
            return false;
        } catch (Exception ex) {
            return false;
        }
    }
    
    @Override
    public void close() throws IOException {
        try {
            if (isOpen()) {
                this.conexion.close();
            }
        } catch (SQLException ex) {            
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
