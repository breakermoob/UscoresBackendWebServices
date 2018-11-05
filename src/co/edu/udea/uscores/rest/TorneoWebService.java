package co.edu.udea.uscores.rest;

import java.rmi.RemoteException;
import java.util.ArrayList;
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

import co.edu.udea.uscores.bl.TorneoBL;
import co.edu.udea.uscores.dto.Torneo;
import co.edu.udea.uscores.exception.UsException;

@Path("Torneo")
@Component
public class TorneoWebService {

	@Autowired
	private TorneoBL torneoBL;

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Torneo> obtener() throws RemoteException {

		List<Torneo> torneos = new ArrayList<Torneo>();

		try {

			torneos = torneoBL.obtener();

		} catch (UsException e) {
			throw new RemoteException(e.getMessage());
		}
		return torneos;

	}

	@Produces(MediaType.TEXT_PLAIN)
	// @GET
	@POST
	public String crearTorneo(@QueryParam("id") int id, @QueryParam("nombre") String nombre,
			@QueryParam("deporte") String deporte, @QueryParam("correo") String correo) throws RemoteException {

		try {

			torneoBL.crearTorneo(id, nombre, deporte, correo);

		} catch (UsException e) {
			return e.getMessage();
		}
		// http://localhost:8080/UscoresWS/us/Torneo?id=200&nombre=futbolito&deporte=futbol
		return "Se creo correcatamente el torneo " + id;

	}

	@Produces(MediaType.TEXT_PLAIN)
	// @GET
	@PUT
	public String actualizarTorneo(@QueryParam("id") int id, @QueryParam("nombre") String nombre,
			@QueryParam("deporte") String deporte) throws RemoteException {

		try {

			torneoBL.actualizarTorneo(id, nombre, deporte);

		} catch (UsException e) {
			return e.getMessage();
		}

		// http://localhost:8080/UscoresWS/us/Torneo?id=200&nombre=futbolito&deporte=balonmano&correo="Leo@gmail.com"
		return "Se actualizo correcatamente el torneo " + id;

	}

	@Produces(MediaType.TEXT_PLAIN)
	//@GET
	@DELETE
	public String eliminarTorneo(@QueryParam("id")int id,@QueryParam("correo")String correo) throws RemoteException{
		
		try {
			
			torneoBL.eliminarTorneo(id,correo);
			
		} catch (UsException e) {
			return e.getMessage();
		}
		
		//http://localhost:8080/UscoresWS/us/Torneo?id=200&correo="Leo@gmail.com"
		return "Se elimino correcatamente el torneo " + id;
		
		
	}

}
