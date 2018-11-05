package co.edu.udea.uscores.rest;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import co.edu.udea.uscores.bl.MisTorneosBL;
import co.edu.udea.uscores.dto.Torneo;
import co.edu.udea.uscores.exception.UsException;

@Path("MisTorneos")
@Component
public class MisTorneosWebService {

	@Autowired
	private MisTorneosBL misTorneosService;

	@Produces(MediaType.APPLICATION_JSON)
	@GET
	public List<Torneo> obtener(@QueryParam("correo") String correo) throws RemoteException {
		List<Torneo> misTorneos = new ArrayList<Torneo>();

		try {
			misTorneos = misTorneosService.obtener(correo);
		} catch (UsException e) {
			throw new RemoteException(e.getMessage());
		}

		return misTorneos;
	}
}
