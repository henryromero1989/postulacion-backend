/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.romero.postulacion.bo;

import com.romero.postulacion.dao.UsuarioDao;
import com.romero.postulacion.dao.UsuarioDaoImpl;
import com.romero.postulacion.dto.ClienteDto;
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
public class ClienteBo implements UsuarioBo<ClienteDto> {
    
    ModelMapper modelMapper;
    
    public ClienteBo () {
        modelMapper = new ModelMapper();
    }    

    @Override
    public ClienteDto getInformacion(String codigo) throws IOException, ClassNotFoundException, SQLException{
        ClienteDto clienteDto;
        try (Conexion conexion = new Conexion()) {
            UsuarioDao usuarioDao = new UsuarioDaoImpl(conexion);
            
            Usuario usuario = usuarioDao.findUsuarioByCodigo(codigo, TipoUsuario.C.name());
            
            if (usuario != null) {                                  
                clienteDto = modelMapper.map(usuario, ClienteDto.class);                               
                
                clienteDto.setSaldo( calcularSaldo(clienteDto.getMovimientos()) );
            } else {
                return null;
            }
        }
        
        return clienteDto;
    }
    
}
