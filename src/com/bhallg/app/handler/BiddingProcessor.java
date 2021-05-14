package com.bhallg.app.handler;

import java.util.List;

import com.bhallg.app.model.Auction;
import com.bhallg.app.model.Bidder;

public class BiddingProcessor {

	/**
	 * API to process all Auctions for finding the bidder with the winning bid. The
	 * winner will have the highest bid from all the bidders, but the lowest
	 * possible amount from their maximum bid amount.
	 * 
	 * @param auctions
	 */
	public void processAuctionsForMaxBid(List<Auction> auctions) {
		WinnerCalculator winnerCalculator = new WinnerCalculator();

		for (Auction auction : auctions) {
			Bidder winner = winnerCalculator.calculateWinner(auction.getBidders());
			auction.setWinner(winner);
		}
	}

}
