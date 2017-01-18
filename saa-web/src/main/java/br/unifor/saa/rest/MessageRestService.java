package br.unifor.saa.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
/**
 * @author marcelorosa2@hotmail.com
 * @since  05 de janeiro de 2017
 */
@Path("/message")
public class MessageRestService {
	
	@GET
	@Path("/{param}")
	public Response printMessage(@PathParam("param") String msg) {

		String result = "Exemplo Restful : " + msg;

		return Response.status(200).entity(result).build();

	}
//	@GET
//	@Produces({ APPLICATION_XML, APPLICATION_JSON })
//	public Response findAll() {
//		return super.findAll();
//	}

	
	
}
