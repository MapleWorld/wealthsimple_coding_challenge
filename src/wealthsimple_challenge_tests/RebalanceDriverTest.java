package wealthsimple_challenge_tests;

import static org.junit.Assert.*;

import java.util.*;
import org.junit.Test;

import wealthsimple_coding_challenge.*;

public class RebalanceDriverTest {

	@Test
	public final void testDeserialize() {
		//Valid input test case
		String [] test = {"GOOG,80.0,32.0,55,98"};
		List<Stock> result = RebalanceDriver.deserialize(test);
		assertEquals("GOOG", result.get(0).ticker);
		assertSame(80.0, result.get(0).getTarget());
		assertSame(32.0, result.get(0).getActual());
		assertSame(55, result.get(0).getSharesOwned());
		assertSame(98.0, result.get(0).getSharePrice());
		
		//Invalid inputs - should return null after printing error to output
		test = new String[] {"GOOG,afsd,32.0,55,98"};
		result = RebalanceDriver.deserialize(test);
		assertNull(result);
		
		test = new String[] {"GOOG,80.0,asdf,55,98"};
		result = RebalanceDriver.deserialize(test);
		assertNull(result);
		
		test = new String[] {"GOOG,80.0,32.0,asdf,98"};
		result = RebalanceDriver.deserialize(test);
		assertNull(result);
		
		test = new String[] {"GOOG,80.0,32.0,55,asdf"};
		result = RebalanceDriver.deserialize(test);
		assertNull(result);
		
		//Insufficient inputs - should return null after printing error to output
		test = new String[] {"GOOG,80.0,asdf,55"};
		result = RebalanceDriver.deserialize(test);
		assertNull(result);
	}

}
