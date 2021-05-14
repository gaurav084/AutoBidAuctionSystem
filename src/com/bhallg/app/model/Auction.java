package com.bhallg.app.model;

import java.util.List;

/**
 * Auction class containing itemName and its corresponding Bidder Information
 * 
 * @author bhallg
 *
 */
public class Auction {
	private List<Bidder> bidders;
	private String itemName;
	private Bidder winner;

	public Auction(List<Bidder> bidders, String itemName) {
		this.bidders = bidders;
		this.itemName = itemName;
	}

	public List<Bidder> getBidders() {
		return bidders;
	}

	public void setBidders(List<Bidder> bidders) {
		this.bidders = bidders;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Bidder getWinner() {
		return winner;
	}

	public void setWinner(Bidder winner) {
		this.winner = winner;
	}

	
}
