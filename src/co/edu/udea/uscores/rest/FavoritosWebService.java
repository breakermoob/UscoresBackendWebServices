package co.edu.udea.uscores.rest;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.uscores.bl.FavoritoBL;
import co.edu.udea.uscores.dto.Evento;
import co.edu.udea.uscores.exception.UsException;

@Path("Favoritos")
@Component
public class FavoritosWebService {

	@Autowired
	private FavoritoBL favoritosService;
	
	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Evento> obtener(@QueryParam("correo")String correo) throws RemoteException{
		List<Evento> favoritos = new ArrayList<Evento>();
		
		
		try {
			favoritos = favoritosService.obtenerFavoritos(correo);
		} catch(UsException e){
			throw new RemoteException(e.getMessage());
		}		
		return favoritos;
	}
		
	@Produces(MediaType.TEXT_PLAIN)
	@POST
	public String insertarEventoFavorito(@QueryParam("correo")String correo, @QueryParam("idEvento")int idEvento) throws RemoteException{		
		try {
			favoritosService.insertarFavorito(correo, idEvento);
		}catch(UsException e) {
			return e.getMessage();
		}		
		return "Evento marcado como favorito";
	}
	
	@Produces(MediaType.TEXT_PLAIN)
	@DELETE
	public String eliminarEventoFavorito(@QueryParam("correo")String correo, @QueryParam("idEvento")int idEvento) throws RemoteException{
		
		try {
			favoritosService.eliminarFavorito(correo, idEvento);
		}catch(UsException e){
			return e.getMessage();
		}		
		return "Evento eliminado de mis favoritos";
	}

}
