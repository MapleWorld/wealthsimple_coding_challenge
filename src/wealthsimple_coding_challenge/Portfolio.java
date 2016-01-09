package wealthsimple_coding_challenge;

import java.util.*;

public class Portfolio {
	private List<Stock> stocks;
	private List<String> transactions;
	private double total_value;
	public Rebalancer reb;
	
	public Portfolio(List<Stock> stock_list){
		stocks = stock_list;
		transactions = new ArrayList<String>();
		calculateValue();
		reb = new Rebalancer(this);
	}
	
	private double calculateValue(){
		total_value = 0;
		for (Stock s : stocks){
			total_value += s.getSharePrice() * s.getSharesOwned();
		}
		
		return total_value;
	}
	
	public void rebalance(){
		reb.beginRebalance();
	}
	
	public List<Stock> getStocks(){
		return stocks;
	}
	
	public double getTotalValue(){
		return total_value;
	}
	
	public List<String> viewTransactions(){
		return transactions;
	}
	
	public void addTransaction(String trans){
		transactions.add(trans);
	}
}