package org.chinpon.mom.rest;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.chinpon.mom.entities.Player;
import org.chinpon.mom.service.MOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/player")
@Produces(MediaType.APPLICATION_JSON)
public class PlayerResource {
	@Autowired
	private MOMService service;
	
	@POST
	@Path("/add")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response addPlayer(@FormParam("idGame") String idGame,
			@FormParam("name") String name) {
		Player player = new Player();
		player.setIdGame(idGame);
		player.setName(name);
		
		String idPlayer = service.addPlayer(player);
		return Response.ok(idPlayer, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/actions/list")
	public Response listActions(@QueryParam("idGame") String idGame,
							  @QueryParam("idPlayer") String idPlayer) {
		Player player = new Player();
		player.setIdGame(idGame);
		player.setIdPlayer(idPlayer);
		
		List<org.chinpon.mom.entities.PlayerAction> playerActions =
				service.listPlayerActions(player);
		return Response.ok(playerActions, MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/actions/exec")
	public Response executeAction(@QueryParam("idGame") String idGame,
							  @QueryParam("idPlayer") String idPlayer,
							  @QueryParam("idPlayerAction") String idPlayerAction) {
		Player player = new Player();
		player.setIdGame(idGame);
		player.setIdPlayer(idPlayer);
		
		org.chinpon.mom.entities.PlayerAction playerAction = 
				new org.chinpon.mom.entities.PlayerAction();
		playerAction.setPlayer(player);
		playerAction.setIdPlayerAction(idPlayerAction);
		
		Object object = service.executePlayerAction(playerAction);
		
		return Response.ok(object).build(); 
		//Response.ok(playerActions, MediaType.APPLICATION_JSON).build();
	}
	
	
	
	
}