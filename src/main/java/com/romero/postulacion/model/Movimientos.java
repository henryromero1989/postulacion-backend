/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romero.postulacion.model;

import com.romero.postulacion.enums.TipoMovimiento;
import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Data
public class Movimientos {
    
    private Long id;
    
    String descripcion;
        
    String tipo;
    
    BigDecimal monto;
    
    @Column(name="id_ciente")
    Long idCliente;
    
}
