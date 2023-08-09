/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romero.postulacion.model;

import com.romero.postulacion.dto.MovimientosDto;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import lombok.Data;

@Data
public class Usuario {
    
    @Column(name="id_cliente")
    private Long id;
    
    private String codigo;
    
    private String nombre;
    
    private String apellido;
    
    private int edad;
    
    @Column(name="numero_cuenta")
    private String numeroCuenta;
        
    @Column(name="fecha_creacion")
    private Date fechaCreacion = new Date();
    

    private String cargo;
    
    private String tipo;
    
    List<MovimientosDto> movimientos;
    
}
