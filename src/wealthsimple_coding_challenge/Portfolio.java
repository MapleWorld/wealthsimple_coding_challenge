package wealthsimple_coding_challenge;

import java.util.*;
import net.sf.javailp.*;

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
		int[] target_shares = new int[stocks.size()];
		String[] variables = new String[stocks.size()];
		int count = 0;
		int total_target = 0;
		for (Stock s : stocks){
			target_shares[count] = (int) (s.getTarget() / 100 * total_value / s.getSharePrice());
			total_target += target_shares[count];
			variables[count] = s.ticker;
			count++;
		}
		
		Result result = createLinearSystem(target_shares, variables, total_target);
		displayTransactions(result);
	}
	
	private Result createLinearSystem(int[] target_shares, String[] variables, int total_target){
		SolverFactory factory = new SolverFactoryLpSolve(); // use lp_solve
		factory.setParameter(Solver.VERBOSE, 0); 
		factory.setParameter(Solver.TIMEOUT, 100); // set timeout to 100 seconds
		Problem problem = new Problem();
		Linear linear = new Linear();
		for (int i = 0; i < variables.length; i++){
			linear.add(1, variables[i]);
		}
		problem.setObjective(linear, OptType.MAX);
		
		//Add total shares constraint, based on total objective shares
		linear = new Linear();
		for (int i = 0; i < variables.length; i++){
			linear.add(1, variables[i]);
		}
		problem.add(linear, "<=", total_target);
		
		//Add total portfolio value constraint
		linear = new Linear();
		for (int i = 0; i < variables.length; i++){
			linear.add(stocks.get(i).getSharePrice(), variables[i]);
		}
		problem.add(linear, "=", total_value);
		
		//Add constraints for each individual stock's target share number. Based on whether
		//the current number of shares is higher or lower than the target ,the constraint is
		//set to ">=" or "<=", respectively.
		for (int i = 0; i < variables.length; i++){
			linear = new Linear();
			linear.add(1, variables[i]);
			if (target_shares[i] >= stocks.get(i).getSharesOwned()) 
				problem.add(linear, ">=", target_shares[i]);
			else
				problem.add(linear, "<=", target_shares[i]);
		}
		
		Solver solver = factory.get(); // you should use this solver only once for one problem
		return solver.solve(problem);
	}
	
	private void displayTransactions(Result result){
		
	}
}
