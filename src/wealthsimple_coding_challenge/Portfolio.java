package wealthsimple_coding_challenge;

import java.util.*;

public class Portfolio {
	private List<Stock> stocks;
	private List<String> transactions;
	private double total_value;
	
	public Portfolio(List<Stock> stock_list){
		stocks = stock_list;
		transactions = new ArrayList<String>();
		calculateValue();
	}
	
	private void calculateValue(){
		total_value = 0;
		for (Stock s : stocks){
			total_value += s.getSharePrice() * s.getSharesOwned();
		}
	}
	
	public void rebalance(){
		/*Cost function: (Actual_1,...) = (Target_1 - Actual_1)^2...
		 *
		 * Constraints:
		 *    Shares / stock >= 0
		 *    SharePrice_1 * Actual_1 + ... = total_value
		 *    
		 * 1. Calculate target # of shares
		 */
		
	}
}
