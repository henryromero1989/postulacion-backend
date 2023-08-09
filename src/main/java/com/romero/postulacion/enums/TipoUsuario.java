/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romero.postulacion.enums;

/**
 *
 * @author Henry
 */
public enum TipoUsuario {
    E("Empleado"), C("Cliente");
    private final String name;    

    TipoUsuario (String name){
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
