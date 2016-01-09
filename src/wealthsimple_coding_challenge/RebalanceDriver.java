package wealthsimple_coding_challenge;

import java.util.*;


public class RebalanceDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<Stock> stocks = deserialize(args);
		if (stocks != null) {
			Portfolio p = new Portfolio(stocks);
			p.rebalance();
		}
		System.exit(0);
	}
	
	public static List<Stock> deserialize(String[] args){
		List<Stock> stocks = new ArrayList<Stock>();
		
		for (int i = 0; i < args.length; i++){
			String[] values = args[i].split(",");
			if (values.length < 5){
				System.out.println("Stock " + i + ": Invalid number of inputs!");
				return null;
			}
			String ticker = values[0];
			try{
				double target_alloc = Double.parseDouble(values[1]);
				double actual_alloc = Double.parseDouble(values[2]);
				int shares_owned = Integer.parseInt(values[3]);
				double share_price = Double.parseDouble(values[4]);
				Stock s = new Stock(ticker, target_alloc, actual_alloc, shares_owned, share_price);
				stocks.add(s);
			}
			catch (Exception ex){
				System.out.println("Exception in Stock " + i);
				return null;
			}
		}
		
		return stocks;
	}
}
