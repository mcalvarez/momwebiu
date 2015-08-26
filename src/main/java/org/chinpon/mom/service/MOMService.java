package org.chinpon.mom.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import org.chinpon.logic.AppLogic;
import org.chinpon.logic.IAppLogic;
import org.chinpon.logic.IMOMGame;
import org.chinpon.logic.actions.hero.ShowProfileCards;
import org.chinpon.logic.ia.PlayerAction;
import org.chinpon.logic.ia.PlayerType;
import org.chinpon.mom.entities.Player;
import org.chinpon.mom.entities.ProfileCard;
import org.springframework.stereotype.Service;

@Service
public class MOMService {
	private IAppLogic logic;

	public MOMService() {
		logic = new AppLogic();
	}

	public IMOMGame createNewGame() {
		return logic.createNewGame();
	}

	public IMOMGame getGame(String idGame) {
		return logic.getGame(idGame);
	}

	public List<IMOMGame> listGames() {
		return logic.listGames();
	}

	public String addPlayer(Player player) {
		IMOMGame game = logic.getGame(player.getIdGame());
		return game.addPlayer(player.getName(), PlayerType.SOR);
	}

	public List<org.chinpon.mom.entities.PlayerAction> listPlayerActions(Player player) {
		List<org.chinpon.mom.entities.PlayerAction> playerActions = new ArrayList<org.chinpon.mom.entities.PlayerAction>();

		IMOMGame game = logic.getGame(player.getIdGame());
		List<PlayerAction> playerActionsAux = game.getPlayerActions(player.getIdPlayer());
		for (PlayerAction playerAction : playerActionsAux) {
			playerActions.add(new org.chinpon.mom.entities.PlayerAction(playerAction));
		}

		return playerActions;
	}

	public Object executePlayerAction(org.chinpon.mom.entities.PlayerAction playerAction) {
		final IMOMGame game = logic.getGame(playerAction.getPlayer().getIdGame());
		ExecuterAsync executerAsync = new ExecuterAsync(this);
		game.addObserver(executerAsync);
		game.executePlayerAction(playerAction.getPlayer().getIdGame(), playerAction.getIdPlayerAction());
		
		return executerAsync.getResult();
		
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				
			}
		}).start();*/
		
		/*new Thread(executerAsync).start();
		
		try {
			this.wait();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
	
	class ExecuterAsync implements Observer, Runnable {
		private MOMService service;
		private boolean stop = false;
		private Object result = null;
		
		public ExecuterAsync(MOMService service) {
			this.service = service;
		}

		@Override
		public void update(Observable o, Object arg) {
			// TODO Auto-generated method stub
			if (arg instanceof ShowProfileCards) {
				ShowProfileCards showProfileCards = (ShowProfileCards) arg;
				List<org.chinpon.model.entities.ProfileCard> profileCards =
						showProfileCards.getProfileCards();
				
				List<ProfileCard> profileCardsDTOs = new ArrayList<ProfileCard>();
				for (org.chinpon.model.entities.ProfileCard pCard:profileCards) {
					profileCardsDTOs.add(new ProfileCard(pCard));
				}
				
				result = profileCardsDTOs;
			}
			
			System.out.println(o.getClass().getName());
			stop = true;
		}

		@Override
		public void run() {
			while (!stop) {
				try {
					Thread.sleep(10000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			service.notify();
		}

		public Object getResult() {
			return result;
		}

		public void setResult(Object result) {
			this.result = result;
		}
	}
}
