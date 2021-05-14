package com.bhallg.app.handler;

import java.util.List;

import com.bhallg.app.model.Bidder;

public class WinnerCalculator {

	public Bidder calculateWinner (List<Bidder> bidders) {
		
		Bidder winner = new Bidder(null, 0, 0, 0);
		
		for (Bidder bidder : bidders) {
			int maxBidIterations = 0;
			float maxPossibleBidAmount = bidder.getStartingBid();
			float possibleIncrementalAmount = bidder.getMaxBid() - bidder.getStartingBid();
			
			if(possibleIncrementalAmount % bidder.getIncrementAmount() == 0)
				bidder.setMaxPossibleBid(bidder.getMaxBid());
			else {
				maxBidIterations = (int)(possibleIncrementalAmount / bidder.getIncrementAmount());
				maxPossibleBidAmount += (bidder.getIncrementAmount() * maxBidIterations);
				bidder.setMaxPossibleBid(maxPossibleBidAmount);
			}
		}
		
		//Now iterating through all Bidders to find the bidder with highest possible bid.
		float maxBidOnItem = 0.0f;
		for (Bidder bidder : bidders) {
			if(bidder.getMaxPossibleBid() > maxBidOnItem) {
				maxBidOnItem = bidder.getMaxPossibleBid();
				winner = bidder;
			}
		}
		return winner;
	}
}
