package co.edu.udea.uscores.rest;

import java.rmi.RemoteException;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.uscores.bl.UsuarioBL;
import co.edu.udea.uscores.dto.Usuario;
import co.edu.udea.uscores.exception.UsException;

@Path("Usuario")
@Component
public class UsuarioWebService {
	
	@Autowired
	private UsuarioBL usuarioService;
	
	@Produces(MediaType.TEXT_PLAIN)
	@GET
	public String validar(@QueryParam("correo") String correo, @QueryParam("contrasena") String contrasena) throws RemoteException{
		Usuario us = new Usuario();
		
		try {
			
			us = usuarioService.autentificar(correo, contrasena);
		}catch(UsException e) {
			return e.getMessage();
		}
		return us.getNombre();
	}
	
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public String registrar(@QueryParam("correo")String correo, @QueryParam("nombre")String nombre,
			@QueryParam("contrasena")String contrasena) throws RemoteException{
		
		try {
			usuarioService.insertar(correo, nombre, contrasena);
		}catch(UsException e) {
			return e.getMessage();
		}		
		return "Usuario registrado exitosamente";
	}

}
