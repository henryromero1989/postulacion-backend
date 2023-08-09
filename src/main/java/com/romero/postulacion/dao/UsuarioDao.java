/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.romero.postulacion.dao;

import com.romero.postulacion.model.Usuario;

/**
 *
 * @author Henry
 */
public interface UsuarioDao {
    public Usuario findUsuarioByCodigo(String codigo, String tipo);
}
