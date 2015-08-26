package org.chinpon.mom.entities;

import java.util.ArrayList;
import java.util.List;

import org.chinpon.model.entities.InitObjectCard;
import org.chinpon.model.entities.SpellCard;

public class ProfileCard extends Card {
	private List<org.chinpon.mom.entities.InitObjectCard> initObjectCards = null;
	private List<org.chinpon.mom.entities.SpellCard> spellCards = null;
	
	public ProfileCard() {
		setInitObjectCards(new ArrayList<org.chinpon.mom.entities.InitObjectCard>());
		setSpellCards(new ArrayList<org.chinpon.mom.entities.SpellCard>());
	}
	
	public ProfileCard(org.chinpon.model.entities.ProfileCard profileCard) {
		this();
		for (InitObjectCard initObjectCard:profileCard.getInitObjectCard()) {
			initObjectCards.add(new org.chinpon.mom.entities.InitObjectCard());
		}
		for (SpellCard initObjectCard:profileCard.getSpellCard()) {
			spellCards.add(new org.chinpon.mom.entities.SpellCard());
		}
	}

	public List<org.chinpon.mom.entities.InitObjectCard> getInitObjectCards() {
		return initObjectCards;
	}

	public void setInitObjectCards(List<org.chinpon.mom.entities.InitObjectCard> initObjectCards) {
		this.initObjectCards = initObjectCards;
	}

	public List<org.chinpon.mom.entities.SpellCard> getSpellCards() {
		return spellCards;
	}

	public void setSpellCards(List<org.chinpon.mom.entities.SpellCard> spellCards) {
		this.spellCards = spellCards;
	}

}
