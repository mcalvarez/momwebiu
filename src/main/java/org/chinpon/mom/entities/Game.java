package org.chinpon.mom.entities;

import java.util.ArrayList;
import java.util.List;

import org.chinpon.logic.IMOMGame;

public class Game {	
	private String idGame;
	private List<Player> players;

	public Game(IMOMGame game) {
		this.idGame = game.getIdGame();
		players = new ArrayList<Player>();
		for (org.chinpon.model.entities.Player momPlayer:game.getPlayers().values()) {
			players.add(new Player(momPlayer));
		}
	}

	public String getIdGame() {
		return idGame;
	}

	public void setIdGame(String idGame) {
		this.idGame = idGame;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
	}
}
