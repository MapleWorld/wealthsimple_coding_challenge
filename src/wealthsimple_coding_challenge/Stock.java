package wealthsimple_coding_challenge;

public class Stock {
	public final String ticker;
	public Double error_reduc;
	public int shares_desired;
	private double target_alloc;
	private double actual_alloc;
	private int shares_owned;
	private double share_price;
	
	
	public Stock(String ticker_name, double target, double actual, int shares, double price){
		ticker = ticker_name;
		target_alloc = target;
		actual_alloc = actual;
		shares_owned = shares;
		share_price = price;
	}
	
	public double getTarget(){
		return target_alloc;
	}
	
	public double getActual(){
		return actual_alloc;
	}
	
	public int getSharesOwned(){
		return shares_owned;
	}
	
	public double getSharePrice(){
		return share_price;
	}
}
