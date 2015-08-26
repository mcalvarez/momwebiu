package org.chinpon.mom.entities;

public class PlayerAction {
	private String idPlayerAction = null;
	private Player player = null;
	private String description = null;
	
	public PlayerAction() {
		
	}
	
	public PlayerAction(org.chinpon.logic.ia.PlayerAction playerAction) {
		this.setDescription(playerAction.toString());
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getIdPlayerAction() {
		return idPlayerAction;
	}

	public void setIdPlayerAction(String idPlayerAction) {
		this.idPlayerAction = idPlayerAction;
	}
}
