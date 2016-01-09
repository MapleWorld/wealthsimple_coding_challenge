package wealthsimple_coding_challenge;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

//This class is tasked with rebalancing the portfolio (and from time to time, the force)

public class Rebalancer {
	
	private Portfolio p;
	
	public Rebalancer(Portfolio port){
		p = port;
	}
	
	public void beginRebalance(){
		/*
		 * The rebalancing of the portfolio happens in two steps. First, we determine how
		 * many shares are needed for each stock to achieve the target allocation. Then,
		 * we use a greedy algorithm to buy and sell those stocks in order of their
		 * priority in reducing error. The definition of 'priority' is explained within
		 * the greedySolution function.
		 */
		for (Stock s : p.getStocks()){
			s.shares_desired = (int) (s.getTarget() / 100 * p.getTotalValue() / s.getSharePrice());
		}
		
		greedySolution();
	}
	private void greedySolution(){
		/*
		 * This is a 'greedy' algorithm that first orders stocks into the order of their
		 * 'priority', based on how much improvement to the total error spending $1 on
		 * buying a particular stock will make.
		 * 
		 * Key metric 	= reduction in error per dollar spent buying
		 * 				= 1 / (current # of shares * $share * number of companies)
		 * 
		 * ***Note: This score will be negative if buying a stock will increase the error,
		 * as seen below
		 */
		
		List<Stock> stocks = p.getStocks();
		
		//Step 1: Calculate key metric score for each stock
		for (Stock s : stocks){
			if (s.getTarget() >= s.getActual())
				s.error_reduc = 1 / (s.getSharesOwned() * s.getSharePrice() * stocks.size());
			else
				s.error_reduc = -1 / (s.getSharesOwned() * s.getSharePrice() * stocks.size());		
		}
		
		//Step 1.1: Re-order items in stocks by error_reduc, largest to smallest
		Collections.sort(stocks, new Comparator<Stock>(){
			public int compare(Stock o1, Stock o2) {
	            return o2.error_reduc.compareTo(o1.error_reduc);
	        }
		});
		
		//Step 2: Buy as many shares as required of each stock to bring as close to target
		//as possible, and calculate dollar value. Perform in order of priority as
		//established in Step 1
		int[] buys = new int[stocks.size()];
		double money = 0;
		for (int i = 0; i < buys.length; i++){
			Stock s = stocks.get(i);
			buys[i] = Math.max(0, s.shares_desired - s.getSharesOwned());
			money += buys[i]*s.getSharePrice();
		}
		
		//Step 3: Going in reverse priority order, determine how many shares need to be
		//sold from each stock to pay for the transaction. Perform until the money runs
		//out.
		int[] sells = new int[stocks.size()];
		int counter = sells.length - 1;
		while (money > 0){
			if (counter < 0) break;
			Stock s = stocks.get(counter);
			int share_diff = s.getSharesOwned() - s.shares_desired;
			if (money > s.getSharePrice()*share_diff)
				sells[counter] = share_diff;
			else
				sells[counter] = (int) (money / s.getSharePrice());
			
			money -= sells[counter] * s.getSharePrice();
			counter--;
		}
		
		//Output the buys and sells to the screen.
		displayTransactions(buys, sells);
	}

	public void displayTransactions(int[] buys, int[] sells){
		for (int i = 0; i < buys.length; i++){
			String output = "";
			if (buys[i] > 0){
				output = "BUY " + buys[i] + " shares of " + p.getStocks().get(i).ticker;
				System.out.println(output);
				p.addTransaction(output);
			}
			else continue;
		}
		for (int i = 0; i < sells.length; i++){
			String output = "";
			if (sells[i] > 0){
				output = "SELL " + sells[i] + " shares of " + p.getStocks().get(i).ticker;
				System.out.println(output);
				p.addTransaction(output);
			}
			else continue;
		}
	}
}
