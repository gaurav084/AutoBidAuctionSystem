# AutoBidAuctionSystem
Design a system which generates winner based on their start bids, maximum possible bids and bid increments.


## Model
Auction
* Item Name
* List of Bidders

Bidder
* Bidder name
* Start bid amount
* Maximum bid amount
* Bid increments


## Handler
Handler processes List of Auctions and uses WinnerCalculator to determine winner of each Auction individually. 
Once done, it sets the winning Bidder for each Auction which can then be retrieved for winner's name and winning bid price.

We also ensure that Highest bidder only pays the lowest bid amount required to beat all the other bids, 
so handler needs determine the winning price just more than the second highest bidder's maximum possible price.

## Tests
* Lowest winning price by bidder to beat all other bidders
* Invalid inputs - negative bids, $0 bids, Max bid price > start bid price
* Start Bid of winner is more than Max possible bid of any other bidders.
* Tie winning bid amount