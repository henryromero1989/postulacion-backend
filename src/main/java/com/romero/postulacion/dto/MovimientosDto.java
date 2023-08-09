/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romero.postulacion.dto;

import com.romero.postulacion.enums.TipoMovimiento;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovimientosDto {
    
    private String tipo;
    private BigDecimal monto;
    private String descripcion;
    
}
