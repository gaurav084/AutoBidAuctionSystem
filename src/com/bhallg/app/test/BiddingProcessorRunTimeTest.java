package com.bhallg.app.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bhallg.app.model.Auction;
import com.bhallg.app.handler.BiddingProcessor;
import com.bhallg.app.model.Bidder;
import com.bhallg.app.test.testhelper.TestDataGenerator;

/**
 * This Test file contains tests to ensure Runtime
 * 
 * @author bhallg
 *
 */
public class BiddingProcessorRunTimeTest {

	BiddingProcessor classunderTest;
	TestDataGenerator testDataGenerator;
	List<Auction> auctions;
	HashMap<Auction, Bidder> expectedAuctionWinners;
	final String auction1Name = "Bicycle";
	final String auction2Name = "Scooter";
	final String auction3Name = "Boat";

	@BeforeEach
	public void setUp() throws Exception {
		classunderTest = new BiddingProcessor();
		testDataGenerator = new TestDataGenerator();
		auctions = new ArrayList<Auction>();
		expectedAuctionWinners = new HashMap<Auction, Bidder>();
	}

	/**
	 * This test verifies scenario where there are large numbers for maxBid and
	 * small iteration value. This test verifies that the program logic handles
	 * large numbers efficiently and runtime is small
	 */
	@Test
	public void testBiddersWithLargeIterationNumbers() {
		// Setting the testData

		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 2.0f, 8000000000000000099999999999999990.0f, 2.0f));
		bidders1.add(testDataGenerator.generateBidder("Aaron", 5.0f, 99999999999999999999999999999999999999.0f, 5.0f));
		bidders1.add(testDataGenerator.generateBidder("Amanda", 5.0f, 89999999999999999999999999999999999999.0f, 1.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		classunderTest.processAuctionsForMaxBid(auctions);

		// setting Expected output
		expectedAuctionWinners.put(auctions.get(0), auctions.get(0).getBidders().get(1));

		String expectedWinnerName = expectedAuctionWinners.get(auctions.get(0)).getName();
		float expectedWinningBidPrice = expectedAuctionWinners.get(auctions.get(0)).getWinningBidAmount();

		Assert.assertEquals(expectedWinnerName, auctions.get(0).getWinner().getName());
		Assert.assertEquals(expectedWinningBidPrice, auctions.get(0).getWinner().getWinningBidAmount(), 0.0f);
	}

}
