package com.bhallg.app.model;

/**
 * The Bidder Class containing each Bidder details. Getters and setters for
 * Bidder details
 * 
 * @author bhallg
 *
 */
public class Bidder {
	private String name;
	// private MonetaryAmount startingBid;
	private float startingBid;
	private float maxBid;
	private float incrementAmount;
	private float maxPossibleBid;
	private float winningBidAmount;

	public Bidder(String name, float startingBid, float maxBid, float incrementAmount) {
		this.name = name;
		this.startingBid = startingBid;
		this.maxBid = maxBid;
		this.incrementAmount = incrementAmount;
	}

	public String getName() {
		return name;
	}

	public float getStartingBid() {
		return startingBid;
	}

	public void setStartingBid(float startingBid) {
		this.startingBid = startingBid;
	}

	public float getMaxBid() {
		return maxBid;
	}

	public void setMaxBid(float maxBid) {
		this.maxBid = maxBid;
	}

	public float getIncrementAmount() {
		return incrementAmount;
	}

	public void setIncrementAmount(float incrementAmount) {
		this.incrementAmount = incrementAmount;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getMaxPossibleBid() {
		return maxPossibleBid;
	}

	public void setMaxPossibleBid(float maxPossibleBid) {
		this.maxPossibleBid = maxPossibleBid;
	}

	public float getWinningBidAmount() {
		return winningBidAmount;
	}

	public void setWinningBidAmount(float winningBidAmount) {
		this.winningBidAmount = winningBidAmount;
	}

}
