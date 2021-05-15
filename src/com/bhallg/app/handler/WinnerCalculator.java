package com.bhallg.app.handler;

import java.util.List;

import com.bhallg.app.model.Bidder;

public class WinnerCalculator {

	public Bidder calculateWinner(List<Bidder> bidders) {

		Bidder winner = null;
		float highestMaxPossibleBidAmount = Float.MIN_VALUE;

		/*
		 * Find Bidding winner, which has the highest possible bid amount
		 */
		for (Bidder bidder : bidders) {
			float maxPossibleBidAmount = bidder.getStartingBid();
			float possibleIncrementalAmount = bidder.getMaxBid() - bidder.getStartingBid();
			int maxBidIterations = (int) (possibleIncrementalAmount / bidder.getIncrementAmount());
			maxPossibleBidAmount += (bidder.getIncrementAmount() * maxBidIterations);
			bidder.setMaxPossibleBid(maxPossibleBidAmount);

			if (highestMaxPossibleBidAmount < maxPossibleBidAmount) {
				highestMaxPossibleBidAmount = maxPossibleBidAmount;
				winner = bidder;
			}
		}

		/*
		 * Now iterating through all Bidders to find the bidder with second highest
		 * permissible bid.
		 */
		float secondHighestPossibleBid = Float.MIN_VALUE;

		for (Bidder bidder : bidders) {
			if (bidder != winner) {
				if (secondHighestPossibleBid < bidder.getMaxPossibleBid())
					secondHighestPossibleBid = bidder.getMaxPossibleBid();
			}
		}
		// TODO remove these before submission
		System.out.println("secondHighestPossibleBid : " + secondHighestPossibleBid);

		/*
		 * This code block ensures the winner's bid is lowest amount possible observing
		 * all other auto-bidding rules.
		 */
		if (secondHighestPossibleBid > winner.getStartingBid()) {
			int divider = (int) ((winner.getMaxPossibleBid() - secondHighestPossibleBid) / winner.getIncrementAmount());
			float winningBid = 0.0f;
			if (winner.getMaxPossibleBid() - divider * winner.getIncrementAmount() <= secondHighestPossibleBid) 
				winningBid = winner.getMaxPossibleBid() - (divider - 1) * winner.getIncrementAmount();
			else 
				winningBid = winner.getMaxPossibleBid() - divider * winner.getIncrementAmount();
			
			winner.setWinningBidAmount(winningBid);
		} else 
			winner.setWinningBidAmount(winner.getStartingBid());
		
		return winner;
	}
}
