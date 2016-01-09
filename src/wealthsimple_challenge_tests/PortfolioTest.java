package wealthsimple_challenge_tests;

import static org.junit.Assert.*;

import java.util.*;

import wealthsimple_coding_challenge.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PortfolioTest {
	
	//NOTE: Given that the rebalance() method is just a wrapper for the Rebalancer class's
	//method, it will not be tested here.
	
	private List<Stock> stocks;
	private static final double DELTA = 1e-15;
	
	public static Portfolio p;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
		stocks = new ArrayList<Stock>();
		stocks.add(new Stock("GOOG",60,50.96,52,98));
		stocks.add(new Stock("AAPL",30,29.92,136,22));
		stocks.add(new Stock("TSLA",10,19.12,239,8));
		p = new Portfolio(stocks);
	}

	@After
	public void tearDown() throws Exception {
		p = null;
	}
	
	@Test
	public final void testGetTotalValue(){
		assertEquals(10000., p.getTotalValue(), DELTA);
	}
	
	@Test
	public final void testCalculateValue(){
		Portfolio pi = new Portfolio(new ArrayList<Stock>());
		assertEquals(0., pi.getTotalValue(), DELTA);
	}
	
	@Test
	public final void testGetStocks(){
		List<Stock> s = p.getStocks();
		assertEquals(3, s.size(), DELTA);
		for (int i = 0; i < s.size(); i++){
			assertEquals(stocks.get(i).ticker, s.get(i).ticker);
		}
	}
}
