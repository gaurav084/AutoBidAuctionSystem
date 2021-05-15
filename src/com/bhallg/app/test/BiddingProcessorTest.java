package com.bhallg.app.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import com.bhallg.app.handler.BiddingProcessor;
import com.bhallg.app.model.Auction;
import com.bhallg.app.model.Bidder;
import com.bhallg.app.test.testhelper.TestDataGenerator;

class BiddingProcessorTest {

	BiddingProcessor classunderTest = new BiddingProcessor();
	TestDataGenerator testDataGenerator = new TestDataGenerator();

	@Test
	public void testSampleInput() {
		// Setting the testData
		List<Auction> auctions = new ArrayList<Auction>();
		
		final String auction1Name = "Bicycle";
		final String auction2Name = "Scooter";
		final String auction3Name = "Boat";

		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		// bidders.add(generateBidder("Alice", MonetaryAmount.dollars(new
		// BigDecimal(10.00)), 80.0f, 5.0f));
		bidders1.add(testDataGenerator.generateBidder("Alice", 50.0f, 80.0f, 3.0f));
		bidders1.add(testDataGenerator.generateBidder("Aaron", 60.0f, 82.0f, 2.0f));
		bidders1.add(testDataGenerator.generateBidder("Amanda", 55.0f, 85.0f, 5.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		// Creating second Auction details
		List<Bidder> bidders2 = new ArrayList<Bidder>();
		bidders2.add(testDataGenerator.generateBidder("Alice", 700.0f, 725.0f, 2.0f));
		bidders2.add(testDataGenerator.generateBidder("Aaron", 599.0f, 725.0f, 15.0f));
		bidders2.add(testDataGenerator.generateBidder("Amanda", 625.0f, 725.0f, 8.0f));

		auctions.add(testDataGenerator.generateAuction(bidders2, auction2Name));

		// Creating third Auction details
		List<Bidder> bidders3 = new ArrayList<Bidder>();
		bidders3.add(testDataGenerator.generateBidder("Alice", 2500.0f, 3000.0f, 500.0f));
		bidders3.add(testDataGenerator.generateBidder("Aaron", 2800.0f, 3100.0f, 201.0f));
		bidders3.add(testDataGenerator.generateBidder("Amanda", 2501.0f, 3200.0f, 247.0f));

		auctions.add(testDataGenerator.generateAuction(bidders3, auction3Name));

		classunderTest.processAuctionsForMaxBid(auctions);

		// setting Expected output
		HashMap<Auction, Bidder> expectedAuctionWinners = new HashMap<Auction, Bidder>();
		expectedAuctionWinners.put(auctions.get(0), auctions.get(0).getBidders().get(2));
		expectedAuctionWinners.put(auctions.get(1), auctions.get(1).getBidders().get(0));
		expectedAuctionWinners.put(auctions.get(2), auctions.get(2).getBidders().get(1));

		for (int i = 0; i < auctions.size(); i++) {
			String expectedWinnerName = expectedAuctionWinners.get(auctions.get(i)).getName();
			float expectedWinningBidPrice = expectedAuctionWinners.get(auctions.get(i)).getWinningBidAmount();

			Assert.assertEquals(expectedWinnerName, auctions.get(i).getWinner().getName());
			Assert.assertEquals(expectedWinningBidPrice, auctions.get(i).getWinner().getWinningBidAmount(), 0.0f);
		}
	}
	
	@Test
	public void testWinningBidIsLowerThanMaxBid() {
		// Setting the testData
		List<Auction> auctions = new ArrayList<Auction>();
		
		final String auction1Name = "Bicycle";
		final String auction2Name = "Scooter";
		final String auction3Name = "Boat";

		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 40.0f, 80.0f, 3.0f));
		bidders1.add(testDataGenerator.generateBidder("Aaron", 60.0f, 88.0f, 2.0f));
		bidders1.add(testDataGenerator.generateBidder("Amanda", 55.0f, 110.0f, 5.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		// Creating second Auction details
		List<Bidder> bidders2 = new ArrayList<Bidder>();
		bidders2.add(testDataGenerator.generateBidder("Alice", 700.0f, 800.0f, 2.0f));
		bidders2.add(testDataGenerator.generateBidder("Aaron", 599.0f, 725.0f, 15.0f));
		bidders2.add(testDataGenerator.generateBidder("Amanda", 625.0f, 775.0f, 8.0f));

		auctions.add(testDataGenerator.generateAuction(bidders2, auction2Name));

		// Creating third Auction details
		List<Bidder> bidders3 = new ArrayList<Bidder>();
		bidders3.add(testDataGenerator.generateBidder("Alice", 2500.0f, 3500.0f, 500.0f));
		bidders3.add(testDataGenerator.generateBidder("Aaron", 2800.0f, 3702.0f, 201.0f));
		bidders3.add(testDataGenerator.generateBidder("Amanda", 2501.0f, 3200.0f, 247.0f));

		auctions.add(testDataGenerator.generateAuction(bidders3, auction3Name));

		classunderTest.processAuctionsForMaxBid(auctions);

		// setting Expected output
		HashMap<Auction, Bidder> expectedAuctionWinners = new HashMap<Auction, Bidder>();
		expectedAuctionWinners.put(auctions.get(0), auctions.get(0).getBidders().get(2));
		expectedAuctionWinners.put(auctions.get(1), auctions.get(1).getBidders().get(0));
		expectedAuctionWinners.put(auctions.get(2), auctions.get(2).getBidders().get(1));

		for (int i = 0; i < auctions.size(); i++) {
			String expectedWinnerName = expectedAuctionWinners.get(auctions.get(i)).getName();
			float expectedWinningBidPrice = expectedAuctionWinners.get(auctions.get(i)).getWinningBidAmount();

			Assert.assertEquals(expectedWinnerName, auctions.get(i).getWinner().getName());
			Assert.assertEquals(expectedWinningBidPrice, auctions.get(i).getWinner().getWinningBidAmount(), 0.0f);
			Assert.assertTrue(expectedWinningBidPrice < auctions.get(i).getWinner().getMaxBid());
		}
	}
	
	@Test
	public void testFloatingValuesInIncrements() {
		// Setting the testData
		List<Auction> auctions = new ArrayList<Auction>();
		
		final String auction1Name = "Bicycle";
		final String auction2Name = "Scooter";
		final String auction3Name = "Boat";

		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 40.0f, 80.0f, 3.5f));
		bidders1.add(testDataGenerator.generateBidder("Aaron", 60.0f, 90.0f, 2.5f));
		bidders1.add(testDataGenerator.generateBidder("Amanda", 55.0f, 110.0f, 5.5f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		// Creating second Auction details
		List<Bidder> bidders2 = new ArrayList<Bidder>();
		bidders2.add(testDataGenerator.generateBidder("Alice", 700.0f, 800.0f, 2.99f));
		bidders2.add(testDataGenerator.generateBidder("Aaron", 599.0f, 725.0f, 15.0f));
		bidders2.add(testDataGenerator.generateBidder("Amanda", 625.0f, 775.0f, 8.99f));

		auctions.add(testDataGenerator.generateAuction(bidders2, auction2Name));

		// Creating third Auction details
		List<Bidder> bidders3 = new ArrayList<Bidder>();
		bidders3.add(testDataGenerator.generateBidder("Alice", 2500.0f, 3500.0f, 500.5f));
		bidders3.add(testDataGenerator.generateBidder("Aaron", 2800.0f, 3702.0f, 201.99f));
		bidders3.add(testDataGenerator.generateBidder("Amanda", 2501.0f, 3200.0f, 247.5f));

		auctions.add(testDataGenerator.generateAuction(bidders3, auction3Name));

		classunderTest.processAuctionsForMaxBid(auctions);

		// setting Expected output
		HashMap<Auction, Bidder> expectedAuctionWinners = new HashMap<Auction, Bidder>();
		expectedAuctionWinners.put(auctions.get(0), auctions.get(0).getBidders().get(2));
		expectedAuctionWinners.put(auctions.get(1), auctions.get(1).getBidders().get(0));
		expectedAuctionWinners.put(auctions.get(2), auctions.get(2).getBidders().get(1));

		for (int i = 0; i < auctions.size(); i++) {
			String expectedWinnerName = expectedAuctionWinners.get(auctions.get(i)).getName();
			float expectedWinningBidPrice = expectedAuctionWinners.get(auctions.get(i)).getWinningBidAmount();

			Assert.assertEquals(expectedWinnerName, auctions.get(i).getWinner().getName());
			Assert.assertEquals(expectedWinningBidPrice, auctions.get(i).getWinner().getWinningBidAmount(), 0.0f);
		}
	}
	
	@Test
	public void testBiddersWithEqualIncrementalAmounts() {
		// Setting the testData
		List<Auction> auctions = new ArrayList<Auction>();
		
		final String auction1Name = "Bicycle";
		final String auction2Name = "Scooter";
		final String auction3Name = "Boat";

		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 40.0f, 80.0f, 2.0f));
		bidders1.add(testDataGenerator.generateBidder("Aaron", 60.0f, 90.0f, 5.0f));
		bidders1.add(testDataGenerator.generateBidder("Amanda", 55.0f, 110.0f, 5.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		// Creating second Auction details
		List<Bidder> bidders2 = new ArrayList<Bidder>();
		bidders2.add(testDataGenerator.generateBidder("Alice", 700.0f, 762.0f, 2.0f));
		bidders2.add(testDataGenerator.generateBidder("Aaron", 599.0f, 779.0f, 15.0f));
		bidders2.add(testDataGenerator.generateBidder("Amanda", 625.0f, 750.0f, 15.0f));

		auctions.add(testDataGenerator.generateAuction(bidders2, auction2Name));

		// Creating third Auction details
		List<Bidder> bidders3 = new ArrayList<Bidder>();
		bidders3.add(testDataGenerator.generateBidder("Alice", 2500.0f, 3400.0f, 500.0f));
		bidders3.add(testDataGenerator.generateBidder("Aaron", 2900.0f, 3702.0f, 200.0f));
		bidders3.add(testDataGenerator.generateBidder("Amanda", 2500.0f, 3300.0f, 200.0f));

		auctions.add(testDataGenerator.generateAuction(bidders3, auction3Name));

		classunderTest.processAuctionsForMaxBid(auctions);

		// setting Expected output
		HashMap<Auction, Bidder> expectedAuctionWinners = new HashMap<Auction, Bidder>();
		expectedAuctionWinners.put(auctions.get(0), auctions.get(0).getBidders().get(2));
		expectedAuctionWinners.put(auctions.get(1), auctions.get(1).getBidders().get(1));
		expectedAuctionWinners.put(auctions.get(2), auctions.get(2).getBidders().get(1));

		for (int i = 0; i < auctions.size(); i++) {
			String expectedWinnerName = expectedAuctionWinners.get(auctions.get(i)).getName();
			float expectedWinningBidPrice = expectedAuctionWinners.get(auctions.get(i)).getWinningBidAmount();

			Assert.assertEquals(expectedWinnerName, auctions.get(i).getWinner().getName());
			Assert.assertEquals(expectedWinningBidPrice, auctions.get(i).getWinner().getWinningBidAmount(), 0.0f);
		}
	}
	
	@Test
	public void testOnlyOneBidderHasNonZeroBids() {
		// Setting the testData
		List<Auction> auctions = new ArrayList<Auction>();
		
		final String auction1Name = "Bicycle";
		final String auction2Name = "Scooter";

		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 0.0f, 0.0f, 0.0f));
		bidders1.add(testDataGenerator.generateBidder("Aaron", 0.0f, 0.0f, 0.0f));
		bidders1.add(testDataGenerator.generateBidder("Amanda", 30.0f, 110.0f, 5.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		// Creating second Auction details
		List<Bidder> bidders2 = new ArrayList<Bidder>();
		bidders2.add(testDataGenerator.generateBidder("Alice", 0.0f, 0.0f, 0.0f));
		bidders2.add(testDataGenerator.generateBidder("Aaron", 99.0f, 779.0f, 15.0f));
		bidders2.add(testDataGenerator.generateBidder("Amanda", 0.0f, 0.0f, 0.0f));

		auctions.add(testDataGenerator.generateAuction(bidders2, auction2Name));

		classunderTest.processAuctionsForMaxBid(auctions);

		// setting Expected output
		HashMap<Auction, Bidder> expectedAuctionWinners = new HashMap<Auction, Bidder>();
		expectedAuctionWinners.put(auctions.get(0), auctions.get(0).getBidders().get(2));
		expectedAuctionWinners.put(auctions.get(1), auctions.get(1).getBidders().get(1));

		for (int i = 0; i < auctions.size(); i++) {
			String expectedWinnerName = expectedAuctionWinners.get(auctions.get(i)).getName();
			float expectedWinningBidPrice = expectedAuctionWinners.get(auctions.get(i)).getWinningBidAmount();

			Assert.assertEquals(expectedWinnerName, auctions.get(i).getWinner().getName());
			System.out.println("Auction" + (i + 1) + " winner name is " + expectedWinnerName + " and  winning bid is "
					+ expectedWinningBidPrice);
			Assert.assertEquals(expectedWinningBidPrice, auctions.get(i).getWinner().getWinningBidAmount(), 0.0f);
		}
	}
	
	@Test
	public void testIncrementalbidZeroWinnerWinsWithStartBid() {
		// Setting the testData
		List<Auction> auctions = new ArrayList<Auction>();
		
		final String auction1Name = "Bicycle";

		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 0.0f, 0.0f, 2.0f));
		bidders1.add(testDataGenerator.generateBidder("Aaron", 0.0f, 0.0f, 0.0f));
		bidders1.add(testDataGenerator.generateBidder("Amanda", 30.0f, 110.0f, 0.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		classunderTest.processAuctionsForMaxBid(auctions);

		// setting Expected output
		HashMap<Auction, Bidder> expectedAuctionWinners = new HashMap<Auction, Bidder>();
		expectedAuctionWinners.put(auctions.get(0), auctions.get(0).getBidders().get(2));

		for (int i = 0; i < auctions.size(); i++) {
			String expectedWinnerName = expectedAuctionWinners.get(auctions.get(i)).getName();
			float expectedWinningBidPrice = expectedAuctionWinners.get(auctions.get(i)).getWinningBidAmount();

			Assert.assertEquals(expectedWinnerName, auctions.get(i).getWinner().getName());
			System.out.println("Auction" + (i + 1) + " winner name is " + expectedWinnerName + " and  winning bid is "
					+ expectedWinningBidPrice);
			Assert.assertEquals(expectedWinningBidPrice, auctions.get(i).getWinner().getWinningBidAmount(), 0.0f);
		}
	}

}
