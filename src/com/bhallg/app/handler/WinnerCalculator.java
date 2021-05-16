package com.bhallg.app.handler;

import java.util.List;

import com.bhallg.app.model.Bidder;

/**
 * This class contains logic for calculating winners for each Auction. This also
 * checks if the user has entered invalid input
 * 
 * @author bhallg
 *
 */
public class WinnerCalculator {

	public Bidder calculateWinner(List<Bidder> bidders) throws IllegalArgumentException {

		for (Bidder bidder : bidders) {
			if (!isValidBiddingInput(bidder)) {
				throw new IllegalArgumentException("Invalid Input");
			}
		}

		Bidder winner = null;
		float highestMaxPossibleBidAmount = Float.MIN_VALUE;

		// Find Bidding winner, which has the highest possible bid amount
		for (Bidder bidder : bidders) {
			float maxPossibleBidAmount = bidder.getStartingBid();
			float possibleIncrementalAmount = bidder.getMaxBid() - bidder.getStartingBid();
			if (possibleIncrementalAmount != 0 && bidder.getIncrementAmount() != 0) {
				int maxBidIterations = (int) (possibleIncrementalAmount / bidder.getIncrementAmount());
				maxPossibleBidAmount += (bidder.getIncrementAmount() * maxBidIterations);
			}

			bidder.setMaxPossibleBid(maxPossibleBidAmount);

			if (highestMaxPossibleBidAmount < maxPossibleBidAmount) {
				highestMaxPossibleBidAmount = maxPossibleBidAmount;
				winner = bidder;
			}
		}

		// Iterating through all Bidders to find the second highest permissible bid.
		float secondHighestPossibleBid = Float.MIN_VALUE;

		for (Bidder bidder : bidders) {
			if (bidder != winner) {
				if (secondHighestPossibleBid < bidder.getMaxPossibleBid())
					secondHighestPossibleBid = bidder.getMaxPossibleBid();
			}
		}
		// This code block ensures the winner's bid is lowest amount possible observing
		// all other auto-bidding rules.
		if (secondHighestPossibleBid > winner.getStartingBid()) {
			int divider = (int) ((winner.getMaxPossibleBid() - secondHighestPossibleBid) / winner.getIncrementAmount());
			float winningBid = 0.0f;
			if (winner.getMaxPossibleBid() - divider * winner.getIncrementAmount() <= secondHighestPossibleBid)
				if (divider > 0)
					winningBid = winner.getMaxPossibleBid() - (divider - 1) * winner.getIncrementAmount();
			else
				winningBid = winner.getMaxPossibleBid() - divider * winner.getIncrementAmount();

			winner.setWinningBidAmount(winningBid);
		} else
			winner.setWinningBidAmount(winner.getStartingBid());

		return winner;
	}

	private boolean isValidBiddingInput(Bidder bidder) {
		return isValidAmount(bidder.getIncrementAmount()) && isValidAmount(bidder.getStartingBid())
				&& isValidAmount(bidder.getMaxBid()) && bidder.getMaxBid() > bidder.getStartingBid()
				&& bidder.getIncrementAmount() < bidder.getMaxBid();
	}

	private boolean isValidAmount(float amount) {
		return amount > 0;
	}
}
