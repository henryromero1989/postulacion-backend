/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romero.postulacion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

/**
 *
 * @author Henry Romero
 */

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClienteDto {
   private String nombre;
    private String apellido;
    private int edad;
    private String numeroCuenta;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private Date fechaCreacion;    
    private List<MovimientosDto> movimientos;
    private BigDecimal saldo; 
}
