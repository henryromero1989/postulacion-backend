/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romero.postulacion.bo;

import java.io.IOException;
import java.sql.SQLException;

/**
 *
 * @author Henry
 */
public interface UsuarioBo<T> {
        
    public T getInformacion(String codigo) throws IOException, ClassNotFoundException, SQLException;
            
}
