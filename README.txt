-----README-----

Wealthsimple Coding Challenge - Ishan Tikku

--Technical Design--

The program contains two packages:

1. wealthsimple_coding_challenge

Contains 4 classes - RebalanceDriver, Portfolio, Stock, Rebalancer

The 4 classes allow for the main program to be built in a highly modular, maintainable structure - avoiding tight coupling. This is evidenced by the fact that the entire main() function of the program takes 6 lines.

List<Stock> stocks = deserialize(args);
if (stocks != null) {
	Portfolio p = new Portfolio(stocks);
	p.rebalance();
}
System.exit(0);

The RebalanceDriver converts the input arguments to a list of Stocks, which are then added to a Portfolio object.

To perform the rebalancing, the Portfolio class calls upon the Rebalancer class. This class in turn breaks down the process into two steps. First it, determines each stocks' level of priority in terms of reducing the error in allocation. Then, it uses a greedy approach to calculate how many shares of each stock must be bought to approach equilibrium, and then backtracks to determine how many shares of each stock must be sold to cover the costs of this transaction.

This approach was chosen over an optimization-based solution for two reasons:

One, this approach is much, much faster. In the context of a dynamic stock market, where transactions must happen quickly with recent, relevant information, speed will trump as a criteria every time.

Secondly, optimization is wholly unncessary here. This rebalancing module is a closed system in which all we need to do is shift resources from one item to another to reduce the total error. This can be accomplished by passing through the data a couple times to determine how to balance the actions (BUYS) and reactions (SELLS).

2. wealthsimple_challenge_tests

Contains 4 classes - RebalanceDriverTest, PortfolioTest, StockTest, RebalancerTest

Each class has a suite of test cases that cover the major functionality and boundary scenarios of its respective class in the first package.

--Running the program--

Each stock should be entered into the arguments as a string of 5 comma-separated values, in this order: 
-Ticker
-Target allocation
-Current allocation
-# of Shares Owned
-Share Price

When entering multiple stocks, separate each set with a space/break:

e.g.
GOOG,60,50.96,52,98 AAPL,30,29.92,136,22 TSLA,10,19.12,239,8

--Areas for Improvement--

For v2, I would like to do two things:

1. Make the program more robust to handling various input argument formats. For example, currently the program will throw an error if it receives $98 in place of 98 for the share price. If, for example an inexperienced user instinctively placed the dollar sign ahead of the value, they would be rather confused and frustrated by this error.

2. Improve the program's sensitivity to rounding errors in making transactions. Currently, the rebalancing module does not try to ensure that exactly the same amount of dollars are spent on purchasing and selling the different stocks in the portfolio - it comes within a few dollars, usually fractions of a share. V2 would allow for buying partial shares to correct this.