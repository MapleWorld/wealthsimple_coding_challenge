package wealthsimple_challenge_tests;

import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import wealthsimple_coding_challenge.Stock;

public class StockTest {

	private static Stock s;
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		s = new Stock("GOOG",60,50.96,52,98);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		s = null;
	}

	@Test
	public final void testGetTarget() {
		assertSame(60, s.getTarget());
	}

	@Test
	public final void testGetActual() {
		assertSame(50.96, s.getActual());
	}

	@Test
	public final void testGetSharesOwned() {
		assertSame(52, s.getActual());
	}

	@Test
	public final void testGetSharePrice() {
		assertSame(98, s.getActual());
	}

}
