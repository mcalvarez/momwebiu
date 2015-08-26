package org.chinpon.mom.entities;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Player {
	private String idPlayer;
	private String idGame;
	private String name;

	public Player() {
		
	}
	
	public Player(org.chinpon.model.entities.Player player) {
		this.idPlayer = player.getIdPlayer();
		this.name = player.getName();
	}
	
	public String getIdPlayer() {
		return idPlayer;
	}

	public void setIdPlayer(String idPlayer) {
		this.idPlayer = idPlayer;
	}
	
	public String getIdGame() {
		return idGame;
	}

	public void setIdGame(String idGame) {
		this.idGame = idGame;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
