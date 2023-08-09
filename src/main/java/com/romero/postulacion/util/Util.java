/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romero.postulacion.util;

import com.romero.postulacion.dto.MovimientosDto;
import com.romero.postulacion.enums.TipoMovimiento;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

/**
 *
 * @author Henry
 */
public class Util {
    
    public static boolean validarDigitos(String cadena) {
        return cadena.matches("[0-9]{9}");
    }
    
    public static BigDecimal calcularSaldo(List<MovimientosDto> movimientos) {
        BigDecimal saldo = new BigDecimal(BigInteger.ZERO);
        
        saldo = movimientos.stream()
                .map(o -> {
                        return o.getTipo().equals(TipoMovimiento.D.toString()) ? o.getMonto().negate() : o.getMonto();
                    } )
                .reduce((x, y) -> x.add(y))
                .get();
        
        return saldo;
    }
    
}
