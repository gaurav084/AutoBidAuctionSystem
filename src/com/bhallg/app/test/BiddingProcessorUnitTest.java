package com.bhallg.app.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.junit.Assert;
import org.junit.function.ThrowingRunnable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.bhallg.app.handler.BiddingProcessor;
import com.bhallg.app.model.Auction;
import com.bhallg.app.model.Bidder;
import com.bhallg.app.test.testhelper.TestDataGenerator;

/**
 * 
 * @author bhallg
 *
 */
class BiddingProcessorUnitTest {

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
	 * This Test has input from the specified problem and it calculates winner for
	 * each of the auction
	 * 
	 * Asserts Winner name and winning bid.
	 */
	@Test
	public void testSampleInput() {
		// Setting the testData

		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
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
	public void testWinningBidIsLowerThanMaxPossibleBid() {
		// Setting the testData

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

		classunderTest.processAuctionsForMaxBid(auctions);

		// setting Expected output
		expectedAuctionWinners.put(auctions.get(0), auctions.get(0).getBidders().get(2));
		expectedAuctionWinners.put(auctions.get(1), auctions.get(1).getBidders().get(0));

		for (int i = 0; i < auctions.size(); i++) {
			String expectedWinnerName = expectedAuctionWinners.get(auctions.get(i)).getName();
			float expectedWinningBidPrice = expectedAuctionWinners.get(auctions.get(i)).getWinningBidAmount();

			Assert.assertEquals(expectedWinnerName, auctions.get(i).getWinner().getName());
			Assert.assertEquals(expectedWinningBidPrice, auctions.get(i).getWinner().getWinningBidAmount(), 0.0f);
		}
	}

	/**
	 * This test verifies scenario where a winning bidder has max amount much higher
	 * than second highest, but the incremental amount results in highest bidders
	 * same bid as second highest, so the logic calculates highest bidder's winning
	 * bid as 1 increment more as the second highest bidder's max bid.
	 */
	@Test
	public void testLowestBiddingLogicWithIncrementalAmountAsSecondHighest() {
		// Setting the testData

		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 40.0f, 80.0f, 2.0f));
		bidders1.add(testDataGenerator.generateBidder("Aaron", 60.0f, 90.0f, 10.0f));
		bidders1.add(testDataGenerator.generateBidder("Amanda", 55.0f, 110.0f, 5.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		// Creating second Auction details
		List<Bidder> bidders2 = new ArrayList<Bidder>();
		bidders2.add(testDataGenerator.generateBidder("Alice", 700.0f, 762.0f, 2.0f));
		bidders2.add(testDataGenerator.generateBidder("Aaron", 599.0f, 779.0f, 15.0f));
		bidders2.add(testDataGenerator.generateBidder("Amanda", 625.0f, 750.0f, 15.0f));

		auctions.add(testDataGenerator.generateAuction(bidders2, auction2Name));

		classunderTest.processAuctionsForMaxBid(auctions);

		// setting Expected output
		expectedAuctionWinners.put(auctions.get(0), auctions.get(0).getBidders().get(2));
		expectedAuctionWinners.put(auctions.get(1), auctions.get(1).getBidders().get(1));

		for (int i = 0; i < auctions.size(); i++) {
			String expectedWinnerName = expectedAuctionWinners.get(auctions.get(i)).getName();
			float expectedWinningBidPrice = expectedAuctionWinners.get(auctions.get(i)).getWinningBidAmount();

			Assert.assertEquals(expectedWinnerName, auctions.get(i).getWinner().getName());
			Assert.assertEquals(expectedWinningBidPrice, auctions.get(i).getWinner().getWinningBidAmount(), 0.0f);
		}
	}

	/**
	 * This Test verifies the logic in winnerCalculator.java where handler code checks if
	 * the startBid for winner is more than secondHighestbidders maxPossible bid.
	 */
	@Test
	public void testWinnerWinsWithStartBid() {
		// Setting the testData
		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 2.0f, 10.0f, 2.0f));
		bidders1.add(testDataGenerator.generateBidder("Aaron", 5.0f, 25.0f, 5.0f));
		bidders1.add(testDataGenerator.generateBidder("Amanda", 30.0f, 110.0f, 1.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		classunderTest.processAuctionsForMaxBid(auctions);

		// setting Expected output
		expectedAuctionWinners.put(auctions.get(0), auctions.get(0).getBidders().get(2));

		String expectedWinnerName = expectedAuctionWinners.get(auctions.get(0)).getName();
		float expectedWinningBidPrice = expectedAuctionWinners.get(auctions.get(0)).getWinningBidAmount();

		Assert.assertEquals(expectedWinnerName, auctions.get(0).getWinner().getName());
		Assert.assertEquals(expectedWinningBidPrice, auctions.get(0).getWinner().getWinningBidAmount(), 0.0f);
	}

	/*
	 * Testing where any bidder has an invalid input, in this test bidder has
	 * invalid startBid.
	 */
	@Test
	public void testInvalidInputWithStartBidAsZero() {
		// Setting the testData
		// Creating Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 0.0f, 10.0f, 1.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		Assert.assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {

			@Override
			public void run() throws Throwable {
				classunderTest.processAuctionsForMaxBid(auctions);
			}
		});
	}

	/*
	 * Testing where any bidder has an invalid input, in this test bidder has
	 * invalid maxBid.
	 */
	@Test
	public void testInvalidInputWithMaxBidAsZero() {
		// Setting the testData
		// Creating Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 5.0f, 0.0f, 1.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		Assert.assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {

			@Override
			public void run() throws Throwable {
				classunderTest.processAuctionsForMaxBid(auctions);
			}
		});
	}

	/*
	 * Testing where any bidder has an invalid input, in this test bidder has
	 * invalid increment value.
	 */
	@Test
	public void testInvalidInputWithIncrementAsZero() {
		// Setting the testData
		// Creating Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 5.0f, 10.0f, 0.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		Assert.assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {

			@Override
			public void run() throws Throwable {
				classunderTest.processAuctionsForMaxBid(auctions);
			}
		});
	}

	/*
	 * Testing where any bidder has an invalid input, in this test bidder has maxBid
	 * lower than startBid.
	 */
	@Test
	public void testInvalidInputWithLowerMaxBidThanStart() {
		// Setting the testData
		// Creating Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 15.0f, 10.0f, 1.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		Assert.assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {

			@Override
			public void run() throws Throwable {
				classunderTest.processAuctionsForMaxBid(auctions);
			}
		});
	}

	/*
	 * Testing where any bidder has an invalid input, in this test bidder has
	 * negative value bids
	 */
	@Test
	public void testInvalidInputWithNegativeValues() {
		// Setting the testData
		// Creating Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", -5.0f, -10.0f, -1.0f));

		auctions.add(testDataGenerator.generateAuction(bidders1, auction1Name));

		Assert.assertThrows(IllegalArgumentException.class, new ThrowingRunnable() {

			@Override
			public void run() throws Throwable {
				classunderTest.processAuctionsForMaxBid(auctions);
			}
		});
	}

	/**
	 * This test verifies scenario where there are two bidders with a tie of winning
	 * bid. As per requirement the first person who entered the bid should win
	 */
	@Test
	public void testBiddersWithTieOnWinningBids() {
		// Setting the testData

		// Creating first Auction details
		List<Bidder> bidders1 = new ArrayList<Bidder>();
		bidders1.add(testDataGenerator.generateBidder("Alice", 40.0f, 80.0f, 2.0f));
		bidders1.add(testDataGenerator.generateBidder("Aaron", 60.0f, 115.0f, 10.0f));
		bidders1.add(testDataGenerator.generateBidder("Amanda", 55.0f, 110.0f, 5.0f));

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
