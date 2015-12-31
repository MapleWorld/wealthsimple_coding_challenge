package wealthsimple_coding_challenge;

import java.util.*;

public class RebalanceDriver {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			List<Stock> stocks = deserialize(args);
			Portfolio p = new Portfolio(stocks);
			p.rebalance();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static List<Stock> deserialize(String[] args) throws Exception{
		List<Stock> stocks = new ArrayList<Stock>();
		
		for (int i = 0; i < args.length; i++){
			String[] values = args[i].split(",");
			if (values.length < 5) throw new Exception("Stock " + i + ": Invalid number of inputs!");
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
				throw ex;
			}
		}
		
		return stocks;
	}
}
