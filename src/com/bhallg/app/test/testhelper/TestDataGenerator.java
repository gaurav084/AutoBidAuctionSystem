package com.bhallg.app.test.testhelper;

import java.util.List;

import com.bhallg.app.model.Auction;
import com.bhallg.app.model.Bidder;

/**
 * This class is used to help generate the Bidders with their corresponding
 * start bid, max bid and incremental values, and generate each Auction details
 * with those bidders
 * 
 * @author bhallg
 *
 */
public class TestDataGenerator {

	public Bidder generateBidder(String name, float startingBid, float maxBid, float incrementAmount) {
		return new Bidder(name, startingBid, maxBid, incrementAmount);
	}

	public Auction generateAuction(List<Bidder> bidders, String auctionName) {
		return new Auction(bidders, auctionName);
	}

}
