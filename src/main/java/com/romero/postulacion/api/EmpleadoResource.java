/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/WebServices/GenericResource.java to edit this template
 */
package com.romero.postulacion.api;

import com.romero.postulacion.bo.EmpleadoBo;
import com.romero.postulacion.bo.UsuarioBo;
import com.romero.postulacion.dto.EmpleadoDto;
import com.romero.postulacion.dto.MensajeDto;
import static com.romero.postulacion.util.Util.validarDigitos;
import java.io.IOException;
import java.sql.SQLException;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Henry
 */
@Path("empleado")
public class EmpleadoResource {    

    private UsuarioBo empleadoBo = new EmpleadoBo();
    /**
     * Retrieves representation of an instance of com.romero.postulacion.services.ClienteResource
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{codigo}")
    @Produces(javax.ws.rs.core.MediaType.APPLICATION_JSON)
    public Response getEmpleado(@PathParam(value = "codigo") String codigo) throws IOException {       
                        
        try {
            
            if (!validarDigitos(codigo)) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }            
                        
            EmpleadoDto personaDto = (EmpleadoDto) empleadoBo.getInformacion(codigo);
            
            if (personaDto == null) {
                MensajeDto mensajeDto = new MensajeDto("001", "No encontrado");
                return Response
                        .status(Response.Status.NO_CONTENT)
                        .entity(mensajeDto)
                        .build();
            }
            
            return Response.ok()                    
                    .entity(personaDto)
                    .build();
            
            
        } catch (ClassNotFoundException ex) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        } catch (SQLException ex) {
            return Response
                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                    .build();
        }        
    }
    
}
