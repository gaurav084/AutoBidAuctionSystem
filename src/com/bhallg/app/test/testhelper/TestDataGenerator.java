package com.bhallg.app.test.testhelper;

import java.util.ArrayList;
import java.util.List;

import com.bhallg.app.model.Auction;
import com.bhallg.app.model.Bidder;

public class TestDataGenerator {

	public List<Auction> initializeAuctions() {
		List<Auction> auctions = new ArrayList<Auction>();

		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		// bidders.add(generateBidder("Alice", MonetaryAmount.dollars(new
		// BigDecimal(10.00)), 80.0f, 5.0f));
		bidders1.add(generateBidder("Alice", 50.0f, 80.0f, 3.0f));
		bidders1.add(generateBidder("Aaron", 60.0f, 82.0f, 2.0f));
		bidders1.add(generateBidder("Amanda", 55.0f, 85.0f, 5.0f));

		Auction auction1 = new Auction(bidders1, "Bicycle");
		auctions.add(auction1);

		// Creating second Auction details
		List<Bidder> bidders2 = new ArrayList<Bidder>();
		bidders2.add(generateBidder("Alice", 700.0f, 725.0f, 2.0f));
		bidders2.add(generateBidder("Aaron", 599.0f, 725.0f, 15.0f));
		bidders2.add(generateBidder("Amanda", 625.0f, 725.0f, 8.0f));

		Auction auction2 = new Auction(bidders2, "Scooter");
		auctions.add(auction2);

		// Creating second Auction details
		List<Bidder> bidders3 = new ArrayList<Bidder>();
		bidders3.add(generateBidder("Alice", 2500.0f, 3000.0f, 500.0f));
		bidders3.add(generateBidder("Aaron", 2800.0f, 3100.0f, 201.0f));
		bidders3.add(generateBidder("Amanda", 2501.0f, 3200.0f, 247.0f));

		Auction auction3 = new Auction(bidders3, "Boat");
		auctions.add(auction3);

		return auctions;
	}

	public Bidder generateBidder(String name, float startingBid, float maxBid, float incrementAmount) {
		return new Bidder(name, startingBid, maxBid, incrementAmount);
	}
	
	public Auction generateAuction(List<Bidder> bidders, String auctionName) {
		return new Auction(bidders, auctionName);
	}

}
