package com.bhallg.app.test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.bhallg.app.handler.BiddingProcessor;
import com.bhallg.app.model.Auction;
import com.bhallg.app.model.Bidder;
import com.bhallg.app.model.MonetaryAmount;


class BiddingProcessorTest {
	
	BiddingProcessor classunderTest = new BiddingProcessor();

	@Test
	public void testProcess() {
		List<Auction> auctions = initializeAuctions();
		classunderTest.processAuctionsForMaxBid(auctions);
		
		for (Auction auction : auctions) {
			Assert.assertEquals("Amanda", auction.getWinner().getName());
			//Assert.assertEquals(85.0f, auction.getWinner().getWinningBidAmount());
			Assert.assertEquals(85.0f, auction.getWinner().getMaxPossibleBid(), 0.0f);
		}
		
	}
	
	private List<Auction> initializeAuctions(){
		List<Bidder> bidders = new ArrayList<Bidder>();
		//bidders.add(generateBidder("Alice", MonetaryAmount.dollars(new BigDecimal(10.00)), 80.0f, 5.0f));
		bidders.add(generateBidder("Alice", 50.0f, 80.0f, 3.0f));
		bidders.add(generateBidder("Aaron", 60.0f, 82.0f, 2.0f));
		bidders.add(generateBidder("Amanda", 55.0f, 90.0f, 5.0f));
		
		List<Auction> auctions = new ArrayList<Auction>();
		Auction auction1 = new Auction(bidders, "Bicycle");
		auctions.add(auction1);
		
		return auctions;
	}
	
	private Bidder generateBidder(String name, float startingBid, float maxBid, float incrementAmount) {
		return new Bidder(name, startingBid, maxBid, incrementAmount);
	}

}
