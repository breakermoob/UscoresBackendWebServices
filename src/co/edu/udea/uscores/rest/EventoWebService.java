package co.edu.udea.uscores.rest;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.uscores.bl.EventoBL;
import co.edu.udea.uscores.dto.Evento;
import co.edu.udea.uscores.exception.UsException;

@Path("Evento")
@Component
public class EventoWebService {

	@Autowired
	private EventoBL eventoBL;

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Evento> obtener(@QueryParam("idTorneo")int idTorneo) throws RemoteException {

		List<Evento> eventos = new ArrayList<Evento>();

		try {

			eventos = eventoBL.obtener(idTorneo);

		} catch (UsException e) {
			throw new RemoteException(e.getMessage());
		}
		return eventos;
	}
	
	
	
	@Produces(MediaType.TEXT_PLAIN)
	//@GET
	@POST
	public String crearEvento(@QueryParam("id")int id, @QueryParam("nombre") String nombre,@QueryParam("lugar")String lugar, 
			@QueryParam("idTorneo")int idTorneo) throws RemoteException{
		

		
		try {
			eventoBL.crearEvento(id, nombre, new Date(6), lugar, idTorneo);
			
		} catch (UsException e) {
			return e.getMessage();
		}
		//http://localhost:8080/UscoresWS/us/Evento?id=200&nombre=futbolito&lugar=udea&idTorneo=1
		return "Se creo correcatamente el evento  " + id;
		
	}
	
	
	
	@Produces(MediaType.TEXT_PLAIN)
	//@GET
	@PUT
	public String actualizarEvento(@QueryParam("id")int id, @QueryParam("nombre") String nombre,@QueryParam("lugar")String lugar, 
			@QueryParam("idTorneo")int idTorneo) throws RemoteException{
		
		try {
			
			eventoBL.actualizarEvento(id, nombre, new Date(6), lugar, idTorneo);
			
		} catch (UsException e) {
			return e.getMessage();
		}
		
		//http://localhost:8080/UscoresWS/us/Evento?id=200&nombre=partidazo&lugar=UdeAPrime&idTorneo=1
		return "Se actualizo correcatamente el evento " + id;
		
	}
	
	
	
	@Produces(MediaType.TEXT_PLAIN)
	//@GET
	@DELETE
	public String eliminarEvento(@QueryParam("id")int id) throws RemoteException{
		
		try {
			
			eventoBL.eliminarEvento(id);
			
		} catch (UsException e) {
			return e.getMessage();
		}
		
		//http://localhost:8080/UscoresWS/us/Evento?id=200
		return "Se elimino correcatamente el evento " + id;
		
		
	}

}

