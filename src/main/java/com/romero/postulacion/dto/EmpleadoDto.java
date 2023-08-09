/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romero.postulacion.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import lombok.Data;

@Data
@JsonInclude(Include.NON_NULL)
public class EmpleadoDto {
    private String nombre;
    private String apellido;
    private int edad;
    private String numeroCuenta;
    private String cargo; //empleado
    private List<MovimientosDto> movimientos;
    private BigDecimal saldo;       
}
