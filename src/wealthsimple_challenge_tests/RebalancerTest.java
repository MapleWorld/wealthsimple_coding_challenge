package wealthsimple_challenge_tests;

import static org.junit.Assert.*;

import java.util.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import wealthsimple_coding_challenge.Portfolio;
import wealthsimple_coding_challenge.Rebalancer;
import wealthsimple_coding_challenge.Stock;

public class RebalancerTest {
	
	private Rebalancer r;
	private Portfolio p;
	private List<Stock> s;
	private List<String> trans;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}
	
	/*
	 * This test function tests the main re-balancing functionality of the program
	 */
	@Test
	public final void testBeginRebalance() {
		/*
		 * Major test cases:
		 * 
		 * 1. Provided test case on Github
		 * 2. Case where portfolio is already balanced
		 * 3. Case where 4 stocks in portfolio, 2 above allocation, 2 below allocation
		 * 4. Case where 4 stocks in portfolio, 1 above allocation, 3 below allocation
		 */
		
		//Test Case 1
		s = null;
		s = new ArrayList<Stock>();
		s.add(new Stock("GOOG",60,50.96,52,98));
		s.add(new Stock("AAPL",30,29.92,136,22));
		s.add(new Stock("TSLA",10,19.12,239,8));
		
		p = new Portfolio(s);
		r = new Rebalancer(p);
		r.beginRebalance();
		trans = p.viewTransactions();
		assertSame(2, trans.size());
		assertEquals("BUY 9 shares of GOOG", trans.get(0));
		assertEquals("SELL 110 shares of TSLA", trans.get(1));
		
		
		//Test Case 2
		s = null;
		s = new ArrayList<Stock>();
		s.add(new Stock("GOOG",50.96,50.96,52,98));
		s.add(new Stock("AAPL",29.92,29.92,136,22));
		s.add(new Stock("TSLA",19.12,19.12,239,8));
		
		p = new Portfolio(s);
		r = new Rebalancer(p);
		r.beginRebalance();
		trans = p.viewTransactions();
		assertEquals(0, trans.size());
		
		//Test Case 3
		s = null;
		s = new ArrayList<Stock>();
		s.add(new Stock("GOOG",30,55.55,50,100));
		s.add(new Stock("AAPL",20,27.78,50,50));
		s.add(new Stock("TSLA",30,13.89,50,25));
		s.add(new Stock("FB",20,2.78,50,5));
		
		p = new Portfolio(s);
		r = new Rebalancer(p);
		r.beginRebalance();
		trans = p.viewTransactions();
		assertEquals(4, p.viewTransactions().size());
		assertEquals("BUY 310 shares of FB", trans.get(0));
		assertEquals("BUY 58 shares of TSLA", trans.get(1));
		assertEquals("SELL 23 shares of GOOG", trans.get(2));
		assertEquals("SELL 14 shares of AAPL", trans.get(3));
		
		//Test Case 4
		s = null;
		s = new ArrayList<Stock>();
		s.add(new Stock("GOOG",30,25.00,50,56));
		s.add(new Stock("AAPL",30,25.00,50,56));
		s.add(new Stock("TSLA",30,25.00,50,56));
		s.add(new Stock("FB",10,25.00,50,56));
		
		p = new Portfolio(s);
		r = new Rebalancer(p);
		r.beginRebalance();
		trans = p.viewTransactions();
		assertEquals("BUY 10 shares of GOOG", trans.get(0));
		assertEquals("BUY 10 shares of AAPL", trans.get(1));
		assertEquals("BUY 10 shares of TSLA", trans.get(2));
		assertEquals("SELL 30 shares of FB", trans.get(3));
	}

}
