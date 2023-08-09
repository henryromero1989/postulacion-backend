/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romero.postulacion.bo;

import com.romero.postulacion.dao.UsuarioDao;
import com.romero.postulacion.dao.UsuarioDaoImpl;
import com.romero.postulacion.dto.EmpleadoDto;
import com.romero.postulacion.enums.TipoUsuario;
import com.romero.postulacion.model.Usuario;
import com.romero.postulacion.util.Conexion;
import static com.romero.postulacion.util.Util.calcularSaldo;
import java.io.IOException;
import java.sql.SQLException;
import org.modelmapper.ModelMapper;

/**
 *
 * @author Henry
 */
public class EmpleadoBo implements UsuarioBo<EmpleadoDto> {
    
    ModelMapper modelMapper;
    
    public EmpleadoBo () {
        modelMapper = new ModelMapper();
    }    

    @Override
    public EmpleadoDto getInformacion(String codigo) throws IOException, ClassNotFoundException, SQLException{
        EmpleadoDto empleadoDto;
        try (Conexion conexion = new Conexion()) {
            UsuarioDao usuarioDao = new UsuarioDaoImpl(conexion);
            
            Usuario usuario = usuarioDao.findUsuarioByCodigo(codigo, TipoUsuario.E.name());
            
            if (usuario != null) {                                  
                empleadoDto = modelMapper.map(usuario, EmpleadoDto.class);                               
                
                empleadoDto.setSaldo( calcularSaldo(empleadoDto.getMovimientos()) );
            } else {
                return null;
            }
        }
        
        return empleadoDto;
    }
}
