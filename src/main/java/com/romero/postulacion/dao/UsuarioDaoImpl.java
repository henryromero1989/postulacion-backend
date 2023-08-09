package com.romero.postulacion.dao;

import com.romero.postulacion.dto.MovimientosDto;
import com.romero.postulacion.enums.TipoUsuario;
import com.romero.postulacion.model.Usuario;
import com.romero.postulacion.util.Conexion;
import com.romero.postulacion.util.ResultSetToObject;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Henry
 */
public class UsuarioDaoImpl implements UsuarioDao{
     
    private final Conexion conexion;
    public UsuarioDaoImpl(Conexion conexion){
        this.conexion = conexion;
    }
    
    @Override
    public Usuario findUsuarioByCodigo(String codigo, String tipo) {
        try {
            StringBuilder consulta = new StringBuilder()
                    .append("SELECT ")
                        .append("id_cliente, ")
                        .append("codigo, ")
                        .append("nombre, ")
                        .append("apellido, ")
                        .append("edad, ")
                        .append("numero_cuenta, ")
                        .append("fecha_creacion, ")
                        .append("cargo, ")
                        .append("tipo ")
                    .append("FROM clientes ")
                    .append("WHERE codigo = ?")
                    .append("and tipo = ?");
            ResultSet resultSet = conexion.select(consulta.toString(), codigo, tipo);
            
            ResultSetToObject obj = new ResultSetToObject(resultSet);
            Usuario usuario = obj.mapToSingle(Usuario.class);                        
            
            if (usuario != null && usuario.getId() != null) {
                consulta = new StringBuilder()
                        .append("SELECT ")
                            .append("tipo, ")
                            .append("monto, ")
                            .append("descripcion, ")
                            .append("id_cliente ")
                        .append("FROM movimientos ")
                        .append("WHERE id_cliente = ? ")
                        .append("ORDER BY id_movimiento asc");
                
                resultSet = conexion.select(consulta.toString(), usuario.getId());
                obj.setData(resultSet);                
                usuario.setMovimientos( obj.mapToList(MovimientosDto.class) );
            }
                                
            return usuario;
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDaoImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
}
