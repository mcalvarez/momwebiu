package org.chinpon.mom.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.chinpon.logic.IMOMGame;
import org.chinpon.mom.entities.Game;
import org.chinpon.mom.service.MOMService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Path("/games")
@Produces(MediaType.APPLICATION_JSON)
public class MOMGameResource {
	@Autowired
	private MOMService service;
	
	@GET
	@Path("/create")
	public Response startNewGame() {
		IMOMGame game = service.createNewGame();
		return Response.ok(new Game(game), MediaType.APPLICATION_JSON).build();
	}
	
	@GET
	@Path("/list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response showAllGames() {
		List<Game> games = new ArrayList<Game>();
		for (IMOMGame momGame : service.listGames()) {
			games.add(new Game(momGame));
		}
		return Response.ok(games, MediaType.APPLICATION_JSON).build();
	}
}